package com.nikolas.master_thesis.service;

import com.nikolas.master_thesis.dto.AuthorDTO;
import com.nikolas.master_thesis.dto.AuthorListDTO;

public interface AuthorService {

	AuthorDTO getAuthor(Long id);
	
	AuthorListDTO getAllAuthors();

	boolean saveAuthor(AuthorDTO saveUpdateAuthorDTO);

	boolean updateAuthor(AuthorDTO saveUpdateAuthorDTO, Long authorId);
	
	Boolean deleteAuthor(Long authorId);

}
