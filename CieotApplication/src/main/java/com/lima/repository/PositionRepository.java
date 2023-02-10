package com.lima.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lima.entity.Position;

@Repository
@Transactional
public interface PositionRepository extends JpaRepository<Position, Integer> {
		
	@Query(value = "select * from position", nativeQuery = true)
	List<Position> getAllPosition();

}
