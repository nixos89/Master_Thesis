package com.nikolas.master_thesis.service;

import com.nikolas.master_thesis.dto.AddUpdateBookDTO;
import com.nikolas.master_thesis.dto.BookDTO;
import com.nikolas.master_thesis.dto.BookListDTO;

public interface BookService {
	
	public BookDTO getBook(Long id);
	
	public BookListDTO getAllBooks();
	
	public BookListDTO getBooksForCategory(Long categoryId);
	
	public BookListDTO getBooksForAuthor(Long authorId);
	
	public boolean addBook(AddUpdateBookDTO addUpdateBookDTO);
	
	public boolean updateBook(AddUpdateBookDTO addUpdateBookDTO, long id);
	
	public boolean deleteBook(long id);
}
