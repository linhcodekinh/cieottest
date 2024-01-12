package com.lima.service;

import java.util.List;

import com.lima.dto.RoleDTO;
import com.lima.entity.Role;

public interface IRoleService {

	List<RoleDTO> getAllRole();

	void setDefaultRole(Integer accountId, Integer roleId);
	
	void setRole(Integer accountId, Integer roleId);


	void deleteRole(Integer id);
}
