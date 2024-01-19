package com.lima.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lima.dto.AccountDTO;
import com.lima.entity.Account;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {
	Account findAccountByUserName(String username);

	@Query(value = "select id from account where user_name = ?1", nativeQuery = true)
	Integer findIdUserByUserName(String userName);

	@Query(value = "select user_name from account where user_name = ?1", nativeQuery = true)
	String existsByUserName(String userName);

//	@Query(value = "select user_name from account where user_name = ?1 and encrypt_pw = ?2", nativeQuery = true)
//	String existsByUserNameAndPassword(String username, String password);

	@Query(value = "select email from account where email = ?1", nativeQuery = true)
	String existsByEmail(String email);

	@Modifying
	@Query(value = "insert into account(user_name, email, encrypt_pw, is_enabled, verification_code, delete_flag) value (?1,?2,?3,?4,?5,0)", nativeQuery = true)
	void addNew(String userName, String email, String password, Boolean is_enabled, String randomCode);

	@Query(value = "select * from account where verification_code = ?1", nativeQuery = true)
	Account findAccountByVerificationCode(String verifyCode);

	@Modifying
	@Query(value = "update account set verification_code = ?1 where user_name = ?2", nativeQuery = true)
	void addVerificationCode(String code, String userName);

	@Query(value = "select account.* from account where delete_flag = 0", nativeQuery = true)
	List<Account> getAllAccount();

	@Modifying
	@Query(value = "UPDATE account SET delete_flag = 1 WHERE id = :id", nativeQuery = true)
	void deleteAccount(Integer id);
	
	@Query(value = "select account.* from account where id = :id", nativeQuery = true)
	Account findAccountById(Integer id);

	@Query(value = "select count(user_name) from account where user_name = :userName", nativeQuery = true)
	Integer countByUserName(String userName);
	

}
