package com.poly.controllers.admins;

import java.sql.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.entities.Category;
import com.poly.entities.Product;
import com.poly.mappers.ProductMapper;
import com.poly.models.ProductModel;
import com.poly.services.CategoryService;
import com.poly.services.ProductService;
import com.poly.utilities.UploadFileUtils;

@Controller
@RequestMapping("/admin/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductMapper mapper;

	@Autowired
	private UploadFileUtils uploadFileUtils;

	@GetMapping("index")
	public String index(Model model) {

		List<Product> listProducts = this.productService.findAllActive();
		int countProducts = listProducts.size();

		model.addAttribute("listProducts", listProducts);
		model.addAttribute("countProducts", countProducts);

		return "/admin/product/index";
	}

	@GetMapping("create")
	public String create(Model model, @ModelAttribute("productModel") ProductModel productModel) {

		List<Category> listCategories = this.categoryService.findAllActive();

		model.addAttribute("listCategories", listCategories);

		return "/admin/product/create";
	}

	@PostMapping("store")
	public String store(Model model, @Valid ProductModel productModel, BindingResult result,
			@RequestParam("image") MultipartFile uploadedFile) {

		Product product = this.mapper.convertToEntity(productModel);
		Category category = this.categoryService.getById(productModel.getCategoryById().getId());
		List<Category> listCategories = this.categoryService.findAllActive();
		Date currentDate = new Date(System.currentTimeMillis());
		String imageFileName = uploadedFile.getOriginalFilename().toString();
		
		System.out.println(imageFileName);
		if (result.hasErrors()) {

			System.err.println(result.getAllErrors());
			model.addAttribute("listCategories", listCategories);
			return "/admin/product/create";
		}

		product.setImage(imageFileName);
		this.uploadFileUtils.handleUploadFile(uploadedFile);
		product.setAvailable(productModel.getAvailable());
		product.setCategoryById(category);
		product.setCreateDate(currentDate);
		product.setName(productModel.getName());
		product.setPrice(productModel.getPrice());
		product.setStatus(1);

		this.productService.save(product);

		return "redirect:/admin/product/index";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Product product) {

		ProductModel productModel = this.mapper.convertToDTO(product);
		List<Category> listCategories = this.categoryService.findAllActive();
		Category category = this.categoryService.getById(product.getCategoryById().getId());

		model.addAttribute("productModel", productModel);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("categoryId", category.getId());

		return "/admin/product/edit";
	}

	@PostMapping("update/{id}")
	public String update(Model model, @PathVariable("id") Integer id, @Valid ProductModel productModel,
			BindingResult result) {

		Product product = this.productService.getById(id);
		Category category = this.categoryService.getById(productModel.getCategoryById().getId());
		List<Category> listCategories = this.categoryService.findAllActive();

		if (result.hasErrors()) {

			model.addAttribute("listCategories", listCategories);
			model.addAttribute("categoryId", category.getId());

			return "/admin/product/edit";
		}

		product.setAvailable(productModel.getAvailable());
		product.setCategoryById(category);
		product.setCreateDate(product.getCreateDate());
		product.setName(productModel.getName());
		product.setPrice(productModel.getPrice());
		product.setImage("none");
		product.setStatus(1);

		this.productService.save(product);

		return "redirect:/admin/product/index";

	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

		Product product = this.productService.getById(id);

		product.setStatus(0);

		this.productService.save(product);

		return "redirect:/admin/product/index";
	}
}
