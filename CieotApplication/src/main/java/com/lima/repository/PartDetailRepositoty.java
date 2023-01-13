package com.lima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.lima.entity.PartDetail;

public interface PartDetailRepositoty extends JpaRepository<PartDetail, Integer> {

	@Query(value = "UPDATE part_detail SET active = 0 WHERE id = :id", nativeQuery = true)
	void deletePartDetail(@Param("id") Integer id);

}
