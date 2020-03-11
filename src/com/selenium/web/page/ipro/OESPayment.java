package com.selenium.web.page.ipro;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;
import com.selenium.web.page.common.SettingsPage;

public class OESPayment extends SettingsPage{

	BaseActionDriver actionDriver;
	CommonUtil common;

	public OESPayment() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}
			
	private String OES_Payment_URL = "OAS/OAS_PaymentOptions4.aspx?";
	private String OES_Cov_Builder_Payment_URL = "OAS/OAS_ConsolidatorPaymentOptions.aspx?";
	public static String OES_Payment_Method  = "Your first payment will be taken with the submission of the application today";

	public static String OES_Cov_Builder_Payment_Method  = "Select Your Payment Method";

	private String firstNameInputBox = "(//input[contains(@datamapname,'FMP_CC_FirstName')])";
	private String firstName = "Testzzz";
	
	private String lastNameInputBox = "(//input[contains(@datamapname,'FMP_CC_LastName')])";
	private String lastName = "Testzzz";
	
	private String ccNumberInputBox = "//input[@datamapname='FMP_Visa-LastFourDigits']";
	private String ccNumber = "4111111111111111";
	
	private String expDateInputBox = "//input[@datamapname='FMP_CC_CardExpirationDate']";
	//private String expDate = common.addYear("MM/YYYY", 4);
	
	private String cvv2InputBox = "//input[@datamapname='FMP_CC_CVV2']";
	private String cvv2 = "011";

	public By getFirstNameInputBox() {
		return By.xpath(firstNameInputBox); 
	}

	public By getLastNameInputBox() {
		return By.xpath(lastNameInputBox);
	}

	public By getCcNumberInputBox() {
		return By.xpath(ccNumberInputBox);
	}

	public By getExpDateInputBox() {
		return By.xpath(expDateInputBox);
	}
	
	public By getCvv2InputBox() {
		return By.xpath(cvv2InputBox);
	}

	public void paymentForm() throws Exception {
		
		common.log("73. Verifying URL Text for Payment page");
		common.verifyUrl(OES_Payment_URL, 20);
		
		common.log("74. Verifying page title text on OES Payment page");
		actionDriver.waitForElementToBeVisible(OES_Payment_Method, 40);

		common.log("75. Inserting first name");
		common.enterTestData(getFirstNameInputBox(), firstName);
		
		common.log("76. Inserting last name");
		common.enterTestData(getLastNameInputBox(), lastName);
		
		common.log("77. Inserting credit card Number");
		common.enterPhoneNumber(getCcNumberInputBox(), ccNumber);
	
		common.log("78. Inserting cc exp. date");
		common.enterTestData(getExpDateInputBox(), common.addYear("MM/YYYY", 4));		
		
		common.log("79. Inserting cvv2 code");
		common.enterTestData(getCvv2InputBox(), cvv2);	
		
		common.log("80. Clicking Continue button");
		actionDriver.click(getContinueButton());	
	}
	
	public void paymentFormCoverageBuilder() throws Exception {
		
		common.log("73. Verifying URL Text for Payment page for coverage builder");
		common.verifyUrl(OES_Cov_Builder_Payment_URL, 20);
		
		common.log("74. Verifying page title text on OES Payment page");
		actionDriver.waitForElementToBeVisible(OES_Cov_Builder_Payment_Method, 40);

		common.log("75. Inserting first name");
		common.enterTestData(getFirstNameInputBox(), firstName);
		
		common.log("76. Inserting last name");
		common.enterTestData(getLastNameInputBox(), lastName);
		
		common.log("77. Inserting credit card Number");
		common.enterPhoneNumber(getCcNumberInputBox(), ccNumber);
	
		common.log("78. Inserting cc exp. date");
		//common.enterTestData(getExpDateInputBox(), expDate);
		common.enterBirthDay(getExpDateInputBox(), (common.addYear("MM/YYYY", 4).replace("/", "")));
		//common.ent
		//.enterTestData(getExpDateInputBox(), expDate);


		
		common.log("79. Inserting cvv2 code");
		common.enterTestData(getCvv2InputBox(), cvv2);	
		
		common.log("80. Clicking Continue button");
		actionDriver.click(getContinueButton());	
	}
}
