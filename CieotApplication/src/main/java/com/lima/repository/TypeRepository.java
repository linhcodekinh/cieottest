package com.lima.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lima.entity.Type;

@Repository
@Transactional
public interface TypeRepository extends JpaRepository<Type, Integer> {
	@Modifying
	@Query(value = "insert into account_type(account_id, type_id) value (?1, ?2)", nativeQuery = true)
	void setType(Integer accountId, Integer roleId);

	@Query(value = "select * from type", nativeQuery = true)
	List<Type> getAllType();

	@Query(value = "select name from type where id = ?1", nativeQuery = true)
	String getTypeById(Integer idType);

	@Modifying
	@Query(value = "delete from account_type where account_id = ?1", nativeQuery = true)
	void deleteType(Integer id);

}
