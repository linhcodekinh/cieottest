package com.lima.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lima.dto.AccountDTO;
import com.lima.payload.request.AccountDTOAddRequest;
import com.lima.payload.request.AccountDTOUpdateRequest;
import com.lima.payload.response.AccountListResponse;
import com.lima.payload.response.MessageResponse;
import com.lima.service.IAccountService;
import com.lima.vadidation.AccountDTOAddRequestValidator;
import com.lima.vadidation.AccountDTORequestValidator;
import com.lima.vadidation.AccountDTOUpdateRequestValidator;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*")
public class AccountController {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private AccountDTORequestValidator accountDTORequestValidator;

	@Autowired
	private AccountDTOAddRequestValidator accountDTOAddRequestValidator;

	@Autowired
	private AccountDTOUpdateRequestValidator accountDTOUpdateRequestValidator;

	// GET ALL ACC
	@GetMapping("/account")
	public ResponseEntity<?> getAllAccount(@RequestParam(name = "offset", required = false) Integer offset,
			@RequestParam(name = "pageSize", required = false) Integer pageSize,
			@RequestParam(name = "field", required = false) String field,
			@RequestParam(name = "direction", required = false) String direction,
			@RequestParam(name = "textSearch", required = false) String textSearch) {
		List<AccountDTO> accountDTOList = accountService.getAllAccount(offset, pageSize, field, direction, textSearch);
		Integer totalItem = 0;
		if (!"".equals(textSearch)) {
			totalItem = accountService.getTotalItem(textSearch);
		} else {
			totalItem = accountService.getTotalItem();
		}
		AccountListResponse accountListResponse = new AccountListResponse(accountDTOList, totalItem);
		if (accountDTOList.isEmpty()) {
			return new ResponseEntity<AccountListResponse>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<AccountListResponse>(accountListResponse, HttpStatus.OK);
	}

	// GET ALL ACC
//	@GetMapping("/account")
//	public ResponseEntity<List<AccountDTO>> getAllAccount(){
//		List<AccountDTO> accountDTOList = accountService.getAllAccount();
//		if (accountDTOList.isEmpty()) {
//			return new ResponseEntity<List<AccountDTO>>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<List<AccountDTO>>(accountDTOList, HttpStatus.OK);
//	}

	// GET ONE ACC
	@GetMapping("/account/{id}")
	public ResponseEntity<AccountDTO> getAccount(@PathVariable Integer id) {
		AccountDTO accountDTO = accountService.getAccountById(id);
		if (accountDTO == null) {
			return new ResponseEntity<AccountDTO>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<AccountDTO>(accountDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/account", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> createAccount(
			@RequestPart(name = "accountDetail", required = false) AccountDTOAddRequest accountDTOAddRequest,
			BindingResult bindingResult, @RequestPart(name = "imageFile", required = false) MultipartFile imageFile) {
		accountDTOAddRequest.setImageFile(imageFile);
		accountDTOAddRequestValidator.validate(accountDTOAddRequest, bindingResult);
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
		}
		accountService.create(accountDTOAddRequest);
		return ResponseEntity.ok(new MessageResponse("Tao tài khoản thành công!"));
	}

	// DELETE BY ID
	@PatchMapping("/account/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		accountService.deleteById(id);
		return ResponseEntity.ok(new MessageResponse("Xóa tài khoản thành công!"));
	}

	// UPDATE
	@PutMapping(value = "/account/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> updateAccount(@PathVariable Integer id,
			@RequestPart(name = "accountDetail", required = false) AccountDTOUpdateRequest accountDTOUpdateRequest,
			BindingResult bindingResult, @RequestPart(name = "imageFile", required = false) MultipartFile imageFile) {
		if (imageFile != null) {
			accountDTOUpdateRequest.setImageFile(imageFile);
		}
		accountDTOUpdateRequest.setImageFile(imageFile);
		accountDTOUpdateRequestValidator.validate(accountDTOUpdateRequest, bindingResult);
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.OK);
		accountService.update(id, accountDTOUpdateRequest);
		return ResponseEntity.ok(new MessageResponse("Cập nhật tài khoản thành công!"));
	}
}
