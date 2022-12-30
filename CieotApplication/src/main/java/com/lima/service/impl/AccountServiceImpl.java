package com.lima.service.impl;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.lima.common.MyConstants;
import com.lima.entity.Account;
import com.lima.repository.AccountRepository;
import com.lima.service.IAccountService;

import net.bytebuddy.utility.RandomString;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	public Account findAccountByUserName(String userName) {
		return accountRepository.findAccountByUserName(userName);
	}

	@Override
	public Long findIdUserByUserName(String userName) {
		return accountRepository.findIdUserByUserName(userName);
	}

	@Override
	public String existsByUserName(String userName) {
		return accountRepository.existsByUserName(userName);
	}

	@Override
	public String existsByEmail(String email) {
		return accountRepository.existsByEmail(email);
	}

	@Override
	public void addNew(String userName, String email, String password)
			throws MessagingException, UnsupportedEncodingException {
		String randomCode = RandomString.make(64);
		accountRepository.addNew(userName, email, password, false, randomCode);
		sendVerificationEmail(userName, email, randomCode);
	}

	private void sendVerificationEmail(String userName, String email, String randomCode)
			throws MessagingException, UnsupportedEncodingException {
		String subject = "Hay xac thuc email cua ban";
		String mailContent = "";
		String confirmUrl = "http://localhost:4200/verification?code=" + randomCode;

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
		helper.setTo(email);
		helper.setFrom(MyConstants.MY_EMAIL, "LIMA CIEOT");
		helper.setSubject(subject);
		mailContent = "<p sytle='color:red;'>Xin chào " + userName + " ,<p>"
				+ "<p> Nhấn vào link sau để xác thực email của bạn:</p>" + "<h3><a href='" + confirmUrl
				+ "'>Link Xác thực( nhấn vào đây)!</a></h3>" + "<p>LIMA CIEOT</p>";
		helper.setText(mailContent, true);
		javaMailSender.send(message);
	}

	@Override
	public Boolean findAccountByVerificationCode(String verifyCode) {
		Account account = accountRepository.findAccountByVerificationCode(verifyCode);
		if (account == null || account.getIsEnabled()) {
			return false;
		} else {
			account.setIsEnabled(true);
			account.setVerificationCode(null);
			accountRepository.save(account);
			return true;
		}
	}

	@Override
	public void addVerificationCode(String userName) throws UnsupportedEncodingException, MessagingException {
		String code = RandomString.make(64);
		accountRepository.addVerificationCode(code, userName);
		Account account = accountRepository.findAccountByVerificationCode(code);
		sendVerificationEmailForResetPassWord(account.getUserName(), account.getEmail(), code);
	}

	private void sendVerificationEmailForResetPassWord(String userName, String email, String code)
			throws UnsupportedEncodingException, MessagingException {
		String subject = "Hay xac thuc email cua ban";
		String mailContent = "";
		String confirmUrl = "http://localhost:4200/verify-reset-password?code=" + code;

		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
		helper.setFrom(MyConstants.MY_EMAIL, "LIMA CIEOT");
		helper.setTo(email);
		helper.setSubject(subject);
		mailContent = "<p sytle='color:red;'>Xin chào " + userName + " ,<p>"
				+ "<p> Nhấn vào link sau để xác thực email của bạn:</p>" + "<h3><a href='" + confirmUrl
				+ "'>Link Xác thực( nhấn vào đây)!</a></h3>" + "<p>LIMA CIEOT</p>";
		helper.setText(mailContent, true);
		javaMailSender.send(message);
	}

}
