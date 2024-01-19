package com.lima.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
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
import com.lima.dto.CodeDTO;
import com.lima.dto.EmployeeDTO;
import com.lima.dto.MemberDTO;
import com.lima.dto.PartDTO;
import com.lima.dto.PositionDTO;
import com.lima.dto.RoleDTO;
import com.lima.dto.TypeDTO;
import com.lima.entity.Account;
import com.lima.entity.AccountRole;
import com.lima.entity.AccountType;
import com.lima.entity.Employee;
import com.lima.entity.Member;
import com.lima.entity.Part;
import com.lima.entity.PartDetail;
import com.lima.entity.Position;
import com.lima.entity.Role;
import com.lima.exception.AccountException;
import com.lima.exception.PartException;
import com.lima.payload.request.AccountDTORequest;
import com.lima.payload.request.AccountDTOUpdateRequest;
import com.lima.repository.AccountRepository;
import com.lima.repository.EmployeeRepository;
import com.lima.repository.MemberRepository;
import com.lima.repository.PositionRepository;
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
	private MemberRepository memberRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PositionRepository positionRepository;

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
	public void update(Integer accountId, AccountDTOUpdateRequest accountDTOUpdateRequest) {
		Optional<Account> accOptional = accountRepository.findById(accountId);
		if (!accOptional.isPresent())
			throw new AccountException("Account id supplied is not exists", HttpStatus.UNPROCESSABLE_ENTITY);
		Account account = accOptional.get();
		account.setIsEnabled(accountDTOUpdateRequest.getIsEnabled());
		account.setUserName(accountDTOUpdateRequest.getUserName());
		accountRepository.save(account);

		roleService.deleteRole(accountId);

		// set role
		List<Integer> idRoleList = accountDTOUpdateRequest.getIdRoleList();
		if (idRoleList != null && !idRoleList.isEmpty()) {
			for (Integer idRole : idRoleList) {
				roleService.setRole(accountId, idRole);
			}
		}
		List<AccountType> accountTypeList = account.getAccountTypeList();
		if (accountTypeList != null && !accountTypeList.isEmpty()) {
			for (AccountType accountType : accountTypeList) {
				if ((MyConstants.TYPE_EMPLOYEE).equals(accountType.getType().getName())) {
					employeeService.deleteByAccountId(accountId);
				}
			}
		}
		typeService.deleteType(accountId);

		// set type
		List<Integer> idTypeList = accountDTOUpdateRequest.getIdTypeList();
		if (idTypeList != null && !idTypeList.isEmpty()) {
			String firstName = accountDTOUpdateRequest.getFirstName();
			String lastName = accountDTOUpdateRequest.getLastName();
			String name = firstName + " " + lastName;
			String dateOfBirth = accountDTOUpdateRequest.getDateOfBirth();
			Integer gender = accountDTOUpdateRequest.getGender();
			String phone = accountDTOUpdateRequest.getPhone();
			String address = accountDTOUpdateRequest.getAddress();
			String idCard = accountDTOUpdateRequest.getIdCard();
			Integer positionId = accountDTOUpdateRequest.getPositionId();
			for (Integer idType : idTypeList) {
				// System.out.println("idType: " + idType);
				typeService.setType(accountId, idType);
				String typeName = typeService.getTypeById(idType);
				if ((MyConstants.TYPE_MEMBER).equals(typeName)) {
					memberService.updateMember(name, firstName, lastName, dateOfBirth, gender, phone, address, false,
							accountId);
				}
				if ((MyConstants.TYPE_EMPLOYEE).equals(typeName)) {
					if (employeeService.existsByAccountId(accountId) != null) {
						employeeService.updateEmployee(idCard, positionId, false, accountId);
					} else {
						employeeService.addNewEmployee(idCard, positionId, false, accountId);
					}
				}
			}
		}
		// accountRepository.save(acc);

		// TODO Auto-generated method stub
		// return null;
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
		Integer gender = accountDTORequest.getGender();
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
					memberService.addNewMember(name, firstName, lastName, dateOfBirth, gender, phone, address, false,
							idAccountAfterCreated);
				}
				if ((MyConstants.TYPE_EMPLOYEE).equals(typeName)) {
					employeeService.addNewEmployee(idCard, positionId, false, idAccountAfterCreated);
				}
			}
		}

	}

	@Override
	public AccountDTO getAccountById(Integer id) {
		Optional<Account> accountOptional = accountRepository.findById(id);
		Account account = accountOptional.get();

		AccountDTO accountDTO = null;
		Member member = null;
		Employee employee = null;
		Position position = null;

		MemberDTO memberDTO = null;
		EmployeeDTO employeeDTO = null;
		PositionDTO positionDTO = null;

		Integer arrRoleId[], arrTypeId[];

		if (account != null) {
			accountDTO = modelMapper.map(account, AccountDTO.class);
			member = memberRepository.findByAccountIdAndDeleteFlag(id, false);
			employee = employeeRepository.findByAccountIdAndDeleteFlag(id, false);

			String emailHead[] = account.getEmail().split("@");
			accountDTO.setEmail(emailHead[0]);
			String emailTail[] = emailHead[1].split("\\.");
			accountDTO.setEmail1(emailTail[0]);
			accountDTO.setEmail2(emailTail[1]);

			int sizeRole = account.getAccountRoleList().size();
			arrRoleId = new Integer[sizeRole];
			for (int i = 0; i < sizeRole; i++) {
				arrRoleId[i] = account.getAccountRoleList().get(i).getRole().getId();
			}

			int sizeType = account.getAccountTypeList().size();
			arrTypeId = new Integer[sizeType];
			for (int i = 0; i < sizeType; i++) {
				arrTypeId[i] = account.getAccountTypeList().get(i).getType().getId();
			}
			accountDTO.setArrTypeId(arrTypeId);
			accountDTO.setArrRoleId(arrRoleId);
		}
		if (member != null) {
			memberDTO = modelMapper.map(member, MemberDTO.class);
			String address[] = member.getAddress().split("\\|");
			memberDTO.setAddress1(address[0]);
			memberDTO.setAddress2(address[1]);
		}
		if (employee != null) {
			position = employee.getPosition();
			employeeDTO = modelMapper.map(employee, EmployeeDTO.class);
		}
		if (position != null) {
			positionDTO = modelMapper.map(position, PositionDTO.class);
		}

		if (memberDTO != null) {
			accountDTO.setMember(memberDTO);
		}
		if (positionDTO != null && employeeDTO != null) {
			employeeDTO.setPositionDTO(positionDTO);
			accountDTO.setEmployee(employeeDTO);
		}
		return accountDTO;
	}

	@Override
	public Integer countByUserName(String userName) {
		Integer check = accountRepository.countByUserName(userName);
		return check;
	}

}
