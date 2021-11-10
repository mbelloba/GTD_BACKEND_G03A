package com.capgemini.persistence;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.capgemini.model.Task;

/**
 * Task repository that extends of JpaRepository
 * Implements custom queries over data store
 * @author GTD-G03A
 *
 */
public interface TaskRepository extends JpaRepository<Task, Long>, CrudRepository<Task, Long> {
	
	/**
	 * Find all tasks from a determined category
	 * @param id Id of category
	 * @return List of tasks within this category
	 */
	@Query("SELECT t FROM Task AS t WHERE t.category.id = ?1")
	List<Task> findTaskByCategoryId(Long id);
	
	/**
	 * Find all tasks planned for current date and in ascending order
	 * @return List of tasks that match criteria
	 */
	@Query("SELECT t FROM Task AS t WHERE t.planned <= current_date ORDER BY t.planned asc")
	List<Task> findTaskToday();
	
	/**
	 * Find all tasks planned for week in course ordered by date and category
	 * @param postDate Date until search criteria
	 * @return List of tasks that match criteria
	 */
	@Query("SELECT t FROM Task AS t WHERE t.planned <= ?1 AND t.planned >= current_date ORDER BY t.planned, t.category.id asc")
	List<Task> findTaskByWeek(Date postDate);
	
	/**
	 * List of all categories in data store
	 * @return List of all categories
	 */
	@Query("SELECT Distinct t.id FROM Task AS t ORDER BY t.id")
	List<Long> findAllIdCategories();
	
	/**
	 * Task specified by a param id
	 * @param id Id of task to search
	 * @return Task with match criteria
	 */
	@Query("SELECT t FROM Task AS t WHERE t.id = ?1")
	Task findTaskById(Long id);
		
	/**
	 * Delete a task from DataStore
	 * @param id Id of task to erase
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM Task t WHERE t.id = ?1")
	void deleteTaskById(Long id);
	
	
}
