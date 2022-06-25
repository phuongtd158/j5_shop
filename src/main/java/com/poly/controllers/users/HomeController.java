package com.poly.controllers.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entities.Category;
import com.poly.entities.Product;
import com.poly.services.CategoryService;
import com.poly.services.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("home")
	public String home(Model model) {

		List<Product> listProducts = this.productService.findAll();

		model.addAttribute("listProducts", listProducts);

		model.addAttribute("view", "/views/user/home.jsp");
		return "/user/index";
	}

	@GetMapping("shop")
	public String shop(Model model, @RequestParam(name = "category", defaultValue = "") Integer id,
			@RequestParam(name = "sort-by", defaultValue = "") String sortBy) {

		List<Product> listProducts = null;
		List<Category> listCategories = this.categoryService.findAllActive();
		Sort sort = Sort.by(Direction.ASC, "id");

		if (id != null) {
			Optional<Category> cate = this.categoryService.findById(id);
			if (cate.isPresent()) {
				listProducts = cate.get().getProducts();
			}
		} else if (sortBy != null) {
			if (sortBy.equals("1")) {
				sort = Sort.by(Direction.DESC, "createDate");
			} else if (sortBy.equals("2")) {
				sort = Sort.by(Direction.ASC, "price");
			} else {
				sort = Sort.by(Direction.DESC, "price");
			}
			listProducts = this.productService.findAllActive(sort);
		} else {
			listProducts = this.productService.findAllActive();
		}

		model.addAttribute("listProducts", listProducts);
		model.addAttribute("sortBy", sortBy);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("view", "/views/user/shop.jsp");

		return "/user/index";
	}

	@GetMapping("about")
	public String about(Model model) {

		model.addAttribute("view", "/views/user/about.jsp");
		return "/user/index";
	}

	@GetMapping("contact")
	public String contact(Model model) {

		model.addAttribute("view", "/views/user/contact.jsp");
		return "/user/index";
	}

	@GetMapping("shopping-cart")
	public String shoppingCart(Model model) {

		model.addAttribute("view", "/views/user/shopping-cart.jsp");
		return "/user/index";
	}

	@GetMapping("product-detail/{id}")
	public String productDetail(Model model, @PathVariable("id") Integer id) {

		Product product = this.productService.getById(id);
		List<Product> listProducts = this.productService.findAll();
		List<Product> listProductsByCategoryId = this.productService
				.findAllByCategoryId(product.getCategoryById().getId());
		
		model.addAttribute("product", product);
		model.addAttribute("listProducts", listProducts);
		model.addAttribute("listProductsByCategoryId", listProductsByCategoryId);
		model.addAttribute("view", "/views/user/product-detail.jsp");

		return "/user/index";
	}

	@GetMapping("check-out")
	public String checkOut(Model model) {

		model.addAttribute("view", "/views/user/check-out.jsp");
		return "/user/index";
	}

	@GetMapping("thankyou")
	public String thankYou(Model model) {

		model.addAttribute("view", "/views/user/thankyou.jsp");
		return "/user/index";
	}

}
