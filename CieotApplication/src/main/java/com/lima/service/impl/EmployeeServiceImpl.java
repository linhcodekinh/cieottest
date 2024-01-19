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
	public void addNewEmployee(
			String idCard, Integer positionId, Boolean deleteFlag, Integer accountId) {
		employeeRepository.saveEmployee(idCard, positionId,
				deleteFlag, accountId);
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
	public void updateEmployee(String idCard, Integer positionId, boolean delete_flag, Integer accountId) {
		employeeRepository.updateEmployee(idCard, positionId, delete_flag, accountId);
		
	}

	@Override
	public String existsByAccountId(Integer accountId) {
		return employeeRepository.existsByAccountId(accountId);
	}

}
