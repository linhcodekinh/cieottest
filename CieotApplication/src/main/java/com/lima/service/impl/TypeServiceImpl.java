package com.lima.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.dto.TypeDTO;
import com.lima.entity.Type;
import com.lima.repository.TypeRepository;
import com.lima.service.ITypeService;

@Service
public class TypeServiceImpl implements ITypeService {

	@Autowired
	private TypeRepository typeRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void setType(Integer accountId, Integer typeId) {
		typeRepository.setType(accountId, typeId);
	}

	@Override
	public List<TypeDTO> getAllType() {
		List<Type> typeList = typeRepository.getAllType();
		List<TypeDTO> typeDTOList = modelMapper.map(typeList, new TypeToken<List<TypeDTO>>() {
		}.getType());
		return typeDTOList;
	}

	@Override
	public String getTypeById(Integer idType) {
		return typeRepository.getTypeById(idType);
	}

}
