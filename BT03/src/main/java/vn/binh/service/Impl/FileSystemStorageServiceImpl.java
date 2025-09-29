package vn.binh.service.Impl;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.binh.Config.StorageProperties;
import vn.binh.service.IStorageService;

import java.io.InputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Properties;
import jakarta.servlet.http.Part;

@Service
public class FileSystemStorageServiceImpl implements IStorageService {
    private final Path rootLocation;

    @Override
    public String getStorageFilename(MultipartFile file, String id) {
        String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
        return "p" + id + "." + ext;
    }

    @Override
    public String getStorageLocation() {
        return rootLocation.toString();
    }

    public FileSystemStorageServiceImpl(StorageProperties properties) {
        String resolvedLocation = resolveLocation(properties);
        this.rootLocation = Paths.get(resolvedLocation);
    }

    private String resolveLocation(StorageProperties properties) {
        if (properties != null && properties.getLocation() != null && !properties.getLocation().isBlank()) {
            return properties.getLocation();
        }
        Properties props = new Properties();
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (is != null) {
                props.load(is);
            }
        } catch (IOException ignored) {
        }
        String fallback = props.getProperty("storage.location", "D:\\LTWEB\\upload");
        return fallback;
    }

    @Override
    public void store(MultipartFile file, String storeFilename) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(storeFilename))
                    .normalize().toAbsolutePath(); // lấy đường dẫn tuyệt đối
            if (!destinationFile.startsWith(this.rootLocation.toAbsolutePath())) {
                throw new RuntimeException("Cannot store file outside current directory");
            }
            // Tạo thư mục cha nếu chưa tồn tại (hỗ trợ lưu vào thư mục con như "book/...")
            Files.createDirectories(destinationFile.getParent());
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFile,
                        StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    // Loại bỏ hỗ trợ Part để thống nhất theo Spring Boot

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            throw new RuntimeException("Cannot read file: " + filename);
        } catch (Exception e) {
            throw new RuntimeException("Could not read file: " + filename, e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public void delete(String storeFilename) throws Exception {
        Path destinationFile = rootLocation.resolve(Paths.get(storeFilename)).normalize().toAbsolutePath();
        Files.delete(destinationFile);
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            System.out.println(rootLocation.toString());
        } catch (Exception e) {
            throw new RuntimeException("Could not initialize storage", e);
        }
    }

    @Override
    public void store(Part part, String storeFilename) {
        try {
            if (part == null || part.getSize() == 0) {
                throw new RuntimeException("Failed to store empty file");
            }
            Path destinationFile = this.rootLocation.resolve(Paths.get(storeFilename))
                    .normalize().toAbsolutePath();
            if (!destinationFile.startsWith(this.rootLocation.toAbsolutePath())) {
                throw new RuntimeException("Cannot store file outside current directory");
            }
            Files.createDirectories(destinationFile.getParent());
            try (InputStream inputStream = part.getInputStream()) {
                Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }
}
