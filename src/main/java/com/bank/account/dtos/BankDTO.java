package com.bank.account.dtos;

import com.bank.account.enums.Country;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Bank DTO.
 */
@Data
@ApiModel("Bank")
public class BankDTO {

	/**
	 * Bank Code.
	 */
	@ApiModelProperty("Bank Code.")
	private Integer bankCode;

	/**
	 * Bank Name.
	 */
	@ApiModelProperty("Bank Name.")
	private String bankName;

	/**
	 * Bank Identifier Code.
	 */
	@ApiModelProperty("Bank Identifier Code.")
	private String bankIdentifierCode;

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
