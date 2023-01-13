package com.lima.service;

import java.util.List;

import com.lima.dto.PartDetailDTO;
import com.lima.payload.request.PartDetailDTORequest;

public interface IPartDetailService {

	List<PartDetailDTO> getAll();

	PartDetailDTO create(PartDetailDTORequest partDetailDTORequest);

	PartDetailDTO update(Integer id, PartDetailDTORequest partDetailDTORequest);

	void deleteById(Integer id);

}
