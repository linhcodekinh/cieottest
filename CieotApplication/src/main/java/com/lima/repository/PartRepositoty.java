package com.lima.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lima.entity.Part;

@Repository
public interface PartRepositoty extends JpaRepository<Part, Integer> {

	@Query(value = "SELECT * FROM part WHERE code_id = :code", nativeQuery = true)
	List<Part> findListByCode(@Param("code") Integer code);

	@Query(value = "SELECT * FROM part WHERE level_id = :level", nativeQuery = true)
	List<Part> findListByLevel(@Param("level") Integer level);

	@Query(value = "SELECT * FROM part WHERE part_no = :partNo", nativeQuery = true)
	List<Part> findListByPartNo(@Param("partNo") Integer partNo);

	@Query(value = "UPDATE part SET active = 1 WHERE id = :id", nativeQuery = true)
	void deletePart(@Param("id") Integer id);

}
