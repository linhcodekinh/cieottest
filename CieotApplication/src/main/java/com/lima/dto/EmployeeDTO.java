package com.lima.dto;

public class EmployeeDTO {

	private String idCard;
	private PositionDTO position;
	
	public EmployeeDTO() {
		
	}

	public EmployeeDTO(String idCard, PositionDTO position) {
		super();
		this.idCard = idCard;
		this.position = position;
	}

	public PositionDTO getPosition() { //properties cua chuoi JSON
		return position;
	}

	public void setPositionDTO(PositionDTO position) {
		this.position = position;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

}
