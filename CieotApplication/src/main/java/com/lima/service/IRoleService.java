package com.lima.service;

import java.util.List;

import com.lima.entity.Role;

public interface IRoleService {
	
	List<Role> findAllRole();

	void setDefaultRole(Long accountId, Integer roleId);

	List<Role> getAllRoles();
}