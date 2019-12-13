package com.bank.account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bank.account.common.Constants;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectParametersException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4264547781021105368L;

	/**
	 * Constructor for AmountNotAllowedException.
	 */
	public IncorrectParametersException() {
		super(Constants.INCORRECT_PARAMETERS);
	}

}
