package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.ApiResponse;
import com.capgemini.model.User;
import com.capgemini.persistence.UserRepository;

/**
 * Service that use users repository
 * @author GTD-G03A
 * 
 */
@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	/**
	 * Get a users group from repository
	 * @param id Id to search users group
	 * @return Users group found by Id in an Optional container
	 */
	public Optional<User> get(Long id) {
		return repository.findById(id);
	}
	
	/**
	 * Get the list of users from repository
	 * @return All users in a List collection
	 */
	public List<User> list() {
		return repository.findAll(); 
	}
	
	/**
	 * Create a new user in repository
	 * @param user New user to save in repository
	 * @return User created
	 */
	public User create(User user) {
		return repository.save(user);
	}

	/**
	 * Delete a user 
	 * @param id Id of user to delete
	 */
	public void deleteById (Long id) {
		repository.deleteById(id);
	}
	
	/**
	 * Method to login a user in app
	 * @param user User object to login in app
	 * @return ApiResponse for login process
	 */
    public ApiResponse login(User user) {
		
        User u = repository.findByUsername(user.getLogin());
        
        if(u==null){
            return new ApiResponse(402, "Login mismatch.",null);
        } 
        else if(!u.getPassword().equals(user.getPassword())) {
        	return new ApiResponse(401, "Password mismatch.",null);
        }
        return new ApiResponse(200, "Login success", null) ;
    }

}
