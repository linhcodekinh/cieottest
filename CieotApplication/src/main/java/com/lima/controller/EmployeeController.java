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
import com.lima.service.IAccountService;

@RestController
@RequestMapping("api/public")
public class EmployeeController {

	@Autowired
	private IAccountService accountService;

	// GET ALL EMP
	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
		return null;
	}

	// GET POS
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
