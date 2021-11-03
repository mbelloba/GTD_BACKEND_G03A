package com.capgemini.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.model.User;

/**
 * * Implementation of User DAO Interface
 * @author gtd-g03
 *
 */
@Repository("userDaoImpl")
@Transactional
public class UserDaoJpaImpl implements UserDao {

	@Autowired
	private EntityManager entityManager;
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * Establish manager for entity operations
	 * @param entityManager
	 */
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
	
    /**
	 * Add new User to DB
	 * @param user User to be added
	 */
	@Override
	public void add(User user) {
		entityManager.persist(user);
	}

	/**
	 * Get a User entity from the data store
	 * @param id Id of User to find
	 * @return User find in the data store
	 */
	@Override
	public User findById(Long id) {
		return entityManager.find(User.class, id);
	}

	/**
	 * Get a list of all User entities from the DataStore
	 * @return List of Users
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		String query = "from User";
		return entityManager.createQuery(query).getResultList();
	}


	/**
	 * Delete a User from de DataStore
	 * @param id Id of User entity to delete
	 */
	public void deleteById(Long id) {
		userRepository.delete(this.findById(id));
	}

	/**
	 * Delete User entity
	 * @param entity Entoty to delete
	 */
	public void delete(User entity) {
		userRepository.delete(entity);
	}
	
	

}
