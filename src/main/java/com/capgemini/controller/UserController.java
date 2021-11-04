package com.capgemini.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.model.User;
import com.capgemini.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	
	@Autowired
	private UserService service;
	
	@PostMapping(path="/add")
	public ResponseEntity<?> save(@RequestBody User user, Principal principal){
		service.create(user);
		return new ResponseEntity<>(service.create(user),HttpStatus.OK);

	}
	
	@GetMapping("/all")
	public ResponseEntity<?> findAll(Principal pricipal){
		return new ResponseEntity<>(service.list() ,HttpStatus.OK);

	}
	
	@GetMapping(path="/get{id}")
	public ResponseEntity<?> getById(@PathVariable Long id, Principal principal){
		return new ResponseEntity<>(service.get(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id, Principal principal){
		service.deleteById(id); 
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
}
