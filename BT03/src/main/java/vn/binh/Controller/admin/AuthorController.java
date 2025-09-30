package vn.binh.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.binh.entity.Author;
import vn.binh.service.IAuthorService;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/admin")
public class AuthorController {
    private final IAuthorService authorService;

    public AuthorController(IAuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author/add")
    public String addAuthorForm() {
        return "admin/add-author";
    }

    @GetMapping("/author/edit")
    public String editAuthor(@RequestParam("authorId") int authorId, Model model) {
        Author author = authorService.getAuthor(authorId);
        model.addAttribute("author", author);
        return "admin/edit-author";
    }

    @PostMapping("/author/delete")
    public String deleteAuthor(@RequestParam("authorId") int authorId, RedirectAttributes ra) {
        try {
            authorService.delete(authorId);
            ra.addFlashAttribute("message", "Xoá thành công");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Xoá thất bại");
        }
        return "redirect:/admin/authors";
    }

    @GetMapping("/authors")
    public String listAuthors(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
            Model model) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), pageSize);
        Page<Author> authors = authorService.findAll(pageable);
        long totalAuthors = authors.getTotalElements();
        int totalPages = (int) Math.ceil((double) totalAuthors / pageSize);

        model.addAttribute("authorList", authors.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("totalAuthors", totalAuthors);
        return "admin/list-author";
    }

    @PostMapping("/author/add")
    public String insertAuthor(@RequestParam("authorName") String authorName,
            @RequestParam(value = "dateOfBirth", required = false) String dateOfBirthParam,
            RedirectAttributes ra) {
        try {
            Author author = new Author();
            author.setAuthorName(authorName);
            if (dateOfBirthParam != null && !dateOfBirthParam.trim().isEmpty()) {
                author.setDateOfBirth(LocalDate.parse(dateOfBirthParam));
            }
            authorService.insert(author);
            ra.addFlashAttribute("message", "Thêm tác giả thành công!");
            return "redirect:/admin/authors";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Thêm tác giả thất bại!");
            return "redirect:/admin/author/add";
        }
    }

    @PostMapping("/author/update")
    public String updateAuthor(@RequestParam("authorId") int authorId,
            @RequestParam("authorName") String authorName,
            @RequestParam(value = "dateOfBirth", required = false) String dateOfBirthParam,
            RedirectAttributes ra, Model model) {
        try {
            Author author = authorService.getAuthor(authorId);
            if (author != null) {
                author.setAuthorName(authorName);
                if (dateOfBirthParam != null && !dateOfBirthParam.trim().isEmpty()) {
                    author.setDateOfBirth(LocalDate.parse(dateOfBirthParam));
                }
                authorService.edit(author);
                ra.addFlashAttribute("message", "Cập nhật tác giả thành công");
            }
            return "redirect:/admin/authors";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Cập nhật tác giả thất bại!");
            model.addAttribute("author", authorService.getAuthor(authorId));
            return "admin/edit-author";
        }
    }
}
