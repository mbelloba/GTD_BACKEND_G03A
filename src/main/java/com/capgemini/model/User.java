package com.capgemini.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
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

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * This class provides the model for User entity. This model is mapped to 'user' table in DB. 
 * @author GTD-G03A
 *
 */
@Entity
@Table(name="user")
public class User {

	@Schema(
			description="User unique identifier ID",
			example = "1",
			required = true
		   )
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	
	@Schema(
			description="User mail",
			example = "user@gmail.com"
		   )
	private String email;
	
	@Schema(
			description="User admin identifier",
			example = "true"
		   )
	private boolean isAdmin;
	
	@Schema(
			description="User login",
			example = "User 1"
		   )
	private String login;
	
	@Schema(
			description="User password",
			example = "Password123"
		   )
	private String password;
	
	@Schema(
			description="User status",
			example = "ENABLED"
		   )
	@Enumerated(EnumType.STRING)
	private UserStatus status = UserStatus.ENABLED;
	
	@Schema(
			description="User groups list",
			example = "List of user groups..."
		   )
	@ManyToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="groupId")
	private List<UsersGroup> usersGroup;

	@Schema(
			description="User tasks list",
			example = "List of tasks..."
		   )
	@OneToMany(cascade= {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="id")
	private List<Task> tasks;

	/**
	 * No args constructor
	 */
	public User() {	}

	/**
	 * All args constructor
	 * @param email Email of user
	 * @param isAdmin Boolean flag to set if user is admin or not
	 * @param login unique user name in app
	 * @param password Password for user account
	 * @param status Field to indicate if user account is enabled or disabled
	 */
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
