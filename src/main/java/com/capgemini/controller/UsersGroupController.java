package com.capgemini.controller;

import java.security.Principal;

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

import com.capgemini.model.User;
import com.capgemini.model.UsersGroup;
import com.capgemini.service.UserService;
import com.capgemini.service.UsersGroupService;

@Controller
public class UsersGroupController {
	
	@Autowired
	private UsersGroupService service;

	@PostMapping("/")
	public ResponseEntity<?> save(@RequestBody UsersGroup usersGroup, Principal principal){
		service.create(usersGroup);
		return new ResponseEntity<>(service.create(usersGroup),HttpStatus.OK);

	}

	@GetMapping("/")
	public ResponseEntity<?> findAll(Principal pricipal){
		return new ResponseEntity<>(service.list() ,HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<UsersGroup> getUsersGroup(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
		UsersGroup usersGroup = service.get(id).orElseThrow(() -> new ResourceNotFoundException("Group of users not found with id: " + id));
		return ResponseEntity.ok().body(usersGroup);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id, Principal principal){
		UsersGroup usersGroup = service.get(id).orElseThrow(() -> new ResourceNotFoundException("Group of users not found with id: " + id));
		service.deleteById(id); 
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
