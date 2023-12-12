package com.nikolas.master_thesis.service;

import com.nikolas.master_thesis.dto.AddUpdateBookDTO;
import com.nikolas.master_thesis.dto.BookDTO;
import com.nikolas.master_thesis.dto.BookListDTO;

public interface BookService {
	
	BookDTO getBook(Long id);
	
	BookListDTO getAllBooks();
	
	BookListDTO getBooksForCategory(Long categoryId);
	
	BookListDTO getBooksForAuthor(Long authorId);
	
	boolean addBook(AddUpdateBookDTO addUpdateBookDTO);
	
	boolean updateBook(AddUpdateBookDTO addUpdateBookDTO, long id);
	
	boolean deleteBook(long id);
}
