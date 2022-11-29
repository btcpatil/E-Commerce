package com.masai.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.model.Category;
import com.masai.repository.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryRepo catRepo;
	
	@Override
	public void addCategory(Category cat) {
		
		catRepo.save(cat);
		
	}

	@Override
	public List<Category> getAllCategory() {
		
	return catRepo.findAll();
	}

	@Override
	public void deleteCategory(Integer id) {
		
		catRepo.deleteById(id);
		
	}

	@Override
	public Optional<Category> updateCategory(Integer id) {
		
	 return	catRepo.findById(id);
		
	}

}
