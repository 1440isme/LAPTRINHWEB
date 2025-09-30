package vn.binh.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.binh.entity.Author;
import vn.binh.entity.Book;
import vn.binh.service.IAuthorService;
import vn.binh.service.IBookService;
import vn.binh.service.IStorageService;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/admin")
public class BookController {
    private final IBookService bookService;
    private final IAuthorService authorService;
    private final IStorageService storageService;

    public BookController(IBookService bookService, IAuthorService authorService, IStorageService storageService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.storageService = storageService;
    }

    @GetMapping("/book/add")
    public String addForm(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "admin/add-book";
    }

    @GetMapping("/book/edit")
    public String editForm(@RequestParam("bookId") int bookId, Model model) {
        Book book = bookService.getBook(bookId);
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        model.addAttribute("book", book);
        return "admin/edit-book";
    }

    @PostMapping("/book/delete")
    public String delete(@RequestParam("bookId") int bookId, RedirectAttributes ra) {
        try {
            bookService.delete(bookId);
            ra.addFlashAttribute("message", "Xoá thành công");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Xoá thất bại");
        }
        return "redirect:/admin/books";
    }

    @GetMapping("/books")
    public String list(@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), pageSize);
        Page<Book> pageBooks = bookService.findAll(pageable);
        model.addAttribute("bookList", pageBooks.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageBooks.getTotalPages());
        model.addAttribute("totalBooks", pageBooks.getTotalElements());
        return "admin/list-book";
    }

    @PostMapping("/book/add")
    public String insertBook(@RequestParam("title") String title,
            @RequestParam(value = "isbn", required = false) String isbnParam,
            @RequestParam("publisher") String publisher,
            @RequestParam(value = "price", required = false) String priceParam,
            @RequestParam(value = "quantity", required = false) String quantityParam,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "publishDate", required = false) String publishDateParam,
            @RequestParam(value = "authorIds", required = false) List<String> authorIds,
            @RequestParam(value = "image", required = false) MultipartFile file,
            RedirectAttributes ra, Model model) {
        try {
            Book book = new Book();
            book.setTitle(title);

            // Xử lý ISBN
            if (isbnParam != null && !isbnParam.trim().isEmpty()) {
                book.setIsbn(Integer.parseInt(isbnParam));
            }

            // Xử lý publisher
            book.setPublisher(publisher);

            // Xử lý price
            if (priceParam != null && !priceParam.trim().isEmpty()) {
                book.setPrice(Double.parseDouble(priceParam));
            }

            // Xử lý quantity
            if (quantityParam != null && !quantityParam.trim().isEmpty()) {
                book.setQuantity(Integer.parseInt(quantityParam));
            } else {
                book.setQuantity(0);
            }

            // Xử lý description
            book.setDescription(description);

            // Xử lý publishDate
            if (publishDateParam != null && !publishDateParam.trim().isEmpty()) {
                book.setPublishDate(LocalDate.parse(publishDateParam));
            }

            // Xử lý chọn tác giả
            if (authorIds != null && !authorIds.isEmpty()) {
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
            if (file != null && !file.isEmpty()) {
                String originalFileName = Paths.get(file.getOriginalFilename()).getFileName().toString();

                // Kiểm tra định dạng file
                String[] allowedExtensions = { "jpg", "jpeg", "png", "gif" };
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

                    try {
                        storageService.store(file, "book" + File.separator + newFileName);
                        book.setCoverImage("book/" + newFileName);
                    } catch (Exception e) {
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
            ra.addFlashAttribute("message", "Thêm sách thành công!");
            return "redirect:/admin/books";
        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("error", "Thêm sách thất bại!");
            List<Author> authors = authorService.findAll();
            model.addAttribute("authors", authors);
            return "admin/add-book";
        }
    }

    @PostMapping("/book/update")
    public String updateBook(@RequestParam("bookId") int bookId,
            @RequestParam("title") String title,
            @RequestParam(value = "isbn", required = false) String isbnParam,
            @RequestParam("publisher") String publisher,
            @RequestParam(value = "price", required = false) String priceParam,
            @RequestParam(value = "quantity", required = false) String quantityParam,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "publishDate", required = false) String publishDateParam,
            @RequestParam(value = "authorIds", required = false) List<String> authorIds,
            @RequestParam(value = "image", required = false) MultipartFile file,
            RedirectAttributes ra, Model model) {
        try {
            Book book = bookService.getBook(bookId);

            if (book != null) {
                book.setTitle(title);

                // Xử lý ISBN
                if (isbnParam != null && !isbnParam.trim().isEmpty()) {
                    book.setIsbn(Integer.parseInt(isbnParam));
                }

                // Xử lý publisher
                book.setPublisher(publisher);

                // Xử lý price
                if (priceParam != null && !priceParam.trim().isEmpty()) {
                    book.setPrice(Double.parseDouble(priceParam));
                }

                // Xử lý quantity
                if (quantityParam != null && !quantityParam.trim().isEmpty()) {
                    book.setQuantity(Integer.parseInt(quantityParam));
                }

                // Xử lý description
                book.setDescription(description);

                // Xử lý publishDate
                if (publishDateParam != null && !publishDateParam.trim().isEmpty()) {
                    book.setPublishDate(LocalDate.parse(publishDateParam));
                }

                // Xử lý cập nhật tác giả
                List<Author> selectedAuthors = new ArrayList<>();
                if (authorIds != null && !authorIds.isEmpty()) {
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
                if (file != null && !file.isEmpty()) {
                    String originalFileName = Paths.get(file.getOriginalFilename()).getFileName().toString();

                    // Kiểm tra định dạng file
                    String[] allowedExtensions = { "jpg", "jpeg", "png", "gif" };
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

                        try {
                            storageService.store(file, "book" + File.separator + newFileName);
                            book.setCoverImage("book/" + newFileName);
                        } catch (Exception e) {
                            e.printStackTrace();
                            // Giữ nguyên hình ảnh cũ nếu upload thất bại
                        }
                    }
                    // Nếu định dạng không hợp lệ, giữ nguyên hình ảnh cũ
                }

                bookService.edit(book);
                ra.addFlashAttribute("message", "Cập nhật sách thành công");
            }
            return "redirect:/admin/books";

        } catch (Exception e) {
            e.printStackTrace();
            ra.addFlashAttribute("error", "Cập nhật sách thất bại!");
            Book book = bookService.getBook(bookId);
            List<Author> authors = authorService.findAll();
            model.addAttribute("authors", authors);
            model.addAttribute("book", book);
            return "admin/edit-book";
        }
    }
}
