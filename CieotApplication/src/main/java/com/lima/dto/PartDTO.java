package com.lima.dto;

import java.util.Set;

public class PartDTO {
	private Integer id;
	private Long createdDate;
	private String name;
	private Integer partNo;
	private Set<ContentPartDTO> contentPartList;
	private Set<PartDetailDTO> partDetailList;
	private LevelDTO level;
	private CodeDTO code;
	private Boolean active;

	public PartDTO() {

	}

	public PartDTO(Integer id, Long createdDate, String name, Integer partNo, Set<ContentPartDTO> contentPartList,
			Set<PartDetailDTO> partDetailList, LevelDTO level, CodeDTO code, Boolean active) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.name = name;
		this.partNo = partNo;
		this.contentPartList = contentPartList;
		this.partDetailList = partDetailList;
		this.level = level;
		this.code = code;
		this.active = active;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public CodeDTO getCode() {
		return code;
	}

	public void setCode(CodeDTO code) {
		this.code = code;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}

	public Set<PartDetailDTO> getPartDetailList() {
		return partDetailList;
	}

	public void setPartDetailList(Set<PartDetailDTO> partDetailList) {
		this.partDetailList = partDetailList;
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

	public Set<ContentPartDTO> getContentPartList() {
		return contentPartList;
	}

	public void setContentPartList(Set<ContentPartDTO> contentPartList) {
		this.contentPartList = contentPartList;
	}

	public LevelDTO getLevel() {
		return level;
	}

	public void setLevel(LevelDTO level) {
		this.level = level;
	}

}
