package com.lima.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.dto.EmployeeDTO;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*")
public class EmployeeController {

	// GET ALL EMP
	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
		return null;
	}

}
