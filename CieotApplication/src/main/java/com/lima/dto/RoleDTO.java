package com.lima.dto;

public class RoleDTO {
	private Integer id;
	private String namee;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return namee;
	}

	public void setName(String name) {
		this.namee = name;
	}

	public RoleDTO(Integer id, String namee) {
		super();
		this.id = id;
		this.namee = namee;
	}

	public RoleDTO() {

	}
}
