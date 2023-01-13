package com.lima.payload.request;

public class CodeDTORequest {
	private String name;
	private Boolean active;

	public CodeDTORequest() {

	}

	public CodeDTORequest(String name, Boolean active) {
		super();
		this.name = name;
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
