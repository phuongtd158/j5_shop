package com.poly.controllers.admins;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.entities.Order;
import com.poly.services.OrderService;

@Controller
@RequestMapping("/admin/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("index")
	public String index(Model model) {

		List<Order> listOrders = this.orderService.findAll();
		model.addAttribute("listOrders", listOrders);

		return "/admin/order/index";
	}

	@PostMapping("store")
	public String store() {

		return "redirect:/admin/order/index";
	}

	@GetMapping("edit/{id}")
	public String edit() {

		return "/admin/order/edit";
	}

	@PostMapping("update/{id}")
	public String update() {

		return "redirect:/admin/order/index";
	}

	@GetMapping("delete/{id}")
	public String delete() {

		return "redirect:/admin/order/index";
	}
}
