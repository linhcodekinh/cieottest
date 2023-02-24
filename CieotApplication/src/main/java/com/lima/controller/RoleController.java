package com.lima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.dto.RoleDTO;
import com.lima.service.IRoleService;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*")
public class RoleController {
	@Autowired
	private IRoleService roleService;

	// GET ROLE
	@GetMapping("/role")
	public ResponseEntity<List<RoleDTO>> getAllRole() {
		List<RoleDTO> roleDTOList = roleService.getAllRole();
		if (roleDTOList.isEmpty()) {
			return new ResponseEntity<List<RoleDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RoleDTO>>(roleDTOList, HttpStatus.OK);
	}
}
