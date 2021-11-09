package com.capgemini.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Class controller to process all incoming requests relative to users in groups
 * @author GTD-G03A
 *
 */
@Controller
@Tag(name="User Group", description="User Group API")
@RequestMapping("/usersgroup")
public class UsersGroupController {
	
	private static final Logger log = LoggerFactory.getLogger(UsersGroupController.class);
	
	@Autowired
	private UsersGroupService service;
	
	/**
	 * Gets all users groups of repository from a GET request
	 * @return HTTP response with all users groups in body, and OK HTTP status
	 */
	@GetMapping
	@Operation(summary="Get all groups of users")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Successfully getted all groups of users",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "UserGroup not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> findAll(){
		log.debug("Entering get all usersgroups endpoint");
		try {
			log.info("UsersGroup getted");
			return new ResponseEntity<>(service.list() ,HttpStatus.OK);
		} catch (Exception e) {
			log.error("Unable to get UsersGroup, message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/**
	 * Create a new users group in repository from POST request
	 * @param usersgroup Group of users to be created
	 * @return HTTP response with users group created in body, and OK HTTP status
	 */
	@PostMapping("/")
	@Operation(summary="Save new group of users")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Success, the group has been saved",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> save(@RequestBody UsersGroup usersGroup){
		log.debug("Entering create usersgroup endpoint");
		try {
			service.create(usersGroup);
			log.info("New usersGroup created");
			return new ResponseEntity<>(service.create(usersGroup),HttpStatus.OK);
		} catch (Exception e) {
			log.error("Unable to create UsersGroup, message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Get a determined users group from a GET request
	 * @param id Id of users group to be retrieved 
	 * @return HTTP response with users group found in repository
	 * @throws ResourceNotFoundException Exception in case users group do not exist
	 */
	@GetMapping("/{id}")
	@Operation(summary="Get a single group by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Successfully getted the group by id",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "UserGroup not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<UsersGroup> getUsersGroup(@PathVariable(name = "id") Long id) throws ResourceNotFoundException {
		log.debug("Entering get a usersgroup by id endpoint");
		try {
			UsersGroup usersGroup = service.get(id).orElseThrow(() -> new ResourceNotFoundException("Group of users not found with id: " + id));
			log.info("UsersGroup with id: " + id + " getted");
			return ResponseEntity.ok().body(usersGroup);
		} catch (Exception e) {
			log.error("Unable to get UsersGroup with id: " + id + ", message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Delete a determined users group from a DELETE request
	 * @param id Id of users group to be deleted
	 * @return HTTP response with OK status
	 * @throws ResourceNotFoundException Exception in case users group do not exist
	 */
	@DeleteMapping("/{id}")
	@Operation(summary="Delete a group by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Success, the group has been deleted",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "UserGroup not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> deleteById(@PathVariable Long id){
		log.debug("Entering delete usersgroup endpoint");
		try {
			service.get(id).orElseThrow(() -> new ResourceNotFoundException("Group of users not found with id: " + id));
			service.deleteById(id); 
			log.info("UsersGroup with id: " + id + " was deleted");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			log.error("Unable to delete UsersGroup with id: " + id + ", message: " + e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
