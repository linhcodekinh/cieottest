package com.lima.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lima.entity.Member;

@Repository
@Transactional
public interface MemberRepository extends JpaRepository<Member, Long> {

	@Modifying
	@Transactional
	@Query(value = "insert into member(name, date_of_birth,gender,phone,address,account_id,delete_flag) value (?1,?2,?3,?4,?5,?6,?7)", nativeQuery = true)
	void saveMemberToRegister(String name, String dateOfBirth, String gender, String phone, String address,
			Long accountId, Boolean deleteFlag);

	Member findByAccountIdAndDeleteFlag(Long id, Boolean b);
}
