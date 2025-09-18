package vn.binh.springbootsproject.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface IStorageService {
    void init();

    void delete(String storedFileName) throws Exception;

    Path load(String filename);

    Resource loadAsResource(String filename);

    void store(MultipartFile file, String filename);

    String getStoredFileName(MultipartFile file, String id);

}
