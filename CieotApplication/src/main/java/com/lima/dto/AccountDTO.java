package com.lima.dto;

import java.util.List;

public class AccountDTO {
	private Integer id;
	private String userName;
	private Boolean isEnabled;
	private String email;
	private String email1;
	private String email2;
	private List<AccountRoleDTO> accountRoleList;
	private List<AccountTypeDTO> accountTypeList;
	private MemberDTO member;
	private EmployeeDTO employee;
	private Integer arrRoleId[];
	private Integer arrTypeId[];

	public AccountDTO() {

	}
	

	public AccountDTO(Integer id, String userName, Boolean isEnabled, String email, String email1, String email2,
			List<AccountRoleDTO> accountRoleList, List<AccountTypeDTO> accountTypeList, MemberDTO member,
			EmployeeDTO employee, Integer[] arrRoleId, Integer[] arrTypeId) {
		super();
		this.id = id;
		this.userName = userName;
		this.isEnabled = isEnabled;
		this.email = email;
		this.email1 = email1;
		this.email2 = email2;
		this.accountRoleList = accountRoleList;
		this.accountTypeList = accountTypeList;
		this.member = member;
		this.employee = employee;
		this.arrRoleId = arrRoleId;
		this.arrTypeId = arrTypeId;
	}



	public List<AccountRoleDTO> getAccountRoleList() {
		return accountRoleList;
	}



	public void setAccountRoleList(List<AccountRoleDTO> accountRoleList) {
		this.accountRoleList = accountRoleList;
	}



	public List<AccountTypeDTO> getAccountTypeList() {
		return accountTypeList;
	}



	public void setAccountTypeList(List<AccountTypeDTO> accountTypeList) {
		this.accountTypeList = accountTypeList;
	}



	public Integer[] getArrRoleId() {
		return arrRoleId;
	}

	public void setArrRoleId(Integer[] arrRoleId) {
		this.arrRoleId = arrRoleId;
	}

	public Integer[] getArrTypeId() {
		return arrTypeId;
	}

	public void setArrTypeId(Integer[] arrTypeId) {
		this.arrTypeId = arrTypeId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public MemberDTO getMember() {
		return member;
	}

	public void setMember(MemberDTO member) {
		this.member = member;
	}

	public EmployeeDTO getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeDTO employee) {
		this.employee = employee;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

}
