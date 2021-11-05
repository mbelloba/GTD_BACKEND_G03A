package com.capgemini.persistence;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Category;
import com.capgemini.service.CategoryService;

/**
 * Implementation of Category DAO Interface
 * @author gtd-g03
 *
 */
@Service
@Transactional
public class CategoryDaoJpaImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	/**
	 * Add new Category to DB
	 * @param category Category to be added
	 */
	@Override
	public Category add(Category category) {
		return categoryRepository.save(category);
	}

	/**
	 * Get a Category entity from de datastore
	 * @param id Id of Category to find
	 * @return Category find in the datastore
	 */
	@Override
	public Category findById(Long id) {
		Optional<Category> subOptional = categoryRepository.findById(id);
		return subOptional.isPresent()?subOptional.get():null;
	}

	/**
	 * Get a list of all Category entities from the DataStore
	 * @return List od Categories
	 */
	@Override
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	
	public void deleteById(Long id) {
		Optional<Category> category = categoryRepository.findById(id);
		if (category.isPresent()) {
			categoryRepository.deleteById(id);
		}
		
	}

	@Override
	public Category updateById(Long id, Category category) {
		if(categoryRepository.existsById(id)) {
			categoryRepository.save(category);
		}
		return category;	
	}

}
