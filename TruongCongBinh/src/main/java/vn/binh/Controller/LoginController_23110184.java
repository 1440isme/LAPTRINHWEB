package vn.binh.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import vn.binh.entity.User_23110184;
import vn.binh.service.UserService_23110184;

@Controller
@RequestMapping
public class LoginController_23110184 {

	private final UserService_23110184 userService;

	public LoginController_23110184(UserService_23110184 userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String showLoginPage(Model model, HttpSession session) {
		String resetSuccess = (String) session.getAttribute("resetSuccess");
		if (resetSuccess != null) {
			model.addAttribute("success", resetSuccess);
			session.removeAttribute("resetSuccess");
		}
		return "login/login";
	}

	@PostMapping("/login")
	public String processLogin(@RequestParam("username") String username,
			@RequestParam("password") String password,
			HttpSession session,
			Model model) {
		if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
			model.addAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
			return "login/login";
		}

		User_23110184 user = userService.login(username, password);
		if (user != null) {
			session.setAttribute("account", user);
			// Điều hướng theo vai trò: nếu có sellerId => vào trang seller (admin module)
			if (user.getSellerId() != null) {
				return "redirect:/admin/home";
			}
			return "redirect:/home";
		} else {
			model.addAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
			return "login/login";
		}
	}

	@GetMapping("/register")
	public String showRegisterPage() {
		return "login/register";
	}

	@PostMapping("/register")
	public String processRegister(@RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword,
			@RequestParam("fullname") String fullname,
			@RequestParam("username") String username,
			@RequestParam("email") String email,
			@RequestParam("phone") String phone,
			Model model) {
		if (password == null || fullname == null || username == null || phone == null
				|| password.isEmpty() || fullname.isEmpty() || username.isEmpty() || phone.isEmpty()) {
			model.addAttribute("alert", "Vui lòng điền đầy đủ thông tin");
			return "login/register";
		}
		if (!password.equals(confirmPassword)) {
			model.addAttribute("alert", "Mật khẩu xác nhận không khớp");
			return "login/register";
		}
		if (userService.checkUsernameExist(username)) {
			model.addAttribute("alert", "Tài khoản đã được sử dụng");
			return "login/register";
		}

		boolean registerSuccess = false;
		try {
			registerSuccess = userService.register(username, email, password, fullname, Integer.valueOf(phone));
		} catch (Exception ignored) {
		}

		if (registerSuccess) {
			model.addAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
			return "login/login";
		} else {
			model.addAttribute("alert", "Đăng ký thất bại. Vui lòng thử lại.");
			return "login/register";
		}
	}

	@PostMapping("/logout")
	public String processLogout(HttpSession session, RedirectAttributes ra) {
		if (session != null) {
			session.removeAttribute("account");
			session.invalidate();
		}
		return "redirect:/login";
	}

	@GetMapping("/logout")
	public String processLogoutGet(HttpSession session) {
		if (session != null) {
			session.removeAttribute("account");
			session.invalidate();
		}
		return "redirect:/login";
	}

	@GetMapping("/forgot-password")
	public String showForgotPasswordPage() {
		return "login/forgot-password";
	}

	@PostMapping("/forgot-password")
	public String processForgotPassword(@RequestParam("email") String email,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("confirmPassword") String confirmPassword,
			HttpSession session,
			Model model) {
		if (email == null || newPassword == null || confirmPassword == null || email.isEmpty()
				|| newPassword.isEmpty() || confirmPassword.isEmpty()) {
			model.addAttribute("alert", "Vui lòng điền đầy đủ thông tin");
			return "login/forgot-password";
		}
		if (!newPassword.equals(confirmPassword)) {
			model.addAttribute("alert", "Mật khẩu xác nhận không khớp");
			return "login/forgot-password";
		}

		User_23110184 user = userService.findByEmail(email);
		if (user == null) {
			model.addAttribute("alert", "Email không tồn tại trong hệ thống");
			return "/views/login/forgot-password";
		}

		user.setPassword(newPassword);
		userService.edit(user);
		session.setAttribute("resetSuccess", "Đặt lại mật khẩu thành công! Vui lòng đăng nhập.");
		return "redirect:/login";
	}
}