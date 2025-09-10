package vn.binh.controller.admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.binh.entity.User;

@WebServlet(urlPatterns = { "/admin/home" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("DEBUG: AdminHomeController doGet() called");
		
		// Check if user is logged in
		HttpSession session = req.getSession(false);
		
		if (session == null || session.getAttribute("account") == null) {
			System.out.println("DEBUG: No session or account, redirecting to login");
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}
		
		User user = (User) session.getAttribute("account");
		System.out.println("DEBUG: User found - " + user.getName() + ", Role: " + user.getRole());
		
		// Check if user has admin role (role = 1)
		if (user.getRole() != 1) {
			System.out.println("DEBUG: User is not admin, redirecting to home");
			// Not admin, redirect to user home
			resp.sendRedirect(req.getContextPath() + "/home");
			return;
		}
		
		System.out.println("DEBUG: Admin user confirmed, forwarding to JSP");
		// Forward to admin home page
		RequestDispatcher rd = req.getRequestDispatcher("/views/admin/home.jsp");
		rd.forward(req, resp);
		System.out.println("DEBUG: Forward completed");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("DEBUG: AdminHomeController doPost() called");
		doGet(req, resp);
	}
}