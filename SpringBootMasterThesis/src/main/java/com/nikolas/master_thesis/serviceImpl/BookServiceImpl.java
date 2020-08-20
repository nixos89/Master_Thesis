package com.nikolas.master_thesis.serviceImpl;

import java.util.HashSet;
import java.util.List;
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
		Book book = bookRepository.getOne(id);
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
		Set<Book> books = new HashSet<>();
		books.add(book);

		for (Long authorId : addUpdateBookDTO.getAuthorIds()) {
			Author author = authorRepository.getOne(authorId);
			bookAuthors.add(author);
		}

		Set<Category> bookCategories = new HashSet<>();
		for (Long categoryId : addUpdateBookDTO.getCategoryIds()) {
			Category category = categoryRepository.getOne(categoryId);
			bookCategories.add(category);
		}

		book.setAuthors(bookAuthors);
		book.setCategories(bookCategories);

		bookRepository.save(book);

		return true;
	}

	@Override
	public boolean updateBook(AddUpdateBookDTO addUpdateBookDTO, long id) {
		if (addUpdateBookDTO == null) {
			throw new StoreException("Error Request body for book is empty!", HttpStatus.BAD_REQUEST);
		}
		Book book = bookRepository.getOne(id);		
		if (book != null) {
			book.setTitle(addUpdateBookDTO.getTitle());
			book.setPrice(addUpdateBookDTO.getPrice());
			book.setAmount(addUpdateBookDTO.getAmount());
			book.setDeleted(addUpdateBookDTO.isDeleted());

			Set<Author> bookAuthors = new HashSet<>();
			Set<Book> books = new HashSet<>();
			books.add(book);

			for (Long authorId : addUpdateBookDTO.getAuthorIds()) {
				Author author = authorRepository.getOne(authorId);
				bookAuthors.add(author);
			}

			Set<Category> bookCategories = new HashSet<>();
			for (Long categoryId : addUpdateBookDTO.getCategoryIds()) {
				Category category = categoryRepository.getOne(categoryId);
				bookCategories.add(category);
			}

			book.setAuthors(bookAuthors);
			book.setCategories(bookCategories);

			bookRepository.save(book);

			return true;
		} else {
			throw new StoreException("Book for requested id = " + id + " doesn't exist in database!", HttpStatus.NOT_FOUND);
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
