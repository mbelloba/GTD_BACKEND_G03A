package com.capgemini.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.capgemini.model.User;

/**
 * User repository that extends of JpaRepository
 * @author gtd-g03
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
	
	/**
	 * Find user from a determined login
	 * @param login user login
	 * @return user
	 */
	@Query("SELECT u FROM User u WHERE u.login = ?1")
	User findByUsername(String login);

}
