package com.lima.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.dto.PartDTO;
import com.lima.entity.Part;
import com.lima.entity.PartDetail;
import com.lima.repository.PartDetailRepository;
import com.lima.repository.PartRepositoty;
import com.lima.service.PartService;

@Service
public class PartServiceImpl implements PartService {

	@Autowired
	private PartRepositoty partRepository;

	@Autowired
	private PartDetailRepository partDetailRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PartDTO getPartDetail(Integer id) {
		Optional<Part> partOptional = partRepository.findById(id);
		if (!partOptional.isPresent())
			System.out.println("loi");
		Part part = partOptional.get();
		List<PartDetail> partDetailList = partDetailRepository.findByPartId(part.getId());
		part.setPartDetailList(partDetailList);
		PartDTO partDTO = modelMapper.map(part, PartDTO.class);
		return partDTO;
	}

	@Override
	public List<PartDTO> getAllByCode(String code) {
		List<Part> parts = partRepository.findListByCode(code);
		List<PartDTO> partDTOList = new ArrayList<PartDTO>();
		for (Part part : parts) {
			List<PartDetail> partDetailList = partDetailRepository.findByPartId(part.getId());
			part.setPartDetailList(partDetailList);
			PartDTO partDTO = modelMapper.map(part, PartDTO.class);
			partDTOList.add(partDTO);
		}
		return partDTOList;
	}

}
