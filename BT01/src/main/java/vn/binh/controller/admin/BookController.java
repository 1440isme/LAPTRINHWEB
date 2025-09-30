package vn.binh.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.binh.entity.Author;
import vn.binh.entity.Book;
import vn.binh.service.IAuthorService;
import vn.binh.service.IBookService;
import vn.binh.service.impl.AuthorServiceImpl;
import vn.binh.service.impl.BookServiceImpl;
import vn.binh.util.Constant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/books", "/admin/book/add", "/admin/book/edit", "/admin/book/delete", "/admin/book/update" })
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class BookController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IBookService bookService = new BookServiceImpl();
    IAuthorService authorService = new AuthorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");

        if (url.contains("add")) {
            // Load danh sách tác giả cho combobox
            List<Author> authors = authorService.findAll();
            req.setAttribute("authors", authors);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-book.jsp");
            dispatcher.forward(req, resp);
        } else if (url.contains("edit")) {
            String idParam = req.getParameter("bookId");
            int bookId = Integer.parseInt(idParam);
            Book book = bookService.getBook(bookId);

            // Load danh sách tác giả cho combobox
            List<Author> authors = authorService.findAll();
            req.setAttribute("authors", authors);
            req.setAttribute("book", book);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-book.jsp");
            dispatcher.forward(req, resp);
        } else if (url.contains("delete")) {
            String idParam = req.getParameter("bookId");
            int bookId = Integer.parseInt(idParam);
            try {
                bookService.delete(bookId);
                req.setAttribute("message", "Xoá thành công");
            } catch (Exception e) {
                req.setAttribute("error","Xoá thất bại");
            }
            resp.sendRedirect(req.getContextPath() + "/admin/books");
        } else {
            // Xử lý phân trang
            String pageParam = req.getParameter("page");
            int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
            int pageSize = 10; // Số sách mỗi trang

            List<Book> books = bookService.findAll(page, pageSize);
            long totalBooks = bookService.countAll();
            int totalPages = (int) Math.ceil((double) totalBooks / pageSize);

            req.setAttribute("bookList", books);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("totalBooks", totalBooks);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-book.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (url.contains("add")) {
            insertBook(req, resp);
        } else if (url.contains("update")) {
            updateBook(req, resp);
        }
    }

    private void insertBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            Book book = new Book();
            book.setTitle(req.getParameter("title"));

            // Xử lý ISBN
            String isbnParam = req.getParameter("isbn");
            if (isbnParam != null && !isbnParam.trim().isEmpty()) {
                book.setIsbn(Integer.parseInt(isbnParam));
            }

            // Xử lý publisher
            book.setPublisher(req.getParameter("publisher"));

            // Xử lý price
            String priceParam = req.getParameter("price");
            if (priceParam != null && !priceParam.trim().isEmpty()) {
                book.setPrice(Double.parseDouble(priceParam));
            }

            // Xử lý quantity
            String quantityParam = req.getParameter("quantity");
            if (quantityParam != null && !quantityParam.trim().isEmpty()) {
                book.setQuantity(Integer.parseInt(quantityParam));
            } else {
                book.setQuantity(0);
            }

            // Xử lý description
            book.setDescription(req.getParameter("description"));

            // Xử lý publishDate
            String publishDateParam = req.getParameter("publishDate");
            if (publishDateParam != null && !publishDateParam.trim().isEmpty()) {
                book.setPublishDate(LocalDate.parse(publishDateParam));
            }

            // Xử lý chọn tác giả
            String[] authorIds = req.getParameterValues("authorIds");
            if (authorIds != null && authorIds.length > 0) {
                List<Author> selectedAuthors = new ArrayList<>();
                for (String authorIdStr : authorIds) {
                    if (authorIdStr != null && !authorIdStr.trim().isEmpty()) {
                        try {
                            int authorId = Integer.parseInt(authorIdStr);
                            Author author = authorService.getAuthor(authorId);
                            if (author != null) {
                                selectedAuthors.add(author);
                            }
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                }
                book.setAuthors(selectedAuthors);
            }

            // Xử lý upload hình ảnh
            Part filePart = req.getPart("image");
            if (filePart != null && filePart.getSize() > 0) {
                String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                // Kiểm tra định dạng file
                String[] allowedExtensions = {"jpg", "jpeg", "png", "gif"};
                String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
                boolean validExtension = false;
                for (String allowedExt : allowedExtensions) {
                    if (ext.equals(allowedExt)) {
                        validExtension = true;
                        break;
                    }
                }

                if (validExtension) {
                    String newFileName = System.currentTimeMillis() + "." + ext;

                    // Tạo đường dẫn thư mục
                    File uploadDir = new File(Constant.DIR + File.separator + "book");
                    if (!uploadDir.exists()) {
                        uploadDir.mkdirs();
                    }

                    // Tạo file đích
                    File uploadFile = new File(uploadDir, newFileName);

                    // Ghi file
                    try {
                        filePart.write(uploadFile.getAbsolutePath());
                        book.setCoverImage("book/" + newFileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                        book.setCoverImage("default.jpg");
                    }
                } else {
                    book.setCoverImage("default.jpg");
                }
            } else {
                book.setCoverImage("default.jpg");
            }

            bookService.insert(book);
            req.setAttribute("message", "Thêm sách thành công!");
            resp.sendRedirect(req.getContextPath() + "/admin/books");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Thêm sách thất bại!");
            // Load lại danh sách tác giả khi có lỗi
            List<Author> authors = authorService.findAll();
            req.setAttribute("authors", authors);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-book.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("bookId");
            int bookId = Integer.parseInt(idParam);
            Book book = bookService.getBook(bookId);

            if (book != null) {
                book.setTitle(req.getParameter("title"));

                // Xử lý ISBN
                String isbnParam = req.getParameter("isbn");
                if (isbnParam != null && !isbnParam.trim().isEmpty()) {
                    book.setIsbn(Integer.parseInt(isbnParam));
                }

                // Xử lý publisher
                book.setPublisher(req.getParameter("publisher"));

                // Xử lý price
                String priceParam = req.getParameter("price");
                if (priceParam != null && !priceParam.trim().isEmpty()) {
                    book.setPrice(Double.parseDouble(priceParam));
                }

                // Xử lý quantity
                String quantityParam = req.getParameter("quantity");
                if (quantityParam != null && !quantityParam.trim().isEmpty()) {
                    book.setQuantity(Integer.parseInt(quantityParam));
                }

                // Xử lý description
                book.setDescription(req.getParameter("description"));

                // Xử lý publishDate
                String publishDateParam = req.getParameter("publishDate");
                if (publishDateParam != null && !publishDateParam.trim().isEmpty()) {
                    book.setPublishDate(LocalDate.parse(publishDateParam));
                }

                // Xử lý cập nhật tác giả
                String[] authorIds = req.getParameterValues("authorIds");
                List<Author> selectedAuthors = new ArrayList<>();
                if (authorIds != null && authorIds.length > 0) {
                    for (String authorIdStr : authorIds) {
                        if (authorIdStr != null && !authorIdStr.trim().isEmpty()) {
                            try {
                                int authorId = Integer.parseInt(authorIdStr);
                                Author author = authorService.getAuthor(authorId);
                                if (author != null) {
                                    selectedAuthors.add(author);
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
                book.setAuthors(selectedAuthors);

                // Xử lý upload hình ảnh mới (nếu có)
                Part filePart = req.getPart("image");
                if (filePart != null && filePart.getSize() > 0) {
                    String originalFileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                    // Kiểm tra định dạng file
                    String[] allowedExtensions = {"jpg", "jpeg", "png", "gif"};
                    String ext = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toLowerCase();
                    boolean validExtension = false;
                    for (String allowedExt : allowedExtensions) {
                        if (ext.equals(allowedExt)) {
                            validExtension = true;
                            break;
                        }
                    }

                    if (validExtension) {
                        String newFileName = System.currentTimeMillis() + "." + ext;

                        // Tạo đường dẫn thư mục
                        File uploadDir = new File(Constant.DIR + File.separator + "book");
                        if (!uploadDir.exists()) {
                            uploadDir.mkdirs();
                        }

                        // Tạo file đích
                        File uploadFile = new File(uploadDir, newFileName);

                        // Ghi file
                        try {
                            filePart.write(uploadFile.getAbsolutePath());
                            book.setCoverImage("book/" + newFileName);
                        } catch (IOException e) {
                            e.printStackTrace();
                            // Giữ nguyên hình ảnh cũ nếu upload thất bại
                        }
                    }
                    // Nếu định dạng không hợp lệ, giữ nguyên hình ảnh cũ
                }

                bookService.edit(book);
                req.setAttribute("message", "Cập nhật sách thành công");
            }
            resp.sendRedirect(req.getContextPath() + "/admin/books");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Cập nhật sách thất bại!");
            String idParam = req.getParameter("bookId");
            int id = Integer.parseInt(idParam);
            Book book = bookService.getBook(id);
            List<Author> authors = authorService.findAll();
            req.setAttribute("authors", authors);
            req.setAttribute("book", book);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-book.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
