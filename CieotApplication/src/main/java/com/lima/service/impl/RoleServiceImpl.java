package com.lima.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.entity.Role;
import com.lima.repository.RoleRepository;
import com.lima.service.RoleService;

@Service 
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<Role> findAllRole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDefaultRole(Long accountId, Integer roleId) {
		roleRepository.setDefaultRole(accountId, roleId);
	}

	@Override
	public List<Role> getAllRoles() {
		// TODO Auto-generated method stub
		return null;
	}

}
