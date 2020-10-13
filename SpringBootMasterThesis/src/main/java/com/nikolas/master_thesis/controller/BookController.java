package com.nikolas.master_thesis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nikolas.master_thesis.dto.AddUpdateBookDTO;
import com.nikolas.master_thesis.dto.BookDTO;
import com.nikolas.master_thesis.dto.BookListDTO;
import com.nikolas.master_thesis.service.BookService;

import io.micrometer.core.annotation.Timed;

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("api/books/{id}")
	@Timed("getBookById.requests")
	public ResponseEntity<BookDTO> getBook(@PathVariable long id) {
		return ResponseEntity.ok(bookService.getBook(id));
	}

	@GetMapping("api/books")
	@Timed("getAllBooks.requests")
	public ResponseEntity<BookListDTO> getAllBooks() {
		BookListDTO bookListDTO = bookService.getAllBooks();
		return ResponseEntity.ok(bookListDTO);
	}

	@GetMapping(value = "api/categories/{id}/books")
	public ResponseEntity<BookListDTO> getBooksForCategory(@PathVariable(name = "id") Long categoryId) {
		BookListDTO bookListDTO = bookService.getBooksForCategory(categoryId);
		if (bookListDTO != null) {
			return new ResponseEntity<BookListDTO>(bookListDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "api/authors/{id}/books")
	@Timed("getBooksByAuthorId.requests")
	public ResponseEntity<BookListDTO> getBooksByAuthorId(@PathVariable(name = "id") Long authorId) {
		return ResponseEntity.ok(bookService.getBooksForAuthor(authorId));
	}

	@PostMapping("api/books")
	@Timed("createBook.requests")
	public ResponseEntity<?> addBook(@RequestBody AddUpdateBookDTO addUpdateBookDTO) {
		boolean createdBook = bookService.addBook(addUpdateBookDTO);
		if (createdBook) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("api/books/{id}")
	@Timed("updateBook.requests")
	public ResponseEntity<?> updateBook(@RequestBody AddUpdateBookDTO addUpdateBookDTO, @PathVariable long id) {		
		if (addUpdateBookDTO==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			boolean isUpdated = bookService.updateBook(addUpdateBookDTO, id);		
			if (isUpdated) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
			}
		}
	}

	@DeleteMapping("api/books/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable long id) {
		boolean isDeleted = bookService.deleteBook(id);
		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
