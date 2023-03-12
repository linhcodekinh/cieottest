package com.lima.payload.request;

import org.springframework.web.multipart.MultipartFile;

import com.lima.dto.CodeDTO;
import com.lima.dto.LevelDTO;

public class PartDTOWithFileRequest {
	private String name;
	private Integer partNo;
	// private List<PartDetailDTO> partDetailList;
	private CodeDTO code;
	private LevelDTO level;

	private MultipartFile photoName; // ảnh nếu có

	private MultipartFile audio; // audio bài nghe

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

	public MultipartFile getPhotoName() {
		return photoName;
	}

	public void setPhotoName(MultipartFile photoName) {
		this.photoName = photoName;
	}

	public MultipartFile getAudio() {
		return audio;
	}

	public void setAudio(MultipartFile audio) {
		this.audio = audio;
	}

	public MultipartFile getQuestionExcelFile() {
		return questionExcelFile;
	}

	public void setQuestionExcelFile(MultipartFile questionExcelFile) {
		this.questionExcelFile = questionExcelFile;
	}

}
