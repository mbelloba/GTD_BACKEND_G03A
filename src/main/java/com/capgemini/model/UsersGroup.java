package com.capgemini.model;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This class provides the model for Users group entity. This model is mapped to 'usersgroup' table in DB. 
 * @author GTD-G03A
 *
 */
@Entity
@Table(name="usersgroup")
public class UsersGroup {

	
	@Schema(
			description="UsersGroup unique  identifier id",
			example = "1",
			required = true
		   )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long groupId;
	
	@Schema(
			description="UsersGroup name",
			example = "Group 1"
		   )
	public String name;
	
	@Schema(
			description="UsersGroup description",
			example = "Description of the group"
		   )
	public String description;
	
	@Schema(
			description="Group admin",
			example = "User (admin) properties"
		   )
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="adminId")
	public User admin;
	
	@Schema(
			description="Creation date of the group",
			example = "2021-11-09"
		   )
	public Date creationDate;
	
	@Schema(
			description="UserGroup users list",
			example = "List of users of the group..."
		   )
	@ManyToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="userId")
	public List<User> users;
	
	@Schema(
			description="UserGroup tasks list",
			example = "List of tasks of the group"
		   )
	@OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="id")
	public List<Task> tasks;

		
	public Long getId() {
		return groupId;
	}
	public void setId(Long id) {
		this.groupId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	@Override
	public int hashCode() {
		return Objects.hash(admin, creationDate, description, groupId, name, users);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsersGroup other = (UsersGroup) obj;
		return Objects.equals(admin, other.admin) && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(description, other.description) && Objects.equals(groupId, other.groupId)
				&& Objects.equals(name, other.name) && Objects.equals(users, other.users);
	}


}
