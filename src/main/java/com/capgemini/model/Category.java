package com.capgemini.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;


/**
 * This class provides the model for Category entity. This model is mapped to 'category' table in DB. 
 * @author GTD-G03A
 *
 */
@Entity
@Table(name="category")
public class Category {
	
	@Schema(
			description="Category unique identifier id",
			example = "1",
			required = true
			)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Schema(
			description="Category name",
			example = "Category 1"
		   )
	@Column
	private String name;
	
	@Schema(
			description="Owner of the category",
			example = "User properties..."
		   )
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
	@Schema(
			description="Category task list",
			example = "List of tasks..."
		   )
	@OneToMany(mappedBy = "category", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	private List<Task> tasks;
	
	
	/**
	 * Constructor with no-args
	 */
	public Category() { }

	
	/**
	 * Constructor for Category
	 * @param id for Category
	 * @param name of Category
	 * @param user User associated with this category
	 * @param tasks List of tasks in this category
	 */
	public Category(Long id, String name, User user, List<Task> tasks) {
		super();
		this.id = id;
		this.name = name;
		this.user = user;
		this.tasks = tasks;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, name, user, tasks);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(user, other.user) && Objects.equals(tasks, other.tasks);
	}
	
}
