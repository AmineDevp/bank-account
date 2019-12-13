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
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idClient;

	@Column(nullable = false)
	private String firstname;

	@Column(nullable = false)
	private String lastname;

	@Column(nullable = false)
	private String emailAddress;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String zipCode;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private Country country;

}
