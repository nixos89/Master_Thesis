package com.nikolas.master_thesis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nikolas.master_thesis.dto.BookDTO;
import com.nikolas.master_thesis.dto.BookListDTO;
import com.nikolas.master_thesis.exception.StoreException;
import com.nikolas.master_thesis.mapper.BookMapper;
import com.nikolas.master_thesis.model.Book;
import com.nikolas.master_thesis.repository.BookRepository;
import com.nikolas.master_thesis.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	// TODO: implement ALL methods in BookServiceImpl
	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookMapper bookMapper;

	@Override
	public BookDTO getBook(Long id) {
		Book book = bookRepository.getOne(id);
		if (book != null) {
			return bookMapper.convertBookToBookDTO(book);
		} else {
			throw new StoreException("No book has been found!", HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public BookListDTO getAllBooks() {
		BookListDTO bookListDTO = new BookListDTO();
		List<Book> books = bookRepository.findAll();
		if (books != null && !books.isEmpty()) {
			for (Book book : books) {
				bookListDTO.getBookListDTO().add(bookMapper.convertBookToBookDTO(book));
			}
			return bookListDTO;
		} else {
			throw new StoreException("Exception, no books at all!", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public BookListDTO getBooksForCategory(Long categoryId) {
		BookListDTO bookListDTO = new BookListDTO();
		List<Book> books = bookRepository.findBooksForCategory(categoryId);
		if (books != null && !books.isEmpty()) {
			for (Book book : books) {
				bookListDTO.getBookListDTO().add(bookMapper.convertBookToBookDTO(book));
			}
			return bookListDTO;
		} else {
			throw new StoreException("Exception, no books for selected category!", HttpStatus.NOT_FOUND);
		}
	}

}
