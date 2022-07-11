package com.poly.controllers.users;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.entities.Account;
import com.poly.entities.Order;
import com.poly.entities.OrderDetail;
import com.poly.models.AccountModel;
import com.poly.models.ProfileModel;
import com.poly.services.AccountService;
import com.poly.services.OrderService;
import com.poly.utilities.EncryptUtils;
import com.poly.utilities.UploadFileUtils;

import net.bytebuddy.utility.RandomString;

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
    private HttpServletRequest request;

    @Autowired
    private EncryptUtils encryptUtils;

    @Autowired
    private UploadFileUtils uploadFileUtils;

    private JavaMailSender mailSender;

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
                return "redirect:/login";
            } else {

                session.setAttribute("account", account);

                if (account.getAdmin() == 1) {
                    return "redirect:/admin/home";
                } else {
                    return "redirect:/home";
                }
            }

        } else {
            session.setAttribute("errorPassword", "Sai tên tài khoản hoặc mật khẩu");
            return "redirect:/login";
        }
    }

    @GetMapping("order-history")
    public String orderHistory(Model model) {

        Account account = (Account) session.getAttribute("account");

        System.out.println(account.getUsername());

        List<Order> listOrders = this.orderService.findAllByAccountId(account.getId());

        model.addAttribute("listOrders", listOrders);

        model.addAttribute("view", "/views/user/order-history.jsp");
        return "/user/index";
    }

    @GetMapping("order-detail/{id}")
    public String orderDetail(Model model, @PathVariable("id") Integer id) {

        Optional<Order> orderOptional = this.orderService.findById(id);

        if (!orderOptional.isPresent()) {
            session.setAttribute("errorOrder", "Hóa đơn không tồn tại");
            return "redirect:/order-history";
        }

        List<OrderDetail> listOrderDetails = orderOptional.get().getOrderDetails();

        session.removeAttribute("errorOrder");
        model.addAttribute("listOrderDetails", listOrderDetails);
        model.addAttribute("view", "/views/user/order-detail.jsp");
        return "/user/index";
    }

    @GetMapping("cancel-order/{id}")
    public String cancelOrder(Model model, @PathVariable("id") Integer id) {

        Optional<Order> order = this.orderService.findById(id);
        Account accountSession = (Account) session.getAttribute("account");

        if (!order.isPresent()) {
            session.setAttribute("errorOrder", "Đơn hàng không tồn tại");
            return "redirect:/order-history";
        }

        if (order.get().getStatus() != 0) {
            session.setAttribute("errorOrder", "Chỉ có thể hủy các đơn hàng có trạng thái là chờ xác nhận");
            if (order.get().getAccountById().getId() != accountSession.getId()) {
                session.setAttribute("errorOrder", "Đơn hàng không tồn tại");
            }
            return "redirect:/order-history";
        }

        order.get().setStatus(2);
        this.orderService.save(order.get());

        return "redirect:/order-history";
    }

    @GetMapping("change-password")
    public String getChangePasswordForm(Model model) {

        model.addAttribute("view", "/views/user/change-password.jsp");
        return "/user/index";
    }

    @PostMapping("change-password")
    public String changePassword(Model model, @RequestParam("old-password") String oldPassword,
                                 @RequestParam("new-password") String newPassword) {

        Account accountSession = (Account) session.getAttribute("account");
        Account account = this.accountService.getById(accountSession.getId());


        boolean check = this.encryptUtils.check(oldPassword, accountSession.getPassword());

        if (!check) {
            session.setAttribute("errorChangePassword", "Mật khẩu cũ không đúng");
            return "redirect:/change-password";
        }

        newPassword = this.encryptUtils.encrypt(newPassword);
        account.setPassword(newPassword);

        this.accountService.save(account);

        model.addAttribute("message", "Change password successfully");
        model.addAttribute("view", "/views/user/message.jsp");
        return "/user/index";
    }

    @GetMapping("forgot-password")
    public String getFormForgotPassword(Model model) {

        model.addAttribute("view", "/views/user/forgot-password.jsp");
        return "/user/index";
    }

    @PostMapping("forgot-password")
    public String forgotPassword(Model model, @RequestParam("email") String email) {

        String token = RandomString.make(45);

        String url = this.request.getRequestURL().toString().replace(this.request.getServletPath(), "");

        try {
            this.accountService.updateResetPassword(token, email);

            String resetPasswordLink = url + "/reset-password?token=" + token;

            sendEmail(email, resetPasswordLink);

            session.setAttribute("mess", "Reset password link has been sent to your email");
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("view", "/views/user/forgot-password.jsp");
        return "/user/index";
    }

    private void sendEmail(String email, String resetPasswordLink)
            throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("tdphuong2002@gmail.com", "Support");
        helper.setTo(email);

        String subject = "Here's the link to reset your password.";
        String content = "<p>Hello, </p>" + "<p>You have requested to reset password</p>"
                + "<p>Click the link below to change your password:</p>" + "<a href=\"" + resetPasswordLink
                + "\">Change my password</a>";
        helper.setSubject(subject);
        helper.setText(content, true);

        this.mailSender.send(message);
    }

    @GetMapping("reset-password")
    public String getFormResetPassword(Model model, @Param("token") String token) {

        Account account = this.accountService.get(token);

        if (account == null) {
            model.addAttribute("message", "Invalid token");
            model.addAttribute("view", "/views/user/message.jsp");
            return "/user/index";
        }

        model.addAttribute("token", token);
        model.addAttribute("view", "/views/user/reset-password.jsp");
        return "/user/index";
    }

    @PostMapping("reset-password")
    public String resetPassword(Model model, @RequestParam("password") String password,
                                @RequestParam("token") String token) {

        Account account = this.accountService.get(token);

        if (account == null) {
            model.addAttribute("message", "Invalid token");

        } else {
            this.accountService.updatePassword(account, password);
            model.addAttribute("message", "Change password successfully");
        }

        model.addAttribute("view", "/views/user/message.jsp");
        return "/user/index";
    }

    @GetMapping("profile")
    public String editProfile(Model model, @ModelAttribute("accountModel") ProfileModel accountModel) {

        Account account = (Account) session.getAttribute("account");

        model.addAttribute("account", account);
        model.addAttribute("view", "/views/user/profile.jsp");
        return "/user/index";
    }

    @PostMapping("update-profile")
    public String updateProfile(Model model, @Valid @ModelAttribute("accountModel") ProfileModel accountModel,
                                BindingResult result) {

        Account accountSession = (Account) session.getAttribute("account");
        Account account = this.accountService.getById(accountSession.getId());

        if (result.hasErrors()) {
            model.addAttribute("view", "/views/user/profile.jsp");
            return "/user/index";
        }

        if (!accountModel.getImageFile().isEmpty()) {
            account.setPhoto(uploadFileUtils.uploadFile(accountModel.getImageFile()));
        }

        account.setEmail(accountModel.getEmail());
        account.setFullName(accountModel.getFullname());
        account.setUsername(accountModel.getUsername());
        account.setAdmin(accountSession.getAdmin());
        account.setPassword(accountSession.getPassword());
        account.setActivated(accountSession.getActivated());

        this.accountService.save(account);

        session.setAttribute("account", account);

        return "redirect:/profile";
    }

    @GetMapping("logout")
    public String logout() {
        if (session != null) {
            session.removeAttribute("account");
        }
        return "redirect:/home";
    }

}
