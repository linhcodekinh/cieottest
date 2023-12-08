package com.lima.dto;

public class EmployeeDTO {

	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String idCard;
	private String phone;
	private String deleteFlag;
	private String gender;
	private PositionDTO positionDTO;
	
	public EmployeeDTO() {
		
	}

	public EmployeeDTO(String firstName, String lastName, String dateOfBirth, String idCard, String phone, String deleteFlag, String gender,
			PositionDTO positionDTO) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.idCard = idCard;
		this.phone = phone;
		this.deleteFlag = deleteFlag;
		this.gender = gender;
		this.positionDTO = positionDTO;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public PositionDTO getPositionDTO() {
		return positionDTO;
	}

	public void setPositionDTO(PositionDTO positionDTO) {
		this.positionDTO = positionDTO;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

}
