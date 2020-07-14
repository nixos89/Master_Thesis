package com.nikolas.master_thesis.service;

import com.nikolas.master_thesis.dto.AuthorDTO;
import com.nikolas.master_thesis.dto.AuthorListDTO;

public interface AuthorService {

	public AuthorDTO getAuthor(Long id);
	
	public AuthorListDTO getAllAuthors();	

	public boolean saveAuthor(AuthorDTO saveUpdateAuthorDTO);

	public boolean updateAuthor(AuthorDTO saveUpdateAuthorDTO, Long authorId);
	
	public boolean deleteAuthor(Long authorId);
}
