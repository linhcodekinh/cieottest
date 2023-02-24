package com.lima.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lima.common.MyConstants;
import com.lima.dto.AccountDTO;
import com.lima.entity.Account;
import com.lima.entity.AccountRole;
import com.lima.entity.AccountType;
import com.lima.exception.AccountException;
import com.lima.payload.request.AccountDTORequest;
import com.lima.repository.AccountRepository;
import com.lima.service.IAccountService;
import com.lima.service.IEmployeeService;
import com.lima.service.IMemberService;
import com.lima.service.IRoleService;
import com.lima.service.ITypeService;

import net.bytebuddy.utility.RandomString;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private ITypeService typeService;

	@Autowired
	private IMemberService memberService;

	@Autowired
	private IEmployeeService employeeService;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Account findAccountByUserName(String userName) {
		return accountRepository.findAccountByUserName(userName);
	}

//	@Override
//	public String existsByUserNameAndPassword(String username, String password) {
//		return accountRepository.existsByUserNameAndPassword(username, password);
//	}

	@Override
	public Integer findIdUserByUserName(String userName) {
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

	@Override
	public void addNew(String userName, String email, String password, Boolean isEnable, String verificationCode) {
		accountRepository.addNew(userName, email, password, isEnable, verificationCode);
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

	@Override
	public List<AccountDTO> getAllAccount() {
		List<Account> accountList = accountRepository.getAllAccount();
		List<AccountDTO> accountDTOList = modelMapper.map(accountList, new TypeToken<List<AccountDTO>>() {
		}.getType());
		return accountDTOList;
	}

	@Override
	public void deleteById(Integer id) {
		accountRepository.deleteAccount(id);
	}

	@Override
	public AccountDTO update(Integer id, AccountDTORequest accountDTORequest) {
		Optional<Account> accOptional = accountRepository.findById(id);
		if (!accOptional.isPresent())
			throw new AccountException("Account id supplied is not exists", HttpStatus.UNPROCESSABLE_ENTITY);
		Account account = accOptional.get();
		account.setIsEnabled(accountDTORequest.getIsEnabled());

		List<AccountRole> accountRoleList;
		List<AccountType> accountTypeList;
		List<Integer> idRoleList = accountDTORequest.getIdRoleList();
		List<Integer> idTypeList = accountDTORequest.getIdTypeList();

//		if(idRoleList.size() !=0 ) {
//			for(Integer idr : idRoleList) {
//				Role role = new R
//				AccountRole accountRole = new AccountRole(account,)
//				
//			}
//		}

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(AccountDTORequest accountDTORequest) {
		// Init account
		String userName = accountDTORequest.getUserName();
		String email = accountDTORequest.getEmail();
		String password = accountDTORequest.getPassword();
		String name = accountDTORequest.getName();
		String gender = accountDTORequest.getGender();
		String phone = accountDTORequest.getPhone();
		String address = accountDTORequest.getAddress();
		String dateOfBirth = accountDTORequest.getDateOfBirth();
		String idCard = accountDTORequest.getIdCard();
		Integer positionId = accountDTORequest.getPositionId();
		Boolean isEnabled = accountDTORequest.getIsEnabled();
		String verificationCode = "";
		Account account = new Account(userName, email, encoder.encode(password), isEnabled, verificationCode);
		// insert to db
		accountRepository.addNew(account.getUserName(), account.getEmail(), account.getEncryptPw(),
				account.getIsEnabled(), account.getVerificationCode());
		// get ID
		Integer idAccountAfterCreated = accountRepository.findIdUserByUserName(account.getUserName());
		// set role if have
		List<Integer> idRoleList = accountDTORequest.getIdRoleList();
		if (idRoleList != null && !idRoleList.isEmpty()) {
			for (Integer idRole : idRoleList) {
				roleService.setRole(idAccountAfterCreated, idRole);
			}
		}
		// set type
		List<Integer> idTypeList = accountDTORequest.getIdTypeList();
		if (idTypeList != null && !idTypeList.isEmpty()) {
			for (Integer idType : idTypeList) {
				// System.out.println("idType: " + idType);
				typeService.setType(idAccountAfterCreated, idType);
				if ("1".equals(idType.toString())) {
					memberService.addNewMember(name, dateOfBirth, gender, phone, address, idAccountAfterCreated, false);
				}
				if ("2".equals(idType.toString())) {
					employeeService.addNewEmployee(name, dateOfBirth, gender, phone, address, idAccountAfterCreated,
							idCard, positionId, false);
				}
			}
		}

	}

}
