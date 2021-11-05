package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.Task;
import com.capgemini.service.TaskService;

/**
 * Class controller to process all incoming requests relative to tasks
 * @author GTD-G03A
 *
 */
@RestController
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	/**
	 * Gets all tasks of repository from a GET request
	 * @return HTTP response with all tasks in body, and OK http status
	 */
	@GetMapping("/")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(service.list(), HttpStatus.OK);
	}
	
	/**
	 * Create a new task in repository from POST request
	 * @param task Task to be created
	 * @return HTTP response with task created in body, and ok http status
	 */
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody Task task) {
		return new ResponseEntity<>(service.create(task), HttpStatus.OK);		
	}
	
	/**
	 * Get a determined task from a GET request
	 * @param id Id of task to be retrieved 
	 * @return HTTP response task found in repository
	 * @throws ResourceNotFoundException Exception in case task do not exist
	 */
	@GetMapping("/{id}")
	public ResponseEntity<?> getTask(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
		Task task = service.get(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
		return ResponseEntity.ok().body(task);
	}
	
	/**
	 * Delete a determined task from a DELETE request
	 * @param id Id of task to be deleted
	 * @return HTTP response with OK status
	 * @throws ResourceNotFoundException Exception in case task do not exist
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
		service.get(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
		service.deleteById(id); 
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Update (or create) a task a from a PUT request
	 * @param id Id of task to be updated/created
	 * @param taskDetails Task object with updated properties
	 * @return HTTP response with updated task
	 * @throws ResourceNotFoundException Exception in case task do not exist
	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> updateTask(@PathVariable(name = "id") Long id, @RequestBody Task taskDetails) throws ResourceNotFoundException {
		Task task = service.get(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

		task.setTitle(taskDetails.getTitle());
		task.setComments(taskDetails.getComments());
		task.setCreated(taskDetails.getCreated());
		task.setPlanned(taskDetails.getPlanned());
		task.setFinished(taskDetails.getFinished());
		task.setUser(taskDetails.getUser());
		task.setCategory(taskDetails.getCategory());
		
		final Task updatedTask = service.create(task);
		return ResponseEntity.ok(updatedTask);	
	}
	
	/**
	 * Get all tasks from inbox category from a GET request
	 * @return A list of tasks with inbox category
	 */
	@GetMapping("/inbox")
	public ResponseEntity<?> getInbox() {
		return new ResponseEntity<>(service.getInbox(), HttpStatus.OK);
	}
	
	/**
	 * Get all tasks planned for today from a GET request
	 * @return A list of tasks planned for current date
	 */
	@GetMapping("/today")
	public ResponseEntity<?> getToday() {
		return new ResponseEntity<>(service.getToday(), HttpStatus.OK);
	}
	
	/**
	 * Get a list of tasks planned within a week from a GET request
	 * @return A list os tasks planned for incoming week
	 */
	@GetMapping("/week")
	public ResponseEntity<?> getWeek() {
		return new ResponseEntity<>(service.getWeek(), HttpStatus.OK);
	}

}
