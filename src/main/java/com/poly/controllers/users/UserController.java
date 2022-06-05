package com.poly.controllers.users;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entities.Account;
import com.poly.entities.Order;
import com.poly.entities.OrderDetail;
import com.poly.services.AccountService;
import com.poly.services.OrderService;
import com.poly.utilities.EncryptUtils;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private HttpSession session;

	@Autowired
	private EncryptUtils encryptUtils;

	@GetMapping("login")
	public String loginView() {

		return "/user/login";
	}

	@PostMapping("login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password) {

		Account account = this.accountService.findByUsername(username);

		if (account != null) {
			String passwordEncrypt = account.getPassword();

			boolean checkEncryptPassword = this.encryptUtils.check(password, passwordEncrypt);

			if (!checkEncryptPassword) {
				session.setAttribute("errorPassword", "Sai tên tài khoản hoặc mật khẩu");
			} else {
				System.out.println(account.getFullName());
				session.setAttribute("account", account);
				if (account.getAdmin() == 1) {
					return "redirect:/admin/home";
				} else {
					return "redirect:/home";
				}

			}

		} else {
			session.setAttribute("errorPassword", "Sai tên tài khoản hoặc mật khẩu");
			return "/user/login";
		}

		System.out.println(account.getId());

		return "/user/login";
	}

	@GetMapping("order-history")
	public String orderHistory(Model model) {

		Account account = (Account) session.getAttribute("account");

		System.out.println(account.getUsername());

		List<Order> listOrders = this.orderService.findAllByAccountId(account.getId());

		listOrders.forEach(order -> {
			System.out.print(order.getId());
		});

		model.addAttribute("listOrders", listOrders);

		model.addAttribute("view", "/views/user/order-history.jsp");
		return "/user/index";
	}

	@GetMapping("order-detail/{id}")
	public String orderDetail(Model model, @PathVariable("id") Integer id) {

		List<OrderDetail> listOrderDetails = this.orderService.getById(id).getOrderDetails();

		model.addAttribute("listOrderDetails", listOrderDetails);
		model.addAttribute("view", "/views/user/order-detail.jsp");
		return "/user/index";
	}

	@GetMapping("change-password")
	public String changePassword() {

		return "/user/change-password";
	}

	@GetMapping("profile")
	public String editProfileView(Model model) {
		
		Account account = (Account) session.getAttribute("account");
		
		model.addAttribute("account", account);
		model.addAttribute("view", "/views/user/profile.jsp");
		return "/user/index";
	}

	@PostMapping("")
	public String editProfile(Model model) {

		model.addAttribute("view", "/views/user/profile.jsp");
		return "/user/index";
	}

	@GetMapping("logout")
	public String logout() {
		if (session != null) {
			session.removeAttribute("account");
		}
		return "redirect:/home";
	}

}
