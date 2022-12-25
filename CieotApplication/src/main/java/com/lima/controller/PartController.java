package com.lima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lima.dto.PartDTO;
import com.lima.service.PartService;

@RestController
@RequestMapping("api/public/part")
@CrossOrigin(origins = "*")
public class PartController {

	@Autowired
	private PartService partService;

	@GetMapping("/hello2")
	public String hello() {
		return "hello";
	}

	@GetMapping("/list")
	public ResponseEntity<List<PartDTO>> getAll(
			@RequestParam(name = "code", required = false, defaultValue = "") String code) {
		List<PartDTO> listPart = partService.getAllByCode(code);
		return new ResponseEntity<>(listPart, HttpStatus.OK);
	}

	@GetMapping("/get-part-detail")
	public PartDTO getPartDetail(@RequestParam(name = "id", required = false) Integer id) {
		PartDTO partDTO = partService.getPartDetail(id);
		return partDTO;
	}
}
