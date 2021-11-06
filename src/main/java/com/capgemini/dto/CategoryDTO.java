package com.capgemini.dto;

import java.io.Serializable;
import java.util.List;

import com.capgemini.model.Task;
import com.capgemini.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CategoryDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@JsonProperty("id")
	public Long id;
	@JsonProperty("name")
	public String name;
	@JsonProperty("user")
	public User user;
	@JsonProperty("tasks")
	public List<Task> tasks;
	
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

}
