package com.lima.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lima.entity.Employee;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Modifying
	@Transactional
	@Query(value = "insert into employee(name, date_of_birth, gender, phone, address, account_id, id_card, position_id, delete_flag) value (?1,?2,?3,?4,?5,?6,?7,?8,?9)", nativeQuery = true)
	void saveEmployee(String name, String dateOfBirth, String gender, String phone, String address,
			Integer accountId, String idCard, Integer positionId, Boolean deleteFlag);
	
	@Modifying
	@Query(value = "UPDATE employee SET delete_flag = 1 WHERE account_id = :id", nativeQuery = true)
	void deleteByAccountId(Integer id);

	Employee findByAccountIdAndDeleteFlag(Integer id, boolean b);
}
