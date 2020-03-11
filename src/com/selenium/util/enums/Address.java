package com.selenium.util.enums;

public enum Address {

	UNIVERSAL_CITY_100_UNIVERSAL_CITY_PLAZA_CA_91608("100 Universal City Plaza", "Universal City",
			States.CALIFORNIA.toString(), "CA", "91608", "US"),
	SANTA_ANA_1800_NEWPORT_CIR_CA_92705("1800 Newport Cir", "Santa Ana", States.CALIFORNIA.toString(), "CA", "92705",
			"US"),
	IRVINE_1063_MCGAW_AVE_CA_92614("1063 McGaw Ave", "Irvine", States.CALIFORNIA.toString(), "CA", "92614", " US"),
	ORANGE_AVE_SANTA_ANA_CA_2720("2720 Orange Ave", "Santa Ana", States.CALIFORNIA.toString(), "CA", "92707", "US"),
	ATLANTIC_1333_STREET_CA("1333 Atlantic Street", "Union City", States.CALIFORNIA.toString(), "CA", "94587", "US"),
	JOYCEE_STREET_ALABAMA_36542("3042 Joyce Street", "Gulf Shores", States.ALABAMA.toString(), "AL", "36542", "US"),
	ASHWOOD_DRIVE_NEBRASKA_68102("3961 Ashwood Drive", "Omaha", States.ALABAMA.toString(), "NE", "68102", "US"),
	HUNTINGTON_AVE_177("177 Huntington Ave Ste", "Boston", States.MASSACHUSETTS.toString(), "MA", "01803", "US");


	private String addressLine1;
	private String city;
	private String state;
	private String stateAbbreviation;
	private String zipCode;

	Address(String addressLine1, String city, String state, String stateAbbreviation, String zipCode, String country) {
		this.addressLine1 = addressLine1;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.stateAbbreviation = stateAbbreviation;
	}

	public String getStateAbbreviation() {
		return stateAbbreviation;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZipcode() {
		return zipCode;
	}
}
