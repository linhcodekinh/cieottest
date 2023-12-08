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
		if (memberService.findByAccountIdAndDeleteFlag(id, false) != null) {
			memberService.deleteByAccountId(id);
		}
		if (employeeService.findByAccountIdAndDeleteFlag(id, false) != null) {
			employeeService.deleteByAccountId(id);
		}
	}

	@Override
	public AccountDTO update(Integer accountId, AccountDTORequest accountDTORequest) {
		Optional<Account> accOptional = accountRepository.findById(accountId);
		if (!accOptional.isPresent())
			throw new AccountException("Account id supplied is not exists", HttpStatus.UNPROCESSABLE_ENTITY);
		Account account = accOptional.get();
		account.setIsEnabled(accountDTORequest.getIsEnabled());
		roleService.deleteRole(accountId);
		//typeService.deleteType(accountId);
		
		// set role
		List<Integer> idRoleList = accountDTORequest.getIdRoleList();
		if (idRoleList != null && !idRoleList.isEmpty()) {
			for (Integer idRole : idRoleList) {
				roleService.setRole(accountId, idRole);
			}
		}
		List<AccountType> accountTypeList = account.getAccountTypeList();
		//set type
		if (accountTypeList != null && !accountTypeList.isEmpty()) {
			String firstName = accountDTORequest.getFirstName();
			String lastName = accountDTORequest.getLastName();
			String name = firstName + " " + lastName;
			String dateOfBirth = accountDTORequest.getDateOfBirth();
			String gender = accountDTORequest.getGender();
			String phone = accountDTORequest.getPhone();
			String address = accountDTORequest.getAddress();
			String idCard = accountDTORequest.getIdCard();
			Integer positionId = accountDTORequest.getPositionId();
			for (AccountType accountType : accountTypeList) {
				Integer accId = accountType.getAccount().getId();
				String typeName = accountType.getType().getName();

				if ((MyConstants.TYPE_MEMBER).equals(typeName)) {
					memberService.updateMember(name, firstName, lastName, dateOfBirth, gender, phone, address, accountId, false);
				}
				if ((MyConstants.TYPE_EMPLOYEE).equals(typeName)) {
					employeeService.updateEmployee(name, firstName, lastName, dateOfBirth, gender, phone, address, accountId,
							idCard, positionId, false);
				}
			}
		}

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(AccountDTORequest accountDTORequest) {
		// Init account
		String userName = accountDTORequest.getUserName();
		String email = accountDTORequest.getEmail();
		String password = accountDTORequest.getPassword();
		String firstName = accountDTORequest.getFirstName();
		String lastName = accountDTORequest.getLastName();
		String name = firstName + " " + lastName;
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
				String typeName = typeService.getTypeById(idType);
				if ((MyConstants.TYPE_MEMBER).equals(typeName)) {
					memberService.addNewMember(name, firstName, lastName, dateOfBirth, gender, phone, address, idAccountAfterCreated, false);
				}
				if ((MyConstants.TYPE_EMPLOYEE).equals(typeName)) {
					employeeService.addNewEmployee(name, firstName, lastName, dateOfBirth, gender, phone, address, idAccountAfterCreated,
							idCard, positionId, false);
				}
			}
		}

	}

}
