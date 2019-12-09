package com.bank.account.enums;

public enum Country {

	FRANCE("FR76", "France");

	private String code;
	private String name;

	private Country(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
