package com.poly.controllers.admins;

import com.poly.entities.Account;
import com.poly.mappers.AccountMapper;
import com.poly.models.AccountModel;
import com.poly.services.AccountService;
import com.poly.utilities.EncryptUtils;
import com.poly.utilities.UploadFileUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountMapper accountMapper;

	@Autowired
	private UploadFileUtils uploadFileUtils;

	@Autowired
	private EncryptUtils encryptUtils;

	@GetMapping("index")
	public String index(Model model) {
		List<Account> listAccounts = this.accountService.findAllActive();

		model.addAttribute("listAccounts", listAccounts);

		return "/admin/account/index";
	}

	@GetMapping("create")
	public String create(@ModelAttribute("accountModel") AccountModel accountModel) {
		return "/admin/account/create";
	}

	@PostMapping("store")
	public String store(@Valid AccountModel accountModel, BindingResult result) {

		Account account = this.accountMapper.convertToEntity(accountModel);
		String passwordEncrypt = encryptUtils.encrypt(accountModel.getPassword());

		if (result.hasErrors()) {
			return "/admin/account/create";
		}

		if (!accountModel.getImageFile().isEmpty()) {
			account.setPhoto(uploadFileUtils.uploadFile(accountModel.getImageFile()));
		}

		account.setActivated(accountModel.getActivated());
		account.setAdmin(accountModel.getAdmin());
		account.setEmail(accountModel.getEmail());
		account.setFullName(accountModel.getFullname());
		account.setPassword(passwordEncrypt);
		account.setUsername(accountModel.getUsername());

		this.accountService.save(account);

		return "redirect:/admin/account/index";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Account account) {
		AccountModel accountModel = this.accountMapper.convertToDTO(account);
		model.addAttribute("accountModel", accountModel);
		return "/admin/account/edit";
	}

	@PostMapping("update/{id}")
	public String update(@PathVariable("id") Integer id, @Valid AccountModel accountModel, BindingResult result) {

		Account account = this.accountService.getById(id);

		if (result.hasErrors()) {
			return "/admin/account/edit";
		}

		if (!accountModel.getImageFile().isEmpty()) {
			account.setPhoto(uploadFileUtils.uploadFile(accountModel.getImageFile()));
		}

		account.setActivated(accountModel.getActivated());
		account.setAdmin(accountModel.getAdmin());
		account.setEmail(accountModel.getEmail());
		account.setFullName(accountModel.getFullname());
		account.setPassword(account.getPassword());
		account.setUsername(accountModel.getUsername());

		this.accountService.save(account);

		return "redirect:/admin/account/index";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

		Account account = this.accountService.getById(id);

		account.setActivated(0);

		this.accountService.save(account);

		return "redirect:/admin/account/index";
	}
}
