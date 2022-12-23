package com.lima.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private Integer parentId;
	private Integer orderBy;
	private String part;
	
	public Menu() {
		
	}
	
	public Menu(Integer id, String name, Integer parentId, Integer orderBy, String part) {
		super();
		this.id = id;
		this.name = name;
		this.parentId = parentId;
		this.orderBy = orderBy;
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
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer order) {
		this.orderBy = order;
	}
	public String getPart() {
		return part;
	}
	public void setPart(String part) {
		this.part = part;
	}
	
	
	
}
