package com.lima.dto;

import java.util.List;

public class AccountDTO {
	private Long id;
	private String userName;
	private String isEnabled;
	private String email;
	private List<AccountRoleDTO> accountRoleList;

	public AccountDTO() {

	}

	public AccountDTO(Long id, String userName, String isEnabled, String email, List<AccountRoleDTO> accountRoleList) {
		super();
		this.id = id;
		this.userName = userName;
		this.isEnabled = isEnabled;
		this.email = email;
		this.accountRoleList = accountRoleList;
	}

	public List<AccountRoleDTO> getAccountRoleList() {
		return accountRoleList;
	}

	public void setAccountRoleList(List<AccountRoleDTO> accountRoleList) {
		this.accountRoleList = accountRoleList;
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
