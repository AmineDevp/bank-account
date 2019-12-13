package com.bank.account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bank.account.common.Constants;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4364547781021105368L;

	/**
	 * Constructor for AmountNotAllowedException.
	 */
	public AccountNotFoundException() {
		super(Constants.ACCOUNT_NOT_FOUND);
	}

}
