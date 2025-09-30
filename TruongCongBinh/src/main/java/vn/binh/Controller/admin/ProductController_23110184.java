package vn.binh.Controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import vn.binh.entity.Category_23110184;
import vn.binh.entity.Product_23110184;
import vn.binh.entity.User_23110184;
import vn.binh.service.ICategoryService_23110184;
import vn.binh.service.IProductService_23110184;
import vn.binh.service.IStorageService_23110184;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController_23110184 {
    private final IProductService_23110184 productService;
    private final ICategoryService_23110184 categoryService;
    private final IStorageService_23110184 storageService;

    public ProductController_23110184(IProductService_23110184 productService,
            ICategoryService_23110184 categoryService,
            IStorageService_23110184 storageService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.storageService = storageService;
    }

    @GetMapping("/products")
    public String list(@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), pageSize);
        Page<Product_23110184> pageProducts = productService.findAll(pageable);
        model.addAttribute("productList", pageProducts.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pageProducts.getTotalPages());
        model.addAttribute("totalItems", pageProducts.getTotalElements());
        return "admin/list-product";
    }

    @GetMapping("/product/add")
    public String addForm(Model model) {
        List<Category_23110184> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "admin/add-product";
    }

    @GetMapping("/product/edit")
    public String editForm(@RequestParam("productId") int productId, Model model) {
        Product_23110184 product = productService.findById(productId);
        List<Category_23110184> categories = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "admin/edit-product";
    }

    @PostMapping("/product/delete")
    public String delete(@RequestParam("productId") int productId, RedirectAttributes ra) {
        try {
            productService.delete(productId);
            ra.addFlashAttribute("message", "Xoá sản phẩm thành công");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Xoá sản phẩm thất bại");
        }
        return "redirect:/admin/products";
    }

    @PostMapping("/product/insert")
    public String insert(@RequestParam("productName") String productName,
            @RequestParam("productCode") String productCode,
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "amount", required = false) Integer amount,
            @RequestParam(value = "stock", required = false) Integer stock,
            @RequestParam(value = "price", required = false) Float price,
            @RequestParam(value = "status", required = false, defaultValue = "1") Integer status,
            @RequestParam(value = "image", required = false) MultipartFile file,
            HttpSession session,
            RedirectAttributes ra) {
        try {
            Product_23110184 p = new Product_23110184();
            p.setProductName(productName);
            p.setProductCode(productCode);
            p.setCategoryId(categoryId);
            p.setDescription(description);
            p.setAmount(amount);
            p.setPrice(price);
            p.setStock(stock);
            p.setStatus(status);
            p.setCreatedDate(LocalDate.now());

            User_23110184 user = (User_23110184) session.getAttribute("account");
            if (user != null) {
                p.setSellerId(user.getSellerId());
            }

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
                    storageService.store(file, "product" + File.separator + newFileName);
                    p.setImages("product/" + newFileName);
                }
            }

            productService.insert(p);
            ra.addFlashAttribute("message", "Thêm sản phẩm thành công!");
            return "redirect:/admin/products";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Thêm sản phẩm thất bại!");
            return "redirect:/admin/product/add";
        }
    }

    @PostMapping("/product/update")
    public String update(@RequestParam("productId") int productId,
            @RequestParam("productName") String productName,
            @RequestParam("productCode") String productCode,
            @RequestParam(value = "categoryId", required = false) Integer categoryId,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "amount", required = false) Integer amount,
            @RequestParam(value = "stock", required = false) Integer stock,
            @RequestParam(value = "price", required = false) Float price,
            @RequestParam(value = "status", required = false, defaultValue = "1") Integer status,
            @RequestParam(value = "image", required = false) MultipartFile file,
            RedirectAttributes ra) {
        try {
            Product_23110184 p = productService.findById(productId);
            if (p != null) {
                p.setProductName(productName);
                p.setProductCode(productCode);
                p.setCategoryId(categoryId);
                p.setDescription(description);
                p.setAmount(amount);
                p.setPrice(price);
                p.setStock(stock);
                p.setStatus(status);

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
                        storageService.store(file, "product" + File.separator + newFileName);
                        p.setImages("product/" + newFileName);
                    }
                }

                productService.edit(p);
                ra.addFlashAttribute("message", "Cập nhật sản phẩm thành công");
            }
            return "redirect:/admin/products";
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Cập nhật sản phẩm thất bại!");
            return "redirect:/admin/product/edit?productId=" + productId;
        }
    }
}
