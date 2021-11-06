package com.capgemini.dto;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.capgemini.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UsersGroupDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("groupId")
	public Long groupId;
	@JsonProperty("name")
	public String name;
	@JsonProperty("descripction")
	public String descripction;
	@JsonProperty("admin")
	public User admin;
	@JsonProperty("creationDate")
	public Date creationDate;
	@JsonProperty("users")
	public List<User> users;
	
	
	public Long getGroupId() {
		return groupId;
	}
	
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescripction() {
		return descripction;
	}
	public void setDescripction(String descripction) {
		this.descripction = descripction;
	}
	
	public User getAdmin() {
		return admin;
	}
	
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
