package com.bank.account.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBeneficiary;

	@Column(nullable = false)
	private Long accountNumber;

	@ManyToOne
	@JoinColumn(nullable = false)
	private WicketBank wicketBank;

	public Beneficiary(Long idBeneficiary, Long accountNumber, WicketBank wicketBank) {
		this.idBeneficiary = idBeneficiary;
		this.accountNumber = accountNumber;
		this.wicketBank = wicketBank;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public WicketBank getWicketBank() {
		return wicketBank;
	}

	public void setWicketBank(WicketBank wicketBank) {
		this.wicketBank = wicketBank;
	}

	public Long getIdBeneficiary() {
		return idBeneficiary;
	}

	public void setIdBeneficiary(Long idBeneficiary) {
		this.idBeneficiary = idBeneficiary;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		result = prime * result + ((idBeneficiary == null) ? 0 : idBeneficiary.hashCode());
		result = prime * result + ((wicketBank == null) ? 0 : wicketBank.hashCode());
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
		Beneficiary other = (Beneficiary) obj;
		if (accountNumber == null) {
			if (other.accountNumber != null)
				return false;
		} else if (!accountNumber.equals(other.accountNumber))
			return false;
		if (idBeneficiary == null) {
			if (other.idBeneficiary != null)
				return false;
		} else if (!idBeneficiary.equals(other.idBeneficiary))
			return false;
		if (wicketBank == null) {
			if (other.wicketBank != null)
				return false;
		} else if (!wicketBank.equals(other.wicketBank))
			return false;
		return true;
	}

}
