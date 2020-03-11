package com.selenium.web.page.ipro;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;

public class CreateNewContactForm{

	BaseActionDriver actionDriver;
	CommonUtil common;

	public CreateNewContactForm() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}

	private String newContactPage_URL = "qiprom1/ifp/contacts/new.asp?brokerID=";
	public static String CREATE_NEW_INDIVIDUAL_CONTACT  = "Create New Individual Contact";
	public static String QUICK_QUOTE  = "Quick Quote";
	
	private String quickQuote_URL = "qiprom1/ifp/infoentry/xo/infoentry.asp?";
	private String firstNameInputBox = "applicantFirstName";
	private String lastNameInputBox = "applicantLastName";
	private String zipCodeInputbox = "zipCode";
	private String dobInputbox = "txtDoB-0";
	private String createContactButton = "//em[contains(text(),'Create Contact')]";
	private String genderDropDown = "selGender";
	private String gender = "M";
	private String showPlansButton = "//em[contains(text(),'Show Plans')]";
	//private String tomorrowsDate = common.getDate("dd.MM.yyyy", 1);
	private String coverageDateInputBox = "effectiveStartDate";
	
	private String coverageTermDropDown = "covMonths";
	private String coverageTerm = "3 Months";
		
	public By getCoverageTermDropDown() {
		return By.id(coverageTermDropDown);
	}
	
	public By getFirstNameInputBox() {
		return By.name(firstNameInputBox);
	}

	public By getLastNameInputBox() {
		return By.name(lastNameInputBox);
	}

	public By getZipCodeInputbox() {
		return By.name(zipCodeInputbox);
	}

	public By getDobInputbox() {
		return By.id(dobInputbox);
	}
	
	public By getCreateContactButton() {
		return By.xpath(createContactButton);
	}

	public By getGenderDropDown() {
		return By.id(genderDropDown);
	}
	
	public By getShowPlansButton() {
		return By.xpath(showPlansButton);
	}
	
	public By getCoverageDateInputBox() {
		return By.id(coverageDateInputBox);
	}
	
	public void createNewIndividualContact() throws Exception {
		
		common.log("11. verifying URL Text for create new individual contact page");
		common.verifyUrl(newContactPage_URL, 20);
		
		common.log("12. verifying page title text on Create New Individual Contact");
		actionDriver.waitForElementToBeVisible(CREATE_NEW_INDIVIDUAL_CONTACT, 40);

		common.log("13. Inserting first name");
		common.enterTestData(getFirstNameInputBox(), "Testzzz");
		
		common.log("14. Inserting last name");
		common.enterTestData(getLastNameInputBox(), "Testzzz");
		
		common.log("15. Inserting zip code");
		common.enterTestData(getZipCodeInputbox(), "92618");

		common.log("16 Inserting DOB");
		actionDriver.typeUsingActionApi(getDobInputbox(), "01011981");
		
		common.log("17. Clicking createnew contact button and waiting for it to dissappear");
		actionDriver.click(getCreateContactButton());		
	}

	public void selectGenderDOBAndCoverage() throws Exception {
		String tomorrowsDate = common.getDate("MM.dd.yyyy", 1);
		common.log("18. verifying URL for quick quote page");
		common.verifyUrl(quickQuote_URL, 20);
		
		common.log("19. verifying page title text on QUICK QUOTE");
		actionDriver.waitForElementToBeVisible(QUICK_QUOTE, 40);

		common.log("20. Selecting Gender type from drop-down");
		actionDriver.waitForElementToBeVisible(getGenderDropDown());
		actionDriver.selectByText(getGenderDropDown(), gender);
		
		common.log("21. Inserting DOB");
		actionDriver.typeUsingActionApi(getDobInputbox(), "01011981");
		
		common.log("22. Inserting coverage start days");
		actionDriver.typeUsingActionApi(getCoverageDateInputBox(), tomorrowsDate);
		
		common.log("23. Selecting coverage term from drop-down");
		actionDriver.waitForElementToBeVisible(getCoverageTermDropDown());
		actionDriver.selectByText(getCoverageTermDropDown(), coverageTerm);
		
		common.log("24. Clicking New Proposal button and waiting for it to dissappear");
		actionDriver.click(getShowPlansButton());			
	}
	
}
