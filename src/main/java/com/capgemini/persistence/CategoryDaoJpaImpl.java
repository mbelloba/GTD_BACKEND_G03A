package com.capgemini.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.Category;

/**
 * Implementation of Category DAO Interface
 * @author gtd-g03
 *
 */
@Repository("categoryDaoImpl")
@Transactional
public class CategoryDaoJpaImpl implements CategoryDao {
	
	private EntityManager entityManager;
	
	/**
	 * Establish manager for entity operations
	 * @param entityManager
	 */
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	/**
	 * Add new Category to DB
	 * @param category Category to be added
	 */
	@Override
	public void add(Category category) {
		entityManager.persist(category);
	}

	/**
	 * Get a Category entity from de datastore
	 * @param id Id of Category to find
	 * @return Category find in the datastore
	 */
	@Override
	public Category findById(Long id) {
		return entityManager.find(Category.class, id);
	}

	/**
	 * Get a list of all Category entities from the DataStore
	 * @return List od Categories
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		String query = "from Category";
		return entityManager.createQuery(query).getResultList();
	}

}
