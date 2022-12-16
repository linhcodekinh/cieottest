package com.lima.payload.response;

import java.util.List;

import com.lima.entity.Member;

public class JwtLoginResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String userName;
	private List<String> roles;
	private Member member;

	public JwtLoginResponse() {

	}

	public JwtLoginResponse(String token, Long id, String userName, List<String> roles, Member member) {
		super();
		this.token = token;
		this.id = id;
		this.userName = userName;
		this.roles = roles;
		this.member = member;
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

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
