package com.bank.account.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bank.account.enums.Country;

import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class Bank {

	@Id
	@Column(length = 5)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bankCode;

	@Column(nullable = false)
	private String bankName;

	@Column(length = 8, nullable = false)
	private String bankIdentifierCode;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String zipCode;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private Country country;

}
