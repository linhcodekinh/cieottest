package com.lima.service;

import com.lima.entity.Employee;

public interface IEmployeeService {

	void addNewEmployee(String idCard, Integer positionId, Boolean deleteFlag, Integer accountId);

	Boolean existsById(Integer id);

	void deleteByAccountId(Integer id);

	Employee findByAccountIdAndDeleteFlag(Integer id, boolean b);

	void updateEmployee(String idCard, Integer positionId, boolean delete_flag, Integer accountId);
	
	String existsByAccountId(Integer accountId);

}
