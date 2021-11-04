package com.capgemini.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService	categoryService;
	
	/**
	 * Map web request for url: /category/list
	 * @return View with a list of categories
	 */
	@GetMapping
	public ResponseEntity<?> getCategories(Principal principal){
		return new ResponseEntity<>(categoryService.findAll() ,HttpStatus.OK);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) throws ResourceNotFoundException{
		return new ResponseEntity<>(categoryService.findById(id),HttpStatus.OK);
	}
	
	/**
	 * Map web request for url: /category/post 
	 * Use POST method from a form
	 * @param category Category to be inserted in DB
	 * @return String with view to display
	 */
	@PostMapping
	public  ResponseEntity<?> addCategory(@RequestBody Category category, @Validated Principal principal) {
		return new ResponseEntity<>(categoryService.add(category),HttpStatus.OK);

	}
	
	/**
	 * Map web request for url: /category/new
	 * @return View with a new category form
	 */
	/*@GetMapping(value = "/new")
	public ResponseEntity<?> newCategory() {
		return new ResponseEntity<>("categoryform","category",new Category()) ,HttpStatus.OK);
	}
	*/
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) throws ResourceNotFoundException{
		categoryService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody Category category){
		
		return new ResponseEntity <>(categoryService.updateById(id, category),HttpStatus.OK);
		/*return categoryService.findById(id)
				.map(category -> {
				student.setName(newStudent.getName());
				student.setSurname(newStudent.getSurname());
				student.setCourse(newStudent.getCourse());
				student.setBirthdate(newStudent.getBirthdate());
				student.setCourse(newStudent.getCourse());
				return service.save(student);
				})
				.orElseGet(() -> {
				newStudent.setCod_stud(id);
				return service.save(newStudent);
				});
		*/		
	}

}
	
		
	

