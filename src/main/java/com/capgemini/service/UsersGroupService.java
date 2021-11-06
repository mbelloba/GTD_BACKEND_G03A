package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.UsersGroup;
import com.capgemini.persistence.UsersGroupRepository;

/**
 * Service that use users group repository
 * @author GTD-G03A
 * 
 */
@Service
public class UsersGroupService {
	
	@Autowired
	private UsersGroupRepository repository;
	
	/**
	 * Get a users group from repository
	 * @param id Id to search users group
	 * @return UsersGroup found by Id in an Optional container
	 */
	public Optional<UsersGroup> get(Long id) {
		return repository.findById(id);
	}
	
	/**
	 * Get the list of users groups from repository
	 * @return All users groups in a List collection
	 */
	public List<UsersGroup> list() {
		List<UsersGroup> groups = (List<UsersGroup>) repository.findAll(); 
		return groups;
	}
	
	/**
	 * Create a new users group in repository
	 * @param group New users group to save in repository
	 * @return UsersGroup created
	 */
	public UsersGroup create(UsersGroup group) {
		return repository.save(group);
	}

	/**
	 * Delete a users group 
	 * @param id Id of users group to delete
	 */
	public void deleteById (Long id) {
		repository.deleteById(id);
	}
	

}
