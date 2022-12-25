package com.lima.dto;

import java.util.Set;

public class PartDTO {
	private Integer id;
	private String name;
	private Integer partNo;
	private String code;
	private Set<ContentPartDTO> contentPartList;
	private Set<PartDetailDTO> partDetailList;
	private LevelDTO level;

	public PartDTO() {

	}

	public PartDTO(Integer id, String name, Integer partNo, String code, Set<ContentPartDTO> contentPartList,
			Set<PartDetailDTO> partDetailList, LevelDTO level) {
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
