package com.lima.controller;

import java.util.List;
import java.util.Optional;

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
import com.lima.entity.Code;
import com.lima.exception.CodeException;
import com.lima.payload.request.CodeDTORequest;
import com.lima.repository.CodeRepository;
import com.lima.service.ICodeService;

@RestController
@RequestMapping("api/public/code")
@CrossOrigin(origins = "*")
public class CodeController {
	@Autowired
	private ICodeService codeService;
	@Autowired
	private CodeRepository codeRepository;
	//GET ALL
	@GetMapping("/")
	public ResponseEntity<List<CodeDTO>> getAll(){
		List<CodeDTO> listCode = codeService.getAll();
		return new ResponseEntity<>(listCode, HttpStatus.OK);
	}
	//DELETE
	@PatchMapping("delete/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Integer id){
		codeService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	//UPDATE
	@PutMapping("/update")
	public ResponseEntity<CodeDTO> update(@RequestBody CodeDTORequest codeDTORequest){
		Optional<Code> codeOptional = codeRepository.findById(codeDTORequest.getId());
		if(!codeOptional.isPresent())
			throw new CodeException("Code id supplied is not exists", HttpStatus.UNPROCESSABLE_ENTITY);
		CodeDTO codeDTO = codeService.update(codeDTORequest);
		return new ResponseEntity<>(codeDTO, HttpStatus.OK);
	}
	//INSERT
	@PostMapping("/create")
	public ResponseEntity<CodeDTO> create(@RequestBody CodeDTORequest codeDTORequest){
		CodeDTO codeDTO = codeService.create(codeDTORequest);
		return new ResponseEntity<>(codeDTO, HttpStatus.OK);
	}
}
