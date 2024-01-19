package com.lima.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.lima.dto.AccountDTO;
import com.lima.entity.Account;
import com.lima.payload.request.AccountDTORequest;
import com.lima.payload.request.AccountDTOUpdateRequest;

public interface IAccountService {
	/**
	 * Nguyen Van Linh
	 */
	Account findAccountByUserName(String userName);

	Integer findIdUserByUserName(String userName);

	String existsByUserName(String userName);

	String existsByEmail(String email);

	void addNew(String userName, String email, String password)
			throws MessagingException, UnsupportedEncodingException;

	void addNew(String userName, String email, String password, Boolean isEnable, String verificationCode);

	Boolean findAccountByVerificationCode(String verifyCode);

	void addVerificationCode(String userName) throws UnsupportedEncodingException, MessagingException;

//	String existsByUserNameAndPassword(String username, String password);

	List<AccountDTO> getAllAccount();
	
	AccountDTO getAccountById(Integer id);
	
	void deleteById(Integer id);

	void update(Integer id, AccountDTOUpdateRequest accountDTORequest);

	void create(AccountDTORequest accountDTORequest);

	Integer countByUserName(String userName);
}
