 package com.masai.service;

import java.util.List;
import java.util.Optional;

import com.masai.model.Product;

public interface ProductService {

	public List<Product> getAllProduct();
	
	public void addProduct(Product product);
	
	public void deleteProductById(Long productId);
	
	public Optional<Product> getProductById(Long productId);
	
	public List<Product> getAllProductsByCategoryId(Integer categoryId);
	
}
