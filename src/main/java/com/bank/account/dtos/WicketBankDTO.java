package com.bank.account.dtos;

import com.bank.account.enums.Country;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Wicket Bank DTO.
 */
@Data
@ApiModel("Wicket Bank")
public class WicketBankDTO {

	/**
	 * Id Wicket Bank.
	 */
	@ApiModelProperty("Id Wicket Bank.")
	private Long idWicketBank;

	/**
	 * Wicket Bank Code.
	 */
	@ApiModelProperty("Wicket Bank Code.")
	private Integer wicketCode;

	/**
	 * Wicket Bank Name.
	 */
	@ApiModelProperty("Wicket Bank Name.")
	private String wicketName;

	/**
	 * Wicket Bank Address.
	 */
	@ApiModelProperty("Wicket Bank Address.")
	private String address;

	/**
	 * Wicket Bank ZIPCode.
	 */
	@ApiModelProperty("Wicket Bank ZIPCode.")
	private String zipCode;

	/**
	 * Wicket Bank City.
	 */
	@ApiModelProperty("Wicket Bank City.")
	private String city;

	/**
	 * Wicket Bank Country.
	 */
	@ApiModelProperty("Wicket Bank Country.")
	private Country country;

	/**
	 * Wicket Bank Related Bank.
	 */
	@ApiModelProperty("Wicket Bank Related Bank.")
	private BankDTO bank;

}
