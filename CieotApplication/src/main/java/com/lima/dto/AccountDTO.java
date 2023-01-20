package com.lima.dto;

public class AccountDTO {
	private Long id;
	private String userName;
	private String isEnabled;
	private String email;

	public AccountDTO() {

	}

	public AccountDTO(Long id, String userName, String isEnabled, String email) {
		super();
		this.id = id;
		this.userName = userName;
		this.isEnabled = isEnabled;
		this.email = email;
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

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
