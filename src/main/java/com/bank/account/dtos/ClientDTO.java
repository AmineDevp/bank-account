package com.bank.account.dtos;

import com.bank.account.enums.Country;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Client DTO.
 */
@Data
@ApiModel("Client")
public class ClientDTO {

	/**
	 * Id Client.
	 */
	@ApiModelProperty("Id Client.")
	private Long id;

	/**
	 * Client Firstname.
	 */
	@ApiModelProperty("Firstname.")
	private String firstname;

	/**
	 * Client Lastname.
	 */
	@ApiModelProperty("Lastname.")
	private String lastname;

	/**
	 * Email Address.
	 */
	@ApiModelProperty("Email Address.")
	private String emailAddress;

	/**
	 * Address.
	 */
	@ApiModelProperty("Address.")
	private String address;

	/**
	 * ZIPCode.
	 */
	@ApiModelProperty("ZIPCode.")
	private String zipCode;

	/**
	 * City.
	 */
	@ApiModelProperty("City.")
	private String city;

	/**
	 * Country.
	 */
	@ApiModelProperty("Country.")
	private Country country;

}
