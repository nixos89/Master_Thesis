package com.nikolas.master_thesis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nikolas.master_thesis.model.Author;
import com.nikolas.master_thesis.repository.AuthorRepository;
import com.nikolas.master_thesis.service.AuthorService;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@Override
	public Author getAuthor(Long id) {
		Author a = authorRepository.getOne(id);
		if (a != null) {
			return a;
		} else {
			return null;
		}
	}

	
	@Override
	public List<Author> getAllAuthors() {
		List<Author> authors = authorRepository.findAll();
		if (authors != null) {
			return authors;
		} else {
			return null;
		}
	}

}
