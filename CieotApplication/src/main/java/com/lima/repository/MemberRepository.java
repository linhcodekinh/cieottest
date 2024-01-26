package com.lima.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lima.entity.Member;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, Integer> {

	@Modifying
	@Transactional
	@Query(value = "insert into member(name, first_name, last_name, date_of_birth, gender, phone, image, address, delete_flag, account_id) value (?1,?2,?3,?4,?5,?6,?7,?8,?9,?10)", nativeQuery = true)
	void saveMemberToRegister(String name, String firstName, String lastName, String dateOfBirth, Integer gender,
			String phone, String image, String address, Boolean deleteFlag, Integer accountId);

	Member findByAccountIdAndDeleteFlag(Integer id, Boolean b);

	@Modifying
	@Query(value = "UPDATE member SET delete_flag = 1 WHERE account_id = :id", nativeQuery = true)
	void deleteByAccountId(Integer id);

	@Modifying
	@Query(value = "UPDATE member SET name = ?1,first_name = ?2,last_name = ?3,date_of_birth = ?4,gender = ?5,phone = ?6,address = ?7,delete_flag = ?8 WHERE account_id = ?9 ", nativeQuery = true)
	void updateMember(String name, String firstName, String lastName, String dateOfBirth, Integer gender, String phone,
			String address, boolean delete_flag, Integer accountId);

}
