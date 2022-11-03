package com.pruebaclean.demoCl.auth.domain.entities;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class User {
	private final String REGEX_PATTERN ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

	private Long id;
	private String username;
	private String email;
	private String password;
	private Set<Role> roles = new HashSet<>();

	public User() {
	}
	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public boolean usernameIsValid(){
		return username != null && username.length() >= 3 && username.length() <= 20 ;
	}
	public boolean passwordIsValid(){
		return password != null && password.length() >= 5 && password.length() <= 40 ;
	}

	public boolean emailIsValid(){
		return Pattern.compile(REGEX_PATTERN)
				.matcher(email)
				.matches();
	}
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
}