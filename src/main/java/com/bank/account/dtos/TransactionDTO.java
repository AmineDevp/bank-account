package com.bank.account.dtos;

import java.util.Currency;
import java.util.Date;

import com.bank.account.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Transaction DTO.
 */
@Data
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

}
