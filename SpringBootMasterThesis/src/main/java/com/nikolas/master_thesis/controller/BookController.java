package com.nikolas.master_thesis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nikolas.master_thesis.dto.BookListDTO;
import com.nikolas.master_thesis.service.BookService;

@RestController
@RequestMapping("api/books")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping
	public ResponseEntity<BookListDTO> getAllBooks() {
		BookListDTO bookListDTO = bookService.getAllBooks();
		if (bookListDTO != null) {
			return new ResponseEntity<BookListDTO>(bookListDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping(value="/categories")
	public ResponseEntity<BookListDTO> getBooksForCategory(@RequestParam(name="id") Long categoryId) {
		BookListDTO bookListDTO = bookService.getBooksForCategory(categoryId);
		if (bookListDTO != null) {
			return new ResponseEntity<BookListDTO>(bookListDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
