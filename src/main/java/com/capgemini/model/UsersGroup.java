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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="groups")
public class UsersGroup {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long groupId;
	public String name;
	public String descripction;
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="userId")
	public User admin;
	public Date creationDate;
	@ManyToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name="userId")
	public List<User> users;


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
		return Objects.hash(admin, creationDate, descripction, groupId, name, users);
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
				&& Objects.equals(descripction, other.descripction) && Objects.equals(groupId, other.groupId)
				&& Objects.equals(name, other.name) && Objects.equals(users, other.users);
	}



}
