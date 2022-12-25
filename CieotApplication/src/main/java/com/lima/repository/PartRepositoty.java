package com.lima.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lima.entity.Part;

@Repository
public interface PartRepositoty extends JpaRepository<Part, Integer> {

	@Query(value = "SELECT * FROM part WHERE code LIKE %:code%", nativeQuery = true)
	List<Part> findListByCode(@Param("code") String code);
}
