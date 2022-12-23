package com.lima.dto;

public class ContentPartDTO {
	private Integer id;
	private Integer contentId;
	private Integer partId;

	public ContentPartDTO() {

	}

	public ContentPartDTO(Integer id, Integer contentId, Integer partId) {
		super();
		this.id = id;
		this.contentId = contentId;
		this.partId = partId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public Integer getPartId() {
		return partId;
	}

	public void setPartId(Integer partId) {
		this.partId = partId;
	}

}
