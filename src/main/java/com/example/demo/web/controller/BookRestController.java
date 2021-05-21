package com.example.demo.web.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.persistence.model.Book;
import com.example.demo.service.BookService;
import com.example.demo.validator.OnCreate;
import com.example.demo.validator.OnUpdate;
import com.example.demo.web.dto.inout.ApiBaseRequestDto;
import com.example.demo.web.dto.inout.ApiBaseResponseDto;
import com.example.demo.web.dto.inout.GetBookRequestDto;
import com.example.demo.web.dto.inout.GetBookResponseDto;
import com.example.demo.web.dto.inout.SaveBookRequestDto;
import com.example.demo.web.dto.inout.SaveBookResponseDto;
import com.example.demo.web.dto.model.BookDto;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = { "/api/v1/books" }, produces = "application/json")
@Slf4j
@Validated
public class BookRestController {

	@Autowired
	private BookService bookService;

	@Autowired
	private ModelMapper modelMapper;

	@GetMapping(consumes = "application/json")
	public ResponseEntity<?> getBook(@Valid @RequestParam(required = true, name = "isbn") String isbn,
			@Valid @RequestBody GetBookRequestDto requestDto) {
		log.trace("Arrived at BookServiceImpl.getBook method. Parameters isbn: {}, request: {}", isbn, requestDto);
		final UUID uuid = UUID.randomUUID();
		Book book = bookService.getBookByIsbn(isbn);
		log.debug("Returned book: {} with isbn: {}", book, isbn);
		BookDto bookDto = modelMapper.map(book, BookDto.class);
		GetBookResponseDto responseDto = GetBookResponseDto.builder().authorizationNumber(uuid.toString()).book(bookDto)
				.build();
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@GetMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<?> getBook(@Valid @PathVariable(required = true) Long id,
			@Valid @RequestBody GetBookRequestDto requestDto) {
		log.trace("Arrived at BookServiceImpl.getBook method. Parameters id: {}, request: {}", id, requestDto);
		final UUID uuid = UUID.randomUUID();
		Book book = bookService.getBookById(id);
		log.debug("Returned book: {} with id: {}", book, id);
		BookDto bookDto = modelMapper.map(book, BookDto.class);
		GetBookResponseDto responseDto = GetBookResponseDto.builder().authorizationNumber(uuid.toString()).book(bookDto)
				.build();
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@PostMapping(consumes = "application/json")
	@Validated(OnCreate.class)
	public ResponseEntity<?> createBook(@Valid @RequestBody SaveBookRequestDto requestDto) {
		log.trace("Arrived at BookServiceImpl.createBook method. Parameters {}", requestDto);
		final UUID uuid = UUID.randomUUID();
		Book book = modelMapper.map(requestDto.getBook(), Book.class);
		log.debug("Mapped Book persistence object from Dto: {}", book);
		Book newBook = bookService.createBook(book);
		log.debug("New persisted object: {}", newBook);
		BookDto newBookDto = modelMapper.map(newBook, BookDto.class);
		log.debug("Mapped Dto from persistence object: {}", newBookDto);
		SaveBookResponseDto responseDto = SaveBookResponseDto.builder().authorizationNumber(uuid.toString())
				.createdAt(LocalDateTime.now()).book(newBookDto).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
	}

	@PutMapping(consumes = "application/json")
	@Validated(OnUpdate.class)
	public ResponseEntity<?> updateBook(@Valid @RequestBody SaveBookRequestDto requestDto) {
		log.trace("Arrived at BookServiceImpl.updateBook method. Parameters {}", requestDto);
		final UUID uuid = UUID.randomUUID();
		Book book = modelMapper.map(requestDto.getBook(), Book.class);
		log.debug("Mapped Book persistence object from Dto: {}", book);
		Book oldBook = bookService.updateBook(book);
		log.debug("Old persisted object: {}", oldBook);
		BookDto oldBookDto = modelMapper.map(oldBook, BookDto.class);
		log.debug("Mapped Dto from persistence object: {}", oldBookDto);
		SaveBookResponseDto responseDto = SaveBookResponseDto.builder().authorizationNumber(uuid.toString())
				.modifiedAt(LocalDateTime.now()).book(oldBookDto).build();
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}

	@DeleteMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<?> deleteBook(@Valid @PathVariable(required = true) Long id,
			@Valid @RequestBody ApiBaseRequestDto requestDto) {
		log.trace("Arrived at BookServiceImpl.deleteBook method. Parameters id: {}, request: {}", id, requestDto);
		final UUID uuid = UUID.randomUUID();
		bookService.deleteBookById(id);
		log.debug("The book with id: {} was successfully deleted", id);
		ApiBaseResponseDto responseDto = ApiBaseResponseDto.builder().authorizationNumber(uuid.toString()).build();
		return ResponseEntity.status(HttpStatus.OK).body(responseDto);
	}
}
