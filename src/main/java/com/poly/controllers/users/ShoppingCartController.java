package com.poly.controllers.users;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entities.Product;
import com.poly.models.CartModel;
import com.poly.services.ProductService;

@Controller
@RequestMapping("/")
public class ShoppingCartController {

	@Autowired
	private ProductService productService;

	@Autowired
	private HttpSession session;

	@PostMapping("add-to-cart")
	public String addToCart(Model model, @RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity) {

		Product product = this.productService.getById(id);
		CartModel productCart;
		double totalPrice = 0;
		int count = 0;

		HashMap<Integer, CartModel> cart = (HashMap<Integer, CartModel>) session.getAttribute("cart");
		if (cart == null) {

			cart = new HashMap<Integer, CartModel>();
			productCart = new CartModel(product, quantity);

			cart.put(id, productCart);
		} else {
			if (cart.containsKey(id)) {
				productCart = cart.get(id);
				productCart.setQuantity(quantity + productCart.getQuantity());
			} else {
				productCart = new CartModel(product, quantity);

				cart.put(id, productCart);
			}
		}

		for (Map.Entry<Integer, CartModel> entry : cart.entrySet()) {
			totalPrice = totalPrice + entry.getValue().getQuantity() * entry.getValue().getProduct().getPrice();
			count++;
		}

		session.setAttribute("cart", cart);
		session.setAttribute("totalPrice", totalPrice);
		session.setAttribute("count", count);
		// model.addAttribute("view", "/views/user/shopping-cart.jsp");
		return "redirect:/shopping-cart";
	}
	
	
}
