package com.nikolas.master_thesis.serviceImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nikolas.master_thesis.dto.AddUpdateBookDTO;
import com.nikolas.master_thesis.dto.BookDTO;
import com.nikolas.master_thesis.dto.BookListDTO;
import com.nikolas.master_thesis.exception.StoreException;
import com.nikolas.master_thesis.mapper.BookMapper;
import com.nikolas.master_thesis.model.Author;
import com.nikolas.master_thesis.model.Book;
import com.nikolas.master_thesis.model.Category;
import com.nikolas.master_thesis.repository.AuthorRepository;
import com.nikolas.master_thesis.repository.BookRepository;
import com.nikolas.master_thesis.repository.CategoryRepository;
import com.nikolas.master_thesis.repository.OrderItemRepository;
import com.nikolas.master_thesis.repository.UserRepository;
import com.nikolas.master_thesis.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {	

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	OrderItemRepository orderItemRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	BookMapper bookMapper;

	@Override
	public BookDTO getBook(Long id) {
		Book book = bookRepository.getSingleBookById(id);
		if (book != null) {
			return bookMapper.mapBookToBookDTO(book);
		} else {
			throw new StoreException("No book has been found!", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public BookListDTO getAllBooks() {
		BookListDTO bookListDTO = new BookListDTO();
		List<Book> books = bookRepository.getAllBooks();
		if (books != null && !books.isEmpty()) {
			for (Book book : books) {
				bookListDTO.getBookListDTO().add(bookMapper.mapBookToBookDTO(book));
			}
			return bookListDTO;
		} else {
			throw new StoreException("Exception, no books at all!", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public BookListDTO getBooksForCategory(Long categoryId) {
		BookListDTO bookListDTO = new BookListDTO();
		List<Book> books = bookRepository.findBooksForCategory(categoryId);
		if (books != null && !books.isEmpty()) {
			for (Book book : books) {
				bookListDTO.getBookListDTO().add(bookMapper.mapBookToBookDTO(book));
			}
			return bookListDTO;
		} else {
			throw new StoreException("Exception, no books for selected category!", HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public BookListDTO getBooksForAuthor(Long authorId) {
		BookListDTO bookListDTO = new BookListDTO();
		List<Book> books = bookRepository.findBooksForAuthor(authorId);
		if (books != null && !books.isEmpty()) {
			for (Book book : books) {
				bookListDTO.getBookListDTO().add(bookMapper.mapBookToBookDTO(book));
			}
			return bookListDTO;
		} else {
			throw new StoreException("Exception, no books for select author with id = " + authorId,
					HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public boolean addBook(AddUpdateBookDTO addUpdateBookDTO) {
		Book book = new Book();
		book.setAmount(addUpdateBookDTO.getAmount());
		book.setDeleted(addUpdateBookDTO.isDeleted());
		book.setTitle(addUpdateBookDTO.getTitle());
		book.setPrice(addUpdateBookDTO.getPrice());

		Set<Author> bookAuthors = new HashSet<>();
		Set<Category> bookCategories = new HashSet<>();
		Set<Book> books = new HashSet<>();
		books.add(book);

		Set<Author> authorsToBeSaved = authorRepository.getAuthorsByAuthorIds(addUpdateBookDTO.getAuthorIds());
		Map<Long, Author> authorIdsMap = new HashMap<>();
		authorsToBeSaved.forEach(a -> authorIdsMap.put(a.getAuthorId(), a));
		for (Long authorId : addUpdateBookDTO.getAuthorIds()) {
			Author author = authorIdsMap.get(authorId);
			bookAuthors.add(author);
		}

		Set<Category> categoriesToBeSaved = categoryRepository.getCategoriesByCategoryIds(addUpdateBookDTO.getCategoryIds());
		Map<Long, Category> categoryIdsMap = new HashMap<>();
		categoriesToBeSaved.forEach(c -> categoryIdsMap.put(c.getCategoryId(), c));
		for (Long categoryId : addUpdateBookDTO.getCategoryIds()) {
			Category category = categoryIdsMap.get(categoryId);
			bookCategories.add(category);
		}

		book.setAuthors(bookAuthors);
		book.setCategories(bookCategories);

		bookRepository.save(book);

		return true;
	}

	@Override
	public boolean updateBook(AddUpdateBookDTO addUpdateBookDTO, long bookId) {
		if (addUpdateBookDTO == null) {
			throw new StoreException("Error Request body for book is empty!", HttpStatus.BAD_REQUEST);
		}		
		Book book = bookRepository.getSingleBookById(bookId);		
		if (book != null) {			
			book.setTitle(addUpdateBookDTO.getTitle());
			book.setPrice(addUpdateBookDTO.getPrice());
			book.setAmount(addUpdateBookDTO.getAmount());
			book.setDeleted(addUpdateBookDTO.isDeleted());
			 
			Set<Author> authorsToBeSaved = authorRepository.getAuthorsByAuthorIds(addUpdateBookDTO.getAuthorIds());
			Set<Author> authorsToBeDeleted = new HashSet<>();
			book.getAuthors().addAll(authorsToBeSaved);			
			for (Author author : book.getAuthors()) {
				if (!authorsToBeSaved.contains(author)) {
					authorsToBeDeleted.add(author);
				}
			}
			book.getAuthors().removeAll(authorsToBeDeleted);
						
			Set<Category> categoriesToBeSaved = categoryRepository.getCategoriesByCategoryIds(addUpdateBookDTO.getCategoryIds());
			Set<Category> categoriesToBeDeleted = new HashSet<>();
			book.getCategories().addAll(categoriesToBeSaved);
			for (Category category : book.getCategories()) {
				if (!categoriesToBeSaved.contains(category)) {
					categoriesToBeDeleted.add(category);
				}		
			}
			book.getCategories().removeAll(categoriesToBeDeleted);

			bookRepository.save(book);
			return true;
		} else {
			throw new StoreException("Book for requested id = " + bookId + " doesn't exist in database!", HttpStatus.NOT_FOUND);
		}
	}

	public boolean deleteBook(long id) {
		Book book = bookRepository.getOne(id);
		if (book != null) {
			bookRepository.delete(book);
			return true;
		} else {
			throw new StoreException("Error, book with id = " + id + " doesn't exist!", HttpStatus.NOT_FOUND);
		}
	}

}
