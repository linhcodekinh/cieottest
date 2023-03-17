package com.lima.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	private Long createdDate;

	private String name;

	private Integer partNo;

	private Boolean active;

	private String photoLink;

	private String audioLink;

	private String excelLink;

	@OneToMany(mappedBy = "part")
	@JsonBackReference
	private List<ContentPart> contentPartList;

//	@ManyToOne
//	@JoinColumn(name = "level_id")
//	private Level level;

	@OneToMany(mappedBy = "part")
	@JsonBackReference
	private List<PartDetail> partDetailList;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "level_id", referencedColumnName = "id")
	private Level level;

//	@OneToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "code_id", referencedColumnName = "id")
//	private Code code;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "code_id")
	private Code code;

	public Part() {

	}

	public Part(Integer id, Long createdDate, String name, Integer partNo, Boolean active, String photoLink,
			String audioLink, String excelLink, List<ContentPart> contentPartList, List<PartDetail> partDetailList,
			Level level, Code code) {
		super();
		this.id = id;
		this.createdDate = createdDate;
		this.name = name;
		this.partNo = partNo;
		this.active = active;
		this.photoLink = photoLink;
		this.audioLink = audioLink;
		this.excelLink = excelLink;
		this.contentPartList = contentPartList;
		this.partDetailList = partDetailList;
		this.level = level;
		this.code = code;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Code getCode() {
		return code;
	}

	public void setCode(Code code) {
		this.code = code;
	}

	public Long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
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

	public String getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}

	public String getAudioLink() {
		return audioLink;
	}

	public void setAudioLink(String audioLink) {
		this.audioLink = audioLink;
	}

	public String getExcelLink() {
		return excelLink;
	}

	public void setExcelLink(String excelLink) {
		this.excelLink = excelLink;
	}

}
