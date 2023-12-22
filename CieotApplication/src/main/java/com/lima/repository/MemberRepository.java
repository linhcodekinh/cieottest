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
	@Query(value = "insert into member(name, first_name, last_name, date_of_birth, gender, phone, address, account_id, delete_flag) value (?1,?2,?3,?4,?5,?6,?7,?8,?9)", nativeQuery = true)
	void saveMemberToRegister(String name, String firstName, String lastName, String dateOfBirth, String gender, String phone, String address,
			Integer accountId, Boolean deleteFlag);

	Member findByAccountIdAndDeleteFlag(Integer id, Boolean b);

	@Modifying
	@Query(value = "UPDATE member SET delete_flag = 1 WHERE account_id = :id", nativeQuery = true)
	void deleteByAccountId(Integer id);

	@Modifying
	@Query(value = "UPDATE member "
			+ "SET name = ?1, "
			+ "	   first_name = ?2	"
			+ "	   last_name = ?3	"
			+ "	   dateOfBirth = ?4	"
			+ "	   gender = ?5	"
			+ "	   phone = ?6	"
			+ "	   address = ?7	"
			+ "	   delete_flag = ?9	"
			+ "WHERE account_id = ?8", nativeQuery = true)
	void updateMember(String name, String firstName, String lastName, String dateOfBirth, String gender, String phone, String address,
			Integer accountId, boolean delete_flag);

}
