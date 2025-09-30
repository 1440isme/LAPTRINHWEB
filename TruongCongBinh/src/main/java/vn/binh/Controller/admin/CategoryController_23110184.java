package vn.binh.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.binh.entity.Category_23110184;
import vn.binh.service.ICategoryService_23110184;
import vn.binh.service.IStorageService_23110184;
import vn.binh.service.Impl.CategoryServiceImpl_23110184;
import vn.binh.service.Impl.FileSystemStorageServiceImpl_23110184;

import java.io.File;
import java.nio.file.Paths;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Controller
@RequestMapping("/admin")
public class CategoryController_23110184 {
    private final CategoryServiceImpl_23110184 categoryService;
    private final FileSystemStorageServiceImpl_23110184 storageService;

    public CategoryController_23110184(CategoryServiceImpl_23110184 categoryService,
            FileSystemStorageServiceImpl_23110184 storageService) {
        this.categoryService = categoryService;
        this.storageService = storageService;
    }

    @GetMapping("/category/add")
    public String addForm() {
        return "admin/add-category";
    }

    @GetMapping("/category/edit")
    public String editForm(@RequestParam("categoryId") int categoryId, Model model) {
        Category_23110184 category = categoryService.findAll().stream()
                .filter(c -> c.getCategoryId() == categoryId)
                .findFirst().orElse(null);
        model.addAttribute("category", category);
        return "admin/edit-category";
    }

    @PostMapping("/category/delete")
    public String delete(@RequestParam("categoryId") int categoryId, RedirectAttributes ra) {
        try {
            categoryService.delete(categoryId);
            ra.addFlashAttribute("message", "Xoá danh mục thành công");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Xoá danh mục thất bại");
        }
        return "redirect:/admin/categories";
    }

    @GetMapping("/categories")
    public String list(@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), pageSize);
        Page<Category_23110184> pageCategories = categoryService.findAll(pageable);
        model.addAttribute("categoryList", pageCategories.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageCategories.getTotalPages());
        model.addAttribute("totalItems", pageCategories.getTotalElements());
        return "admin/list-category";
    }

    @PostMapping("/category/add")
    public String insert(@RequestParam("categoryName") String categoryName,
            @RequestParam(value = "status", required = false, defaultValue = "1") Integer status,
            @RequestParam(value = "image", required = false) MultipartFile file,
            RedirectAttributes ra) {
        try {
            Category_23110184 category = new Category_23110184();
            category.setCategoryName(categoryName);
            category.setStatus(status);

            if (file != null && !file.isEmpty()) {
                String originalFileName = Paths.get(file.getOriginalFilename()).getFileName().toString();
                String[] allowedExtensions = { "jpg", "jpeg", "png", "gif" };
                String ext = originalFileName.substring(originalFileName.lastIndexOf('.') + 1).toLowerCase();
                boolean valid = false;
                for (String a : allowedExtensions) {
                    if (ext.equals(a)) {
                        valid = true;
                        break;
                    }
                }
                if (valid) {
                    String newFileName = System.currentTimeMillis() + "." + ext;
                    storageService.store(file, "category" + File.separator + newFileName);
                    category.setImages("category/" + newFileName);
                }
            }

            categoryService.insert(category);
            ra.addFlashAttribute("message", "Thêm danh mục thành công!");
            return "redirect:/admin/categories";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Thêm danh mục thất bại!");
            return "admin/add-category";
        }
    }

    @PostMapping("/category/update")
    public String update(@RequestParam("categoryId") int categoryId,
            @RequestParam("categoryName") String categoryName,
            @RequestParam(value = "status", required = false, defaultValue = "1") Integer status,
            @RequestParam(value = "image", required = false) MultipartFile file,
            RedirectAttributes ra, Model model) {
        try {
            Category_23110184 category = categoryService.findAll().stream()
                    .filter(c -> c.getCategoryId() == categoryId)
                    .findFirst().orElse(null);
            if (category != null) {
                category.setCategoryName(categoryName);
                category.setStatus(status);

                if (file != null && !file.isEmpty()) {
                    String originalFileName = Paths.get(file.getOriginalFilename()).getFileName().toString();
                    String[] allowedExtensions = { "jpg", "jpeg", "png", "gif" };
                    String ext = originalFileName.substring(originalFileName.lastIndexOf('.') + 1).toLowerCase();
                    boolean valid = false;
                    for (String a : allowedExtensions) {
                        if (ext.equals(a)) {
                            valid = true;
                            break;
                        }
                    }
                    if (valid) {
                        String newFileName = System.currentTimeMillis() + "." + ext;
                        storageService.store(file, "category" + File.separator + newFileName);
                        category.setImages("category/" + newFileName);
                    }
                }

                categoryService.edit(category);
                ra.addFlashAttribute("message", "Cập nhật danh mục thành công");
            }
            return "redirect:/admin/categories";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Cập nhật danh mục thất bại!");
            model.addAttribute("categoryId", categoryId);
            return "admin/edit-category";
        }
    }
}
