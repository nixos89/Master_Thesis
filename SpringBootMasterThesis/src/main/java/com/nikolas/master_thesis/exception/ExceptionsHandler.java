package com.nikolas.master_thesis.exception;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

	@ExceptionHandler(StoreException.class)
	public ResponseEntity<?> handleApiException(StoreException ex, WebRequest request) {
		LOGGER.error("Error, exception " + ex.getClass().getSimpleName() + " occurred! Cause: \n");
		ex.printStackTrace();
		return new ResponseEntity<>(ex.getMessage(), ex.getHttpStatus()); 
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		LOGGER.error("Error, exception " + ex.getClass().getSimpleName() + " occurred! Cause: \n");
		ex.printStackTrace();
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAnyException(Exception ex, WebRequest request) {
		LOGGER.error("Error, exception " + ex.getClass().getSimpleName() + " occurred! Cause:\n");
		ex.printStackTrace();
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
