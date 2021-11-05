package com.capgemini.service;

import java.sql.Date;
import java.util.Calendar;
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
	 * Get all tasks from inbox category from a GET request
	 * @return A list of tasks with inbox category
	 */
	public List<Task> getInbox() {
		return repository.findTaskByCategoryId(1L);
	}
	
	/**
	 * Get all tasks planned for today from a GET request
	 * @return A list of tasks planned for current date
	 */
	public List<Task> getToday() {
		return repository.findTaskToday();
	}
	
	/**
	 * Get a list of tasks planned within a week from a GET request
	 * @return A list os tasks planned for incoming week
	 */
	public List<Task> getWeek() {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, 6);
		Date postDate = (Date) date.getTime();
		return repository.findTaskByWeek(postDate);
	}
	
}
