package com.masai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long>{

	List<Product> findAllByCategory_id(Integer categoryId);
	
}
