package com.lima.payload.response;

import java.util.List;

import com.lima.entity.Member;

public class JwtAuthenticateResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String userName;
	private List<String> roles;

	public JwtAuthenticateResponse() {

	}

	public JwtAuthenticateResponse(String token, Long id, String userName, List<String> roles) {
		super();
		this.token = token;
		this.id = id;
		this.userName = userName;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
