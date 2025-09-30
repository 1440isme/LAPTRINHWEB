package vn.binh.Controller;

import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import vn.binh.entity.Product_23110184;
import vn.binh.entity.User_23110184;
import vn.binh.service.IProductService_23110184;

@Controller
@RequestMapping
public class HomeController_23110184 {

	private final IProductService_23110184 productService;

	public HomeController_23110184(IProductService_23110184 productService) {
		this.productService = productService;
	}

	@GetMapping({ "/home", "/products" })
	public String home(Model model, HttpSession session,
			@RequestParam(value = "keyword", required = false) String keyword) {
		User_23110184 user = (User_23110184) session.getAttribute("account");
		// Nếu chưa login, quay về trang đăng nhập
		if (user == null) {
			return "redirect:/login";
		}

		List<Product_23110184> products = (keyword == null || keyword.trim().isEmpty())
				? productService.findAll()
				: productService.search(keyword.trim());
		// Sắp xếp theo sellerId để JSP có thể nhóm dễ dàng
		products.sort(Comparator.comparing(p -> p.getSellerId() == null ? 0 : p.getSellerId()));

		model.addAttribute("productList", products);
		model.addAttribute("user", user);
		model.addAttribute("keyword", keyword);
		return "home";
	}
}