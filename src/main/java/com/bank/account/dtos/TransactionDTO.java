package com.bank.account.dtos;

import java.util.Currency;
import java.util.Date;

import com.bank.account.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Transaction DTO.
 */
@ApiModel("Transaction")
public class TransactionDTO {

	/**
	 * Id Transaction.
	 */
	@ApiModelProperty("Id Transaction.")
	private Long idTransaction;

	/**
	 * Transaction Type.
	 */
	@ApiModelProperty("Transaction Type.")
	private TransactionType transactionType;

	/**
	 * Transaction Amount.
	 */
	@ApiModelProperty("Transaction Amount.")
	private Double amount;

	/**
	 * Transaction Motif.
	 */
	@ApiModelProperty("Transaction Motif.")
	private String motif;

	/**
	 * Opration Date.
	 */
	@ApiModelProperty("Opration Date.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date date;

	/**
	 * Transaction Currency.
	 */
	@ApiModelProperty("Transaction Currency.")
	private Currency currency;

	/**
	 * Transaction Sender.
	 */
	@ApiModelProperty("Transaction Sender.")
	private AccountDTO senderAccount;

	/**
	 * Transaction Receiver.
	 */
	@ApiModelProperty("Transaction Receiver.")
	private AccountDTO reveiverAccount;

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

	public AccountDTO getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(AccountDTO senderAccount) {
		this.senderAccount = senderAccount;
	}

	public AccountDTO getReveiverAccount() {
		return reveiverAccount;
	}

	public void setReveiverAccount(AccountDTO reveiverAccount) {
		this.reveiverAccount = reveiverAccount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((idTransaction == null) ? 0 : idTransaction.hashCode());
		result = prime * result + ((motif == null) ? 0 : motif.hashCode());
		result = prime * result + ((reveiverAccount == null) ? 0 : reveiverAccount.hashCode());
		result = prime * result + ((senderAccount == null) ? 0 : senderAccount.hashCode());
		result = prime * result + ((transactionType == null) ? 0 : transactionType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransactionDTO other = (TransactionDTO) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (idTransaction == null) {
			if (other.idTransaction != null)
				return false;
		} else if (!idTransaction.equals(other.idTransaction))
			return false;
		if (motif == null) {
			if (other.motif != null)
				return false;
		} else if (!motif.equals(other.motif))
			return false;
		if (reveiverAccount == null) {
			if (other.reveiverAccount != null)
				return false;
		} else if (!reveiverAccount.equals(other.reveiverAccount))
			return false;
		if (senderAccount == null) {
			if (other.senderAccount != null)
				return false;
		} else if (!senderAccount.equals(other.senderAccount))
			return false;
		if (transactionType != other.transactionType)
			return false;
		return true;
	}

}
