package com.lima.payload.request;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class AccountDTOAddRequest {
	private String userName;
	private String email;
	private String password;
	private Boolean isEnabled;
	private String firstName;
	private String lastName;
	private Integer gender;
	private String phone;
	private String address;
	private String dateOfBirth;
	private String idCard;
	private Integer positionId;
	private MultipartFile imageFile;
	private List<Integer> idRoleList;
	private List<Integer> idTypeList;

	public AccountDTOAddRequest() {

	}

	public AccountDTOAddRequest(String userName, String email, String password, Boolean isEnabled, String firstName,
			String lastName, Integer gender, String phone, String address, String dateOfBirth, String idCard,
			Integer positionId, List<Integer> idRoleList, List<Integer> idTypeList, MultipartFile imageFile) {
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
		this.imageFile = imageFile;
	}

	public MultipartFile getImageFile() {
		return imageFile;
	}

	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
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

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
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
