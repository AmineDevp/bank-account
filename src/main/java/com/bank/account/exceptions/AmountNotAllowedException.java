package com.bank.account.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.bank.account.common.Constants;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AmountNotAllowedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8226786478970759387L;

	/**
	 * Constructor for AmountNotAllowedException.
	 */
	public AmountNotAllowedException() {
		super(Constants.AMOUNT_NOT_ALLOWED);
	}

}
