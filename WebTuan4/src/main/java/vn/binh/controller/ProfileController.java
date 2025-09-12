package vn.binh.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.binh.entity.User;
import vn.binh.service.UserService;
import vn.binh.service.impl.UserServiceImpl;
import vn.binh.util.Constant;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024, // 1MB
    maxFileSize = 1024 * 1024 * 10, // 10MB
    maxRequestSize = 1024 * 1024 * 50 // 50MB
)
@WebServlet(urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy thông tin người dùng từ session
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");
        
        if (user == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang login
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        // Lấy thông tin mới nhất của user từ database
        User currentUser = userService.getIdUser(user.getId());
        req.setAttribute("user", currentUser);
        
        // Chuyển đến trang profile
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/profile.jsp");
        dispatcher.forward(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        
        // Lấy thông tin người dùng từ session
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("account");
        
        if (user == null) {
            // Nếu chưa đăng nhập, chuyển hướng về trang login
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        
        try {
            // Lấy thông tin mới nhất từ form
            String fullname = req.getParameter("fullname");
            String phone = req.getParameter("phone");
            
            // Lấy thông tin user từ database
            User currentUser = userService.getIdUser(user.getId());
            
            // Cập nhật thông tin mới
            currentUser.setFullname(fullname);
            currentUser.setPhone(phone);
            
            // Xử lý upload file ảnh đại diện
            Part filePart = req.getPart("avatar");
            if (filePart != null && filePart.getSize() > 0) {
                String realPath = req.getServletContext().getRealPath("/uploads");
                String fileName = UUID.randomUUID().toString() + getFileName(filePart);
                
                // Tạo thư mục uploads nếu chưa tồn tại
                File uploadDir = new File(realPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                
                // Lưu file và cập nhật đường dẫn trong database
                filePart.write(realPath + File.separator + fileName);
                currentUser.setImages("uploads/" + fileName);
            }
            
            // Lưu thông tin cập nhật vào database
            userService.edit(currentUser);
            
            // Cập nhật lại thông tin user trong session
            session.setAttribute("account", currentUser);
            
            // Thêm thông báo thành công vào session để hiển thị ở trang home
            session.setAttribute("successMessage", "Cập nhật thông tin thành công!");
            
            // Chuyển hướng về trang home
            resp.sendRedirect(req.getContextPath() + "/home");
            
        } catch (Exception e) {
            // Xử lý lỗi và thông báo
            e.printStackTrace();
            req.setAttribute("errorMessage", "Có lỗi xảy ra: " + e.getMessage());
            doGet(req, resp);
        }
    }
    
    // Helper method để lấy tên file từ Part
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
    }
}