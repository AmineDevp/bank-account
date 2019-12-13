package com.bank.account.dtos;

import java.util.Currency;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Account DTO.
 */
@Data
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

}
