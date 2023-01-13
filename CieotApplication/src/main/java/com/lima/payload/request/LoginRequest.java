package com.lima.payload.request;

import javax.validation.constraints.NotEmpty;

public class LoginRequest {

	@NotEmpty(message = "Thiếu username")
	private String username;
	
	@NotEmpty(message = "Thiếu password")
	private String password;

	public LoginRequest() {

	}

	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
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

}
