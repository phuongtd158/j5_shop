package com.poly.controllers.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.entities.Product;
import com.poly.services.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private ProductService productService;

	@GetMapping("home")
	public String home(Model model) {

		model.addAttribute("view", "/views/user/home.jsp");
		return "/user/index";
	}

	@GetMapping("shop")
	public String shop(Model model) {

		List<Product> listProducts = this.productService.findAllActive();

		model.addAttribute("view", "/views/user/shop.jsp");
		model.addAttribute("listProducts", listProducts);

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

		model.addAttribute("view", "/views/user/product-detail.jsp");
		model.addAttribute("product", product);
		return "/user/index";
	}

	@GetMapping("check-out")
	public String checkOut(Model model) {

		model.addAttribute("view", "/views/user/check-out.jsp");
		return "/user/index";
	}

	
}
