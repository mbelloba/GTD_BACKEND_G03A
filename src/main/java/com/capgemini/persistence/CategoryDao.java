package com.capgemini.persistence;

import java.util.List;

import com.capgemini.model.Category;

/**
 * Interface for Category Data Access Object
 * @author gtd-g03
 *
 */
public interface CategoryDao {
	
	/**
	 * Add new Category to DB
	 * @param category Category to be added
	 */
	public void add(Category category);
	
	/**
	 * Get a Category entity from the data store
	 * @param id Id of Category to find
	 * @return Category find in the data store
	 */
	public Category findById(Long id);
	
	/**
	 * Get a list of all Category entities from the DataStore
	 * @return List of Categories
	 */
	public List<Category> findAll();

}
