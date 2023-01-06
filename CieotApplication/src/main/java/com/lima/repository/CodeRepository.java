package com.lima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lima.entity.Code;

@Repository
public interface CodeRepository extends JpaRepository<Code, Integer> {
	@Query(value = "UPDATE code SET active = 0 WHERE id = :id", nativeQuery = true)
	void deleteCode(@Param("id") Integer id);

}
