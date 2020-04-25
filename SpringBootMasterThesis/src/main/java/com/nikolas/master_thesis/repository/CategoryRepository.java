package com.nikolas.master_thesis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nikolas.master_thesis.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	
	
}
