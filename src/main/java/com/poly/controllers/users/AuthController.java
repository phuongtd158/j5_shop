package com.poly.controllers.users;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entities.Account;
import com.poly.services.AccountService;
import com.poly.utilities.EncryptUtils;

@Controller
@RequestMapping("/")
public class AuthController {

	@Autowired
	private AccountService accountService;

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

	@GetMapping("change-password")
	public String changePassword() {

		return "/user/change-password";
	}

	@GetMapping("profile")
	public String editProfile() {

		return "/user/profile";
	}

	@GetMapping("logout")
	public String logout() {
		if (session != null) {
			session.removeAttribute("account");
		}
		return "redirect:/home";
	}

}
