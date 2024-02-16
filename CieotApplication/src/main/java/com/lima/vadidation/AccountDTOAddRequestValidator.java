package com.lima.vadidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.lima.payload.request.AccountDTOAddRequest;
import com.lima.service.IAccountService;

@Component
public class AccountDTOAddRequestValidator implements Validator {

	@Autowired
	private IAccountService accountService;

	@Override
	public boolean supports(Class<?> clazz) {
		return AccountDTOAddRequest.class == clazz;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		AccountDTOAddRequest accountDTOAddRequest = (AccountDTOAddRequest) target;
		String userName = accountDTOAddRequest.getUserName();
		String email = accountDTOAddRequest.getEmail();
		String firstName = accountDTOAddRequest.getFirstName();
		String lastName = accountDTOAddRequest.getLastName();
		String name = firstName + " " + lastName;
		Integer gender = accountDTOAddRequest.getGender();
		String phone = accountDTOAddRequest.getPhone();
		String address = accountDTOAddRequest.getAddress();
		String dateOfBirth = accountDTOAddRequest.getDateOfBirth();
		String idCard = accountDTOAddRequest.getIdCard();
		Integer positionId = accountDTOAddRequest.getPositionId();

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName", "accountDTOAddRequest.userName.null",
				"Username is required");
		if (StringUtils.hasText(userName) && accountService.existsByUserName(userName) != null) {
			errors.rejectValue("userName", "accountDTOAddRequest.userName.exists", "Username already exists");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "accountDTOAddRequest.email.null",
				"Email is required");
		if (StringUtils.hasText(email) && accountService.existsByEmail(email) != null) {
			errors.rejectValue("email", "accountDTOAddRequest.email.exists", "Email already exists");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "accountDTOAddRequest.lastName.null", "LastName is required");

//		if (userName == "" || userName == null) {
//			errors.rejectValue("userName", "userName.null", "Username khong duoc de trong");
//		} else if (accountService.existsByUserName(userName) != null) {
//			errors.rejectValue("userName", "userName.exists", "Username da ton tai");
//		}
//		if (email == "" || email == null) {
//			errors.rejectValue("email", "email.null", "Email khong duoc de trong");
//		} else if (accountService.existsByEmail(email) != null) {
//			errors.rejectValue("email", "email.exists", "Email da ton tai");
//		}
//		if (name == "" || name == null) {
//			errors.rejectValue("name", "name.null", "Name khong duoc de trong");
//		}
	}

}
