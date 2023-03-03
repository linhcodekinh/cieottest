package com.lima.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lima.entity.Employee;
import com.lima.repository.EmployeeRepository;
import com.lima.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void addNewEmployee(String name, String dateOfBirth, String gender, String phone, String address,
			Integer accountId, String idCard, Integer positionId, Boolean deleteFlag) {
		employeeRepository.saveEmployee(name, dateOfBirth, gender, phone, address, accountId, idCard, positionId,
				deleteFlag);
	}

	@Override
	public Boolean existsById(Integer id) {
		return employeeRepository.existsById(id);
	}

	@Override
	public void deleteByAccountId(Integer id) {
		employeeRepository.deleteByAccountId(id);		
	}

	@Override
	public Employee findByAccountIdAndDeleteFlag(Integer id, boolean b) {
		return employeeRepository.findByAccountIdAndDeleteFlag(id, b);
	}

	@Override
	public void updateEmployee(String name, String dateOfBirth, String gender, String phone, String address,
			Integer accountId, String idCard, Integer positionId, boolean delete_flag) {
		employeeRepository.updateEmployee(name, dateOfBirth, gender, phone, address, accountId, idCard, positionId, delete_flag);
		
	}


}
