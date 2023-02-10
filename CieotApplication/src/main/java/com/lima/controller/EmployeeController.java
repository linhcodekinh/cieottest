package com.lima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.dto.AccountDTO;
import com.lima.dto.EmployeeDTO;
import com.lima.dto.PositionDTO;
import com.lima.service.IAccountService;
import com.lima.service.IPositionService;

@RestController
@RequestMapping("api/public")
public class EmployeeController {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IPositionService positionService;

	// GET ALL EMP
	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
		return null;
	}

	// GET POS
	@GetMapping("/position")
	public ResponseEntity<List<PositionDTO>> getAllPosition() {
		List<PositionDTO> positionDTOList = positionService.getAllPosition();
		if (positionDTOList.isEmpty()) {
			return new ResponseEntity<List<PositionDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PositionDTO>>(positionDTOList, HttpStatus.OK);
	}

	// GET ACC
	@GetMapping("/account")
	public ResponseEntity<List<AccountDTO>> getAllAccount() {
		List<AccountDTO> accountDTOList = accountService.getAllAccount();
		if (accountDTOList.isEmpty()) {
			return new ResponseEntity<List<AccountDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AccountDTO>>(accountDTOList, HttpStatus.OK);
	}
	// GET ALL ROLE
	// DELETE BY ID
}
