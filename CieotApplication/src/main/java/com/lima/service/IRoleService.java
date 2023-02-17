package com.lima.service;

import java.util.List;

import com.lima.dto.RoleDTO;
import com.lima.entity.Role;

public interface IRoleService {
	
	List<Role> findAllRole();

	void setDefaultRole(Long accountId, Integer roleId);
	
	void setRole(Long accountId, Integer roleId);

	List<RoleDTO> getAllRole();
}
