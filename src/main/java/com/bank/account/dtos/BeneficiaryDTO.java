package com.bank.account.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Beneficiary DTO.
 */
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

	public Long getIdBeneficiary() {
		return idBeneficiary;
	}

	public void setIdBeneficiary(Long idBeneficiary) {
		this.idBeneficiary = idBeneficiary;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public WicketBankDTO getWicketBank() {
		return wicketBank;
	}

	public void setWicketBank(WicketBankDTO wicketBank) {
		this.wicketBank = wicketBank;
	}

}
