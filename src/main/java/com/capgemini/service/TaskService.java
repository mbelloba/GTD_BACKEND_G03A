package com.capgemini.service;

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
}
