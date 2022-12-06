package com.masai.service;

import java.util.List;
import java.util.Optional;

import com.masai.model.Category;

public interface CategoryService {

	public void addCategory(Category category);
	
	public List<Category> getAllCategory();
	
	public void deleteCategory(Integer id);
	
	public Optional<Category> getCategoryById(Integer id);
	
}
