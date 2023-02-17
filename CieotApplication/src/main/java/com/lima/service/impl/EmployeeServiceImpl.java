package com.lima.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.repository.EmployeeRepository;
import com.lima.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void addNewEmployee(String name, String dateOfBirth, String gender, String phone, String address,
			Long accountId, String idCard, Integer positionId, Boolean deleteFlag) {
		employeeRepository.saveEmployee(name, dateOfBirth, gender, phone, address, accountId, idCard, positionId,
				deleteFlag);
	}

}
