package com.lima.payload.response;

import java.util.List;

import com.lima.entity.Account;

public class JwtLoginResponse {
	private String token;
	private String type = "Bearer";
	private Integer id;
	private String userName;
	private List<String> roles;
	private Account account;

	public JwtLoginResponse() {

	}

	public JwtLoginResponse(String token, Integer id, String userName, List<String> roles, Account account) {
		super();
		this.token = token;
		this.id = id;
		this.userName = userName;
		this.roles = roles;
		this.account = account;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
