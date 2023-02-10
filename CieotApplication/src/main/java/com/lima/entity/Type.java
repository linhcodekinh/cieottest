package com.lima.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "type")
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@OneToMany(mappedBy = "type")
	@JsonBackReference
	private List<AccountType> accountTypeList;

	public Type() {

	}

	public Type(Integer id, String name, List<AccountType> accountTypeList) {
		super();
		this.id = id;
		this.name = name;
		this.accountTypeList = accountTypeList;
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

	public List<AccountType> getAccountTypeList() {
		return accountTypeList;
	}

	public void setAccountTypeList(List<AccountType> accountTypeList) {
		this.accountTypeList = accountTypeList;
	}

}
