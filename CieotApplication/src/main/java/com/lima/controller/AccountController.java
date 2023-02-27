package com.lima.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.dto.AccountDTO;
import com.lima.payload.request.AccountDTORequest;
import com.lima.payload.response.MessageResponse;
import com.lima.service.IAccountService;
import com.lima.vadidation.AccountDTORequestValidator;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private AccountDTORequestValidator accountDTORequestValidator;

	// GET ACC
	@GetMapping("/account")
	public ResponseEntity<List<AccountDTO>> getAllAccount() {
		List<AccountDTO> accountDTOList = accountService.getAllAccount();
		if (accountDTOList.isEmpty()) {
			return new ResponseEntity<List<AccountDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<AccountDTO>>(accountDTOList, HttpStatus.OK);
	}

	@PostMapping("/account")
	public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDTORequest accountDTORequest,
			BindingResult bindingResult) {
		accountDTORequestValidator.validate(accountDTORequest, bindingResult);
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
		accountService.create(accountDTORequest);
		return ResponseEntity.ok(new MessageResponse("Tao tài khoản thành công!"));
	}

	// DELETE BY ID
	@PatchMapping("/account/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		accountService.deleteById(id);
		return ResponseEntity.ok(new MessageResponse("Xoa tài khoản thành công!"));
	}

	// UPDATE
	@PutMapping("/account/{id}")
	public ResponseEntity<AccountDTO> update(@PathVariable Integer id,
			@RequestBody AccountDTORequest accountDTORequest) {
		AccountDTO accountDTO = accountService.update(id, accountDTORequest);
		return new ResponseEntity<>(accountDTO, HttpStatus.OK);
	}
}
