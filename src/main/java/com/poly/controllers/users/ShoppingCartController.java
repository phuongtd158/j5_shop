package com.poly.controllers.users;

import java.io.FileNotFoundException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.layout.element.Paragraph;
import com.poly.entities.Account;
import com.poly.entities.Order;
import com.poly.entities.OrderDetail;
import com.poly.entities.Product;
import com.poly.models.CartModel;
import com.poly.services.AccountService;
import com.poly.services.OrderDetailService;
import com.poly.services.OrderService;
import com.poly.services.ProductService;
import com.poly.utilities.PDFUtils;

@Controller
@RequestMapping("/")
public class ShoppingCartController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private HttpSession session;

    @Autowired
    private PDFUtils pdfUtils;

    @PostMapping("add-to-cart")
    public String addToCart(Model model, @RequestParam("id") Integer id, @RequestParam("quantity") Integer quantity) {

        Product product = this.productService.getById(id);
        CartModel productCart;
        double totalPrice = 0;
        int count = 0;

        HashMap<Integer, CartModel> cart = (HashMap<Integer, CartModel>) session.getAttribute("cart");

        if (quantity < 0) {
            session.setAttribute("errorQuantity", "Số lượng phải lớn hơn 0");
            return "redirect:/product-detail/" + id;
        }

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

        return "redirect:/shopping-cart";
    }

    @PostMapping("check-out")
    public String checkOut(Model model, @RequestParam("address") String address,
                           @RequestParam(name = "check") Optional<Boolean> check) throws FileNotFoundException {

        Account account = (Account) session.getAttribute("account");
        Date createDate = new Date(System.currentTimeMillis());
        double totalPrice = (double) session.getAttribute("totalPrice");

        Order order = new Order();
        order.setAccountById(account);
        order.setCreateDate(createDate);
        order.setAddress(address);
        order.setStatus(0);

        this.orderService.save(order);

        HashMap<Integer, CartModel> cart = (HashMap<Integer, CartModel>) session.getAttribute("cart");

        for (Map.Entry<Integer, CartModel> entry : cart.entrySet()) {
            OrderDetail orderDetail = new OrderDetail();
            Product product = this.productService.getById(entry.getValue().getProduct().getId());
            System.out.println(product.getId());
            orderDetail.setOrderById(order);
            orderDetail.setPrice(entry.getValue().getQuantity() * entry.getValue().getProduct().getPrice());
            orderDetail.setProductById(product);
            orderDetail.setQuantity(entry.getValue().getQuantity());

            this.orderDetailService.save(orderDetail);
        }

        if (check.isPresent()) {
            this.pdfUtils.prinfPDF(cart, order.getId(), createDate, account.getFullName(), address, account.getEmail(),
                    "0385606568", totalPrice);
        }

        session.removeAttribute("cart");
        session.removeAttribute("totalPrice");
        session.removeAttribute("count");

        return "redirect:/thankyou";
    }

    @GetMapping("remove-item")
    public String removeItem(@RequestParam("key") Integer key) {

        HashMap<Integer, CartModel> cart = (HashMap<Integer, CartModel>) session.getAttribute("cart");

        if (cart != null) {
            if (cart.containsKey(key)) {
                cart.remove(key);
            }
        }

        if (cart == null || cart.isEmpty()) {
            session.removeAttribute("cart");
            session.removeAttribute("totalPrice");
            session.removeAttribute("count");
        }

        return "redirect:/shopping-cart";
    }

    @PostMapping("update-cart")
    public String updateCart(@RequestParam(name = "key") Optional<Integer[]> key,
                             @RequestParam("quantity") Optional<Integer[]> quantity) {

        CartModel productCart;
        double totalPrice = 0;
        HashMap<Integer, CartModel> cart = (HashMap<Integer, CartModel>) session.getAttribute("cart");

        if (!key.isPresent()) {
            session.setAttribute("errorShoppingCart", "Giỏ hàng trống");
            return "redirect:/shopping-cart";
        }

        for (int i = 0; i < key.get().length; i++) {
            if (cart.containsKey(key.get()[i])) {

                productCart = cart.get(key.get()[i]);

                if (quantity.get()[i] < 0) {

                    productCart.setQuantity(1);

                    session.setAttribute("errorShoppingCart", "Số lượng phải lớn hơn 0");
                    return "redirect:/shopping-cart";
                }

                if (quantity.get()[i] == 0) {
                    cart.remove(key.get()[i]);
                }

                productCart.setQuantity(quantity.get()[i]);

            }
        }

        if (cart == null || cart.isEmpty()) {
            session.removeAttribute("cart");
            session.removeAttribute("totalPrice");
            session.removeAttribute("count");
        }

        for (Map.Entry<Integer, CartModel> entry : cart.entrySet()) {
            totalPrice = totalPrice + entry.getValue().getQuantity() * entry.getValue().getProduct().getPrice();
        }

        session.setAttribute("cart", cart);
        session.setAttribute("totalPrice", totalPrice);

        return "redirect:/shopping-cart";
    }

}
