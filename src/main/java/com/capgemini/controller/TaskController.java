package com.capgemini.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.capgemini.model.Category;
import com.capgemini.model.Task;
import com.capgemini.service.CategoryService;
import com.capgemini.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Class controller to process all incoming requests relative to tasks
 * @author GTD-G03A
 *
 */
@RestController
@Tag(name="Task", description="Task API")
@RequestMapping("/task")
public class TaskController {
	
	private static final Logger log = LoggerFactory.getLogger(TaskController.class);
	
	@Autowired
	private TaskService service;
	
	@Autowired 
	CategoryService catService;
	
	/**
	 * Gets all tasks of repository from a GET request
	 * @return HTTP response with all tasks in body, and OK HTTP status
	 */
	@GetMapping
	@Operation(summary="Get all tasks")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Successfully getted all tasks",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "Task not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> findAll() {
		log.debug("Entering get all tasks endpoint");
		try {
			log.info("Tasks getted");
			return new ResponseEntity<>(service.list(), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Unable to get tasks, message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Create a new task in repository from POST request
	 * @param task Task to be created
	 * @return HTTP response with task created in body, and OK HTTP status
	 */
	@PostMapping
	@Operation(summary="Save new task")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Success, the task has been saved",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> save(@RequestBody Task task) {
		log.debug("Entering get a task by id endpoint");
		try {
			log.info("New task created");
			return new ResponseEntity<>(service.create(task), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Unable to create task, message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	/**
	 * Get a determined task from a GET request
	 * @param id Id of task to be retrieved 
	 * @return HTTP response task found in repository
	 * @throws ResourceNotFoundException Exception in case task do not exist
	 */
	@GetMapping("/{id}")
	@Operation(summary="Get a single task by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Successfully getted the task by id",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "Task not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> getTask(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
		log.debug("Entering create task endpoint");
		try {
			Task task = service.get(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
			log.info("Task with id: " + id + " getted");
			return ResponseEntity.ok().body(task);
		} catch (Exception e) {
			log.error("Unable to get task with id: " + id + ", message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Delete a determined task from a DELETE request
	 * @param id Id of task to be deleted
	 * @return HTTP response with OK status
	 * @throws ResourceNotFoundException Exception in case task do not exist
	 */
	@DeleteMapping("/{id}")
	@Operation(summary="Delete a task by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Success, the task has been deleted",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "Task not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> deleteById(@PathVariable Long id) throws ResourceNotFoundException {
		log.debug("Entering delete task endpoint");
		try {
			service.get(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));
			service.deleteById(id); 
			log.info("Task with id: " + id + " was deleted");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("Unable to delete task with id: " + id + ", message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update (or create) a task a from a PUT request
	 * @param id Id of task to be updated/created
	 * @param taskDetails Task object with updated properties
	 * @return HTTP response with updated task
	 * @throws ResourceNotFoundException Exception in case task do not exist
	 */
	@PutMapping("/{id}")
	@Operation(summary="Update a task by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Success, the task has been updated",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "Task not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> updateTask(@PathVariable(name = "id") Long id, @RequestBody Task taskDetails) throws ResourceNotFoundException {
		log.debug("Entering update task endpoint");
		try {
			Task task = service.get(id).orElseThrow(() -> new ResourceNotFoundException("Task not found with id: " + id));

			task.setTitle(taskDetails.getTitle());
			task.setComments(taskDetails.getComments());
			task.setCreated(taskDetails.getCreated());
			task.setPlanned(taskDetails.getPlanned());
			task.setFinished(taskDetails.getFinished());
			task.setUsers(taskDetails.getUsers());
			task.setCategory(taskDetails.getCategory());
			
			final Task updatedTask = service.create(task);
			log.info("Task with id: " + id + " was updated");
			return ResponseEntity.ok(updatedTask);
		} catch (Exception e) {
			log.error("Unable to update task with id: " + id + ", message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	/**
	 * Get the list of tasks in inbox category from a GET request
	 * @return List of tasks in inbox category
	 */
	@GetMapping("/inbox")
	@Operation(summary="Get tasks in inbox category")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Successfully getted the tasks in inbox category",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "Task not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> getInbox() {
		log.debug("Entering get inbox tasks endpoint");
		try {
			log.info("Tasks from inbox category getted");
			return new ResponseEntity<>(service.listInbox(), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Unable to get inbox tasks, message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Get the list of tasks planned for current date from a GET request
	 * @return HTTP response with all tasks planned for today in body, and OK HTTP status
	 */
	@GetMapping("/today")
	@Operation(summary="Get tasks planned for current date")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Successfully getted the tasks planned for today",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "Task not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> getToday() {
		log.debug("Entering get today tasks endpoint");
		try {
			log.info("Today Tasks getted");
			return new ResponseEntity<>(service.listToday(), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Unable to get today tasks, message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Get the list of tasks planned within a week from a GET request
	 * @return HTTP response with all tasks planned within a week, and a OK HTTP status 
	 */
	@GetMapping("/week")
	@Operation(summary="Get tasks planned within a week")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Successfully getted the tasks planned within a week",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "Task not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> getWeek() {
		log.debug("Entering get week tasks endpoint");
		try {
			Calendar date = Calendar.getInstance();
			date.add(Calendar.DATE, 6);
			Date postDate = (Date) date.getTime();
			log.info("Week Tasks getted");
			return new ResponseEntity<>(service.listWeek(postDate), HttpStatus.OK);
		} catch (Exception e) {
			log.error("Unable to get week tasks, message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Get a list of tasks list grouped by category from a GET request
	 * @return A list of tasks list grouped by category
	 */
	@GetMapping("/categories")
	@Operation(summary="Get tasks listed by category")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Successfully getted the tasks listed by category",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "Task not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> getListByCategories() {
		log.debug("Entering get categories tasks endpoint");
		try {
			List<Category> categories = catService.list();
			List<List<Task>> tasks = new ArrayList<List<Task>>();
			for (Category category : categories) {
				tasks.add(service.listByCategory(category.getId()));
			}
			log.info("Categories Tasks getted");
			return new ResponseEntity<List<?>>(tasks, HttpStatus.OK);
		} catch (Exception e) {
			log.error("Unable to get categories tasks, message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
