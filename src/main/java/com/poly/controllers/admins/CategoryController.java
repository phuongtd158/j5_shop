package com.poly.controllers.admins;

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

import com.poly.entities.Category;
import com.poly.mappers.CategoryMapper;
import com.poly.models.CategoryModel;
import com.poly.services.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryMapper mapper;;

	@GetMapping("index")
	public String index(Model model) {

		List<Category> listCategories = this.categoryService.findAllActive();
		model.addAttribute("listCategories", listCategories);

		return "/admin/category/index";
	}

	@GetMapping("create")
	public String create(@ModelAttribute("categoryModel") CategoryModel categoryModel) {
		return "/admin/category/create";
	}

	@PostMapping("store")
	public String store(@Valid CategoryModel categoryModel, BindingResult result) {

		Category category = this.mapper.convertToEntity(categoryModel);

		if (result.hasErrors()) {
			return "/admin/category/create";
		}

		category.setName(categoryModel.getName());
		category.setStatus(1);

		this.categoryService.save(category);

		return "redirect:/admin/category/index";
	}

	@GetMapping("edit/{id}")
	public String edit(Model model, @PathVariable("id") Category category) {

		CategoryModel categoryModel = this.mapper.convertToDTO(category);
		model.addAttribute("categoryModel", categoryModel);

		return "/admin/category/edit";
	}

	@PostMapping("update/{id}")
	public String update(Model model, @PathVariable("id") Integer id, @Valid CategoryModel categoryModel,
			BindingResult result) {

		Category category = this.categoryService.getById(id);

		if (result.hasErrors()) {
			return "/admin/category/edit";
		}

		category.setName(categoryModel.getName());

		this.categoryService.save(category);

		return "redirect:/admin/category/index";
	}

	@GetMapping("delete/{id}")
	public String delete(@PathVariable("id") Integer id) {

		Category category = this.categoryService.getById(id);

		category.setStatus(0);

		this.categoryService.save(category);

		return "redirect:/admin/category/index";
	}
}
