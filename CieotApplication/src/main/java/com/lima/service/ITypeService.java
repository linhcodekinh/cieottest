package com.lima.service;

import java.util.List;

import com.lima.dto.TypeDTO;

public interface ITypeService {
	void setType(Long accountId, Integer typeId);

	List<TypeDTO> getAllType();
}
