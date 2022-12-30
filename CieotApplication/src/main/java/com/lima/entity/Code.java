package com.lima.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "code")
public class Code {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Boolean active;

	@OneToOne(mappedBy = "code", fetch = FetchType.LAZY)
	private Part part;

	public Code() {

	}

	public Code(Integer id, String name, Boolean active, Part part) {
		super();
		this.id = id;
		this.name = name;
		this.active = active;
		this.part = part;
	}

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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Part getPart() {
		return part;
	}

	public void setPart(Part part) {
		this.part = part;
	}

}
