package com.nikolas.master_thesis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nikolas.master_thesis.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	@Query("SELECT b FROM Book b JOIN FETCH b.authors a JOIN FETCH b.categories c WHERE b.bookId = :id ORDER BY b.bookId ASC")
	public Book getSingleBookById(@Param("id") Long bookId);
	
	@Query("SELECT b FROM Book b LEFT JOIN FETCH b.categories c WHERE c.categoryId = :catId ORDER BY b.bookId ASC")
	public List<Book> findBooksForCategory(@Param("catId") Long categoryId);
	
	@Query("SELECT b FROM Book b LEFT JOIN FETCH b.authors a WHERE a.authorId = :autId ORDER BY b.bookId ASC")
	public List<Book> findBooksForAuthor(@Param("autId") Long authorId);
	
	@Query("SELECT DISTINCT b FROM Book b JOIN FETCH b.authors a JOIN FETCH b.categories c ORDER BY b.bookId ASC")
	public List<Book> getAllBooks();
	
	@Query("SELECT DISTINCT b FROM Book b JOIN FETCH b.authors a JOIN FETCH b.categories c WHERE b.bookId IN :bookIds ORDER BY b.bookId ASC")
	public List<Book> getAllBooksFromOrder(@Param("bookIds") List<Long> bookIds);
}
