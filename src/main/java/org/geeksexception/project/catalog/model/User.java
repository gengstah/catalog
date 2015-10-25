package org.geeksexception.project.catalog.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.geeksexception.project.catalog.enums.UserRole;
import org.geeksexception.project.catalog.enums.UserStatus;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "USER")
public class User implements Serializable {
	
	private static final long serialVersionUID = -8574945399998006362L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name = "USER_ID", nullable = false)
	private Long id;
	
	@Column(name = "USERNAME", nullable = false)
	@NotEmpty(message = "username must not be empty")
	@Size(min = 5, message = "username must consist of at least 5 characters")
	private String username;
	
	@Column(name = "PASSWORD", nullable = false)
	@NotEmpty(message = "password must not be empty")
	@Size(min = 8, message = "password must consist of at least 8 characters")
	private String password;
	
	@Column(name = "FIRSTNAME", nullable = false)
	@NotEmpty(message = "firstName must not be empty")
	private String firstName;
	
	@Column(name = "LASTNAME", nullable = false)
	@NotEmpty(message = "lastName must not be empty")
	private String lastName;
	
	@Column(name = "EMAIL", nullable = false)
	@NotEmpty(message = "Email must not be empty")
	@Email(regexp = "[A-Za-z0-9._%+-]+@(?:[A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}", message = "Email is invalid")
	private String email;
	
	@Column(name = "STATUS", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserStatus status;
	
	@Column(name = "ROLE", nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole role;
	
	public User() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	
}