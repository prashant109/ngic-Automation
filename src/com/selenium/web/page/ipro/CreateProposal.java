package com.selenium.web.page.ipro;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;

public class CreateProposal {

	BaseActionDriver actionDriver;
	CommonUtil common;

	public CreateProposal() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}

	private String showPlansButton = "submitButton";
	public static String CREATE_PROPOSAL  = "Create Proposal";
	private String chooseProductTypes = "//a[contains(text(),'Choose Product Type(s)')]";
	private String healthOffExchangeDropDown = "//*[@ng-model='CoverageInfo.effectiveDate']";	
	//private String healthOffExchangeDate = "February 1,  2020";
	private String healthOffExchangeDate = 	"//*[contains(text(),'February 1,  2020') and @value='2/1/2020']";
	private String healthOffExchangeCheckbox = "(//*[contains(text(),\"Health Off-Exchange\")])[2]/preceding-sibling::td/child::*";
	private String dentalPlanCheckbox = "//td[text()='Dental']/preceding-sibling::td[1]";


	public By getShowPlansButton() {
		return By.id(showPlansButton);
	}

	public By getHealthOffExchangeDropDown() {
		return By.xpath(healthOffExchangeDropDown);
	}	
	
	public By getHealthOffExchangeDate() {
		return By.xpath(healthOffExchangeDate);
	}	
	
	public By getChooseProductTypes() {
		return By.xpath(chooseProductTypes);
	}	
	
	
	public By getHealthOffExchangeCheckbox() {
		return By.xpath(healthOffExchangeCheckbox);
	}
	
	public By getDentalPlanCheckbox() {
		return By.xpath(dentalPlanCheckbox);
	}

	public void chooseProductTypeAndClickShowPlans() throws Exception {
		
		common.log("27. verifying page title text on Create Proposal");
		actionDriver.waitForElementToBeVisible(CREATE_PROPOSAL, 40);

		common.log("28. Clicking CHOOSE PRODUCT TYPE(S) TAB");
		if (actionDriver.isElementVisible(getChooseProductTypes(), 2)) {
		actionDriver.click(getChooseProductTypes());}

		common.log("28 a. Scrolling to the bottom of the page");
		actionDriver.scrollDown();
		//common.log("checking on Health Off-Exchange checkbox");
		//actionDriver.click(getHealthOffExchangeCheckbox());
		
		actionDriver.waitForElementToBePresent(getDentalPlanCheckbox(), 2);
		actionDriver.click(getDentalPlanCheckbox());

		//common.log("Selecting proposal type from drop-down");
/*		actionDriver.waitForElementToBeVisible(getHealthOffExchangeDropDown());
		actionDriver.click(getHealthOffExchangeDropDown());
		actionDriver.waitForElementToBeVisible(getHealthOffExchangeDate());
		actionDriver.click(getHealthOffExchangeDate());*/

		//actionDriver.selectByText(getHealthOffExchangeDropDown(), healthOffExchangeDate);
		
		common.log("29. Clicking New Proposal button and waiting for it to dissappear");
		actionDriver.waitForElementToBePresent(getShowPlansButton(), 2);
		actionDriver.click(getShowPlansButton());		
		
	}

}
