package com.nikolas.master_thesis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nikolas.master_thesis.dto.AuthorDTO;
import com.nikolas.master_thesis.dto.AuthorListDTO;
import com.nikolas.master_thesis.exception.StoreException;
import com.nikolas.master_thesis.mapper.AuthorMapper;
import com.nikolas.master_thesis.model.Author;
import com.nikolas.master_thesis.repository.AuthorRepository;
import com.nikolas.master_thesis.service.AuthorService;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	AuthorMapper authorMapper;

	@Override
	public AuthorDTO getAuthor(Long id) {
		Author author = authorRepository.getOne(id);
		if (author != null) {
			return authorMapper.mapAuthorToAuthorDTO(author);
		} else {
			throw new StoreException("Exception, no author returned!", HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public AuthorListDTO getAllAuthors() {
		AuthorListDTO authorListDTO = new AuthorListDTO();
		List<Author> authors = authorRepository.findAll();
		if (authors == null || authors.isEmpty()) {
			throw new StoreException("No Authors have been found!", HttpStatus.NOT_FOUND);
		} else {
			for (Author author : authors) {
				authorListDTO.getAuthorsDTO().add(authorMapper.mapAuthorToAuthorDTO(author));
			}
			return authorListDTO;
		}
	}

}
