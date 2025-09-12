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

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Check if user is logged in
		HttpSession session = req.getSession(false);

		if (session == null || session.getAttribute("account") == null) {
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		User user = (User) session.getAttribute("account");

//		// Check if user has user role (role = 2)
//		if (user.getRole() == 1) {
//			// Admin user, redirect to admin home
//			resp.sendRedirect(req.getContextPath() + "/admin/home");
//			return;
//		}

		req.setAttribute("user", user);
		RequestDispatcher rd = req.getRequestDispatcher("/views/home.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}