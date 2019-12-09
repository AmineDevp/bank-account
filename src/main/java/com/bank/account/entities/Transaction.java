package com.bank.account.entities;

import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bank.account.enums.TransactionType;

@Entity
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTransaction;

	@Column(nullable = false)
	private TransactionType transactionType;

	@Column(nullable = false)
	private Double amount;

	@Column(nullable = true)
	private String motif;

	@Column(nullable = false)
	private Date date = new Date();

	@Column(nullable = false)
	private Currency currency = Currency.getInstance(Locale.getDefault());

	@ManyToOne
	@JoinColumn(nullable = true)
	private Account senderAccount;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Account reveiverAccount;

	private Transaction(Long idTransaction, TransactionType transactionType, Double amount, String motif, Date date, Currency currency, Account senderAccount,
			Account reveiverAccount) {
		this.idTransaction = idTransaction;
		this.transactionType = transactionType;
		this.amount = amount;
		this.motif = motif;
		if (date != null)
			this.date = date;
		if (currency != null)
			this.currency = currency;
		this.senderAccount = senderAccount;
		this.reveiverAccount = reveiverAccount;
	}

	public void setIdTransaction(Long idTransaction) {
		this.idTransaction = idTransaction;
	}

	public Long getIdTransaction() {
		return idTransaction;
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

	public Account getSenderAccount() {
		return senderAccount;
	}

	public void setSenderAccount(Account senderAccount) {
		this.senderAccount = senderAccount;
	}

	public Account getReveiverAccount() {
		return reveiverAccount;
	}

	public void setReveiverAccount(Account reveiverAccount) {
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
		Transaction other = (Transaction) obj;
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
		return transactionType == other.transactionType;
	}

	public static final class Builder {

		private Long idTransaction;

		private TransactionType transactionType;

		private Double amount;

		private String motif;

		private Date date;

		private Currency currency;

		private Account senderAccount;

		private Account reveiverAccount;

		public Builder withIdTransaction(Long idTransaction) {
			this.idTransaction = idTransaction;
			return this;
		}

		public Builder withTransactionType(TransactionType transactionType) {
			this.transactionType = transactionType;
			return this;
		}

		public Builder withAmount(Double amount) {
			this.amount = amount;
			return this;
		}

		public Builder withMotif(String motif) {
			this.motif = motif;
			return this;
		}

		public Builder withDate(Date date) {
			this.date = date;
			return this;
		}

		public Builder withCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		public Builder withSenderAccount(Account senderAccount) {
			this.senderAccount = senderAccount;
			return this;
		}

		public Builder withReveiverAccount(Account reveiverAccount) {
			this.reveiverAccount = reveiverAccount;
			return this;
		}

		public Transaction build() {
			return new Transaction(idTransaction, transactionType, amount, motif, date, currency, senderAccount, reveiverAccount);
		}
	}

}
