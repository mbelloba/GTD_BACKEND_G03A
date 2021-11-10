package com.capgemini.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class CategoryControllerTest {
	

	@InjectMocks
	private CategoryController categoryController;
	
	@Test
	public void categoryControllerCodeOk200() {
		ResponseEntity<?> httpResponse = categoryController.findAll();

		Assert.assertEquals(httpResponse.getStatusCode(), HttpStatus.OK);
	}
	
	@Test
	public void categoryControllerNotNull() {
		ResponseEntity<?> httpResponse = categoryController.findAll();

		Assert.assertNotNull(httpResponse.getBody());
	}
		
	
	
}
