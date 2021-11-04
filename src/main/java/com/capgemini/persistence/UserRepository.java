package com.capgemini.persistence;

import org.springframework.data.repository.CrudRepository;

import com.capgemini.model.User;

/**
 * User repository that extends of JpaRepository
 * @author gtd-g03
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {

}
