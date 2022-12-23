package com.lima.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lima.entity.PartDetail;

public interface PartDetailRepository extends JpaRepository<PartDetail, Integer> {

	@Query(value = "SELECT * FROM part_detail WHERE part_id = :id", nativeQuery = true)
	List<PartDetail> findByPartId(@Param("id") Integer id);
}
