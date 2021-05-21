package com.example.demo.service;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.persistence.dao.BookRepository;
import com.example.demo.persistence.model.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book getBookById(Long id) {
		log.trace("Arrived at BookServiceImpl.getBookById method. Parameters {}", id);
		Book book = bookRepository.getOne(id);
		Long bookId = book != null ? book.getId() : null;
		String isbn = book != null ? book.getIsbn() : null;
		log.debug("BookId: {}, ISBN: {}", bookId, isbn);
		return book;
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		log.trace("Arrived at BookServiceImpl.getBookByIsbn method. Parameters {}", isbn);
		Optional<Book> oBook = bookRepository.findByIsbn(isbn);
		Book book = oBook.orElseThrow(
				() -> new EntityNotFoundException(String.format("The book with ISBN: %s wasn't found", isbn)));
		return book;
	}

	@Override
	public Book createBook(Book book) {
		log.trace("Arrived at BookServiceImpl.createBook method. Parameters {}", book);
		String isbn = book.getIsbn();
		if (bookRepository.existsBookByIsbn(isbn)) {
			throw new EntityExistsException(String.format("The book with ISBN: %s already exists", isbn));
		}
		return bookRepository.save(book);
	}

	@Override
	public Book updateBook(Book book) {
		log.trace("Arrived at BookServiceImpl.updateBook method. Parameters {}", book);
		return bookRepository.save(book);
	}

	@Override
	public void deleteBookById(Long id) {
		log.trace("Arrived at BookServiceImpl.deleteBookById method. Parameters {}", id);
		bookRepository.deleteById(id);
	}
}
