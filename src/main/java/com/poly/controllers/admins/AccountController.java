package com.poly.controllers.admins;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/account")
public class AccountController {
	
	@GetMapping("index")
	public String index(Model model) {

		

		return "/admin/category/index";
	}

	@PostMapping("store")
	public String store() {

		return "redirect:/admin/category/index";
	}

	@GetMapping("edit/{id}")
	public String edit() {

		return "/admin/category/index";
	}

	@PostMapping("update/{id}")
	public String update() {

		return "redirect:/admin/category/index";
	}

	@GetMapping("delete/{id}")
	public String delete() {

		return "redirect:/admin/category/index";
	}
}
