package com.nikolas.master_thesis.service;

import com.nikolas.master_thesis.dto.AuthorDTO;
import com.nikolas.master_thesis.dto.AuthorListDTO;
import com.nikolas.master_thesis.dto.SaveUpdateAuthorDTO;

public interface AuthorService {

	public AuthorDTO getAuthor(Long id);
	
	public AuthorListDTO getAllAuthors();	

	public boolean saveAuthor(SaveUpdateAuthorDTO saveUpdateAuthorDTO);

	public boolean updateAuthor(SaveUpdateAuthorDTO saveUpdateAuthorDTO, Long authorId);
}
