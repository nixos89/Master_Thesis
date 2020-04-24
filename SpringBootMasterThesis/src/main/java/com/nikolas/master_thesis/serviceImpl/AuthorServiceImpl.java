package com.nikolas.master_thesis.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nikolas.master_thesis.dto.AuthorDTO;
import com.nikolas.master_thesis.dto.AuthorListDTO;
import com.nikolas.master_thesis.dto.SaveUpdateAuthorDTO;
import com.nikolas.master_thesis.exception.StoreException;
import com.nikolas.master_thesis.mapper.AuthorMapper;
import com.nikolas.master_thesis.model.Author;
import com.nikolas.master_thesis.repository.AuthorRepository;
import com.nikolas.master_thesis.repository.BookRepository;
import com.nikolas.master_thesis.service.AuthorService;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

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

	@Override
	public boolean saveAuthor(SaveUpdateAuthorDTO saveUpdateAuthorDTO) {
		Author author = new Author();
		author.setAuthorId(saveUpdateAuthorDTO.getAuthorId());
		author.setFirstName(saveUpdateAuthorDTO.getFirstName());
		author.setLastName(saveUpdateAuthorDTO.getLastName());

//		Set<Book> books = new HashSet<Book>();
//		if (saveUpdateAuthorDTO.getBookIds().isEmpty() || saveUpdateAuthorDTO.getBookIds() == null) {
//			throw new StoreException("Error, author must contain book ids!", HttpStatus.BAD_REQUEST);
//		}
//		
//		for (Long id : saveUpdateAuthorDTO.getBookIds()) {
//			books.add(bookRepository.getOne(id));
//		}
//		author.setBooks(books);
		authorRepository.save(author);

		return true;
	}

	@Override
	public boolean updateAuthor(SaveUpdateAuthorDTO saveUpdateAuthorDTO, Long authorId) {
		Author author = authorRepository.getOne(authorId);
		if (author != null) {
			author.setFirstName(saveUpdateAuthorDTO.getFirstName());
			author.setLastName(saveUpdateAuthorDTO.getLastName());
			authorRepository.save(author);
			return true;
		} else {
			throw new StoreException("Error, author for id = " + authorId + " has not been found!",
					HttpStatus.NOT_ACCEPTABLE);
		}

	}

}
