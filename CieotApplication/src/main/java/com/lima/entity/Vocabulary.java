package com.lima.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vocabulary")
public class Vocabulary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "category_gv_id")
	private CategoryGV category_gv;

	public Vocabulary(Integer id, String name, CategoryGV category_gv) {
		super();
		this.id = id;
		this.name = name;
		this.category_gv = category_gv;
	}

	public CategoryGV getCategory_gv() {
		return category_gv;
	}

	public void setCategory_gv(CategoryGV category_gv) {
		this.category_gv = category_gv;
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

}
