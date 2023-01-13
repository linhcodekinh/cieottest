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
@Table(name = "category_gv")
public class CategoryGV {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer type;

	@OneToMany(mappedBy = "category_gv")
	@JsonBackReference
	private List<Grammar> grammarList;
	
	@OneToMany(mappedBy = "category_gv")
	@JsonBackReference
	private List<Vocabulary> vocabularyList;

	public CategoryGV(Integer id, String name, Integer type, List<Grammar> grammarList) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.grammarList = grammarList;
	}

	public List<Grammar> getGrammarList() {
		return grammarList;
	}

	public void setGrammarList(List<Grammar> grammarList) {
		this.grammarList = grammarList;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
