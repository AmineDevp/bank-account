package com.bank.account.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Beneficiary DTO.
 */
@Data
@ApiModel("Beneficiary")
public class BeneficiaryDTO {

	/**
	 * Id Beneficiary.
	 */
	@ApiModelProperty("Id Beneficiary.")
	private Long idBeneficiary;

	/**
	 * Account Number.
	 */
	@ApiModelProperty("Account Number.")
	private Long accountNumber;

	/**
	 * Wicket Bank.
	 */
	@ApiModelProperty("Wicket Bank.")
	private WicketBankDTO wicketBank;

}
