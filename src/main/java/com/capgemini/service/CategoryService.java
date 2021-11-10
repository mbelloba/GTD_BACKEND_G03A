package com.capgemini.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.model.Category;
import com.capgemini.persistence.CategoryRepository;
import com.capgemini.service.CategoryService;

/**
 * Service that use category repository
 * @author GTD-G03A
 * 
 */
@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	/**
	 * Get a category from repository
	 * @param id Id to search category
	 * @return Category found by Id in an Optional container
	 */
	public Optional<Category> get(Long id) {
		return repository.findById(id);
	}
	
	/**
	 * Get the list of categories from repository
	 * @return All categories in a List collection
	 */
	public List<Category> list() {
		return repository.findAll();
	}

	/**
	 * Create a new category in repository
	 * @param category New category to save in repository
	 * @return Category created
	 */
	public Category create(Category category) {
		return repository.save(category);
	}
	
	/**
	 * Delete a category 
	 * @param id Id of category to delete
	 */
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	
	/**
	 * Update a category
	 * @param id Id of category to update
	 * @param category Category object to update
	 * @return Category updated
	 */
	public Category updateCategory(Long id, Category category) {
		if(repository.existsById(id)) {
			return repository.save(category);
		}else {
			return null;	
		}
	}

}
