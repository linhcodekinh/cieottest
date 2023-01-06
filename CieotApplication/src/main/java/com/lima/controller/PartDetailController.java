package com.lima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.dto.PartDetailDTO;
import com.lima.payload.request.PartDetailDTORequest;
import com.lima.service.IPartDetailService;

@RestController
@RequestMapping("api/public/part-detail")
@CrossOrigin(origins = "*")
public class PartDetailController {

	@Autowired
	private IPartDetailService partDetailService;

	// INSERT
	@PostMapping("/create")
	public ResponseEntity<PartDetailDTO> create(@RequestBody PartDetailDTORequest partDetailDTORequest) {
		PartDetailDTO partDetailDTO = partDetailService.create(partDetailDTORequest);
		return new ResponseEntity<>(partDetailDTO, HttpStatus.OK);
	}

	// DELETE
	// UPDATE
	@PutMapping("/update")
	public ResponseEntity<PartDetailDTO> update(@RequestBody PartDetailDTORequest partDetailDTORequest) {
		PartDetailDTO partDetailDTO = partDetailService.update(partDetailDTORequest);
		return new ResponseEntity<>(partDetailDTO, HttpStatus.OK);
	}

	// GETALL
	@GetMapping("/")
	public ResponseEntity<List<PartDetailDTO>> getAll() {
		List<PartDetailDTO> listPartDetail = partDetailService.getAll();
		return new ResponseEntity<>(listPartDetail, HttpStatus.OK);
	}
}
