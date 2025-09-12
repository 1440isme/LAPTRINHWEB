package vn.binh.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.binh.entity.User;
import vn.binh.service.UserService;
import vn.binh.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/login", "/register", "/logout", "/forgot-password" })
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("/login")) {
			showLoginPage(req, resp);
		} else if (url.contains("/register")) {
			showRegisterPage(req, resp);
		} else if (url.contains("/logout")) {
			processLogout(req, resp);
		} else if (url.contains("/forgot-password")) {
			showForgotPasswordPage(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String url = req.getRequestURI();

		if (url.contains("/login")) {
			processLogin(req, resp);
		} else if (url.contains("/register")) {
			processRegister(req, resp);
		} else if (url.contains("/forgot-password")) {
			processForgotPassword(req, resp);
		}
	}

	// LOGIN FUNCTIONS
	private void showLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Check for reset password success message
		HttpSession session = req.getSession();
		String resetSuccess = (String) session.getAttribute("resetSuccess");
		if (resetSuccess != null) {
			req.setAttribute("success", resetSuccess);
			session.removeAttribute("resetSuccess");
		}

		RequestDispatcher rd = req.getRequestDispatcher("/views/login/login.jsp");
		rd.forward(req, resp);
	}

	private void processLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String alertMsg;

		if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
			alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login/login.jsp").forward(req, resp);
			return;
		}

		User user = userService.login(username, password);
		if (user != null) {
			HttpSession session = req.getSession(true);
			session.setAttribute("account", user);

			resp.sendRedirect(req.getContextPath() + "/home");
//			// Check user role and redirect accordingly
//			if (user.getRole() == 1) {
//				// Admin role - redirect to admin home
//				resp.sendRedirect(req.getContextPath() + "/admin/home");
//			} else {
//				// User role - redirect to user home
//				resp.sendRedirect(req.getContextPath() + "/home");
//			}
		} else {
			alertMsg = "Tài khoản hoặc mật khẩu không đúng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login/login.jsp").forward(req, resp);
		}
	}

	// REGISTER FUNCTIONS
	private void showRegisterPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/views/login/register.jsp");
		rd.forward(req, resp);
	}

	private void processRegister(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String confirmPassword = req.getParameter("confirmPassword");
		String fullname = req.getParameter("fullname");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");

		String alertMsg = "";

		// Validate input
		if (username == null || password == null || fullname == null || email == null || phone == null
				|| username.isEmpty() || password.isEmpty() || fullname.isEmpty() || email.isEmpty()
				|| phone.isEmpty()) {
			alertMsg = "Vui lòng điền đầy đủ thông tin";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login/register.jsp").forward(req, resp);
			return;
		}

		// Check password confirmation
		if (!password.equals(confirmPassword)) {
			alertMsg = "Mật khẩu xác nhận không khớp";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login/register.jsp").forward(req, resp);
			return;
		}

		// Check if username exists
		if (userService.checkUsernameExist(username)) {
			alertMsg = "Tên đăng nhập đã tồn tại";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login/register.jsp").forward(req, resp);
			return;
		}

		// Check if email exists
		if (userService.checkEmailExist(email)) {
			alertMsg = "Email đã được sử dụng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login/register.jsp").forward(req, resp);
			return;
		}

		// Register user with phone
		boolean registerSuccess = userService.register(email, password, username, fullname, phone);

		if (registerSuccess) {
			req.setAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
			req.getRequestDispatcher("/views/login/login.jsp").forward(req, resp);
		} else {
			alertMsg = "Đăng ký thất bại. Vui lòng thử lại.";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login/register.jsp").forward(req, resp);
		}
	}

	// LOGOUT FUNCTION
	private void processLogout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.removeAttribute("account");
			session.invalidate();
		}
		resp.sendRedirect(req.getContextPath() + "/login");
	}

	// FORGOT PASSWORD FUNCTIONS
	private void showForgotPasswordPage(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = req.getRequestDispatcher("/views/login/forgot-password.jsp");
		rd.forward(req, resp);
	}

	private void processForgotPassword(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email = req.getParameter("email");
		String newPassword = req.getParameter("newPassword");
		String confirmPassword = req.getParameter("confirmPassword");

		String alertMsg = "";

		// Validate input
		if (email == null || newPassword == null || confirmPassword == null || email.isEmpty() || newPassword.isEmpty()
				|| confirmPassword.isEmpty()) {
			alertMsg = "Vui lòng điền đầy đủ thông tin";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login/forgot-password.jsp").forward(req, resp);
			return;
		}

		// Check password confirmation
		if (!newPassword.equals(confirmPassword)) {
			alertMsg = "Mật khẩu xác nhận không khớp";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login/forgot-password.jsp").forward(req, resp);
			return;
		}

		// Check if email exists - SỬA LẠI ĐÂY
		User user = userService.findByEmail(email); // Tìm theo email thay vì username
		if (user == null) {
			alertMsg = "Email không tồn tại trong hệ thống";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/login/forgot-password.jsp").forward(req, resp);
			return;
		}

		// Update password
		user.setPassword(newPassword);
		userService.edit(user);

		// Set success message and redirect to login
		HttpSession session = req.getSession();
		session.setAttribute("resetSuccess", "Đặt lại mật khẩu thành công! Vui lòng đăng nhập.");
		resp.sendRedirect(req.getContextPath() + "/login");
	}
}