package com.lima.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lima.entity.Position;
import com.lima.entity.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Modifying
	@Query(value = "insert into account_role(account_id, role_id) value (?1, ?2)", nativeQuery = true)
	void setDefaultRole(Integer accountId, Integer roleId);

	@Modifying
	@Query(value = "insert into account_role(account_id, role_id) value (?1, ?2)", nativeQuery = true)
	void setRole(Integer accountId, Integer roleId);
	
	@Modifying
	@Query(value = "delete from account_role where account_id = ?1", nativeQuery = true)
	void deleteRole(Integer id);

	@Query(value = "select * from role", nativeQuery = true)
	List<Role> getAllRole();
}
