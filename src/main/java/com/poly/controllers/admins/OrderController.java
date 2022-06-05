package com.poly.controllers.admins;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entities.Order;
import com.poly.entities.OrderDetail;
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

	@GetMapping("order-detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		List<OrderDetail> listOrderDetails = this.orderService.getById(id).getOrderDetails();

		model.addAttribute("listOrderDetails", listOrderDetails);
		return "/admin/order/order-detail";
	}

	@PostMapping("update-order/{id}")
	public String update(@PathVariable("id") Integer id, @RequestParam("status") Integer status) {

		Order order = this.orderService.getById(id);
		
		order.setStatus(status);

		this.orderService.save(order);

		return "redirect:/admin/order/index";
	}

	@GetMapping("delete/{id}")
	public String delete() {

		return "redirect:/admin/order/index";
	}
}
