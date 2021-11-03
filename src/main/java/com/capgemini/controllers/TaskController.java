package com.capgemini.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.model.Task;
import com.capgemini.persistence.TaskDao;
import com.capgemini.persistence.TaskRepository;

/**
 * Class for all tasks request
 * @author gtd-g03
 *
 */
@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired 
	@Qualifier("taskDaoImpl")
	private TaskDao taskDao;
	
	@Autowired
	private TaskRepository taskRepository;

	/**
	 * Map web request for url: /task/list
	 * @return View with a list of tasks
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView getTasks() {
		ModelAndView mv = new ModelAndView();
		List<Task> tasks = taskRepository.findAll();
		mv.addObject("tasks", tasks);
		mv.setViewName("tasks");
		return mv;
	}
	
	/**
	 * Map web request for url: /task/new
	 * @return View with a new task form
	 */
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView newCategory() {
		ModelAndView mv = new ModelAndView("taskform", "task", new Task());
		String createdDate = obtainDate();
		mv.addObject("createdDate", createdDate);
		mv.setViewName("taskform");
		return mv;
	}
	
	/**
	 * Map web request for url: /task/create 
	 * Use POST method from a form
	 * @param task Task to be inserted in DB
	 * @return String with view to display
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String addTask(@Validated Task task) {
		taskRepository.save(task);
		return "redirect:list";	
	}
	
	/**
	 * Map web request for url: /task/save 
	 * Use POST method from a form
	 * @param task Task to be inserted in DB
	 * @return List of task in DB
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView updateTask(Task task) {
		taskRepository.save(task);
		return getTasks();	
	}
	
	/**
	 * Map web request for url: /task/edit/{id}
	 * @param id Id of task to be edited
	 * @return View with an edit task form
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editTask(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Task task = taskRepository.findTaskById(id);
		mv.addObject("task", task);
		mv.setViewName("taskeditform");
		return mv;
	}
	
	/**
	 * Map web request for url: /task/delete/{id}
	 * @param id Id of task to be deleted
	 * @return String with the View to display
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteTask(@PathVariable("id") Long id) {
		taskRepository.deleteTaskById(id);
		return "redirect:/task/list";
	}
	
	/**
	 * Map web request for url: /task/inbox
	 * @return View with a list of task in inbox category
	 */
	@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public ModelAndView getInboxTasks() {
		ModelAndView mv = new ModelAndView();
		List<Task> tasks = taskRepository.findTaskByCategoryId(1L);
		mv.addObject("tasks", tasks);
		mv.setViewName("tasks");
		return mv;
	}
	
	/**
	 * Map web request for url: /task/today
	 * @return View with a list of task which finish date is today
	 */
	@RequestMapping(value = "/today", method = RequestMethod.GET)
	public ModelAndView getTodayTasks() {
		ModelAndView mv = new ModelAndView();
		List<Task> tasks = taskRepository.findTaskToday();
		mv.addObject("tasks", tasks);
		mv.setViewName("tasks");
		return mv;
	}
	
	/**
	 * Map web request for url: /task/week
	 * @return View with a list of task which finish date is among current week
	 */
	@RequestMapping(value = "/week", method = RequestMethod.GET)
	public ModelAndView getWeekTasks() {
		ModelAndView mv = new ModelAndView();
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, 6);
		Date postDate = date.getTime();
		List<Task> tasks = taskRepository.findTaskByWeek(postDate);
		mv.addObject("tasks", tasks);
		mv.setViewName("tasks");
		return mv;
	}
	
	/**
	 * Map web request for url: /task/week
	 * @return View with a list of task categorized
	 */
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
	public ModelAndView getPlannedTasks() {
		ModelAndView mv = new ModelAndView();
		List<List<Task>> tasksListCategories = new ArrayList<List<Task>>();
		List<Long> tasksIdCat = taskRepository.findAllIdCategories();
		for (Long task : tasksIdCat) {
			tasksListCategories.add(taskRepository.findTaskByCategoryId(task));
		}
		mv.addObject("listtasks", tasksListCategories);
		mv.setViewName("listtasks");
		return mv;
	}
	
	/**
	 * Obtain the current date
	 * @return String with Day of year in yyyy-MM-dd format
	 */
	private String obtainDate() {
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		 LocalDateTime now = LocalDateTime.now();  
		 return dtf.format(now);
	}
	
}
