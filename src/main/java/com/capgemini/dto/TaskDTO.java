package com.capgemini.dto;

import java.sql.Date;

import com.capgemini.model.Category;
import com.capgemini.model.User;

public class TaskDTO {
	public Long id;
	public String title;
	public Date created;
	public Date planned;
	public boolean finished;
	private User user;
	private Category category;

}
