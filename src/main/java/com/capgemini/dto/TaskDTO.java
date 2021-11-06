package com.capgemini.dto;

import java.io.Serializable;
import java.sql.Date;

import com.capgemini.model.Category;
import com.capgemini.model.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskDTO implements Serializable {
	
	@JsonProperty("id")
	public Long id;
	@JsonProperty("tittle")
	public String title;
	@JsonProperty("created")
	public Date created;
	@JsonProperty("planend")
	public Date planned;
	@JsonProperty("finished")
	public boolean finished;
	@JsonProperty("user")
	private User user;
	@JsonProperty("category")
	private Category category;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getPlanned() {
		return planned;
	}
	public void setPlanned(Date planned) {
		this.planned = planned;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	

}
