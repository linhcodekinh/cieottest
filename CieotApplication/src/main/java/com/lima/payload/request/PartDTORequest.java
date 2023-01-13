package com.lima.payload.request;

import java.util.List;

import com.lima.dto.CodeDTO;
import com.lima.dto.LevelDTO;
import com.lima.dto.PartDetailDTO;

public class PartDTORequest {

	private String name;
	private Integer partNo;
	private CodeDTO code;
	private List<PartDetailDTO> partDetailList;
	private LevelDTO level;

	public PartDTORequest() {

	}

	public PartDTORequest(String name, Integer partNo, CodeDTO code, List<PartDetailDTO> partDetailList,
			LevelDTO level) {
		super();
		this.name = name;
		this.partNo = partNo;
		this.code = code;
		this.partDetailList = partDetailList;
		this.level = level;
	}

	public CodeDTO getCode() {
		return code;
	}

	public void setCode(CodeDTO code) {
		this.code = code;
	}

	public List<PartDetailDTO> getPartDetailList() {
		return partDetailList;
	}

	public void setPartDetailList(List<PartDetailDTO> partDetailList) {
		this.partDetailList = partDetailList;
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

	public LevelDTO getLevel() {
		return level;
	}

	public void setLevel(LevelDTO level) {
		this.level = level;
	}

}
