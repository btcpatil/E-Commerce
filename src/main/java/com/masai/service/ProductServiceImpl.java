package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Product;
import com.masai.repository.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepo productRepo;
	
	@Override
	public List<Product> getAllProduct() {
		
		return productRepo.findAll();
		
	}

	@Override
	public void addProduct(Product product) {
		
		productRepo.save(product);
		
	}

	@Override
	public void deleteProductById(Long productId) {
		
		productRepo.deleteById(productId);
		
	}

	@Override
	public Optional<Product> getProductById(Long productId) {
		
		return  productRepo.findById(productId);
	}

	@Override
	public List<Product> getAllProductsByCategoryId(Integer categoryId) {
		
		return productRepo.findAllByCategory_id(categoryId);
		
	}

}
