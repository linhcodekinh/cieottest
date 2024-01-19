package com.lima.vadidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lima.payload.request.AccountDTORequest;
import com.lima.payload.request.AccountDTOUpdateRequest;
import com.lima.service.IAccountService;

@Component
public class AccountDTOUpdateRequestValidator implements Validator {

	@Autowired
	private IAccountService accountService;

	@Override
	public boolean supports(Class<?> clazz) {
		return AccountDTORequest.class == clazz;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		AccountDTOUpdateRequest accountDTORequest = (AccountDTOUpdateRequest) target;
		String userName = accountDTORequest.getUserName();
		String firstName = accountDTORequest.getFirstName();
		String lastName = accountDTORequest.getLastName();
		String name = firstName + " " + lastName;
		Integer gender = accountDTORequest.getGender();
		String phone = accountDTORequest.getPhone();
		String address = accountDTORequest.getAddress();
		String dateOfBirth = accountDTORequest.getDateOfBirth();
		String idCard = accountDTORequest.getIdCard();
		Integer positionId = accountDTORequest.getPositionId();
		
		if (userName == "" || userName == null) {
			errors.rejectValue("userName", "userName.null", "Username khong duoc de trong");
		} else if (accountService.countByUserName(userName) > 1) {
			errors.rejectValue("userName", "userName.exists", "Username da ton tai");
		}
		if (name == "" || name == null) {
			errors.rejectValue("name", "name.null", "Name khong duoc de trong");
		}
	}

}
