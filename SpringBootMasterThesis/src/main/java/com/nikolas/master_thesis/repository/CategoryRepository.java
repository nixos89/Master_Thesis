package com.nikolas.master_thesis.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nikolas.master_thesis.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("SELECT c FROM Category c WHERE c.categoryId IN :categoryIds ORDER BY c.categoryId ASC")
	Set<Category> getCategoriesByCategoryIds(@Param("categoryIds") List<Long> categoryIds);
		
	@Query("SELECT c FROM Category c JOIN FETCH c.books b WHERE b.bookId = :bookId AND c.categoryId IN :categoryIds ORDER BY c.categoryId ASC")
	Set<Category> getCategoriesByBookIdAndCategoryIds(@Param("bookId") Long bookId, @Param("categoryIds") List<Long> categoryIds);
	
	List<Category> findAllByOrderByCategoryIdAsc();
	
}
