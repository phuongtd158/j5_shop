package com.poly.controllers.admins;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

	@GetMapping("index")
	public String index(Model model) {
		
		return "/admin/product/index";
	}
	
	@GetMapping("create")
	public String create(Model model) {
		
		return "/admin/product/index";
	}
	
	@PostMapping("store")
	public String store() {

		return "redirect:/admin/product/index";
	}

	@GetMapping("edit/{id}")
	public String edit() {

		return "/admin/product/index";
	}

	@PostMapping("update/{id}")
	public String update() {

		return "redirect:/admin/product/index";
	}

	@GetMapping("delete/{id}")
	public String delete() {

		return "redirect:/admin/product/index";
	}
}
