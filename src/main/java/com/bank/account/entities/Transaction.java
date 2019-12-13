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

import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
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

	@Builder.Default
	@Column(nullable = false)
	private Date date = new Date();

	@Builder.Default
	@Column(nullable = false)
	private Currency currency = Currency.getInstance(Locale.getDefault());

	@ManyToOne
	@JoinColumn(nullable = true)
	private Account senderAccount;

	@ManyToOne
	@JoinColumn(nullable = true)
	private Account reveiverAccount;

}
