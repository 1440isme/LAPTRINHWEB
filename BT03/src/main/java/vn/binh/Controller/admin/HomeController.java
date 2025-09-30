package vn.binh.Controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import vn.binh.entity.User;

@Controller("adminHomeController")
@RequestMapping("/admin")
public class HomeController {

	@GetMapping("/home")
	public String adminHome(Model model, HttpSession session) {
		User user = (User) session.getAttribute("account");
		if (user == null) {
			return "redirect:/login";
		}
		if (user.getIsAdmin() != true) {
			return "redirect:/home";
		}
		return "admin/home";
	}
}