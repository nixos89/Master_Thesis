package com.nikolas.master_thesis.mapper;

import org.mapstruct.Mapper;

import com.nikolas.master_thesis.dto.AuthorDTO;
import com.nikolas.master_thesis.model.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
	
	Author mapAuthorDTOToAuthor(AuthorDTO authorDTO);
	
	AuthorDTO mapAuthorToAuthorDTO(Author author);
	
}
