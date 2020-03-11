package com.selenium.web.page.ipro;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;

public class IproHomePage {

	BaseActionDriver actionDriver;
	CommonUtil common;

	public IproHomePage() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}

	
	private String individualsMenu = "//*[contains(text(),'Individuals')] ";
	//private String newContact = "//*[contains(text(),'Individuals')] /..//*[contains(text(),'New')]";
	private String iProHomePage_URL = "home.asp?brokerID=";
	private String quoteAndEnrollPage_URL = "qiprom1/ifp/home/xo/home.asp?brokerID=";
	private String newContactLink = "//*[contains(text(),'Add New Individual Contact')]";
	private String zipCodeInputBox = "quotezip";
	private String zipCode = "36104";
	private String gobutton = "btnQuote";


	public By getIndividualsMenu() {
		return By.xpath(individualsMenu);
	}
	
	public By getNewContactLink() {
		return By.xpath(newContactLink);
	}

	public By getZipCodeInputBox() {
		return By.id(zipCodeInputBox);
	}

	public By getGobutton() {
		return By.id(gobutton);
	}
	
	public void clickNewContactButton() throws Exception {
			common.log("2. Confirming successful login by verifying Homepage URL Text");
			common.verifyUrl(iProHomePage_URL, 20);
			
			common.log("3. Clicking individuals from top Menu");
			actionDriver.clickAndWaitForNextElementToBeVisible(getIndividualsMenu(), getNewContactLink());
			
			common.log("4. Confirming URL Text of quote and enroll page");
			common.verifyUrl(quoteAndEnrollPage_URL, 20);
			
			common.log("5. Waiting for add new individual contact link to be appear");
			actionDriver.waitForElementToBeVisible(getNewContactLink());
			
			common.log("6. Clicking create new contact link and waiting for it to dissappear");
			actionDriver.clickAndWaitForElementToDisappear(getNewContactLink(), 20);
		}

	public void enterZipCode() throws Exception {
		common.log("7. Confirming successful login by verifying Homepage URL Text");
		common.verifyUrl(iProHomePage_URL, 20);

		common.log("8. Inserting Zip Code");
		common.enterTestData(getZipCodeInputBox(), zipCode);
		
		common.log("9.Waiting for add new individual contact link to be appear");
		actionDriver.waitForElementToBeVisible(getGobutton());
		
		common.log("10. Clicking Go button and waiting for it to dissappear");
		actionDriver.clickAndWaitForElementToDisappear(getGobutton(), 20);
	}
	
}
