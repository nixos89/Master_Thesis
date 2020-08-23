package com.nikolas.master_thesis.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nikolas.master_thesis.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
	
	@Query("SELECT a FROM Author a WHERE a.authorId IN :authorIds ORDER BY a.authorId ASC")
	Set<Author> getAuthorsByAuthorIds(@Param("authorIds") List<Long> authorIds);
	
	@Query("SELECT a FROM Author a JOIN FETCH a.books b WHERE b.bookId = :bookId AND a.authorId IN :authorIds ORDER BY a.authorId ASC")
	Set<Author> getAuthorsByBookIdAndAuthorIds(@Param("bookId") Long bookId, @Param("authorIds") List<Long> authorIds);
	
	List<Author> findAllByOrderByAuthorIdAsc();
	
}
