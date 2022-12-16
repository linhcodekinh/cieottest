package com.lima.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@OneToMany(mappedBy = "role")
	@JsonBackReference
	private Set<AccountRole> accountRoleList;

	public Role() {
	}

	public Role(Integer id, String name, Set<AccountRole> accountRoleList) {
		this.id = id;
		this.name = name;
		this.accountRoleList = accountRoleList;
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

	public Set<AccountRole> getAccountRoleList() {
		return accountRoleList;
	}

	public void setAccountRoleList(Set<AccountRole> accountRoleList) {
		this.accountRoleList = accountRoleList;
	}
}
