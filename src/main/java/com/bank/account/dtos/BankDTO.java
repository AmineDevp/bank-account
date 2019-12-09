package com.bank.account.dtos;

import com.bank.account.enums.Country;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Bank DTO.
 */
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

	public Integer getBankCode() {
		return bankCode;
	}

	public void setBankCode(Integer bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankIdentifierCode() {
		return bankIdentifierCode;
	}

	public void setBankIdentifierCode(String bankIdentifierCode) {
		this.bankIdentifierCode = bankIdentifierCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((bankCode == null) ? 0 : bankCode.hashCode());
		result = prime * result + ((bankIdentifierCode == null) ? 0 : bankIdentifierCode.hashCode());
		result = prime * result + ((bankName == null) ? 0 : bankName.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankDTO other = (BankDTO) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (bankCode == null) {
			if (other.bankCode != null)
				return false;
		} else if (!bankCode.equals(other.bankCode))
			return false;
		if (bankIdentifierCode == null) {
			if (other.bankIdentifierCode != null)
				return false;
		} else if (!bankIdentifierCode.equals(other.bankIdentifierCode))
			return false;
		if (bankName == null) {
			if (other.bankName != null)
				return false;
		} else if (!bankName.equals(other.bankName))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country != other.country)
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

}
