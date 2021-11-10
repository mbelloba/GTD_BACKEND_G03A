package com.capgemini.controller;

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

import com.capgemini.model.User;
import com.capgemini.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Class controller to process all incoming requests relative to users
 * @author GTD-G03A
 *
 */
@RestController
@Tag(name="User", description="User API")
@RequestMapping("/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService service;

	/**
	 * Gets all users of repository from a GET request
	 * @return HTTP response with all users in body, and OK HTTP status
	 */
	@GetMapping
	@Operation(summary="Get all users")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Successfully getted all users",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "User not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> findAll(){
		log.debug("Entering get all users endpoint");
		try {
			log.info("Users getted");
			return new ResponseEntity<>(service.list() ,HttpStatus.OK);
		} catch (Exception e) {
			log.error("Unable to get users, message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Create a new user in repository from POST request
	 * @param user User to be created
	 * @return HTTP response with user created in body, and OK HTTP status
	 */
	@PostMapping
	@Operation(summary="Save new user")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Success, the user has been saved",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> save(@RequestBody User user){
		log.debug("Entering create user endpoint");
		try {
			log.info("New user created");
			return new ResponseEntity<>(service.create(user),HttpStatus.OK);
		} catch (Exception e) {
			log.error("Unable to create user, message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Get a determined user from a GET request
	 * @param id Id of user to be retrieved 
	 * @return HTTP response with user found in repository
	 * @throws ResourceNotFoundException Exception in case user do not exist
	 */
	@GetMapping("/{id}")
	@Operation(summary="Get a single user by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Successfully getted the user by id",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "User not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
		log.debug("Entering get a user by id endpoint");
		try {
			User user = service.get(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
			log.info("User with id: " + id + " getted");
			return ResponseEntity.ok().body(user);
		} catch (Exception e) {
			log.error("Unable to get user with id: " + id + ", message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Delete a determined user from a DELETE request
	 * @param id Id of user to be deleted
	 * @return HTTP response with OK status
	 * @throws ResourceNotFoundException Exception in case user do not exist
	 */
	@DeleteMapping("/{id}")
	@Operation(summary="Delete an user by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Success, the user has been deleted",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "User not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		log.debug("Entering delete user endpoint");
		try {
			service.get(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
			service.deleteById(id); 
			log.info("User with id: " + id + " was deleted");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("Unable to delete user with id: " + id + ", message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Update (or create) a user a from a PUT request
	 * @param id Id of user to be updated/created
	 * @param newUser User object with updated properties
	 * @return HTTP response with updated user
	 * @throws ResourceNotFoundException Exception in case user do not exist
	 */
	@PutMapping("/{id}")
	@Operation(summary="Update an user by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Success, the user has been updated",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "User not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public User updateUser(@RequestBody User newUser, @PathVariable Long id) {
		log.debug("Entering update user endpoint");
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
	@Operation(summary="Log of an user")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Success, the user has been logged",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "User not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public com.capgemini.model.ApiResponse login(@RequestBody User user){
		return service.login(user);
	}


}
