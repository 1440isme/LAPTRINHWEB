package vn.binh.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.binh.entity.Book;
import vn.binh.entity.Rating;
import vn.binh.entity.User;
import vn.binh.service.IBookService;
import vn.binh.service.IRatingService;
import vn.binh.service.UserService;
import vn.binh.service.impl.BookServiceImpl;
import vn.binh.service.impl.RatingServiceImpl;
import vn.binh.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/book")
public class BookDetail extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IBookService bookService = new BookServiceImpl();
    private IRatingService ratingService = new RatingServiceImpl();
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String bookIdParam = req.getParameter("bookId");
            if (bookIdParam == null || bookIdParam.trim().isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing bookId parameter");
                return;
            }

            Integer bookId = Integer.valueOf(bookIdParam);
            System.out.println("Loading book details for bookId: " + bookId);

            Book book = bookService.getBook(bookId);
            if (book == null) {
                System.out.println("Book not found for bookId: " + bookId);
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
                return;
            }

            List<Rating> reviews = ratingService.getRating(bookId);
            System.out.println("Found " + (reviews != null ? reviews.size() : 0) + " reviews for bookId: " + bookId);

            req.setAttribute("book", book);
            req.setAttribute("reviews", reviews);

            req.getRequestDispatcher("/views/book-detail.jsp").forward(req, resp);
        } catch (NumberFormatException e) {
            System.err.println("Invalid bookId format: " + req.getParameter("bookId"));
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid bookId format");
        } catch (Exception e) {
            System.err.println("Error in BookDetail.doGet: " + e.getMessage());
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading book details: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String bookIdParam = req.getParameter("bookId");
            String ratingParam = req.getParameter("rating");
            String reviewText = req.getParameter("review");

            System.out.println("Rating submission - bookId: " + bookIdParam + ", rating: " + ratingParam);

            if (bookIdParam == null || ratingParam == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters");
                return;
            }

            Integer bookId = Integer.valueOf(bookIdParam);
            Integer rating = Integer.valueOf(ratingParam);

            // Get current user from session
            HttpSession session = req.getSession();
            User currentUser = (User) session.getAttribute("account");

            if (currentUser == null) {
                System.out.println("User not logged in, redirecting to login");
                resp.sendRedirect(req.getContextPath() + "/login");
                return;
            }

            System.out.println("User submitting review: " + currentUser.getId());

            // Get the book using bookId
            Book book = bookService.getBook(bookId);
            if (book == null) {
                System.err.println("Book not found for bookId: " + bookId);
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
                return;
            }

            System.out.println("Creating rating for book: " + book.getTitle());

            // Create the Rating entity properly
            Rating review = new Rating();

            // No need for composite key anymore - just set the relationships directly
            review.setUser(currentUser);
            review.setBook(book);
            review.setRating(rating);
            review.setReviewText(reviewText);

            System.out.println("Inserting rating into database...");
            ratingService.insert(review);
            System.out.println("Rating inserted successfully");

            resp.sendRedirect(req.getContextPath() + "/book?bookId=" + bookId);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format in rating submission: " + e.getMessage());
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid number format");
        } catch (Exception e) {
            System.err.println("Error in BookDetail.doPost: " + e.getMessage());
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error adding review: " + e.getMessage());
        }
    }
}
