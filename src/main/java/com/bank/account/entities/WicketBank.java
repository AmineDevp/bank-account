package com.bank.account.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bank.account.enums.Country;

@Entity
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

	private WicketBank(Long idWicketBank, Integer wicketCode, String wicketName, String address, String zipCode, String city, Country country, Bank bank) {
		this.idWicketBank = idWicketBank;
		this.wicketCode = wicketCode;
		this.wicketName = wicketName;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.country = country;
		this.bank = bank;
	}

	public void setIdWicketBank(Long idWicketBank) {
		this.idWicketBank = idWicketBank;
	}

	public void setWicketCode(Integer wicketCode) {
		this.wicketCode = wicketCode;
	}

	public void setWicketName(String wicketName) {
		this.wicketName = wicketName;
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

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public Long getIdWicketBank() {
		return idWicketBank;
	}

	public Integer getWicketCode() {
		return wicketCode;
	}

	public String getWicketName() {
		return wicketName;
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

	public Bank getBank() {
		return bank;
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
		WicketBank other = (WicketBank) obj;
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

	public static final class Builder {

		private Long idWicketBank;

		private Integer wicketCode;

		private String wicketName;

		private String address;

		private String zipCode;

		private String city;

		private Country country;

		private Bank bank;

		public Builder withIdWicketBank(Long idWicketBank) {
			this.idWicketBank = idWicketBank;
			return this;
		}

		public Builder withWicketCode(Integer wicketCode) {
			this.wicketCode = wicketCode;
			return this;
		}

		public Builder withWicketName(String wicketName) {
			this.wicketName = wicketName;
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

		public Builder withBank(Bank bank) {
			this.bank = bank;
			return this;
		}

		public WicketBank build() {
			return new WicketBank(this.idWicketBank, this.wicketCode, this.wicketName, this.address, this.zipCode, this.city, this.country, this.bank);
		}

	}

}
