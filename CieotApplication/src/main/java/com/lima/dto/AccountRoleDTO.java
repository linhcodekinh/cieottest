package com.lima.dto;

public class AccountRoleDTO {
	private RoleDTO role;

	public AccountRoleDTO() {

	}

	public AccountRoleDTO(RoleDTO role) {
		super();
		this.role = role;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

}
