package com.bank.account.entities;

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
public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBeneficiary;

	@Column(nullable = false)
	private Long accountNumber;

	@ManyToOne
	@JoinColumn(nullable = false)
	private WicketBank wicketBank;

}
