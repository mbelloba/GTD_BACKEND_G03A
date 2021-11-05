package com.capgemini.model;

import java.sql.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

/**
 * This class provides the model for Task entity. This model is mapped to 'task' table in DB. 
 * @author GTD-G03A
 *
 */
@Entity
@Table(name="task")
@RestResource(rel="tasks", path="task")
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title;
	private String comments;
	private Date created;
	private Date planned;
	private boolean finished;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;
	
	/**
	 * No args constructor
	 */
	public Task() { }

	/**
	 * All args constructor
	 * @param title Title of taks
	 * @param comments Comments made to taks
	 * @param created Date of task creation
	 * @param planned Planned date to finish task
	 * @param finished Boolean flag to indicate if task is done or not
	 * @param user Users assigned to task
	 * @param category Category where task is assigned
	 */
	public Task(String title, String comments, Date created, Date planned, boolean finished, User user, Category category) {
		this.comments = comments;
		this.created = created;
		this.planned = planned;
		this.finished = finished;
		this.user = user;
		this.category = category;
	}

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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public boolean getFinished() {
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

	@Override
	public int hashCode() {
		return Objects.hash(category, comments, created, finished, id, planned, title, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(category, other.category) && Objects.equals(comments, other.comments)
				&& Objects.equals(created, other.created) && finished == other.finished && Objects.equals(id, other.id)
				&& Objects.equals(planned, other.planned) && Objects.equals(title, other.title)
				&& Objects.equals(user, other.user);
	}
	
	
	
}
