package com.nikolas.master_thesis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nikolas.master_thesis.dto.AuthorDTO;
import com.nikolas.master_thesis.dto.AuthorListDTO;
import com.nikolas.master_thesis.service.AuthorService;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@GetMapping
	public ResponseEntity<AuthorListDTO> getAllAuthors() {
		return ResponseEntity.ok(authorService.getAllAuthors());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Long id) {
		AuthorDTO author = authorService.getAuthor(id);
		if (author != null) {
			return new ResponseEntity<AuthorDTO>(author, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping
	public ResponseEntity<Boolean> saveAuthor(@RequestBody AuthorDTO authorDTO){
		return ResponseEntity.ok(authorService.saveAuthor(authorDTO));
	}
	
	@PutMapping("/{authorId}")
	public ResponseEntity<Boolean> updateAuthor(@RequestBody AuthorDTO authorDTO, @PathVariable Long authorId){
		return ResponseEntity.ok(authorService.updateAuthor(authorDTO, authorId));
	}

}
