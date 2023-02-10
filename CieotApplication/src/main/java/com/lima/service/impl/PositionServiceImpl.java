package com.lima.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.dto.PositionDTO;
import com.lima.entity.Position;
import com.lima.repository.PositionRepository;
import com.lima.service.IPositionService;

@Service
public class PositionServiceImpl implements IPositionService {

	@Autowired
	private PositionRepository positionRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<PositionDTO> getAllPosition() {
		List<Position> positionList = positionRepository.getAllPosition();
		List<PositionDTO> positionDTOList = modelMapper.map(positionList, new TypeToken<List<PositionDTO>>() {
		}.getType());
		return positionDTOList;
	}

}
