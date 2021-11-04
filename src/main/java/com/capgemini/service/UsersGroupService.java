package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.User;
import com.capgemini.model.UsersGroup;
import com.capgemini.persistence.UserRepository;
import com.capgemini.persistence.UsersGroupRepository;

@Service
public class UsersGroupService {
	
	@Autowired
	private UsersGroupRepository repository;
	
	/**
	 * 
	 * @param id
	 * @return user by id
	 */
	
	public Optional<UsersGroup> get(Long id) {
		return repository.findById(id);
	}
	
	/**
	 * 
	 * @return all users in database
	 */

	public List<UsersGroup> list() {
		List<UsersGroup> groups = (List<UsersGroup>) repository.findAll(); 
		return groups;
	}
	
	/**
	 * 
	 * @param user
	 * @return Create user in database
	 */

	public UsersGroup create(UsersGroup group) {
		return repository.save(group);
	}

	/**
	 * 
	 * @param id, delete by ID
	 */
	public void deleteById (Long id) {
		repository.deleteById(id);
	}
	

}
