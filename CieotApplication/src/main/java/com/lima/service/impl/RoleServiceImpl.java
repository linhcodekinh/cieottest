package com.lima.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.dto.PositionDTO;
import com.lima.dto.RoleDTO;
import com.lima.entity.Position;
import com.lima.entity.Role;
import com.lima.repository.RoleRepository;
import com.lima.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;


	@Override
	public void setDefaultRole(Integer accountId, Integer roleId) {
		roleRepository.setDefaultRole(accountId, roleId);
	}

	@Override
	public void setRole(Integer accountId, Integer roleId) {
		roleRepository.setRole(accountId, roleId);
	}

	@Override
	public List<RoleDTO> getAllRole() {
		List<Role> roleList = roleRepository.getAllRole();
		List<RoleDTO> roleDTOList = modelMapper.map(roleList, new TypeToken<List<RoleDTO>>() {
		}.getType());
		return roleDTOList;
	}

	@Override
	public void deleteRole(Integer id) {
		roleRepository.deleteRole(id);
	}

}
