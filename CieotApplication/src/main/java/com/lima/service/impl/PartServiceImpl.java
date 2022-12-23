package com.lima.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.dto.PartDTO;
import com.lima.entity.Part;
import com.lima.entity.PartDetail;
import com.lima.repository.PartDetailRepository;
import com.lima.repository.PartRepositoty;
import com.lima.service.PartService;
import com.lima.utils.PartMapper;

@Service
public class PartServiceImpl implements PartService {

	@Autowired
	private PartRepositoty partRepository;

	@Autowired
	private PartDetailRepository partDetailRepository;

	private PartMapper partMapper;

	public PartServiceImpl(PartMapper partMapper) {
		this.partMapper = partMapper;
	}

	@Override
	public List<PartDTO> findAll() {
		List<Part> parts = partRepository.findAll();
		List<PartDTO> partDTOList = new ArrayList<PartDTO>();
		for (Part part : parts) {
			List<PartDetail> partDetailList = partDetailRepository.findByPartId(part.getId());
			part.setPartDetailList(partDetailList);
			PartDTO partDTO = partMapper.toTarget(part);
			partDTOList.add(partDTO);
		}
		return partDTOList;
	}

}
