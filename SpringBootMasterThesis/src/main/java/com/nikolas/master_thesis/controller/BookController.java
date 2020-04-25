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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nikolas.master_thesis.dto.AddUpdateBookDTO;
import com.nikolas.master_thesis.dto.BookDTO;
import com.nikolas.master_thesis.dto.BookListDTO;
import com.nikolas.master_thesis.service.BookService;

@RestController
@RequestMapping("api/books")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping("/{id}")
	public ResponseEntity<BookDTO> getBook(@PathVariable long id) {
		return ResponseEntity.ok(bookService.getBook(id));
	}

	@GetMapping
	public ResponseEntity<BookListDTO> getAllBooks() {
		BookListDTO bookListDTO = bookService.getAllBooks();
		if (bookListDTO != null) {
			return new ResponseEntity<BookListDTO>(bookListDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/categories")
	public ResponseEntity<BookListDTO> getBooksForCategory(@RequestParam(name = "id") Long categoryId) {
		BookListDTO bookListDTO = bookService.getBooksForCategory(categoryId);
		if (bookListDTO != null) {
			return new ResponseEntity<BookListDTO>(bookListDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value = "/authors")
	public ResponseEntity<BookListDTO> getBooksForAuthor(@RequestParam(name = "id") Long authorId) {
		BookListDTO bookListDTO = bookService.getBooksForAuthor(authorId);
		if (bookListDTO != null) {
			return new ResponseEntity<BookListDTO>(bookListDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<?> addBook(@RequestBody AddUpdateBookDTO addUpdateBookDTO) {
		return ResponseEntity.ok(bookService.addBook(addUpdateBookDTO));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateBook(@RequestBody AddUpdateBookDTO addUpdateBookDTO, @PathVariable long id) {
		return ResponseEntity.ok(bookService.updateBook(addUpdateBookDTO, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteBook(@PathVariable long id) {
		return ResponseEntity.ok(bookService.deleteBook(id));
	}

}
