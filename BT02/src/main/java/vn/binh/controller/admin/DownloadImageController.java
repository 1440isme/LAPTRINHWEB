package vn.binh.controller.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.binh.util.Constant;

@WebServlet(urlPatterns = "/image")
public class DownloadImageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("fname");

        if (fileName == null || fileName.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing file name");
            return;
        }

        // Xử lý đường dẫn file
        File file = new File(Constant.DIR, fileName);

        // Kiểm tra file có tồn tại không
        if (!file.exists() || !file.isFile()) {
            // Thử tìm file với path khác nhau (với và không có File.separator)
            if (fileName.contains("book" + File.separator)) {
                // Đã có separator đúng
            } else if (fileName.contains("book/") || fileName.contains("book\\")) {
                // Thay thế separator
                fileName = fileName.replace("/", File.separator).replace("\\", File.separator);
                file = new File(Constant.DIR, fileName);
            } else if (fileName.startsWith("book")) {
                // Thêm separator nếu thiếu
                file = new File(Constant.DIR, fileName);
            }

            // Nếu vẫn không tìm thấy, return 404
            if (!file.exists() || !file.isFile()) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found: " + fileName);
                return;
            }
        }

        // Xác định content type
        String contentType = getContentType(fileName);
        resp.setContentType(contentType);

        // Set headers để cache hình ảnh
        resp.setHeader("Cache-Control", "max-age=3600"); // Cache 1 giờ
        resp.setDateHeader("Expires", System.currentTimeMillis() + 3600000); // 1 giờ

        // Đọc và gửi file
        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = resp.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error reading file");
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
