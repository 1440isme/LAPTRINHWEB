package vn.binh.controller.admin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.binh.entity.Author;
import vn.binh.service.IAuthorService;
import vn.binh.service.impl.AuthorServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = { "/admin/authors", "/admin/author/add", "/admin/author/edit", "/admin/author/delete", "/admin/author/update" })
public class AuthorController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IAuthorService authorService = new AuthorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");

        if (url.contains("add")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-author.jsp");
            dispatcher.forward(req, resp);
        } else if (url.contains("edit")) {
            String idParam = req.getParameter("authorId");
            int authorId = Integer.parseInt(idParam);
            Author author = authorService.getAuthor(authorId);
            req.setAttribute("author", author);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-author.jsp");
            dispatcher.forward(req, resp);
        } else if (url.contains("delete")) {
            String idParam = req.getParameter("authorId");
            int authorId = Integer.parseInt(idParam);
            try {
                authorService.delete(authorId);
                req.setAttribute("message", "Xoá thành công");
            } catch (Exception e) {
                req.setAttribute("error","Xoá thất bại");
            }
            resp.sendRedirect(req.getContextPath() + "/admin/authors");
        } else {
            // Xử lý phân trang
            String pageParam = req.getParameter("page");
            int page = (pageParam != null) ? Integer.parseInt(pageParam) : 1;
            int pageSize = 10; // Số tác giả mỗi trang

            List<Author> authors = authorService.findAll(page, pageSize);
            long totalAuthors = authorService.countAll();
            int totalPages = (int) Math.ceil((double) totalAuthors / pageSize);

            req.setAttribute("authorList", authors);
            req.setAttribute("currentPage", page);
            req.setAttribute("totalPages", totalPages);
            req.setAttribute("totalAuthors", totalAuthors);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/list-author.jsp");
            dispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (url.contains("add")) {
            insertAuthor(req, resp);
        } else if (url.contains("update")) {
            updateAuthor(req, resp);
        }
    }

    private void insertAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        try {
            Author author = new Author();
            author.setAuthorName(req.getParameter("authorName"));

            // Xử lý dateOfBirth
            String dateOfBirthParam = req.getParameter("dateOfBirth");
            if (dateOfBirthParam != null && !dateOfBirthParam.trim().isEmpty()) {
                author.setDateOfBirth(LocalDate.parse(dateOfBirthParam));
            }

            authorService.insert(author);
            req.setAttribute("message", "Thêm tác giả thành công!");
            resp.sendRedirect(req.getContextPath() + "/admin/authors");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Thêm tác giả thất bại!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/add-author.jsp");
            dispatcher.forward(req, resp);
        }
    }

    private void updateAuthor(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String idParam = req.getParameter("authorId");
            int authorId = Integer.parseInt(idParam);
            Author author = authorService.getAuthor(authorId);

            if (author != null) {
                author.setAuthorName(req.getParameter("authorName"));

                // Xử lý dateOfBirth
                String dateOfBirthParam = req.getParameter("dateOfBirth");
                if (dateOfBirthParam != null && !dateOfBirthParam.trim().isEmpty()) {
                    author.setDateOfBirth(LocalDate.parse(dateOfBirthParam));
                }

                authorService.edit(author);
                req.setAttribute("message", "Cập nhật tác giả thành công");
            }
            resp.sendRedirect(req.getContextPath() + "/admin/authors");

        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("error", "Cập nhật tác giả thất bại!");
            String idParam = req.getParameter("authorId");
            int id = Integer.parseInt(idParam);
            Author author = authorService.getAuthor(id);
            req.setAttribute("author", author);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/admin/edit-author.jsp");
            dispatcher.forward(req, resp);
        }
    }
}
