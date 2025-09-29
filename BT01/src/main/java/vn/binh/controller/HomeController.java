package vn.binh.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.binh.entity.Book;
import vn.binh.entity.User;
import vn.binh.service.IBookService;
import vn.binh.service.IRatingService;
import vn.binh.service.impl.BookServiceImpl;
import vn.binh.service.impl.RatingServiceImpl;

@WebServlet(urlPatterns = { "/home" })
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
    private IBookService bookService = new BookServiceImpl();
    private IRatingService ratingService = new RatingServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Check if user is logged in
		HttpSession session = req.getSession(false);

		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("account");
		}

		List<Book> books = bookService.findAll();

		// Thêm số lượng đánh giá cho từng sách
		for (Book book : books) {
			int ratingCount = ratingService.countRatings(book.getBookId());
			book.setRatingCount(ratingCount); // Sẽ cần thêm field này vào Book entity
		}

		req.setAttribute("bookList", books);
		req.setAttribute("user", user);
		RequestDispatcher rd = req.getRequestDispatcher("/views/home.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}