package vn.binh.Controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import vn.binh.entity.User_23110184;
import vn.binh.service.UserService_23110184;

@Controller
@RequestMapping("/admin")
public class UserController_23110184 {
	private final UserService_23110184 userService;

	public UserController_23110184(UserService_23110184 userService) {
		this.userService = userService;
	}

	@GetMapping("/users")
	public String listUsers(@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		int pageSize = 5;
		Pageable pageable = PageRequest.of(Math.max(page - 1, 0), pageSize);
		Page<User_23110184> pageUsers = userService.getAll(pageable);
		model.addAttribute("users", pageUsers.getContent());
		model.addAttribute("currentPage", page);
		model.addAttribute("totalPages", pageUsers.getTotalPages());
		model.addAttribute("totalItems", pageUsers.getTotalElements());
		return "admin/list-user";
	}

	@GetMapping("/user/add")
	public String addForm() {
		return "admin/add-user";
	}

	@GetMapping("/user/edit")
	public String editForm(@RequestParam("id") int id, Model model) {
		User_23110184 user = userService.getIdUser(id);
		model.addAttribute("user", user);
		return "admin/edit-user";
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
			List<User_23110184> searchResults = userService.search(keyword);
			model.addAttribute("users", searchResults);
			model.addAttribute("keyword", keyword);
		}
		return "admin/search-user";
	}

	@PostMapping("/user/insert")
	public String insertUser(@RequestParam("username") String username,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("fullname") String fullname,
			@RequestParam(value = "phone", required = false) Integer phone,
			@RequestParam(value = "roleId", required = false) Integer roleId,
			@RequestParam(value = "sellerId", required = false) Integer sellerId,
			@RequestParam(value = "status", required = false) Integer status,
			RedirectAttributes ra, Model model) {
		try {
			User_23110184 user = new User_23110184();
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setFullname(fullname);
			user.setPhone(phone);
			user.setRoleId(roleId == null ? 2 : roleId);
			user.setSellerId(sellerId);
			user.setStatus(status == null ? 1 : status);
			userService.insert(user);
			return "redirect:/admin/users";
		} catch (Exception e) {
			ra.addFlashAttribute("error", "Thêm mới thất bại!");
			return "redirect:/admin/user/add";
		}
	}

	@PostMapping("/user/update")
	public String updateUser(@RequestParam("id") int id,
			@RequestParam("username") String username,
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("fullname") String fullname,
			@RequestParam(value = "phone", required = false) Integer phone,
			@RequestParam(value = "roleId", required = false) Integer roleId,
			@RequestParam(value = "sellerId", required = false) Integer sellerId,
			@RequestParam(value = "status", required = false) Integer status,
			RedirectAttributes ra, Model model) {
		try {
			User_23110184 user = userService.getIdUser(id);
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password);
			user.setFullname(fullname);
			user.setPhone(phone);
			user.setRoleId(roleId == null ? user.getRoleId() : roleId);
			user.setSellerId(sellerId);
			user.setStatus(status == null ? user.getStatus() : status);
			userService.edit(user);
			return "redirect:/admin/users";
		} catch (Exception e) {
			ra.addFlashAttribute("error", "Cập nhật thất bại!");
			return "redirect:/admin/user/edit?id=" + id;
		}
	}
}