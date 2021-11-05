package com.capgemini.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.model.Category;


@Service
public interface CategoryService {
	

	public Category add(Category category);
	
	
	public Category findById(Long id);
	

	public List<Category> findAll();


	public void deleteById(Long id);


	public Object updateById(Long id, Category category);
	
}
