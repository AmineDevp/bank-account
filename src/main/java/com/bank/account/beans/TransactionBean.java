package com.bank.account.beans;

import java.util.Currency;
import java.util.Date;

import com.bank.account.enums.TransactionType;

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

	public Long getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(Long idTransaction) {
		this.idTransaction = idTransaction;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Long getSenderAccountId() {
		return senderAccountId;
	}

	public void setSenderAccountId(Long senderAccountId) {
		this.senderAccountId = senderAccountId;
	}

	public Long getReveiverAccountId() {
		return reveiverAccountId;
	}

	public void setReveiverAccountId(Long reveiverAccountId) {
		this.reveiverAccountId = reveiverAccountId;
	}

}
