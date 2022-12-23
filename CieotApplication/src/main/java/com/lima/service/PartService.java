package com.lima.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lima.dto.PartDTO;

@Service
public interface PartService {
	List<PartDTO> findAll();
}
