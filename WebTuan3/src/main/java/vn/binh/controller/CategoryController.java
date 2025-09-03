package vn.binh.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.binh.model.Category;
import vn.binh.service.CategoryService;
import vn.binh.service.impl.CategoryServiceImpl;

@WebServlet(urlPatterns = "/admin/category/list")
public class CategoryController extends HttpServlet {
	CategoryService cateService = new CategoryServiceImpl();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Category> cateList = cateService.getAll();
		req.setAttribute("categories", cateList);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-category.jsp");
		dispatcher.forward(req, resp);
	}

}