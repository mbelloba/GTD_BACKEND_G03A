package com.capgemini.persistence;

import java.util.List;

import com.capgemini.model.Task;

/**
 * Interface for Task Data Access Object
 * @author gtd-g03
 *
 */
public interface TaskDao {
	
	/**
	 * Add new Task to DB
	 * @param Task Task to be added
	 */
	public void add(Task task);
	
	/**
	 * Get a Task entity from the data store
	 * @param id Id of Task to find
	 * @return Task find in the data store
	 */
	public Task findById(Long id);
	
	/**
	 * Get a list of all Task entities from the DataStore
	 * @return List of Categories
	 */
	public List<Task> findAll();

}
