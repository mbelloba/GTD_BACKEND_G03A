package com.capgemini.controller;

import java.security.Principal;

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

import com.capgemini.model.ApiResponse;
import com.capgemini.model.User;
import com.capgemini.service.UserService;

/**
 * Class controller to process all incoming requests relative to users
 * @author GTD-G03A
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {


	@Autowired
	private UserService service;

	/**
	 * Gets all users of repository from a GET request
	 * @return HTTP response with all users in body, and OK HTTP status
	 */
	@GetMapping
	public ResponseEntity<?> findAll(Principal pricipal){
		return new ResponseEntity<>(service.list() ,HttpStatus.OK);

	}
	
	/**
	 * Create a new user in repository from POST request
	 * @param user User to be created
	 * @return HTTP response with user created in body, and OK HTTP status
	 */
	@PostMapping
	public ResponseEntity<?> save(@RequestBody User user){
		service.create(user);
		return new ResponseEntity<>(service.create(user),HttpStatus.OK);

	}

	/**
	 * Get a determined user from a GET request
	 * @param id Id of user to be retrieved 
	 * @return HTTP response with user found in repository
	 * @throws ResourceNotFoundException Exception in case user do not exist
	 */
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
		User user = service.get(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		return ResponseEntity.ok().body(user);
	}

	/**
	 * Delete a determined user from a DELETE request
	 * @param id Id of user to be deleted
	 * @return HTTP response with OK status
	 * @throws ResourceNotFoundException Exception in case user do not exist
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		service.get(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		service.deleteById(id); 
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * Update (or create) a user a from a PUT request
	 * @param id Id of user to be updated/created
	 * @param userDetails User object with updated properties
	 * @return HTTP response with updated user
	 * @throws ResourceNotFoundException Exception in case user do not exist
	 */
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
		return service.get(id)
				.map(user -> {
					user.setEmail(newUser.getEmail());
					user.setLogin(newUser.getLogin());
					user.setPassword(newUser.getPassword());
					return service.create(user);
				})
				.orElseGet(() -> {
					newUser.setId(id);
					return service.create(newUser);
				});
	}

	/**
	 * Method to login to App
	 * @param user User credentials to login
	 * @return ApiResponse with result of login process
	 */
	@PostMapping("/login")
	public ApiResponse login(@RequestBody User user){
		return service.login(user);
	}


}
