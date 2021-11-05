package com.capgemini.dto;

import java.sql.Date;
import java.util.List;

import com.capgemini.model.User;

public class UsersGroupDTO {
	public Long groupId;
	public String name;
	public String descripction;
	public User admin;
	public Date creationDate;
	public List<User> users;
	

}
