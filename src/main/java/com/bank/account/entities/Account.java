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

import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idAccount;

	@Column(length = 11, nullable = false)
	private Long accountNumber;

	@Builder.Default
	@Column(nullable = false)
	private Double amount = 0.0;

	@Builder.Default
	@Column(nullable = false)
	private Date lastUpdate = new Date();

	@Builder.Default
	@Column(nullable = false)
	private Currency currency = Currency.getInstance(Locale.getDefault());

	// Overdraft disabled by default
	@Builder.Default
	@Column(nullable = false)
	private Double overDraft = 0.0;

	@ManyToOne
	@JoinColumn(nullable = false)
	private WicketBank wicketBank;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Client client;

}
