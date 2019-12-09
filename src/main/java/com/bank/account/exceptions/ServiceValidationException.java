package com.bank.account.exceptions;

import org.springframework.http.HttpStatus;

public class ServiceValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2437944350826386204L;
	
	/**
	 * 
	 */
	private final HttpStatus status;

	/**
	 * Constructor for ServiceValidationException.
	 *
	 * @param msg the detail message
	 */
	public ServiceValidationException(String msg, HttpStatus status) {
		super(msg);
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
