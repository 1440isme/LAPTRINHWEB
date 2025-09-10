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
import vn.binh.entity.Category;
import vn.binh.service.CategoryService;
import vn.binh.service.impl.CategoryServiceImpl;
import vn.binh.util.Constant;

@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/edit",
		"/admin/category/delete", "/admin/category/insert", "/admin/category/update" })
@MultipartConfig
public class CategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");

		if (url.contains("add")) {
			// Hiển thị form thêm category
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-category.jsp");
			dispatcher.forward(req, resp);
		} else if (url.contains("edit")) {
			// Hiển thị form sửa category
			String idParam = req.getParameter("id");
			int id = Integer.parseInt(idParam);
			Category category = cateService.getIdCategory(id);
			req.setAttribute("category", category);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-category.jsp");
			dispatcher.forward(req, resp);
		} else if (url.contains("delete")) {
			// Xóa category
			String idParam = req.getParameter("id");
			int id = Integer.parseInt(idParam);
			try {
				cateService.delete(id);
				req.setAttribute("message", "Xóa thành công!");
			} catch (Exception e) {
				req.setAttribute("error", "Xóa thất bại!");
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else {
			// Hiển thị danh sách categories
			List<Category> cateList = cateService.getAll();
			req.setAttribute("categories", cateList);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-category.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		if (url.contains("insert")) {
			// Thêm mới category
			insertCategory(req, resp);
		} else if (url.contains("update")) {
			// Cập nhật category
			updateCategory(req, resp);
		}
	}

	private void insertCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Category category = new Category();
			category.setName(req.getParameter("name"));

			// Xử lý upload file image
			Part filePart = req.getPart("image");
			if (filePart != null && filePart.getSize() > 0) {
				String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
				String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
				String newFileName = System.currentTimeMillis() + "." + ext;

				File uploadFile = new File(Constant.DIR + "/category/" + newFileName);
				uploadFile.getParentFile().mkdirs();
				filePart.write(uploadFile.getAbsolutePath());

				category.setImages("category/" + newFileName);
			} else {
				category.setImages("default.jpg");
			}

			cateService.insert(category);
			req.setAttribute("message", "Thêm thành công!");
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Thêm thất bại!");
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-category.jsp");
			dispatcher.forward(req, resp);
		}
	}

	private void updateCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String idParam = req.getParameter("id");
			int id = Integer.parseInt(idParam);
			Category category = cateService.getIdCategory(id);

			if (category != null) {
				category.setName(req.getParameter("name"));

				// Xử lý upload file image
				Part filePart = req.getPart("image");
				if (filePart != null && filePart.getSize() > 0) {
					String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
					String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
					String newFileName = System.currentTimeMillis() + "." + ext;

					File uploadFile = new File(Constant.DIR + "/category/" + newFileName);
					uploadFile.getParentFile().mkdirs();
					filePart.write(uploadFile.getAbsolutePath());

					category.setImages("category/" + newFileName);
				}
				// Nếu không upload file mới thì giữ nguyên image cũ

				cateService.edit(category);
				req.setAttribute("message", "Cập nhật thành công!");
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Cập nhật thất bại!");
			String idParam = req.getParameter("id");
			int id = Integer.parseInt(idParam);
			Category category = cateService.getIdCategory(id);
			req.setAttribute("category", category);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-category.jsp");
			dispatcher.forward(req, resp);
		}
	}
}