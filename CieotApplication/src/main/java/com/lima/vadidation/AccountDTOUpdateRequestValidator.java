package com.lima.vadidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lima.payload.request.AccountDTOUpdateRequest;
import com.lima.service.IAccountService;

@Component
public class AccountDTOUpdateRequestValidator implements Validator {

	@Autowired
	private IAccountService accountService;

	@Override
	public boolean supports(Class<?> clazz) {
		return AccountDTOUpdateRequest.class == clazz;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		AccountDTOUpdateRequest accountDTOUpdateRequest = (AccountDTOUpdateRequest) target;
		String userName = accountDTOUpdateRequest.getUserName();
		String firstName = accountDTOUpdateRequest.getFirstName();
		String lastName = accountDTOUpdateRequest.getLastName();
		String name = firstName + " " + lastName;
		Integer gender = accountDTOUpdateRequest.getGender();
		String phone = accountDTOUpdateRequest.getPhone();
		String address = accountDTOUpdateRequest.getAddress();
		String dateOfBirth = accountDTOUpdateRequest.getDateOfBirth();
		String idCard = accountDTOUpdateRequest.getIdCard();
		Integer positionId = accountDTOUpdateRequest.getPositionId();
		
		if (userName == "" || userName == null) {
			errors.rejectValue("userName", "userName.null", "Username khong duoc de trong");
		} else if (accountService.countByUserName(userName).intValue() > 1) {
			errors.rejectValue("userName", "userName.exists", "Username da ton tai");
		}
//		if (name == "" || name == null) {
//			errors.rejectValue("name", "name.null", "Name khong duoc de trong");
//		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "accountDTOAddRequest.userName.null",
				"Username is required");
		if (StringUtils.hasText(userName) && accountService.existsByUserName(userName) != null) {
			errors.rejectValue("userName", "accountDTOAddRequest.userName.exists", "Username already exists");
		}

//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "accountDTOAddRequest.email.null",
//				"Email is required");
//		if (StringUtils.hasText(email) && accountService.existsByEmail(email) != null) {
//			errors.rejectValue("email", "accountDTOAddRequest.email.exists", "Email already exists");
//		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "accountDTOAddRequest.lastName.null", "LastName is required");
	}

}
