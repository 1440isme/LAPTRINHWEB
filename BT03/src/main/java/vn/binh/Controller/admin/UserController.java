package vn.binh.Controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.binh.entity.User;
import vn.binh.service.UserService;

@Controller
@RequestMapping("/admin")
public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> userList = userService.getAll();
		model.addAttribute("users", userList);
		return "/views/admin/list-user";
	}

	@GetMapping("/user/add")
	public String addForm() {
		return "/views/admin/add-user";
	}

	@GetMapping("/user/edit")
	public String editForm(@RequestParam("id") int id, Model model) {
		User user = userService.getIdUser(id);
		model.addAttribute("user", user);
		return "/views/admin/edit-user";
	}

	@PostMapping("/user/delete")
	public String delete(@RequestParam("id") int id, RedirectAttributes ra) {
		try {
			userService.delete(id);
			ra.addFlashAttribute("message", "Xóa thành công!");
		} catch (Exception e) {
			ra.addFlashAttribute("error", "Xóa thất bại!");
		}
		return "redirect:/admin/users";
	}

	@GetMapping("/user/search")
	public String search(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
		if (keyword != null && !keyword.trim().isEmpty()) {
			List<User> searchResults = userService.search(keyword);
			model.addAttribute("users", searchResults);
			model.addAttribute("keyword", keyword);
		}
		return "/views/admin/search-user";
	}

	@PostMapping("/user/insert")
	public String insertUser(@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("fullname") String fullname,
			@RequestParam("phone") Integer phone,
			RedirectAttributes ra, Model model) {
		try {
			User user = new User();
			user.setEmail(email);
			user.setPassword(password);
			user.setFullname(fullname);
			user.setPhone(phone);
			userService.insert(user);
			return "redirect:/admin/users";
		} catch (Exception e) {
			ra.addFlashAttribute("error", "Thêm mới thất bại!");
			return "redirect:/admin/user/add";
		}
	}

	@PostMapping("/user/update")
	public String updateUser(@RequestParam("id") int id,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("fullname") String fullname,
			@RequestParam("phone") Integer phone,
			RedirectAttributes ra, Model model) {
		try {
			User user = userService.getIdUser(id);
			user.setEmail(email);
			user.setPassword(password);
			user.setFullname(fullname);
			user.setPhone(phone);
			userService.edit(user);
			return "redirect:/admin/users";
		} catch (Exception e) {
			ra.addFlashAttribute("error", "Cập nhật thất bại!");
			return "redirect:/admin/user/edit?id=" + id;
		}
	}
}