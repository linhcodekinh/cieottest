package com.lima.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import com.lima.payload.response.JwtLoginResponse;
import com.lima.payload.response.MessageResponse;
import com.lima.service.IAccountService;
import com.lima.service.IMemberService;
import com.lima.service.IRoleService;
import com.lima.service.impl.AccountDetailsImpl;

@RestController
@RequestMapping("api/public")
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

	@GetMapping("/hello")
	public String testApi() {
		System.out.println("hello");
		return "hello world";
	}

	@PostMapping("/singup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest)
			throws MessagingException, UnsupportedEncodingException {
		System.out.println("ahhhhhhhhhhhhhhhhhhhhhh");
		if (accountService.existsByUserName(signUpRequest.getUserName()) != null) {
			return ResponseEntity.badRequest().body(new MessageResponse("User Name này đã tồn tại!!!"));
		}
		if (accountService.existsByEmail(signUpRequest.getEmail()) != null) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email này đã tồn tại!!!"));
		}
		// Init account
		Account account = new Account(signUpRequest.getUserName(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));
		// insert into table account
		accountService.addNew(account.getUserName(), account.getEmail(), account.getEncryptPw());
		// get ID of new created account
		Long idAccountAfterCreated = accountService.findIdUserByUserName(account.getUserName());
		// set default role is USER_ROLE
		roleService.setDefaultRole(idAccountAfterCreated, 1);
		// insert into table member
		memberService.addNewMember(signUpRequest.getUserName(), signUpRequest.getDateOfBirth(),
				signUpRequest.getGender(), signUpRequest.getPhone(), signUpRequest.getAddress(),
				signUpRequest.getEmail(), idAccountAfterCreated, false);

		return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công!"));
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authenticatation = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticatation);
		String jwt = jwtUtility.generateJwtToken(loginRequest.getUserName());
		AccountDetailsImpl userDetails = (AccountDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());
		Account account = accountService.findAccountByUserName(loginRequest.getUserName());
		Member member = memberService.findByAccountIdAndDeleteFlag(account.getId(), false);

		return ResponseEntity
				.ok(new JwtLoginResponse(jwt, userDetails.getId(), userDetails.getUsername(), roles, member));
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
		if (accountService.existsByUserName(loginRequest.getUserName()) != null) {
			accountService.addVerificationCode(loginRequest.getUserName());
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
