package com.nikolas.master_thesis.exception;

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

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@ExceptionHandler(StoreException.class)
	public ResponseEntity<?> handleApiException(StoreException ex, WebRequest request) {
		logger.error("Error, exception occured: " + ex.getMessage(), ex);
		return new ResponseEntity<>(ex, ex.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleAnyException(Exception ex, WebRequest request) {
		logger.error("Error, exeception "+ ex.getClass().getSimpleName() +" occured! Cause: " + ex.getCause());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
