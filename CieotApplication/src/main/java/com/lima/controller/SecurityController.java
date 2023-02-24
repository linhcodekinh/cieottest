package com.lima.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindingResultUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lima.entity.Account;
import com.lima.entity.Member;
import com.lima.jwt.JwtUtility;
import com.lima.payload.request.LoginRequest;
import com.lima.payload.request.SignupRequest;
import com.lima.payload.request.VerifyRequest;
import com.lima.payload.response.ErrorMessageResponse;
import com.lima.payload.response.JwtLoginResponse;
import com.lima.payload.response.MessageResponse;
import com.lima.service.IAccountService;
import com.lima.service.IMemberService;
import com.lima.service.IRoleService;
import com.lima.service.impl.AccountDetailsImpl;
import com.lima.vadidation.LoginRequestValidator;
import com.lima.vadidation.SignupRequestValidator;

import net.bytebuddy.implementation.bytecode.Throw;

@RestController
@RequestMapping("api/public")
@CrossOrigin("http://localhost:3000")
public class SecurityController {

	@Autowired
	private JwtUtility jwtUtility;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private IAccountService accountService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private IMemberService memberService;
	@Autowired
	private LoginRequestValidator loginByRequestDTOValidator;
	@Autowired
	private SignupRequestValidator signupRequestValidator;

	@GetMapping("/hello")
	public String testApi() {
		System.out.println("hello");
		return "hello world";
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, BindingResult bindingResult)
			throws MessagingException, UnsupportedEncodingException {
		signupRequestValidator.validate(signUpRequest, bindingResult);
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
		// Init account
		Account account = new Account(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		// insert into table account
		accountService.addNew(account.getUserName(), account.getEmail(), account.getEncryptPw());
		// get ID of new created account
		Integer idAccountAfterCreated = accountService.findIdUserByUserName(account.getUserName());
		// set default role is USER_ROLE
		roleService.setDefaultRole(idAccountAfterCreated, 1);
		// insert into table member
		memberService.addNewMember(signUpRequest.getUsername(), signUpRequest.getDateOfBirth(),
				signUpRequest.getGender(), signUpRequest.getPhone(), signUpRequest.getAddress(), idAccountAfterCreated,
				false);

		return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công!"));
	}

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginRequest loginRequest,
			BindingResult bindingResult) throws Exception {
		loginByRequestDTOValidator.validate(loginRequest, bindingResult);
		if (bindingResult.hasErrors())
			return new ResponseEntity<>(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
		Authentication authenticatation = authenticate(loginRequest.getUsername(), loginRequest.getPassword());

		SecurityContextHolder.getContext().setAuthentication(authenticatation);
		String jwt = jwtUtility.generateJwtToken(loginRequest.getUsername());
		AccountDetailsImpl userDetails = (AccountDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		Account account = accountService.findAccountByUserName(loginRequest.getUsername());
		// Member member = memberService.findByAccountIdAndDeleteFlag(account.getId(),
		// false);
		return ResponseEntity
				.ok(new JwtLoginResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles, account));
	}

	private Authentication authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	// verify email when signup
	@GetMapping("/verify")
	public ResponseEntity<?> verifyEmail(@RequestBody VerifyRequest verifyRequest) {
		Boolean isVerified = accountService.findAccountByVerificationCode(verifyRequest.getRadomCode());
		if (isVerified) {
			return ResponseEntity.ok(new MessageResponse("activated"));
		} else {
			return ResponseEntity.ok(new MessageResponse("error"));
		}
	}

	// forgot password
	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody LoginRequest loginRequest)
			throws MessagingException, UnsupportedEncodingException {
		if (accountService.existsByUserName(loginRequest.getUsername()) != null) {
			accountService.addVerificationCode(loginRequest.getUsername());
			return ResponseEntity.ok(new MessageResponse("Sent mail"));
		}
		return ResponseEntity.badRequest().body(new MessageResponse("Tai khoan khong dung"));
	}

	// forgot password
	@PostMapping("verify-password")
	public ResponseEntity<?> verifyPassword() {
		return null;
	}

	// forgot password
	@PostMapping("/do-reset-password")
	public ResponseEntity<?> doReSetPassword() {
		return null;
	}

}
