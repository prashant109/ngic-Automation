package com.selenium.util.enums;

public enum States {

	ALABAMA("Alabama", "AL"), CALIFORNIA("California", "CA"), NEVADA("Nevada", "NV"), COLORADO("Colorado", "CO"),
	NEBRASKA("Nebraska", "CO"), WASHINGTON("Washington", "WA"),
	MASSACHUSETTS("Massachusetts", "MA");

	private String state;
	private String stateAbbreviation;

	public String toString() {
		return state;
	}

	public String getAbbreviation() {
		return stateAbbreviation;
	}

	public String getFullName() {
		return state;
	}

	States(String state, String stateAbbreviation) {
		this.state = state;
		this.stateAbbreviation = stateAbbreviation;
	}
}
