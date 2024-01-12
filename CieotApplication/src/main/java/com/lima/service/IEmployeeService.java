package com.lima.service;

import com.lima.entity.Employee;

public interface IEmployeeService {

	void addNewEmployee(Integer accountId,
			String idCard, Integer positionId, Boolean deleteFlag);

	Boolean existsById(Integer id);

	void deleteByAccountId(Integer id);

	Employee findByAccountIdAndDeleteFlag(Integer id, boolean b);

	void updateEmployee(Integer accountId, String idCard, Integer positionId, boolean b);

}
