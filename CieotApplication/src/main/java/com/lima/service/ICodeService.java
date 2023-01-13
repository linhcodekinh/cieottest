package com.lima.service;

import java.util.List;

import com.lima.dto.CodeDTO;
import com.lima.payload.request.CodeDTORequest;

public interface ICodeService {

	List<CodeDTO> getAll();

	void deleteById(Integer id);

	CodeDTO update(Integer id, CodeDTORequest codeDTORequest);

	CodeDTO create(CodeDTORequest codeDTORequest);

}
