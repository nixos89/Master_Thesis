package com.nikolas.master_thesis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikolas.master_thesis.model.Author;
import com.nikolas.master_thesis.service.AuthorService;

@RestController("api/authors")
public class AuthorController {

	@Autowired
	AuthorService authorService;
	
	@GetMapping
	public ResponseEntity<List<Author>> getAllAuthors() {
		List<Author> authors = authorService.getAllAuthors();
		if(authors != null) {
			return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}		
	}
	
	

}
