package com.capgemini.dto;

import java.io.Serializable;
import java.util.List;

import com.capgemini.model.UsersGroup;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO implements Serializable {
	
	@JsonProperty("userId")
	public Long userId;
	@JsonProperty("email")
	public String email;
	@JsonProperty("isAdmin")
	public boolean isAdmin;
	@JsonProperty("login")
	public String login;
	@JsonProperty("password")
	public String password;
	@JsonProperty("UsersGroup")
	public List<UsersGroup> usersGroup;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<UsersGroup> getUsersGroup() {
		return usersGroup;
	}
	public void setUsersGroup(List<UsersGroup> usersGroup) {
		usersGroup = usersGroup;
	}
	
	
	

}
