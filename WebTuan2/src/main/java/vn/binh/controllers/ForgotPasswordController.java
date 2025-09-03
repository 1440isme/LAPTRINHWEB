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

@WebServlet("/forgot-password")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email");

		UserDao userDao = new UserDaoImpl();
		boolean emailExists = userDao.checkEmailExist(email);

		if (emailExists) {
			HttpSession session = request.getSession();
			session.setAttribute("resetEmail", email);
			response.sendRedirect(request.getContextPath() + "/reset-password");
		} else {
			request.setAttribute("alert", "Email không tồn tại trong hệ thống!");
			request.getRequestDispatcher("/views/forgot-password.jsp").forward(request, response);
		}
	}
}