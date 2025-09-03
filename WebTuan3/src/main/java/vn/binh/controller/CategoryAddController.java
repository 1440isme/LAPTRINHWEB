package vn.binh.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.binh.model.Category;
import vn.binh.service.CategoryService;
import vn.binh.service.impl.CategoryServiceImpl;
import vn.binh.util.Constant;

@WebServlet(urlPatterns = "/admin/category/add")
@MultipartConfig
public class CategoryAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-category.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		Category category = new Category();
		category.setCatename(req.getParameter("name"));

		// Xử lý upload file icon
		Part filePart = req.getPart("icon"); // name="icon" trong form
		if (filePart != null && filePart.getSize() > 0) {
			String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
			String newFileName = System.currentTimeMillis() + "." + ext;

			File uploadFile = new File(Constant.DIR + "/category/" + newFileName);
			uploadFile.getParentFile().mkdirs();
			filePart.write(uploadFile.getAbsolutePath());

			category.setIcon("category/" + newFileName);
		}

		cateService.insert(category);
		resp.sendRedirect(req.getContextPath() + "/admin/category/list");
	}
}
