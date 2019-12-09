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

@Entity
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAccount;

	@Column(length = 11, nullable = false)
	private Long accountNumber;

	@Column(nullable = false)
	private Double amount = 0.0;

	@Column(nullable = false)
	private Date lastUpdate = new Date();

	@Column(nullable = false)
	private Currency currency = Currency.getInstance(Locale.getDefault());

	// Overdraft disabled by default
	@Column(nullable = false)
	private Double overDraft = 0.0;

	@ManyToOne
	@JoinColumn(nullable = false)
	private WicketBank wicketBank;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Client client;

	private Account(Long idAccount, Long accountNumber, Double amount, Date lastUpdate, Currency currency, Double overDraft, WicketBank wicketBank, Client client) {
		this.idAccount = idAccount;
		this.accountNumber = accountNumber;
		if (amount != null)
			this.amount = amount;
		if (lastUpdate != null)
			this.lastUpdate = lastUpdate;
		if (currency != null)
			this.currency = currency;
		if (overDraft != null)
			this.overDraft = overDraft;
		this.wicketBank = wicketBank;
		this.client = client;
	}

	public Long getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public WicketBank getWicketBank() {
		return wicketBank;
	}

	public void setWicketBank(WicketBank wicketBank) {
		this.wicketBank = wicketBank;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getOverDraft() {
		return overDraft;
	}

	public void setOverDraft(Double overDraft) {
		this.overDraft = overDraft;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((idAccount == null) ? 0 : idAccount.hashCode());
		result = prime * result + ((lastUpdate == null) ? 0 : lastUpdate.hashCode());
		result = prime * result + ((overDraft == null) ? 0 : overDraft.hashCode());
		result = prime * result + ((wicketBank == null) ? 0 : wicketBank.hashCode());
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
		Account other = (Account) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (idAccount == null) {
			if (other.idAccount != null)
				return false;
		} else if (!idAccount.equals(other.idAccount))
			return false;
		if (lastUpdate == null) {
			if (other.lastUpdate != null)
				return false;
		} else if (!lastUpdate.equals(other.lastUpdate))
			return false;
		if (overDraft == null) {
			if (other.overDraft != null)
				return false;
		} else if (!overDraft.equals(other.overDraft))
			return false;
		if (wicketBank == null) {
			if (other.wicketBank != null)
				return false;
		} else if (!wicketBank.equals(other.wicketBank))
			return false;
		return true;
	}

	public static final class Builder {

		private Long idAccount;

		private Long accountNumber;

		private Double amount;

		private Date lastUpdate;

		private Currency currency;

		private Double overDraft;

		private WicketBank wicketBank;

		private Client client;

		public Builder withIdAccount(Long idAccount) {
			this.idAccount = idAccount;
			return this;
		}

		public Builder withAccountNumber(Long accountNumber) {
			this.accountNumber = accountNumber;
			return this;
		}

		public Builder withAmount(Double amount) {
			this.amount = amount;
			return this;
		}

		public Builder withLastUpdate(Date lastUpdate) {
			this.lastUpdate = lastUpdate;
			return this;
		}

		public Builder withCurrency(Currency currency) {
			this.currency = currency;
			return this;
		}

		public Builder withOverDraft(Double overDraft) {
			this.overDraft = overDraft;
			return this;
		}

		public Builder withWicketBank(WicketBank wicketBank) {
			this.wicketBank = wicketBank;
			return this;
		}

		public Builder withClient(Client client) {
			this.client = client;
			return this;
		}

		public Account build() {
			return new Account(idAccount, accountNumber, amount, lastUpdate, currency, overDraft, wicketBank, client);
		}
	}

}
