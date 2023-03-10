package com.lima.payload.request;

import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lima.dto.CodeDTO;
import com.lima.dto.LevelDTO;

public class PartDTOWithFileRequest {
	private String name;
	private Integer partNo;
	// private List<PartDetailDTO> partDetailList;
	private CodeDTO code;
	private LevelDTO level;

	@JsonIgnore
	@Transient
	private MultipartFile photoName; // ảnh nếu có

	@JsonIgnore
	@Transient
	private MultipartFile audio; // audio bài nghe

	@JsonIgnore
	@Transient
	private MultipartFile questionExcelFile;

	public PartDTOWithFileRequest() {

	}

	public PartDTOWithFileRequest(String name, Integer partNo, CodeDTO code, LevelDTO level) {
		super();
		this.name = name;
		this.partNo = partNo;
		this.code = code;
		this.level = level;
	}

	public CodeDTO getCode() {
		return code;
	}

	public void setCode(CodeDTO code) {
		this.code = code;
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
