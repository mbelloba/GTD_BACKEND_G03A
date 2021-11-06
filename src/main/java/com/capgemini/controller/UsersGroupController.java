package com.capgemini.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.model.UsersGroup;
import com.capgemini.service.UsersGroupService;

/**
 * Class controller to process all incoming requests relative to users in groups
 * @author GTD-G03A
 *
 */
@Controller
@RequestMapping("/usersgroup")
public class UsersGroupController {
	
	@Autowired
	private UsersGroupService service;
	
	/**
	 * Gets all users groups of repository from a GET request
	 * @return HTTP response with all users groups in body, and OK HTTP status
	 */
	@GetMapping
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(service.list() ,HttpStatus.OK);

	}
	
	/**
	 * Create a new users group in repository from POST request
	 * @param usersgroup Group of users to be created
	 * @return HTTP response with users group created in body, and OK HTTP status
	 */
	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody UsersGroup usersGroup){
		service.create(usersGroup);
		return new ResponseEntity<>(service.create(usersGroup),HttpStatus.OK);

	}

	/**
	 * Get a determined users group from a GET request
	 * @param id Id of users group to be retrieved 
	 * @return HTTP response with users group found in repository
	 * @throws ResourceNotFoundException Exception in case users group do not exist
	 */
	@GetMapping("/{id}")
	public ResponseEntity<UsersGroup> getUsersGroup(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
		UsersGroup usersGroup = service.get(id).orElseThrow(() -> new ResourceNotFoundException("Group of users not found with id: " + id));
		return ResponseEntity.ok().body(usersGroup);
	}

	/**
	 * Delete a determined users group from a DELETE request
	 * @param id Id of users group to be deleted
	 * @return HTTP response with OK status
	 * @throws ResourceNotFoundException Exception in case users group do not exist
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		service.get(id).orElseThrow(() -> new ResourceNotFoundException("Group of users not found with id: " + id));
		service.deleteById(id); 
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
