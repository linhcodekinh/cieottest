package com.lima.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lima.dto.PartDetailDTO;
import com.lima.entity.Part;
import com.lima.entity.PartDetail;
import com.lima.exception.PartDetailException;
import com.lima.exception.PartException;
import com.lima.payload.request.PartDetailDTORequest;
import com.lima.repository.PartDetailRepositoty;
import com.lima.repository.PartRepository;
import com.lima.service.IPartDetailService;

@Service
public class PartDetailServiceImpl implements IPartDetailService {
	@Autowired
	private PartDetailRepositoty partDetailRepositoty;

	@Autowired
	private PartRepository partRepository;

	@Autowired
	private ModelMapper moldelMapper;

	@Override
	public List<PartDetailDTO> getAll() {
		List<PartDetail> partDetailList = partDetailRepositoty.findAll();
		List<PartDetailDTO> partDetailDTOList = moldelMapper.map(partDetailList, new TypeToken<List<PartDetailDTO>>() {
		}.getType());
		return partDetailDTOList;
	}

	@Override
	public PartDetailDTO create(PartDetailDTORequest partDetailDTORequest) {
		Optional<Part> partOptional = partRepository.findById(partDetailDTORequest.getPartId());
		if (!partOptional.isPresent())
			throw new PartException("part id supplied is not exists", HttpStatus.UNPROCESSABLE_ENTITY);
		Part part = partOptional.get();
		PartDetail partDetail = new PartDetail();
		partDetail.setActive(true);
		partDetail.setAnswer1(partDetailDTORequest.getAnswer1());
		partDetail.setAnswer2(partDetailDTORequest.getAnswer2());
		partDetail.setAnswer3(partDetailDTORequest.getAnswer3());
		partDetail.setAnswer4(partDetailDTORequest.getAnswer4());
		partDetail.setCorrectAnswer(partDetailDTORequest.getCorrectAnswer());
		partDetail.setDemonstrate(partDetailDTORequest.getDemonstrate());
		partDetail.setLinkAudio(partDetailDTORequest.getLinkAudio());
		partDetail.setPhotoName(partDetailDTORequest.getPhotoName());
		partDetail.setQuestion(partDetailDTORequest.getQuestion());
		partDetail.setQuestionNo(partDetailDTORequest.getQuestionNo());
		partDetail.setPart(part);
		PartDetailDTO partDetailDTO = moldelMapper.map(partDetail, PartDetailDTO.class);
		return partDetailDTO;
	}

	@Override
	public PartDetailDTO update(PartDetailDTORequest partDetailDTORequest) {
		Integer partIdNew = partDetailDTORequest.getPartId();
		Optional<PartDetail> partDetailOptional = partDetailRepositoty.findById(partDetailDTORequest.getId());
		if (!partDetailOptional.isPresent())
			throw new PartDetailException("part detail id supplied is not exists", HttpStatus.UNPROCESSABLE_ENTITY);
		PartDetail partDetail = partDetailOptional.get();
		Integer partIdOld = partDetail.getPart().getId();

		if (partIdNew != partIdOld) {
			Optional<Part> partOptional = partRepository.findById(partDetailDTORequest.getPartId());
			if (!partOptional.isPresent())
				throw new PartException("part id supplied is not exists", HttpStatus.UNPROCESSABLE_ENTITY);
			Part part = partOptional.get();
			partDetail.setPart(part);
		}

		partDetail.setActive(partDetailDTORequest.getActive());
		partDetail.setAnswer1(partDetailDTORequest.getAnswer1());
		partDetail.setAnswer2(partDetailDTORequest.getAnswer2());
		partDetail.setAnswer3(partDetailDTORequest.getAnswer3());
		partDetail.setAnswer4(partDetailDTORequest.getAnswer4());
		partDetail.setCorrectAnswer(partDetailDTORequest.getCorrectAnswer());
		partDetail.setDemonstrate(partDetailDTORequest.getDemonstrate());
		partDetail.setLinkAudio(partDetailDTORequest.getLinkAudio());
		partDetail.setPhotoName(partDetailDTORequest.getPhotoName());
		partDetail.setQuestion(partDetailDTORequest.getQuestion());
		partDetail.setQuestionNo(partDetailDTORequest.getQuestionNo());

		PartDetailDTO partDetailDTO = moldelMapper.map(partDetail, PartDetailDTO.class);
		return partDetailDTO;
	}

}
