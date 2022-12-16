package com.lima.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.lima.entity.Account;

public interface AccountService {
	/**
	 * Nguyen Van Linh
	 */
	Account findAccountByUserName(String userName);

	Long findIdUserByUserName(String userName);

	String existsByUserName(String userName);

	String existsByEmail(String email);

	void addNew(String userName, String email, String password)
			throws MessagingException, UnsupportedEncodingException;

	Boolean findAccountByVerificationCode(String verifyCode);

	void addVerificationCode(String userName) throws UnsupportedEncodingException, MessagingException;
}
