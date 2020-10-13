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
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;

import com.nikolas.master_thesis.dto.AuthorDTO;
import com.nikolas.master_thesis.dto.AuthorListDTO;
import com.nikolas.master_thesis.service.AuthorService;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@GetMapping
	@Timed("getAllAuthors.requests")
	public ResponseEntity<AuthorListDTO> getAllAuthors() {
		return ResponseEntity.ok(authorService.getAllAuthors());
	}

	@GetMapping("/{id}")
	@Timed("getAuthor.requests")
	public ResponseEntity<AuthorDTO> getAuthor(@PathVariable Long id) {
		return ResponseEntity.ok(authorService.getAuthor(id));
	}
	
	@PostMapping
	@Timed("createAuthor.requests")
	public ResponseEntity<?> saveAuthor(@RequestBody AuthorDTO authorDTO) {
		boolean savedAuthor = authorService.saveAuthor(authorDTO);
		if (savedAuthor) {
			return new ResponseEntity<>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{authorId}")
	@Timed("updateAuthor.requests")
	public ResponseEntity<?> updateAuthor(@RequestBody AuthorDTO authorDTO, @PathVariable Long authorId){
		boolean isUpdated = authorService.updateAuthor(authorDTO, authorId);
		if(isUpdated) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{id}")
	@Timed("deleteAuthor.requests")
	public ResponseEntity<?> deleteAuthor(@PathVariable Long id) {
		boolean isDeleted = authorService.deleteAuthor(id);		
		if(isDeleted) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
