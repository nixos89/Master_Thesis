package com.nikolas.master_thesis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nikolas.master_thesis.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	@Query("SELECT b FROM Book b LEFT JOIN b.categories c WHERE c.categoryId = :catId")
	public List<Book> findBooksForCategory(@Param("catId") Long categoryId);
	
	@Query("SELECT b FROM Book b LEFT JOIN b.authors a WHERE a.authorId = :autId")
	public List<Book> findBooksForAuthor(@Param("autId") Long authorId);
	
}
