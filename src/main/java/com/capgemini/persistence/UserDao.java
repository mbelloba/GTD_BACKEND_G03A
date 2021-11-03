package com.capgemini.persistence;

import java.util.List;

import com.capgemini.model.User;

/**
 * Interface for User Data Access Object
 * @author gtd-g03
 *
 */
public interface UserDao {
	
	/**
	 * Add new User to DB
	 * @param user User to be added
	 */
	public void add(User user);
	
	/**
	 * Get a User entity from the data store
	 * @param id Id of User to find
	 * @return User find in the data store
	 */
	public User findById(Long id);
	
	/**
	 * Get a list of all User entities from the DataStore
	 * @return List of Users
	 */
	public List<User> findAll();
	
	/**
	 * Delete a User from de DataStore
	 * @param id Id of User entity to delete
	 */
	public void deleteById (Long id);
	
	/**
	 * Delete User entity
	 * @param entity Entoty to delete
	 */
	public void delete (User entity);

}
