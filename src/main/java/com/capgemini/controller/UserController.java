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
import com.capgemini.model.LoginDto;
import com.capgemini.model.User;
import com.capgemini.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {


	@Autowired
	private UserService service;

	@PostMapping("/add")
	public ResponseEntity<?> save(@RequestBody User user, Principal principal){
		service.create(user);
		return new ResponseEntity<>(service.create(user),HttpStatus.OK);

	}

	@GetMapping("/all")
	public ResponseEntity<?> findAll(Principal pricipal){
		return new ResponseEntity<>(service.list() ,HttpStatus.OK);

	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
		User user = service.get(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		return ResponseEntity.ok().body(user);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id, Principal principal){
		User user = service.get(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
		service.deleteById(id); 
		return new ResponseEntity<>(HttpStatus.OK);
	}


	
	@PostMapping("/login")
	public ApiResponse login(@RequestBody User user){
		return service.login(user);
	}
    

}
