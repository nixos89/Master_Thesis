package com.nikolas.master_thesis.service;

import java.util.List;

import com.nikolas.master_thesis.model.Author;

public interface AuthorService {

	public Author getAuthor(Long id);
	
	public List<Author> getAllAuthors();	

}
