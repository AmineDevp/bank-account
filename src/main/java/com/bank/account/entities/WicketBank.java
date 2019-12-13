package com.bank.account.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bank.account.enums.Country;

import lombok.Builder;
import lombok.Data;

@Entity
@Builder
@Data
public class WicketBank {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idWicketBank;

	@Column(length = 5, nullable = false)
	private Integer wicketCode;

	@Column(nullable = false)
	private String wicketName;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String zipCode;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private Country country;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Bank bank;

}
