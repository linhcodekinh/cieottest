package com.lima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import com.lima.dto.PartDTO;
import com.lima.entity.Part;
import com.lima.exception.PartException;
import com.lima.payload.request.PartDTORequest;
import com.lima.repository.PartRepositoty;
import com.lima.service.IPartService;

@RestController
@RequestMapping("api/public/part")
@CrossOrigin(origins = "*")
public class PartController {

	@Autowired
	private IPartService partService;

	@Autowired
	private PartRepositoty partRepositoty;

	@GetMapping("/hello2")
	public String hello() {
		return "hello";
	}

	@GetMapping("/")
	public ResponseEntity<List<PartDTO>> getAll() {
		List<PartDTO> listPart = partService.getAll();
		return new ResponseEntity<>(listPart, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public PartDTO getPartDetail(@PathVariable Integer id) {
		PartDTO partDTO = partService.getPartDetail(id);
		return partDTO;
	}

	@GetMapping("/list-by-part-no")
	public ResponseEntity<List<PartDTO>> getAllByPartNo(
			@RequestParam(name = "partNo", required = false, defaultValue = "") Integer partNo) {
		List<PartDTO> listPart = partService.getAllByPartNo(partNo);
		return new ResponseEntity<>(listPart, HttpStatus.OK);
	}

	@GetMapping("/list-by-code")
	public ResponseEntity<List<PartDTO>> getAllByCode(
			@RequestParam(name = "code", required = false, defaultValue = "") Integer code) {
		List<PartDTO> listPart = partService.getAllByCode(code);
		return new ResponseEntity<>(listPart, HttpStatus.OK);
	}

	@GetMapping("/list-by-level")
	public ResponseEntity<List<PartDTO>> getAllByLevel(@RequestParam(name = "level", required = false) Integer level) {
		List<PartDTO> listPart = partService.getAllByLevel(level);
		return new ResponseEntity<>(listPart, HttpStatus.OK);
	}

	@PutMapping("/update")
	public ResponseEntity<PartDTO> update(@RequestBody PartDTORequest partDTORequest) {
		Optional<Part> partOptional = partRepositoty.findById(partDTORequest.getId());
		if (!partOptional.isPresent())
			throw new PartException("Part id supplied is not exists", HttpStatus.UNPROCESSABLE_ENTITY);
		PartDTO partDTO = partService.update(partDTORequest);
		return new ResponseEntity<>(partDTO, HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<PartDTO> create(@RequestBody PartDTORequest partDTORequest) {
		PartDTO partDTO = partService.create(partDTORequest);
		return new ResponseEntity<>(partDTO, HttpStatus.OK);
	}

	// delete by id
	@PatchMapping("/delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id) {
		partService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
