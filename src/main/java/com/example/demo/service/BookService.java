package com.example.demo.service;

import com.example.demo.persistence.model.Book;

public interface BookService {
	
	Book getBookById(Long id);
	
	void deleteBookById(Long id);
	
	Book getBookByIsbn(String isbn);
	
	Book createBook(Book book);
	
	Book updateBook(Book book);

}
