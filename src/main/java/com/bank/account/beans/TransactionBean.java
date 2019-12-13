package com.bank.account.beans;

import java.util.Currency;
import java.util.Date;

import com.bank.account.enums.TransactionType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionBean {

	/**
	 * Id Transaction.
	 */
	private Long idTransaction;

	/**
	 * Transaction Type.
	 */
	private TransactionType transactionType;

	/**
	 * Transaction Amount.
	 */
	private Double amount;

	/**
	 * Transaction Motif.
	 */
	private String motif;

	/**
	 * Opration Date.
	 */
	private Date date;

	/**
	 * Transaction Currency.
	 */
	private Currency currency;

	/**
	 * Transaction Sender.
	 */
	private Long senderAccountId;

	/**
	 * Transaction Receiver.
	 */
	private Long reveiverAccountId;

}
