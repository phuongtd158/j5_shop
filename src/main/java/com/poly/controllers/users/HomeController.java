package com.poly.controllers.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entities.Product;
import com.poly.services.ProductService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private ProductService productService;

	@GetMapping("home")
	public String home(Model model) {
		
		List<Product> listProducts = this.productService.findAll();

		model.addAttribute("listProducts", listProducts);
		
		model.addAttribute("view", "/views/user/home.jsp");
		return "/user/index";
	}

	@GetMapping("shop")
	public String shop(Model model) {

		List<Product> listProducts = this.productService.findAllActive();

		model.addAttribute("listProducts", listProducts);
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

		model.addAttribute("product", product);
		model.addAttribute("listProducts", listProducts);
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
