package com.lima.service;

public interface IEmployeeService {

	void addNewEmployee(String name, String dateOfBirth, String gender, String phone, String address,
			Long accountId, String idCard, Integer positionId, Boolean deleteFlag);

}
