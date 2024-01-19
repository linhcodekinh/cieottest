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
	@Query(value = "insert into employee(id_card, position_id, delete_flag, account_id) value (?1,?2,?3,?4)", nativeQuery = true)
	void saveEmployee(String idCard, Integer positionId, Boolean deleteFlag, Integer accountId);
	
	@Modifying
	@Query(value = "UPDATE employee SET delete_flag = 1 WHERE account_id = :id", nativeQuery = true)
	void deleteByAccountId(Integer id);

	Employee findByAccountIdAndDeleteFlag(Integer id, boolean b);

	@Modifying
	@Query(value = "UPDATE employee SET id_card = ?1, position_id = ?2, delete_flag = ?3 WHERE account_id = ?4", nativeQuery = true)
	void updateEmployee(String idCard, Integer positionId, boolean delete_flag, Integer accountId);
	
	
	@Query(value = "select id_card from employee where account_id = ?1", nativeQuery = true)
	String existsByAccountId(Integer accountId);
}
