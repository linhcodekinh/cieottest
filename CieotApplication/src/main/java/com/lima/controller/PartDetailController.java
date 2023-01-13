package com.lima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.dto.PartDetailDTO;
import com.lima.payload.request.PartDetailDTORequest;
import com.lima.service.IPartDetailService;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*")
public class PartDetailController {

	@Autowired
	private IPartDetailService partDetailService;

	// INSERT
	@PostMapping("/part-detail")
	public ResponseEntity<PartDetailDTO> create(@RequestBody PartDetailDTORequest partDetailDTORequest) {
		PartDetailDTO partDetailDTO = partDetailService.create(partDetailDTORequest);
		return new ResponseEntity<>(partDetailDTO, HttpStatus.OK);
	}

	// DELETE
	@PatchMapping("part-detail/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		partDetailService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// UPDATE
	@PutMapping("/part-detail/{id}")
	public ResponseEntity<PartDetailDTO> update(@PathVariable Integer id,
			@RequestBody PartDetailDTORequest partDetailDTORequest) {
		PartDetailDTO partDetailDTO = partDetailService.update(id, partDetailDTORequest);
		return new ResponseEntity<>(partDetailDTO, HttpStatus.OK);
	}

	// GETALL
	@GetMapping("/part-detail")
	public ResponseEntity<List<PartDetailDTO>> getAll() {
		List<PartDetailDTO> listPartDetail = partDetailService.getAll();
		return new ResponseEntity<>(listPartDetail, HttpStatus.OK);
	}
}
