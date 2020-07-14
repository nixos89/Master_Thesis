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

@RestController
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("api/books/{id}")
	public ResponseEntity<BookDTO> getBook(@PathVariable long id) {
		return ResponseEntity.ok(bookService.getBook(id));
	}

	@GetMapping("api/books")
	public ResponseEntity<BookListDTO> getAllBooks() {
		BookListDTO bookListDTO = bookService.getAllBooks();
		if (bookListDTO != null) {
			return new ResponseEntity<BookListDTO>(bookListDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
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
	public ResponseEntity<BookListDTO> getBooksForAuthor(@PathVariable(name = "id") Long authorId) {
		BookListDTO bookListDTO = bookService.getBooksForAuthor(authorId);
		if (bookListDTO != null) {
			return new ResponseEntity<BookListDTO>(bookListDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("api/books")
	public ResponseEntity<?> addBook(@RequestBody AddUpdateBookDTO addUpdateBookDTO) {
		return ResponseEntity.ok(bookService.addBook(addUpdateBookDTO));
	}

	@PutMapping("api/books/{id}")
	public ResponseEntity<?> updateBook(@RequestBody AddUpdateBookDTO addUpdateBookDTO, @PathVariable long id) {
		boolean isUpdated = bookService.updateBook(addUpdateBookDTO, id);
		if(isUpdated) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

	@DeleteMapping("api/books/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable long id) {
		return ResponseEntity.ok(bookService.deleteBook(id));
	}

}
