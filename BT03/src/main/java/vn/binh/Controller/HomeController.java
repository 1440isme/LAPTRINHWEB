package vn.binh.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vn.binh.entity.Book;
import vn.binh.entity.User;
import vn.binh.service.IBookService;
import vn.binh.service.IRatingService;

@Controller
@RequestMapping
public class HomeController {

	private final IBookService bookService;
	private final IRatingService ratingService;

	public HomeController(IBookService bookService, IRatingService ratingService) {
		this.bookService = bookService;
		this.ratingService = ratingService;
	}

	@GetMapping("/home")
	public String home(Model model, HttpSession session) {
		User user = (User) session.getAttribute("account");

		List<Book> books = bookService.findAll();
		for (Book book : books) {
			int ratingCount = ratingService.countRatings(book.getBookId());
			book.setRatingCount(ratingCount);
		}

		model.addAttribute("bookList", books);
		model.addAttribute("user", user);
		return "home";
	}
}