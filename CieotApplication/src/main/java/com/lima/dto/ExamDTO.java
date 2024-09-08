package com.lima.dto;

import java.util.List;

public class ExamDTO {
	private List<PartDTO> partDTOs;
	
	public ExamDTO() {
		
	}
	
	public ExamDTO(List<PartDTO> partDTOs) {
		super();
		this.partDTOs = partDTOs;
	}

	public List<PartDTO> getPartDTOs() {
		return partDTOs;
	}

	public void setPartDTOs(List<PartDTO> partDTOs) {
		this.partDTOs = partDTOs;
	}
	
	
}
