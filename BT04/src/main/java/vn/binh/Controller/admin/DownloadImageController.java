package vn.binh.Controller.admin;

import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;
import vn.binh.service.IStorageService;

@Controller
public class DownloadImageController {
    private final IStorageService storageService;

    public DownloadImageController(IStorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/image")
    @ResponseBody
    public void getImage(@RequestParam("fname") String fileName, HttpServletResponse resp) {
        try {
            if (fileName == null || fileName.trim().isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing file name");
                return;
            }

            fileName = fileName.replace("\\", "/");

            Resource resource = storageService.loadAsResource(fileName);
            if (resource == null || !resource.exists()) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found: " + fileName);
                return;
            }

            String contentType = getContentType(fileName);
            resp.setContentType(contentType);
            resp.setHeader("Cache-Control", "max-age=3600");
            resp.setDateHeader("Expires", System.currentTimeMillis() + 3600000);

            try (InputStream is = resource.getInputStream(); OutputStream os = resp.getOutputStream()) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                os.flush();
            }
        } catch (Exception e) {
            try {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error reading file");
            } catch (Exception ignored) {
            }
        }
    }

    private String getContentType(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        switch (extension) {
            case "jpg":
            case "jpeg":
                return "image/jpeg";
            case "png":
                return "image/png";
            case "gif":
                return "image/gif";
            case "bmp":
                return "image/bmp";
            case "webp":
                return "image/webp";
            default:
                return "application/octet-stream";
        }
    }
}
