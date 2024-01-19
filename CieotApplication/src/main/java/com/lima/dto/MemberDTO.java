package com.lima.dto;

public class MemberDTO {
	
	private String firstName;
	private String lastName;
	private String name;
	private String dateOfBirth;
	private Integer gender;
	private String phone;
	private String address1;
	private String address2;
	private Boolean deleteFlag;
	
	public MemberDTO() {
		
	}
	

	public MemberDTO(String firstName, String lastName, String name, String dateOfBirth, Integer gender, String phone,
			String address1, String address2, Boolean deleteFlag) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.phone = phone;
		this.address1 = address1;
		this.address2 = address2;
		this.deleteFlag = deleteFlag;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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

	public String getAddress1() {
		return address1;
	}


	public void setAddress1(String address1) {
		this.address1 = address1;
	}


	public String getAddress2() {
		return address2;
	}


	public void setAddress2(String address2) {
		this.address2 = address2;
	}


	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	

}


