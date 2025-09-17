package vn.binh.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import vn.binh.entity.Category;
import vn.binh.service.CategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryController {
	@Autowired
	CategoryService categoryService;

	@GetMapping("add")

	public String add(ModelMap model) {

		return "admin/categories/add";
	}

	@RequestMapping("")

	public String list(ModelMap model) {

		// gọi hàm findAll() trong service

		List<Category> list = categoryService.findAll();

		// chuyển dữ liệu từ list lên biến categories

		model.addAttribute("categories", list);

		return "admin/categories/list";

	}

	@GetMapping("delete/{categoryId}")

	public ModelAndView delet(ModelMap model, @PathVariable("categoryId") int categoryId) {

		categoryService.deleteById(categoryId);

		model.addAttribute("message", "Category is deleted!!!!");

		return new ModelAndView("redirect:/admin/categories/searchpaginated", model);

	}

	@RequestMapping("search")

	public String search(ModelMap model, @RequestParam(name = "name", required = false) String name) {

		List<Category> list = null;

		// có nội dung truyền về không, name là tùy chọn khi required=false

		if (StringUtils.hasText(name)) {

			list = categoryService.findByCategoryNameContaining(name);

		} else {

			list = categoryService.findAll();

		}

		model.addAttribute("categories", list);

		return "admin/categories/search";

	}
}