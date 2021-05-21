package com.example.demo.web.error;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class Rfc7807ErrorControllerAdvice extends ResponseEntityExceptionHandler {

	@Override
	protected final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		/*
		 * List<String> errorMessages = ex.getBindingResult().getAllErrors().stream()
		 * .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.
		 * toList());
		 */
		List<String> errorMessages = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errorMessages.add(fieldName.concat(" ").concat(errorMessage));
		});
		log.error("handleMethodArgumentNotValid: errors=[{}]", errorMessages);
		try {
			return new ResponseEntity<>(
					Rfc7807Error.builder().type(new URI("urn:ac-payroll-errors:missing-or-invalid-parameters"))
							.title("missing or invalid parameters").status(HttpStatus.BAD_REQUEST.value())
							.instance(new URI(request.getDescription(false))).detail(errorMessages.get(0)).build(),
					HttpStatus.BAD_REQUEST);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return super.handleMethodArgumentNotValid(ex, headers, status, request);
		}
	}

	@ExceptionHandler(ConstraintViolationException.class)
	protected ResponseEntity<Object> onConstraintViolationException(ConstraintViolationException ex,
			WebRequest request) {

		List<String> errorMessages = new ArrayList<>();
		ex.getConstraintViolations().forEach(constraintViolation -> {
			String fieldName = constraintViolation.getPropertyPath().toString();
			String errorMessage = constraintViolation.getMessage();
			errorMessages.add(fieldName.concat(" ").concat(errorMessage));
		});
		log.error("handleConstraintViolationException: errors=[{}]", errorMessages);
		try {
			return new ResponseEntity<>(
					Rfc7807Error.builder().type(new URI("urn:ac-payroll-errors:missing-or-invalid-parameters"))
							.title("missing or invalid parameters").status(HttpStatus.BAD_REQUEST.value())
							.instance(new URI(request.getDescription(false))).detail(errorMessages.get(0)).build(),
					HttpStatus.BAD_REQUEST);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Object> onEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {

		List<String> errorMessages = new ArrayList<>();
		String errorMessage = ex.getMessage();
		errorMessages.add(errorMessage);
		log.error("handleEntityNotFoundException: errors=[{}]", errorMessages);
		try {
			return new ResponseEntity<>(
					Rfc7807Error.builder().type(new URI("urn:ac-payroll-errors:no-results"))
							.title("No results for provided parameters").status(HttpStatus.NOT_FOUND.value())
							.instance(new URI(request.getDescription(false))).detail(errorMessages.get(0)).build(),
					HttpStatus.NOT_FOUND);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@ExceptionHandler(EntityExistsException.class)
	protected ResponseEntity<Object> onEntityExistsException(EntityExistsException ex, WebRequest request) {

		List<String> errorMessages = new ArrayList<>();
		String errorMessage = ex.getMessage();
		errorMessages.add(errorMessage);
		log.error("handleEntityExistsException: errors=[{}]", errorMessages);
		try {
			return new ResponseEntity<>(
					Rfc7807Error.builder().type(new URI("urn:ac-payroll-errors:business-object-already-exists"))
							.title("The entity already exists in the system").status(HttpStatus.UNPROCESSABLE_ENTITY.value())
							.instance(new URI(request.getDescription(false))).detail(errorMessages.get(0)).build(),
					HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> onException(Exception ex, WebRequest request) {

		String errorMessage = ex.getMessage();
		log.error("handleException: errors=[{}]", errorMessage);
		try {
			return new ResponseEntity<>(
					Rfc7807Error.builder().type(new URI("urn:ac-payroll-errors:internal-server-error"))
							.title("Internal Server Error").status(HttpStatus.INTERNAL_SERVER_ERROR.value())
							.instance(new URI(request.getDescription(false))).detail(errorMessage).build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
	}
}
