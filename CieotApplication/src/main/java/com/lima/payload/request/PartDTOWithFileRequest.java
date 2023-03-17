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

	@Transient
	@JsonIgnore
	private MultipartFile photoFile; // ảnh nếu có

	@Transient
	@JsonIgnore
	private MultipartFile audioFile; // audio bài nghe

	@Transient
	@JsonIgnore
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

	public MultipartFile getPhotoFile() {
		return photoFile;
	}

	public void setPhotoFile(MultipartFile photoFile) {
		this.photoFile = photoFile;
	}

	public MultipartFile getAudioFile() {
		return audioFile;
	}

	public void setAudioFile(MultipartFile audioFile) {
		this.audioFile = audioFile;
	}

	public MultipartFile getQuestionExcelFile() {
		return questionExcelFile;
	}

	public void setQuestionExcelFile(MultipartFile questionExcelFile) {
		this.questionExcelFile = questionExcelFile;
	}

}
