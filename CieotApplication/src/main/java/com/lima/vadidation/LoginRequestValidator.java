package com.lima.vadidation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.lima.payload.request.LoginRequest;
import com.lima.service.IAccountService;

@Component
public class LoginRequestValidator implements Validator {

	@Autowired
	private IAccountService accountService;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public boolean supports(Class<?> clazz) {
		return LoginRequest.class == clazz;
	}

	@Override
	public void validate(Object target, Errors errors) {
		LoginRequest loginRequest = (LoginRequest) target;
		if (loginRequest.getUsername() == "" || loginRequest.getUsername() == null) {
			errors.rejectValue("username", "username.null", "Tên đăng nhập không được để trống");
		} else if (accountService.existsByUserName(loginRequest.getUsername()) == null) {
			errors.rejectValue("username", "username.notexists", "Tên đăng nhập không đúng");
		} 
//		else if (loginRequest.getUsername() != "" && loginRequest.getPassword() != ""
//				&& accountService.existsByUserNameAndPassword(loginRequest.getUsername(),
//						encoder.encode(loginRequest.getPassword())) == null) {
//			System.out.println("encoder.encode(loginRequest.getPassword()) : " + encoder.encode(loginRequest.getPassword()));
//			errors.rejectValue("username", "username.notexists", "Tên đăng nhập hoặc mật khẩu không đúng");
//		}
		if (loginRequest.getPassword() == "" || loginRequest.getPassword() == null) {
			errors.rejectValue("password", "password.null", "Mật khẩu không được để trống");
		}

	}

}
