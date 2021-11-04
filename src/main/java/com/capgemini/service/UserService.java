package com.capgemini.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.model.User;
import com.capgemini.persistence.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public Optional<User> get(Long id) {
		return repository.findById(id);
	}

	public List<User> list() {
		List<User> users = (List<User>) repository.findAll(); 
		return users;
	}

	public User create(User user) {
		return repository.save(user);
	}

	public void deleteById (Long id) {
		repository.deleteById(id);
	}
	
	public boolean validateUser (User user) {

        List<User> users = (List<User>) repository.findAll();
        boolean validatedUser = false;
        for (User u:users ) {
            if(user.getPassword().equals(u.getPassword())){
                if(user.getPassword().equals(u.getPassword())){
                    validatedUser = true;

                }
            }

        }
        return validatedUser;
    }
	
	

}
