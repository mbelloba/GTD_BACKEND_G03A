package com.capgemini.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.model.User;
import com.capgemini.model.UsersGroup;

public interface UsersGroupRepository extends JpaRepository<UsersGroup, Long>  {
	
	

}
