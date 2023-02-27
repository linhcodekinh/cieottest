package com.lima.service;

import java.util.List;

import com.lima.dto.TypeDTO;

public interface ITypeService {
	void setType(Integer accountId, Integer typeId);

	List<TypeDTO> getAllType();

	String getTypeById(Integer idType);
}
