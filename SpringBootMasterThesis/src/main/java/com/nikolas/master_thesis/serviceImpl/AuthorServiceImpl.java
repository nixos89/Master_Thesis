package com.nikolas.master_thesis.serviceImpl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nikolas.master_thesis.dto.AuthorDTO;
import com.nikolas.master_thesis.dto.AuthorListDTO;
import com.nikolas.master_thesis.exception.StoreException;
import com.nikolas.master_thesis.mapper.AuthorMapper;
import com.nikolas.master_thesis.model.Author;
import com.nikolas.master_thesis.model.Book;
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
		List<Author> authors = authorRepository.findAllByOrderByAuthorIdAsc();
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
	public boolean saveAuthor(AuthorDTO authorDTO) {
		if (authorDTO == null) {
			throw new StoreException("Request body is empty!", HttpStatus.BAD_REQUEST);
		}
		Author author = new Author();
		author.setAuthorId(authorDTO.getAuthorId());
		author.setFirstName(authorDTO.getFirstName());
		author.setLastName(authorDTO.getLastName());

		authorRepository.save(author);
		return true;
	}

	@Override
	public boolean updateAuthor(AuthorDTO authorDTO, Long authorId) {
		if (authorDTO == null) {
			throw new StoreException("Error, Request Body is empty!", HttpStatus.BAD_REQUEST);
		}
		Author author = authorRepository.getOne(authorId);
		if (author != null) {
			author.setFirstName(authorDTO.getFirstName());
			author.setLastName(authorDTO.getLastName());
			authorRepository.save(author);
			return true;
		} else {
			throw new StoreException("Error, author for id = " + authorId + " has not been found!",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public Boolean deleteAuthor(Long authorId) {
		Author author = authorRepository.getOne(authorId);
		if (author == null) {
			throw new StoreException("Author with id = " + authorId + "doesn't exist!", HttpStatus.NOT_FOUND);
		}
		Set<Book> books = author.getBooks();
		if (!books.isEmpty()) {
			throw new StoreException("You need to delete books with this author first.", HttpStatus.BAD_REQUEST);
		}

		authorRepository.delete(author);
		return true;
	}

}
