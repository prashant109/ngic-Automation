package com.selenium.web.page.ipro;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;

public class ContactInfo {

	BaseActionDriver actionDriver;
	CommonUtil common;

	public ContactInfo() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}

	private String newProposalButton = "//*[@id=\"Header1__ifpSelectedLinks\"]/child::*";
	public static String CONTACT_INFO  = "Contact Info";


	public By getNewProposalButton() {
		return By.xpath(newProposalButton);
	}


	public void clickNewProposalFromContactInfoPage() throws Exception {
		
		
		common.log("25. verifying page title text on Create Info");
		actionDriver.waitForElementToBeVisible(CONTACT_INFO, 40);
		
		common.log("26. Clicking New Proposal button and waiting for it to disappear");
		actionDriver.click(getNewProposalButton());		
		
	}

}
