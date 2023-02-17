package com.lima.vadidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lima.payload.request.AccountDTORequest;
import com.lima.service.IAccountService;

@Component
public class AccountDTORequestValidator implements Validator {

	@Autowired
	private IAccountService accountService;

	@Override
	public boolean supports(Class<?> clazz) {
		return AccountDTORequest.class == clazz;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		AccountDTORequest accountDTORequest = (AccountDTORequest) target;
		String userName = accountDTORequest.getUserName();
		String email = accountDTORequest.getEmail();
		String name = accountDTORequest.getName();
		String gender = accountDTORequest.getGender();
		String phone = accountDTORequest.getPhone();
		String address = accountDTORequest.getAddress();
		String dateOfBirth = accountDTORequest.getAddress();
		String idCard = accountDTORequest.getIdCard();
		Integer positionId = accountDTORequest.getPositionId();
		
		if (userName == "" || userName == null) {
			errors.rejectValue("userName", "userName.null", "Username khong duoc de trong");
		} else if (accountService.existsByUserName(userName) != null) {
			errors.rejectValue("userName", "userName.exists", "Username da ton tai");
		}
		if (email == "" || email == null) {
			errors.rejectValue("email", "email.null", "Email khong duoc de trong");
		} else if (accountService.existsByEmail(email) != null) {
			errors.rejectValue("email", "email.exists", "Email da ton tai");
		}
		if (name == "" || name == null) {
			errors.rejectValue("name", "name.null", "Name khong duoc de trong");
		}
	}

}
