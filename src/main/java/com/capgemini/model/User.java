package com.capgemini.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Class that specifies User properties
 * @author gtd-g03
 *
 */
@Entity
@Table(name="users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String email;
	@ColumnDefault("False")
	private boolean isAdmin;
	private String login;
	private String password;
	@Enumerated(EnumType.STRING)
	private UserStatus status = UserStatus.ENABLED;
	@ManyToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="groupId")
	List<UsersGroup> UsersGroup;

	@OneToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="id")
	private List<Task> tasks;




	public User() {	}

	public User(String email, boolean isAdmin, String login, String password, UserStatus status) {
		this.email = email;
		this.isAdmin = isAdmin;
		this.login = login;
		this.password = password;
		this.status = status;
	}

	public Long getId() {
		return userId;
	}

	public void setId(Long id) {
		this.userId = id;
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

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		return Objects.hash(email, userId, isAdmin, login, password, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email)
				&& Objects.equals(userId, other.userId) && isAdmin == other.isAdmin && Objects.equals(login, other.login)
				&& Objects.equals(password, other.password) && Objects.equals(status, other.status);

	}

	@Override
	public String toString() {
		return "User [id=" + userId + ", email=" + email + ", isAdmin=" + isAdmin + ", login=" + login + ", password="
				+ password + ", status=" + status + ", ]";
	}





}
