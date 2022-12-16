package com.lima.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lima.entity.Account;
import com.lima.repository.AccountRepository;

@Service
public class AccountDetailServiceImpl implements UserDetailsService {

	/**
	 * Nguyen Van Linh
	 */
	@Autowired
	AccountRepository accountRepository;

	/**
	 * Nguyen Van Linh made it
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String userName) {
		Account account = accountRepository.findAccountByUserName(userName);
		if (account == null) {
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}
		return AccountDetailsImpl.build(account);
	}

}
