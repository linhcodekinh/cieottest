package com.lima.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "part")
public class Part {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private Integer partNo;

	private String code;

	@OneToMany(mappedBy = "part")
	@JsonBackReference
	private List<ContentPart> contentPartList;

//	@ManyToOne
//	@JoinColumn(name = "level_id")
//	private Level level;

	@OneToMany(mappedBy = "part")
	@JsonBackReference
	private List<PartDetail> partDetailList;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "level_id", referencedColumnName = "id")
	private Level level;

	public Part() {

	}

	public Part(Integer id, String name, Integer partNo, String code, List<ContentPart> contentPartList,
			List<PartDetail> partDetailList, Level level) {
		super();
		this.id = id;
		this.name = name;
		this.partNo = partNo;
		this.code = code;
		this.contentPartList = contentPartList;
		this.partDetailList = partDetailList;
		this.level = level;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public Integer getPartNo() {
		return partNo;
	}

	public void setPartNo(Integer partNo) {
		this.partNo = partNo;
	}

	public List<ContentPart> getContentPartList() {
		return contentPartList;
	}

	public void setContentPartList(List<ContentPart> contentPartList) {
		this.contentPartList = contentPartList;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public List<PartDetail> getPartDetailList() {
		return partDetailList;
	}

	public void setPartDetailList(List<PartDetail> partDetailList) {
		this.partDetailList = partDetailList;
	}

}
