package com.capgemini.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.model.User;
import com.capgemini.model.UserStatus;
import com.capgemini.persistence.UserDao;
import com.capgemini.persistence.UserRepository;

import validators.UserValidator;

/**
 * Class for all user requests
 * @author gtd-g03
 *
 */
@Controller
public class UserController {

	@Autowired 
	@Qualifier("userDaoImpl")
	private UserDao userDao;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Bind special web parameters to objects
	 * @param binder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new UserValidator());
	}

	/**
	 * Map web request for url: /
	 * @return View for home app
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		ModelAndView mv = new ModelAndView("home", "user", new User());
		mv.addObject("serverTime", formattedDate);

		return mv;
	}

	/**
	 * Map web request for url : /user
	 * @param user User to ibe inserted in DB
	 * @param result Allow validation
	 * @param model View model passed
	 * @return String with view name to display
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String addUser(@Validated User user, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "home";
		}

		userRepository.save(user);

		return "redirect:users";
	}

	/**
	 * Map web resquest for url: /users
	 * @param model Model passed
	 * @return String with view name to display
	 */
	@RequestMapping("/users")
	public String usersList(Model model) {
		model.addAttribute("users", userDao.findAll());
		return "/users";
	}

	/**
	 * Map web request for url: /delete/{id}
	 * @param id Id of User to delete from DB
	 * @param model Model passed
	 * @return String with view name to display
	 */
	@RequestMapping(value= "/delete/{id}", method = RequestMethod.POST)
	public String deleteUser(@PathVariable("id") String id, Model model) {
		long idParsed = Long.parseLong(id);  
		userDao.deleteById(idParsed);
		model.addAttribute("users", userDao.findAll());
		return "/deleted";
	}

	/**
	 * Map web request for url: /block/{id}
	 * @param id Id of user blocked in the application
	 * @param user Model of user
	 * @param model Model passed
	 * @return String with view name to display
	 */
	@RequestMapping(value= "/block/{id}",method = RequestMethod.POST) 
	public String blockUser(@PathVariable("id") String id, @ModelAttribute User
			user, Model model) {
		user = userDao.findById(user.getId());

		if(user.getStatus() == UserStatus.ENABLED) {
			userDao.findById(user.getId());
			user.setStatus(UserStatus.DISABLED);

		} else { 
			user.setStatus(UserStatus.ENABLED); 
		} 
		userRepository.save(user);
		model.addAttribute("users", userDao.findAll());

		return "/blocked"; 
	}

}
