package com.lima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.dto.TypeDTO;
import com.lima.service.ITypeService;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*")
public class TypeController {
	@Autowired
	private ITypeService typeService;

	// GET TYPE
	@GetMapping("/type")
	public ResponseEntity<List<TypeDTO>> getAllType() {
		List<TypeDTO> typenDTOList = typeService.getAllType();
		if (typenDTOList.isEmpty()) {
			return new ResponseEntity<List<TypeDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TypeDTO>>(typenDTOList, HttpStatus.OK);
	}
}
