package com.lima.dto;

public class RoleDTO {
	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoleDTO(Integer id, String namee) {
		super();
		this.id = id;
		this.name = namee;
	}

	public RoleDTO() {

	}
}
