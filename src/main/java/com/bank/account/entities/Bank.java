package com.bank.account.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.bank.account.enums.Country;

@Entity
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

	private Bank(Integer bankCode, String bankName, String bankIdentifierCode, String address, String zipCode, String city, Country country) {
		this.bankCode = bankCode;
		this.bankName = bankName;
		this.bankIdentifierCode = bankIdentifierCode;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
	}

	public void setBankCode(Integer bankCode) {
		this.bankCode = bankCode;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setBankIdentifierCode(String bankIdentifierCode) {
		this.bankIdentifierCode = bankIdentifierCode;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Integer getBankCode() {
		return bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public String getBankIdentifierCode() {
		return bankIdentifierCode;
	}

	public String getAddress() {
		return address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getCity() {
		return city;
	}

	public Country getCountry() {
		return country;
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
		Bank other = (Bank) obj;
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

	public static final class Builder {

		private Integer bankCode;

		private String bankName;

		private String bankIdentifierCode;

		private String address;

		private String zipCode;

		private String city;

		private Country country;

		public Builder withBankCode(Integer bankCode) {
			this.bankCode = bankCode;
			return this;
		}

		public Builder withBankName(String bankName) {
			this.bankName = bankName;
			return this;
		}

		public Builder withBankIdentifierCode(String bankIdentifierCode) {
			this.bankIdentifierCode = bankIdentifierCode;
			return this;
		}

		public Builder withAddress(String address) {
			this.address = address;
			return this;
		}

		public Builder withZipCode(String zipCode) {
			this.zipCode = zipCode;
			return this;
		}

		public Builder withCity(String city) {
			this.city = city;
			return this;
		}

		public Builder withCountry(Country country) {
			this.country = country;
			return this;
		}

		public Bank build() {
			return new Bank(this.bankCode, this.bankName, this.bankIdentifierCode, this.address, this.zipCode, this.city, this.country);
		}

	}

}
