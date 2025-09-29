package vn.binh.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import vn.binh.entity.Book;
import vn.binh.entity.Rating;
import vn.binh.entity.User;
import vn.binh.service.IBookService;
import vn.binh.service.IRatingService;

@Controller
public class BookDetail {
    private final IBookService bookService;
    private final IRatingService ratingService;

    public BookDetail(IBookService bookService, IRatingService ratingService) {
        this.bookService = bookService;
        this.ratingService = ratingService;
    }

    @GetMapping("/book")
    public String getBook(@RequestParam("bookId") Integer bookId, Model model) {
        Book book = bookService.getBook(bookId);
        if (book == null) {
            return "redirect:/home";
        }
        List<Rating> reviews = ratingService.getRating(bookId);
        model.addAttribute("book", book);
        model.addAttribute("reviews", reviews);
        return "book-detail";
    }

    @PostMapping("/book")
    public String submitRating(@RequestParam("bookId") Integer bookId,
            @RequestParam("rating") Integer rating,
            @RequestParam(value = "review", required = false) String reviewText,
            HttpSession session) {
        User currentUser = (User) session.getAttribute("account");
        if (currentUser == null) {
            return "redirect:/login";
        }

        Book book = bookService.getBook(bookId);
        if (book == null) {
            return "redirect:/home";
        }

        Rating review = new Rating();
        review.setUser(currentUser);
        review.setBook(book);
        review.setRating(rating);
        review.setReviewText(reviewText);
        ratingService.insert(review);
        return "redirect:/book?bookId=" + bookId;
    }
}
