package com.nikolas.master_thesis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nikolas.master_thesis.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query("SELECT b FROM Book b RIGHT JOIN b.categories c WHERE c.categoryId = :categoryId")
	public List<Book> findBooksForCategory(@Param("categoryId") Long categoryId);
	
}
