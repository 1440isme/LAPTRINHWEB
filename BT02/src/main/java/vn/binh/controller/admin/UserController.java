package vn.binh.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.binh.entity.User;
import vn.binh.service.UserService;
import vn.binh.service.impl.UserServiceImpl;
import vn.binh.util.Constant;

@WebServlet(urlPatterns = { "/admin/users", "/admin/user/add", "/admin/user/edit", "/admin/user/delete",
		"/admin/user/insert", "/admin/user/update", "/admin/user/search" })
@MultipartConfig
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");

		if (url.contains("add")) {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-user.jsp");
			dispatcher.forward(req, resp);
		} else if (url.contains("edit")) {
			String idParam = req.getParameter("id");
			int id = Integer.parseInt(idParam);
			User user = userService.getIdUser(id);
			req.setAttribute("user", user);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-user.jsp");
			dispatcher.forward(req, resp);
		} else if (url.contains("search")) {
			// Hiển thị trang tìm kiếm
			String keyword = req.getParameter("keyword");
			if (keyword != null && !keyword.trim().isEmpty()) {
				List<User> searchResults = userService.search(keyword);
				req.setAttribute("users", searchResults);
				req.setAttribute("keyword", keyword);
			}
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/search-user.jsp");
			dispatcher.forward(req, resp);
		} else if (url.contains("delete")) {
			String idParam = req.getParameter("id");
			int id = Integer.parseInt(idParam);
			try {
				userService.delete(id);
				req.setAttribute("message", "Xóa thành công!");
			} catch (Exception e) {
				req.setAttribute("error", "Xóa thất bại!");
			}
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		} else {
			List<User> userList = userService.getAll();
			req.setAttribute("users", userList);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-user.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("insert")) {
			insertUser(req, resp);
		} else if (url.contains("update")) {
			updateUser(req, resp);
		}
	}

	private void insertUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			User user = new User();
			user.setEmail(req.getParameter("email"));
			user.setPassword(req.getParameter("password"));
			user.setFullname(req.getParameter("fullname"));
            user.setPhone(Integer.valueOf(req.getParameter("phone")));
//
//			Part filePart = req.getPart("image");
//			if (filePart != null && filePart.getSize() > 0) {
//				String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//				String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
//				String newFileName = System.currentTimeMillis() + "." + ext;
//				File uploadFile = new File(Constant.DIR + "/user/" + newFileName);
//				uploadFile.getParentFile().mkdirs();
//				filePart.write(uploadFile.getAbsolutePath());
//				user.setImages("user/" + newFileName);
//			} else {
//				user.setImages("default.jpg");
//			}
			userService.insert(user);
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		} catch (Exception e) {
			req.setAttribute("error", "Thêm mới thất bại!");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-user.jsp");
			dispatcher.forward(req, resp);
		}
	}

	private void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			User user = userService.getIdUser(id);
			user.setEmail(req.getParameter("email"));
			user.setPassword(req.getParameter("password"));
			user.setFullname(req.getParameter("fullname"));
			user.setPhone(Integer.valueOf(req.getParameter("phone")));
//
//			Part filePart = req.getPart("image");
//			if (filePart != null && filePart.getSize() > 0) {
//				String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
//				String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
//				String newFileName = System.currentTimeMillis() + "." + ext;
//				File uploadFile = new File(Constant.DIR + "/user/" + newFileName);
//				uploadFile.getParentFile().mkdirs();
//				filePart.write(uploadFile.getAbsolutePath());
//				user.setImages("user/" + newFileName);
//			}
			userService.edit(user);
			resp.sendRedirect(req.getContextPath() + "/admin/users");
		} catch (Exception e) {
			req.setAttribute("error", "Cập nhật thất bại!");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-user.jsp");
			dispatcher.forward(req, resp);
		}
	}
}