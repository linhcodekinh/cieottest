package com.lima.vadidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lima.payload.request.SignupRequest;
import com.lima.service.IAccountService;

@Component
public class SignupRequestValidator implements Validator {

	@Autowired
	private IAccountService accountService;

	@Override
	public boolean supports(Class<?> clazz) {
		return SignupRequest.class == clazz;
	}

	@Override
	public void validate(Object target, Errors errors) {
		SignupRequest signupRequest = (SignupRequest) target;
		if (accountService.existsByUserName(signupRequest.getUsername()) != null) {
			errors.rejectValue("username", "username.exists", "Tên đăng nhập này đã tồn tại");
		}
		if (accountService.existsByEmail(signupRequest.getEmail()) != null) {
			errors.rejectValue("email", "email.exists", "Email này đã tồn tại");
		}
	}

}
