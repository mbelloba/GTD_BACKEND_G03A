package com.capgemini.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.model.Category;
import com.capgemini.persistence.CategoryDao;
import com.capgemini.persistence.CategoryRepository;

/**
 * Class for all category requests
 * @author gtd-g03
 *
 */
@Controller
@RequestMapping("/category")
public class CategoryController {
	
	@Autowired 
	@Qualifier("categoryDaoImpl")
	private CategoryDao categoryDao;
	
	@Autowired 
	private CategoryRepository categoryRepository;
	
	/**
	 * Map web request for url: /category/list
	 * @return View with a list of categories
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getCategories() {
		ModelAndView mv = new ModelAndView();
		List<Category> categories = categoryRepository.findAll();
		mv.addObject("categories", categories);
		mv.setViewName("categories");
		return mv;
	}
	
	/**
	 * Map web request for url: /category/new
	 * @return View with a new category form
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newCategory() {
		ModelAndView mv = new ModelAndView("categoryform", "category", new Category());
		return mv;
	}
	
	/**
	 * Map web request for url: /category/post 
	 * Use POST method from a form
	 * @param category Category to be inserted in DB
	 * @return String with view to display
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addCategory(@Validated Category category) {
		categoryRepository.save(category);
		return "redirect:list";	
	}

}
