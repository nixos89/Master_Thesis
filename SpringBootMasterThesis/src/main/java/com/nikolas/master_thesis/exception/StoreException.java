package com.nikolas.master_thesis.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreException extends RuntimeException {

	private static final long serialVersionUID = -4185039825401688387L;
	
	private String message;
	private HttpStatus httpStatus;
	
	public StoreException(String message, HttpStatus httpStatus) {
		this.message = message;
		this.httpStatus = httpStatus;
	}
	
}
