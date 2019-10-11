package com.nikolas.master_thesis.mapper;

import org.mapstruct.Mapper;

import com.nikolas.master_thesis.dto.BookDTO;
import com.nikolas.master_thesis.model.Book;

@Mapper(componentModel = "spring", uses = {AuthorMapper.class, CategoryMapper.class})
public interface BookMapper {
	
	Book convertBookDTOToBook(BookDTO bookDTO);
	
	BookDTO convertBookToBookDTO(Book book);
}
