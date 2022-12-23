package com.lima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lima.entity.Part;

@Repository
public interface PartRepositoty extends JpaRepository<Part, Integer> {
}
