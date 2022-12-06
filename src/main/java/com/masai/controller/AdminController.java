package com.masai.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.masai.dto.ProductDTO;
import com.masai.model.Category;
import com.masai.model.Product;
import com.masai.service.CategoryService;
import com.masai.service.ProductService;

@Controller
public class AdminController {
	
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

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
	    Optional<Category> catOpt = catService.getCategoryById(id);
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
	
	@PostMapping("/admin/products/add")
	public String addProductPost(@ModelAttribute("productDTO") ProductDTO productDTO,
			                     @RequestParam("productImage") MultipartFile imgFile,
			                     @RequestParam("imgName") String imgName) throws IOException {
		
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		product.setCategory(catService.getCategoryById(productDTO.getCategoryId()).get());
		
		String imgUUid;
	    if(!imgFile.isEmpty()) {
	    	imgUUid = imgFile.getOriginalFilename();
	    	Path fileNameAndPath = Paths.get(uploadDir,imgUUid);
	    	Files.write(fileNameAndPath,imgFile.getBytes());
	    }
	    else {
	    	 imgUUid = imgName;
	    }
	    product.setImageName(imgUUid); 
	    
	    proService.addProduct(product);
	    
		return "redirect:/admin/products";
	}
	
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteCategory(@PathVariable Long id) {

		proService.deleteProductById(id);
	
		return "redirect:/admin/products";
	}
	@GetMapping("/admin/product/update/{id}")
	public String updateProductGet(@PathVariable Long id, Model model) {
		
		Product product = proService.getProductById(id).get();
		ProductDTO proDto = new ProductDTO();
		proDto.setId(product.getId());
		proDto.setName(product.getName());
		proDto.setPrice(product.getPrice());
		proDto.setDescription(product.getDescription());
		proDto.setCategoryId(product.getCategory().getId());
		proDto.setWeight(product.getWeight());
		proDto.setImageName(product.getImageName());
		
		model.addAttribute("categories", catService.getAllCategory());
		model.addAttribute("productDTO",proDto);
		
		
		return "productsAdd";
	}
	
}
