package com.lima.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.lima.dto.PartDTO;
import com.lima.payload.request.PartDTORequest;

@Service
public interface IPartService {

	PartDTO getPartDetail(Integer id);

	List<PartDTO> getAll();

	List<PartDTO> getAllByCode(Integer code);

	List<PartDTO> getAllByLevel(Integer level);

	Map<String, List<PartDTO>> getAllByCodePartNo(Integer partNo);
	
	List<PartDTO> getAllByPartNo(Integer partNo);

	PartDTO update(Integer id, PartDTORequest partDTORequest);

	PartDTO create(PartDTORequest partDTORequest);

	void deleteById(Integer id);
}
