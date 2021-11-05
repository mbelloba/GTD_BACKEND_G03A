package com.capgemini.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.model.UsersGroup;

public interface UsersGroupRepository extends JpaRepository<UsersGroup, Long>  {
	
	

}
