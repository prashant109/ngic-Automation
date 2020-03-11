package com.selenium.testdata;

public class AccountDetails {

	private String firstName = RandomUtil.getRandomName();
	private String lastName = RandomUtil.getRandomName();
	private String routingNumber = "011075150";
	private String accountNumber = "1099339999";

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getRoutingNumber() {
		return routingNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

}
