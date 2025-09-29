package vn.binh.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import jakarta.servlet.http.Part;

import java.nio.file.Path;

public interface IStorageService {
    void init();

    void delete(String storeFilename) throws Exception;

    Path load(String filename);

    Resource loadAsResource(String filename);

    void store(MultipartFile file, String storeFilename);

    void store(Part part, String storeFilename);

    String getStorageFilename(MultipartFile file, String id);

    String getStorageLocation();
}
