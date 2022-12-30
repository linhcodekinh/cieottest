package com.lima.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lima.dto.PartDTO;
import com.lima.payload.request.PartDTORequest;

@Service
public interface IPartService {

	PartDTO getPartDetail(Integer id);
	
	List<PartDTO> getAll();

	List<PartDTO> getAllByCode(Integer code);

	List<PartDTO> getAllByLevel(Integer level);

	List<PartDTO> getAllByPartNo(Integer partNo);
	
	PartDTO update(PartDTORequest partDTORequest);
	
	PartDTO create(PartDTORequest partDTORequest);

	void deleteById(Integer id);
}
