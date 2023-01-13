package com.lima.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lima.dto.PartDTO;
import com.lima.dto.PartDetailDTO;
import com.lima.entity.Code;
import com.lima.entity.Level;
import com.lima.entity.Part;
import com.lima.entity.PartDetail;
import com.lima.exception.PartException;
import com.lima.payload.request.PartDTORequest;
import com.lima.repository.CodeRepository;
import com.lima.repository.PartDetailRepository;
import com.lima.repository.PartRepository;
import com.lima.service.IPartService;

@Service
public class PartServiceImpl implements IPartService {

	@Autowired
	private PartRepository partRepository;

	@Autowired
	private PartDetailRepository partDetailRepository;

	@Autowired
	private CodeRepository codeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PartDTO getPartDetail(Integer id) {
		Optional<Part> partOptional = partRepository.findById(id);
		if (!partOptional.isPresent())
			throw new PartException("PartId supplied is not exists", HttpStatus.UNPROCESSABLE_ENTITY);
		Part part = partOptional.get();
		List<PartDetail> partDetailList = partDetailRepository.findByPartId(part.getId());
		part.setPartDetailList(partDetailList);
		PartDTO partDTO = modelMapper.map(part, PartDTO.class);
		return partDTO;
	}
	
	@Override
	public List<PartDTO> getAllByPartNo(Integer partNo) {
		List<Part> parts = partRepository.findListByPartNo(partNo);
		List<PartDTO> partDTOList = new ArrayList<PartDTO>();
		for (Part part : parts) {
			List<PartDetail> partDetailList = partDetailRepository.findByPartId(part.getId());
			part.setPartDetailList(partDetailList);
			PartDTO partDTO = modelMapper.map(part, PartDTO.class);
			partDTOList.add(partDTO);
		}
		return partDTOList;
	}

	@Override
	public List<PartDTO> getAllByCode(Integer code) {
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

	@Override
	public List<PartDTO> getAllByLevel(Integer level) {
		List<Part> parts = partRepository.findListByLevel(level);
		List<PartDTO> partDTOList = new ArrayList<PartDTO>();
		for (Part part : parts) {
			List<PartDetail> partDetailList = partDetailRepository.findByPartId(part.getId());
			part.setPartDetailList(partDetailList);
			PartDTO partDTO = modelMapper.map(part, PartDTO.class);
			partDTOList.add(partDTO);
		}
		return partDTOList;
	}

	@Override
	public Map<String, List<PartDTO>> getAllByCodePartNo(Integer partNo) {
		Map<String, List<PartDTO>> mapPartDTOList = new HashMap<>();
		List<Code> codeList = codeRepository.findAll();

		for (Code code : codeList) {
			Integer codeId = code.getId();
			String codeName = code.getName();
			List<Part> partList = partRepository.findListByCodePartNo(codeId, partNo);
			List<PartDTO> partDTOList = new ArrayList<PartDTO>();
			for (Part part : partList) {

				List<PartDetail> partDetailList = partDetailRepository.findByPartId(part.getId());
				part.setPartDetailList(partDetailList);
				PartDTO partDTO = modelMapper.map(part, PartDTO.class);
				partDTOList.add(partDTO);
			}
			mapPartDTOList.put(codeName, partDTOList);
		}

		return mapPartDTOList;
	}

	@Override
	public PartDTO update(Integer id, PartDTORequest partDTORequest) {
		Optional<Part> partOptional = partRepository.findById(id);
		if (!partOptional.isPresent())
			throw new PartException("Part id supplied is not exists", HttpStatus.UNPROCESSABLE_ENTITY);

		Part part = partOptional.get();
		// level
		Level levelOld = part.getLevel();
		Level levelNew = modelMapper.map(partDTORequest.getLevel(), Level.class);
		if (!levelOld.equals(levelNew)) {
			part.setLevel(levelNew);
		}
		// code
		Code codeOld = part.getCode();
		Code codeNew = modelMapper.map(partDTORequest.getCode(), Code.class);
		if (!codeOld.equals(codeNew)) {
			part.setCode(codeNew);
		}

//		if (!levelOld.equals(levelInput)) {
//			levelOld.setActive(false);
//			levelRepository.save(levelOld);
//			levelInput.setActive(true);
//			levelRepository.save(levelInput);
//		}

//		List<PartDetail> partDetailOldList = part.getPartDetailList();
//		for (PartDetail partDetail : partDetailOldList) {
//			partDetail.setActive(false);
//			partDetailRepository.save(partDetail);
//		}
		// part detail
		List<PartDetail> partDetailList = modelMapper.map(partDTORequest.getPartDetailList(),
				new TypeToken<List<PartDetail>>() {
				}.getType());
		for (PartDetail partDetailNew : partDetailList) {
			// partDetail.setActive(true);
			PartDetail partDetailOld = partDetailRepository.findById(partDetailNew.getId()).get();
			if (!partDetailOld.equals(partDetailNew)) {
				partDetailNew.setPart(part);
				partDetailRepository.save(partDetailNew);
			}
		}
		part.setPartDetailList(partDetailList);

		part.setName(partDTORequest.getName());
		part.setPartNo(partDTORequest.getPartNo());

		partRepository.save(part);

		PartDTO partDTO = modelMapper.map(part, PartDTO.class);

		return partDTO;
	}

	@Override
	public PartDTO create(PartDTORequest partDTORequest) {
		LocalDateTime localDateTime = LocalDateTime.now();
		ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
		Long data = zdt.toInstant().toEpochMilli();

		Level level = modelMapper.map(partDTORequest.getLevel(), Level.class);
		Code code = modelMapper.map(partDTORequest.getCode(), Code.class);

		Part part = new Part();
		part.setCode(code);
		part.setCreatedDate(data);
		part.setName(partDTORequest.getName());
		part.setPartNo(partDTORequest.getPartNo());
		part.setLevel(level);
		partRepository.save(part);

		List<PartDetailDTO> partDetailDTOList = partDTORequest.getPartDetailList();
		if (partDetailDTOList.size() != 0) {
			for (PartDetailDTO partDetailData : partDetailDTOList) {
				PartDetail partDetail = modelMapper.map(partDetailData, PartDetail.class);
				partDetail.setPart(part);
				partDetailRepository.save(partDetail);
			}
		}
		PartDTO partDTO = modelMapper.map(part, PartDTO.class);
		return partDTO;
	}

	@Override
	public List<PartDTO> getAll() {
		List<Part> partList = partRepository.findAll();
		List<PartDTO> partDTOList = modelMapper.map(partList, new TypeToken<List<PartDTO>>() {
		}.getType());
		return partDTOList;
	}

	@Override
	public void deleteById(Integer id) {
		partRepository.deletePart(id);
	}

}
