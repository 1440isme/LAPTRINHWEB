package vn.binh.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.binh.dao.UserDao;
import vn.binh.dao.impl.UserDaoImpl;

@WebServlet("/reset-password")
public class ResetPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("resetEmail");

		if (email == null) {
			response.sendRedirect(request.getContextPath() + "/forgot-password");
			return;
		}

		request.setAttribute("email", email);
		request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");

		if (!newPassword.equals(confirmPassword)) {
			request.setAttribute("alert", "Mật khẩu xác nhận không khớp!");
			request.setAttribute("email", email);
			request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
			return;
		}

		if (newPassword.length() < 6) {
			request.setAttribute("alert", "Mật khẩu phải có ít nhất 6 ký tự!");
			request.setAttribute("email", email);
			request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
			return;
		}

		// Update password in database
		boolean success = updatePasswordInDatabase(email, newPassword);

		if (success) {
			HttpSession session = request.getSession();
			session.removeAttribute("resetEmail");
			session.setAttribute("resetSuccess", "Đặt lại mật khẩu thành công! Vui lòng đăng nhập với mật khẩu mới.");
			response.sendRedirect(request.getContextPath() + "/login");
		} else {
			request.setAttribute("alert", "Có lỗi xảy ra, vui lòng thử lại!");
			request.setAttribute("email", email);
			request.getRequestDispatcher("/views/reset-password.jsp").forward(request, response);
		}
	}

	private boolean updatePasswordInDatabase(String email, String newPassword) {
		UserDao userDao = new UserDaoImpl();
		return userDao.updatePasswordByEmail(email, newPassword);
	}
}