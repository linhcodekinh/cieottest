package com.lima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.dto.ExamDTO;
import com.lima.service.IPartService;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*")
public class ExamController {
	
	@Autowired
	private IPartService partService;
	
	@GetMapping("/exam/{id}")
	public ResponseEntity<List<ExamDTO>> getExamByCode(@PathVariable Integer id){
		
		return null;
	}
}
