package vn.binh.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vn.binh.entity.User_23110184;

@Controller("adminHomeController")
@RequestMapping("/admin")
public class HomeController_23110184 {

	@GetMapping("/home")
	public String adminHome(Model model, HttpSession session) {
		User_23110184 user = (User_23110184) session.getAttribute("account");
		if (user == null) {
			return "redirect:/login";
		}
		// Chỉ cho phép vào trang admin nếu là Seller (có sellerId)
		if (user.getRoleId() != 1) {
			return "redirect:/home";
		}
		return "admin/home";
	}
}