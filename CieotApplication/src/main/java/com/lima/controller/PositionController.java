package com.lima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.dto.PositionDTO;
import com.lima.service.IPositionService;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*")
public class PositionController {
	
	@Autowired
	private IPositionService positionService;
	
	// GET POS
	@GetMapping("/position")
	public ResponseEntity<List<PositionDTO>> getAllPosition() {
		List<PositionDTO> positionDTOList = positionService.getAllPosition();
		if (positionDTOList.isEmpty()) {
			return new ResponseEntity<List<PositionDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PositionDTO>>(positionDTOList, HttpStatus.OK);
	}

}
