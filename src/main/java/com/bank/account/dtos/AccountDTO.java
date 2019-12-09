package com.bank.account.dtos;

import java.util.Currency;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Account DTO.
 */
@ApiModel("Account")
public class AccountDTO {

	/**
	 * Account Number.
	 */
	@ApiModelProperty("Account Number.")
	private Long accountNumber;

	/**
	 * Amount.
	 */
	@ApiModelProperty("Amount.")
	private Double amount;

	/**
	 * Last Update Date.
	 */
	@ApiModelProperty("Last Update Date.")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date lastUpdate;

	/**
	 * Currency.
	 */
	@ApiModelProperty("Currency.")
	private Currency currency;

	/**
	 * OverDraft.
	 */
	@ApiModelProperty("OverDraft.")
	private Double overDraft;

	/**
	 * Wicket Bank.
	 */
	@ApiModelProperty("Wicket Bank.")
	private WicketBankDTO wicketBank;

	/**
	 * Client.
	 */
	@ApiModelProperty("Client.")
	private ClientDTO client;

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
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

	public WicketBankDTO getWicketBank() {
		return wicketBank;
	}

	public void setWicketBank(WicketBankDTO wicketBank) {
		this.wicketBank = wicketBank;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
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
		AccountDTO other = (AccountDTO) obj;
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

}
