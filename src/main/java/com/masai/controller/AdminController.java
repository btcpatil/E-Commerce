package com.masai.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.masai.dto.ProductDTO;
import com.masai.model.Category;
import com.masai.service.CategoryService;
import com.masai.service.ProductService;

@Controller
public class AdminController {

	@Autowired
	CategoryService catService;
	@Autowired
	ProductService proService;
	
	@GetMapping("/admin")
	public String getAdmin() {
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getAdminCategory(Model model) {
		
		model.addAttribute("categories", catService.getAllCategory());
		
		return "categories";
	}
	
	@GetMapping("admin/categories/add")
	public String getAddCategory(Model model) {
		
		model.addAttribute("category", new Category());
		
		return "categoriesAdd";
	}
	
	@PostMapping("admin/categories/add")
	public String postCategory(@ModelAttribute("category") Category category ) {
		
		catService.addCategory(category);
		
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable Integer id) {

		catService.deleteCategory(id);
	
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable Integer id, Model model) {
	    Optional<Category> catOpt = catService.updateCategory(id);
	    if(catOpt.isPresent()) {
	    	model.addAttribute("category",catOpt.get());
	    	return "categoriesAdd";
	    }
	    else {
	    	return "404";
	    }
	}
	
	
	//product_page section
	
	@GetMapping("/admin/products")
	public String getAllProducts(Model model) {
		
		model.addAttribute("products", proService.getAllProduct());
		
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String addProductGet(Model model) {
		
		model.addAttribute("productDTO", new ProductDTO());
		
		model.addAttribute("categories", catService.getAllCategory());
		
		return "productsAdd";
	}
	
}
