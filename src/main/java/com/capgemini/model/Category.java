package com.capgemini.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class that specifies Category properties
 * @author gtd-g03
 *
 */
@Entity
@Table(name = "TCATEGORIES")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private User user;
	
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


}
