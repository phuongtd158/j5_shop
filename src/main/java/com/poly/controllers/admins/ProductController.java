package com.poly.controllers.admins;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@Autowired
	private HttpServletRequest request;

	@GetMapping("index")
	public String index(Model model, @RequestParam(name = "p", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "10") Integer size) {

		List<Product> listProducts;
		Sort sort = Sort.by(Direction.ASC, "id");
		int countProducts = this.productService.findAllActive().size();
		String sortBy = this.request.getParameter("sort_by");

		if (sortBy != null) {
			sort = Sort.by(Direction.ASC, sortBy);
			
			listProducts = this.productService.findAllActive(sort);
		}else {
			listProducts = this.productService.findAllActive();
		}

		Pageable pageable = PageRequest.of(page, size, sort);
		Page<Product> data = this.productService.findAllActive(pageable);

		model.addAttribute("listProducts", data);
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
	public String store(Model model, @Valid ProductModel productModel, BindingResult result) {

		Product product = this.mapper.convertToEntity(productModel);
		Category category = this.categoryService.getById(productModel.getCategoryById().getId());
		List<Category> listCategories = this.categoryService.findAllActive();
		Date currentDate = new Date(System.currentTimeMillis());

		if (result.hasErrors()) {
			model.addAttribute("listCategories", listCategories);
			return "/admin/product/create";
		}

		if (!productModel.getImageFile().isEmpty()) {
			product.setImage(uploadFileUtils.uploadFile(productModel.getImageFile()));
		}

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

		if (!productModel.getImageFile().isEmpty()) {
			product.setImage(uploadFileUtils.uploadFile(productModel.getImageFile()));
		}

		product.setAvailable(productModel.getAvailable());
		product.setCategoryById(category);
		product.setCreateDate(product.getCreateDate());
		product.setName(productModel.getName());
		product.setPrice(productModel.getPrice());
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
