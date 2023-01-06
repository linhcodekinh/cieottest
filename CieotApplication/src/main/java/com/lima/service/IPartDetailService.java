package com.lima.service;

import java.util.List;

import com.lima.dto.PartDetailDTO;
import com.lima.payload.request.PartDetailDTORequest;

public interface IPartDetailService {

	List<PartDetailDTO> getAll();

	PartDetailDTO create(PartDetailDTORequest partDetailDTORequest);

	PartDetailDTO update(PartDetailDTORequest partDetailDTORequest);

}
