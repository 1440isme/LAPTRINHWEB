package vn.binh.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.binh.entity.Product_23110184;
import vn.binh.service.IProductService_23110184;

@Controller
public class ProductDetail_23110184 {
    private final IProductService_23110184 productService;

    public ProductDetail_23110184(IProductService_23110184 productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public String getProduct(@RequestParam("id") Integer id, Model model) {
        Product_23110184 product = productService.findById(id);
        if (product == null) {
            return "redirect:/home";
        }
        model.addAttribute("product", product);
        return "product-detail";
    }
}
