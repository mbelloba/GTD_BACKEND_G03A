package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.ApiResponse;
import com.capgemini.model.LoginDto;
import com.capgemini.model.User;
import com.capgemini.persistence.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	/**
	 * 
	 * @param id
	 * @return user by id
	 */
	
	public Optional<User> get(Long id) {
		return repository.findById(id);
	}
	
	/**
	 * 
	 * @return all users in database
	 */

	public List<User> list() {
		List<User> users = (List<User>) repository.findAll(); 
		return users;
	}
	
	/**
	 * 
	 * @param user
	 * @return Create user in database
	 */

	public User create(User user) {
		return repository.save(user);
	}

	/**
	 * 
	 * @param id, delete by ID
	 */
	public void deleteById (Long id) {
		repository.deleteById(id);
	}
	
	
	
	/**
	 * 
	 * @param user
	 * @return login method
	 */
	
	
    public ApiResponse login(User user) {
		
        User u = repository.findByUsername(user.getLogin());
        
        if(u==null){
            return new ApiResponse(402, "Login mismatch.",null);
        }
        else if(!u.getPassword().equals(user.getPassword())){
            return new ApiResponse(401, "Password mismatch.",null);
        }
        return new ApiResponse(200, "Login success", null) ;

    }
    
	
	

}
