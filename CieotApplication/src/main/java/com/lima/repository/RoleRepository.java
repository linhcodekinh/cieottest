package com.lima.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lima.entity.Role;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Modifying
	@Query(value = "insert into account_role(account_id, role_id) value (?1, ?2)", nativeQuery = true)
	void setDefaultRole(Long accountId, Integer roleId);
}
