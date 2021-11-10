package com.capgemini.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.Task;
import com.capgemini.persistence.TaskRepository;

/**
 * Service that use task repository
 * @author GTD-G03A
 * 
 */
@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repository;
	
	
	/**
	 * Get a task from repository
	 * @param id Id to search task
	 * @return Task found by Id in an Optional container
	 */
	public Optional<Task> get(Long id) {
		return repository.findById(id);
	}
	
	/**
	 * Get the list of tasks from repository
	 * @return All tasks in a List collection
	 */
	public List<Task> list() {
		return repository.findAll();
	}
	
	/**
	 * Create a new task in repository
	 * @param task New task to save in repository
	 * @return Task created
	 */
	public Task create(Task task) {
		return repository.save(task);
	}
	
	/**
	 * Delete a task 
	 * @param id Id of task to delete
	 */
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
	
	/**
	 * Get the list of task from inbox category (we assume id category is 1)
	 * @return The list of task in inbox category
	 */
	public List<Task> listInbox() {
		return repository.findTaskByCategoryId(1L);
	}
	
	/**
	 * Get the list of tasks which planned date is the current date
	 * @return List of tasks planned for today
	 */
	public List<Task> listToday() {
		return repository.findTaskToday();
	}
	
	/**
	 * Get the list of tasks which planned date it´s within a week
	 * @param postDate Date until tasks have to be listed 
	 * @return List of tasks with date criteria
	 */
	public List<Task> listWeek(Date postDate) {
		return repository.findTaskByWeek(postDate);
	}
	
	/**
	 * Get a list of tasks in a category
	 * @param id Id of category 
	 * @return List of tasks in category
	 */
	public List<Task> listByCategory(Long id) {
		return repository.findTaskByCategoryId(id);
	}
}
