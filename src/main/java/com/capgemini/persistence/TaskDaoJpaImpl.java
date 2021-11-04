package com.capgemini.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.capgemini.model.Task;

/**
 * * Implementation of Task DAO Interface
 * @author gtd-g03
 *
 */
@Repository("taskDaoImpl")
@Transactional
public class TaskDaoJpaImpl implements TaskDao {
	
	private EntityManager entityManager;
	
	/**
	 * Establish manager for entity operations
	 * @param entityManager
	 */
	@Override
	public Task findById(Long id) {
		return entityManager.find(Task.class, id);
	}

	/**
	 * Add new Task to DB
	 * @param Task Task to be added
	 */
	@Override
	public void add(Task task) {
		this.entityManager.persist(task);
	}
	
	/**
	 * Get a list of all Task entities from the DataStore
	 * @return List of Categories
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findAll() {
		String query = "from Task";
		return entityManager.createQuery(query).getResultList();
	}
}
