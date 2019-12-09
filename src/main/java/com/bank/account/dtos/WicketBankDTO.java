package com.bank.account.dtos;

import com.bank.account.enums.Country;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Wicket Bank DTO.
 */
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

	public Long getIdWicketBank() {
		return idWicketBank;
	}

	public void setIdWicketBank(Long idWicketBank) {
		this.idWicketBank = idWicketBank;
	}

	public Integer getWicketCode() {
		return wicketCode;
	}

	public void setWicketCode(Integer wicketCode) {
		this.wicketCode = wicketCode;
	}

	public String getWicketName() {
		return wicketName;
	}

	public void setWicketName(String wicketName) {
		this.wicketName = wicketName;
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

	public BankDTO getBank() {
		return bank;
	}

	public void setBank(BankDTO bank) {
		this.bank = bank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((bank == null) ? 0 : bank.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((idWicketBank == null) ? 0 : idWicketBank.hashCode());
		result = prime * result + ((wicketCode == null) ? 0 : wicketCode.hashCode());
		result = prime * result + ((wicketName == null) ? 0 : wicketName.hashCode());
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
		WicketBankDTO other = (WicketBankDTO) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (bank == null) {
			if (other.bank != null)
				return false;
		} else if (!bank.equals(other.bank))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country != other.country)
			return false;
		if (idWicketBank == null) {
			if (other.idWicketBank != null)
				return false;
		} else if (!idWicketBank.equals(other.idWicketBank))
			return false;
		if (wicketCode == null) {
			if (other.wicketCode != null)
				return false;
		} else if (!wicketCode.equals(other.wicketCode))
			return false;
		if (wicketName == null) {
			if (other.wicketName != null)
				return false;
		} else if (!wicketName.equals(other.wicketName))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}

}
