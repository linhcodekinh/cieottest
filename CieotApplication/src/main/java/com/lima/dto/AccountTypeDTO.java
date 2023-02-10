package com.lima.dto;

public class AccountTypeDTO {
	private TypeDTO type;

	public AccountTypeDTO() {

	}

	public AccountTypeDTO(TypeDTO type) {
		super();
		this.type = type;
	}

	public TypeDTO getType() {
		return type;
	}

	public void setType(TypeDTO type) {
		this.type = type;
	}

}
