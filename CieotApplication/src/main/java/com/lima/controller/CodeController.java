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

import com.lima.dto.CodeDTO;
import com.lima.payload.request.CodeDTORequest;
import com.lima.service.ICodeService;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*")
public class CodeController {
	@Autowired
	private ICodeService codeService;

	//GET ALL
	@GetMapping("/code")
	public ResponseEntity<List<CodeDTO>> getAll(){
		List<CodeDTO> listCode = codeService.getAll();
		return new ResponseEntity<>(listCode, HttpStatus.OK);
	}
	//DELETE
	@PatchMapping("/code/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		codeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	//UPDATE
	@PutMapping("/code/{id}")
	public ResponseEntity<CodeDTO> update(@PathVariable Integer id, @RequestBody CodeDTORequest codeDTORequest){
		CodeDTO codeDTO = codeService.update(id, codeDTORequest);
		return new ResponseEntity<>(codeDTO, HttpStatus.OK);
	}
	//INSERT
	@PostMapping("/code")
	public ResponseEntity<CodeDTO> create(@RequestBody CodeDTORequest codeDTORequest){
		CodeDTO codeDTO = codeService.create(codeDTORequest);
		return new ResponseEntity<>(codeDTO, HttpStatus.OK);
	}
}
