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

import com.capgemini.model.Category;
import com.capgemini.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Class controller to process all incoming requests relative to categories
 * @author GTD-G03A
 *
 */
@RestController
@Tag(name="Category", description="Category API")
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService	service;
	
	/**
	 * Gets all categories of repository from a GET request
	 * @return HTTP response with all tasks in body, and OK http status
	 */
	@GetMapping
	@Operation(summary="Get all categories")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Successfully getted all categories",
						 content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "List of categories not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> findAll(){
		return new ResponseEntity<>(service.list() ,HttpStatus.OK);

	}
	
	/**
	 * Get a determined category from a GET request
	 * @param id Id of category to be retrieved 
	 * @return HTTP response category found in repository
	 * @throws ResourceNotFoundException Exception in case category do not exist
	 */
	@GetMapping("/{id}")
	@Operation(summary="Get a single category by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Successfully getted the category by id",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "Category not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> getCategory(@PathVariable(name = "id") Long id) throws ResourceNotFoundException{
		Category category = service.get(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
		return ResponseEntity.ok().body(category);
	}
	
	/**
	 * Create a new category in repository from POST request
	 * @param category Category to be created
	 * @return HTTP response with category created in body, and ok http status
	 */
	@PostMapping
	@Operation(summary="Save new category")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Success, the category has been saved",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")})
	})
	public  ResponseEntity<?> save(@RequestBody Category category) {
		return new ResponseEntity<>(service.create(category),HttpStatus.OK);

	}
	
	
	/**
	 * Delete a determined category from a DELETE request
	 * @param id Id of category to be deleted
	 * @return HTTP response with OK status
	 * @throws ResourceNotFoundException Exception in case category do not exist
	 */
	@DeleteMapping("/{id}")
	@Operation(summary="Delete a category by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Success, the category has been deleted",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "Category not found",
		     content= {@Content(mediaType = "application/json")})
	})
	public ResponseEntity<?> deleteById(@PathVariable Long id) throws ResourceNotFoundException{
		service.get(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
		service.deleteById(id); 
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	/**
	 * Update (or create) a category a from a PUT request
	 * @param id Id of category to be updated/created
	 * @param categoryDetails Category object with updated properties
	 * @return HTTP response with updated category
	 * @throws ResourceNotFoundException Exception in case category do not exist
	 */
	@PutMapping("/{id}")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",
					     description = "Success, the category has been updated",
					     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "400",
		     description = "Bad request",
		     content= {@Content(mediaType = "application/json")}),
			@ApiResponse(responseCode = "404",
		     description = "Category not found",
		     content= {@Content(mediaType = "application/json")})
	})
	@Operation(summary="Update a category by id")
	public ResponseEntity<?> updateCategory(@PathVariable(name = "id") Long id, @RequestBody Category categoryDetails) throws ResourceNotFoundException {
		
		Category category = service.get(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));

		category.setName(categoryDetails.getName());
		category.setUser(categoryDetails.getUser());
				
		final Category updatedCategory = service.create(category);
		return ResponseEntity.ok(updatedCategory);
		
		
	}

}
	
		
	

