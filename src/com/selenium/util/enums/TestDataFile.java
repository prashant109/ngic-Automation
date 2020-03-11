package com.selenium.util.enums;

public enum TestDataFile {

	PacDental_AmerAssist_Unsecured_04_22_2019("4334PacDental-AmerAssist Unsecured_04-22-2019.xlsx", "Sheet1"),
	PacDental_AmerAssist_Unsecured_04_29_2019("4334PacDental-AmerAssist Unsecured_04-29-2019.xlsx", "Sheet1"),
	Ortho041519("Ortho041519.xlsm", "Ortho"), Ortho042219("Ortho042219.xlsm", "Ortho"),
	Ortho042919("Ortho042919.xlsm", "Ortho"), Sp042219("Sp042219.xlsm", "SP"), Sp042919("Sp042919.xlsm", "SP"),
	Sp050819("Sp050819.xlsm", "SP");

	private String fileName;
	private String sheetName;

	public String getFileName() {
		return fileName;
	}

	public String getSheetName() {
		return sheetName;
	}

	TestDataFile(String fileName, String sheetName) {
		this.fileName = fileName;
		this.sheetName = sheetName;
	}
}
