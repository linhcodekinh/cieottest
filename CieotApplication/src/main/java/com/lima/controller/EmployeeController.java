package com.lima.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lima.dto.AccountDTO;
import com.lima.dto.AccountRoleDTO;
import com.lima.dto.AccountTypeDTO;
import com.lima.dto.EmployeeDTO;
import com.lima.dto.PositionDTO;
import com.lima.dto.RoleDTO;
import com.lima.dto.TypeDTO;
import com.lima.entity.Account;
import com.lima.payload.request.AccountDTORequest;
import com.lima.payload.response.MessageResponse;
import com.lima.service.IAccountService;
import com.lima.service.IEmployeeService;
import com.lima.service.IMemberService;
import com.lima.service.IPositionService;
import com.lima.service.IRoleService;
import com.lima.service.ITypeService;
import com.lima.vadidation.AccountDTORequestValidator;

@RestController
@RequestMapping("api/public")
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private IAccountService accountService;

	@Autowired
	private IPositionService positionService;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private ITypeService typeService;

	@Autowired
	private IMemberService memberService;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private AccountDTORequestValidator accountDTORequestValidator;

	// GET ALL EMP
	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
		return null;
	}

	// GET POS
	@GetMapping("/position")
	public ResponseEntity<List<PositionDTO>> getAllPosition() {
		List<PositionDTO> positionDTOList = positionService.getAllPosition();
		if (positionDTOList.isEmpty()) {
			return new ResponseEntity<List<PositionDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<PositionDTO>>(positionDTOList, HttpStatus.OK);
	}
	
	// GET ROLE
	@GetMapping("/role")
	public ResponseEntity<List<RoleDTO>> getAllRole() {
		List<RoleDTO> roleDTOList = roleService.getAllRole();
		if (roleDTOList.isEmpty()) {
			return new ResponseEntity<List<RoleDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<RoleDTO>>(roleDTOList, HttpStatus.OK);
	}
	
	// GET TYPE
	@GetMapping("/type")
	public ResponseEntity<List<TypeDTO>> getAllType() {
		List<TypeDTO> typenDTOList = typeService.getAllType();
		if (typenDTOList.isEmpty()) {
			return new ResponseEntity<List<TypeDTO>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TypeDTO>>(typenDTOList, HttpStatus.OK);
	}

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
		// Init account
		String userName = accountDTORequest.getUserName();
		String email = accountDTORequest.getEmail();
		String password = accountDTORequest.getPassword();
		String name = accountDTORequest.getName();
		String gender = accountDTORequest.getGender();
		String phone = accountDTORequest.getPhone();
		String address = accountDTORequest.getAddress();
		String dateOfBirth = accountDTORequest.getDateOfBirth();
		String idCard = accountDTORequest.getIdCard();
		Integer positionId = accountDTORequest.getPositionId();
		Boolean isEnabled = accountDTORequest.getIsEnabled();
		String verificationCode = "";
		Account account = new Account(userName, email, encoder.encode(password), isEnabled, verificationCode);
		// insert to db
		accountService.addNew(account.getUserName(), account.getEmail(), account.getEncryptPw(), account.getIsEnabled(),
				account.getVerificationCode());
		// get ID
		Long idAccountAfterCreated = accountService.findIdUserByUserName(account.getUserName());
		// set role if have
		List<Integer> idRoleList = accountDTORequest.getIdRoleList();
		if (idRoleList != null && !idRoleList.isEmpty()) {
			for (Integer idRole : idRoleList) {
				roleService.setRole(idAccountAfterCreated, idRole);
			}
		}
		// set type
		List<Integer> idTypeList = accountDTORequest.getIdTypeList();
		if (idTypeList != null && !idTypeList.isEmpty()) {
			for (Integer idType : idTypeList) {
				//System.out.println("idType: " + idType);
				typeService.setType(idAccountAfterCreated, idType);
				if ("1".equals(idType + "")) {
					memberService.addNewMember(name, dateOfBirth, gender, phone, address,idAccountAfterCreated,
							false);
				}
				if ("2".equals(idType + "")) {
					employeeService.addNewEmployee(name, dateOfBirth, gender, phone, address,
							idAccountAfterCreated, idCard, positionId, false);
				}
			}
		}

		return ResponseEntity.ok(new MessageResponse("Tao tài khoản thành công!"));
	}
	// GET ALL ROLE
	// DELETE BY ID
}
