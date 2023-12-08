package com.lima.payload.request;

import java.util.List;

public class AccountDTORequest {
	private String userName;
	private String email;
	private String password;
	private Boolean isEnabled;
	private String firstName;
	private String lastName;
	private String gender;
	private String phone;
	private String address;
	private String dateOfBirth;
	private String idCard;
	private Integer positionId;
	private List<Integer> idRoleList;
	private List<Integer> idTypeList;

	public AccountDTORequest() {

	}
	
	

	public AccountDTORequest(String userName, String email, String password, Boolean isEnabled, String firstName, String lastName,
			String gender, String phone, String address, String dateOfBirth, String idCard, Integer positionId,
			List<Integer> idRoleList, List<Integer> idTypeList) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.isEnabled = isEnabled;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.idCard = idCard;
		this.positionId = positionId;
		this.idRoleList = idRoleList;
		this.idTypeList = idTypeList;
	}



	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public Integer getPositionId() {
		return positionId;
	}

	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}

	public List<Integer> getIdRoleList() {
		return idRoleList;
	}

	public void setIdRoleList(List<Integer> idRoleList) {
		this.idRoleList = idRoleList;
	}

	public List<Integer> getIdTypeList() {
		return idTypeList;
	}

	public void setIdTypeList(List<Integer> idTypeList) {
		this.idTypeList = idTypeList;
	}

}
