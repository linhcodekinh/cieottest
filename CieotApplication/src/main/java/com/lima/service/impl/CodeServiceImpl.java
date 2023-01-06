package com.lima.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.dto.CodeDTO;
import com.lima.entity.Code;
import com.lima.payload.request.CodeDTORequest;
import com.lima.repository.CodeRepository;
import com.lima.service.ICodeService;

@Service
public class CodeServiceImpl implements ICodeService {
	@Autowired
	private CodeRepository codeRepository;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<CodeDTO> getAll() {
		List<Code> codeList = codeRepository.findAll();
		List<CodeDTO> codeDTOList = modelMapper.map(codeList, new TypeToken<List<CodeDTO>>() {
		}.getType());
		return codeDTOList;
	}

	@Override
	public void deleteById(Integer id) {
		codeRepository.deleteCode(id);
	}

	@Override
	public CodeDTO update(CodeDTORequest codeDTORequest) {
		Code code = codeRepository.findById(codeDTORequest.getId()).get();
		code.setActive(codeDTORequest.getActive());
		code.setName(codeDTORequest.getName());
		codeRepository.save(code);

		CodeDTO codeDTO = modelMapper.map(code, CodeDTO.class);
		return codeDTO;
	}

	@Override
	public CodeDTO create(CodeDTORequest codeDTORequest) {
		Code code = new Code();
		code.setActive(codeDTORequest.getActive());
		code.setName(codeDTORequest.getName());
		codeRepository.save(code);
		CodeDTO codeDTO = modelMapper.map(code, CodeDTO.class);
		return codeDTO;
	}

}
