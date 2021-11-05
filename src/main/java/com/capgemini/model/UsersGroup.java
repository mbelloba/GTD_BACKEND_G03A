package com.capgemini.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.RestResource;

@Entity
@Table(name="user_group")
@RestResource(rel="userGroups", path="userGroup")
public class UsersGroup {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String name;
	public String descripction;
	@OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="id")
	public User admin;
	public Date creationDate;
	@ManyToMany(targetEntity= User.class,cascade= {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="id")
	public List<User> users;


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
	@Override
	public int hashCode() {
		return Objects.hash(admin, creationDate, descripction, id, name, users);
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
				&& Objects.equals(descripction, other.descripction) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(users, other.users);
	}



}
