package com.selenium.web.page.ipro;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;

public class IproLoginPage {

	BaseActionDriver actionDriver;
	CommonUtil common;

	public IproLoginPage() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}

	private String emailInputBox = "BrokerID";
	private String passwordInputBox = "VisiblePassword";
	private String loginButton = "//*[@value='Login']";
	private String skipAndContinueButton= "//input[@value='Skip this and Continue Login']"; 

	public By getEmailInputBox() {
		return By.name(emailInputBox);
	}

	public By getPasswordInputBox() {
		return By.name(passwordInputBox);
	}

	public By getLoginButton() {
		return By.xpath(loginButton);
	}
	
	public By getSkipAndContinueButton() {
		return By.xpath(skipAndContinueButton);
	}

	public void loginIntoApplication(String email, String password) throws Exception {
		common.log("1. Signing in to application | ");
		//common.log("Login Email : " + email);
		actionDriver.waitForElementToBeVisible(getEmailInputBox(), 20);
		actionDriver.type(getEmailInputBox(), email);
		actionDriver.type(getPasswordInputBox(), password);
		actionDriver.clickAndWaitForElementToDisappear(getLoginButton(), 20);
		
		if (actionDriver.isElementVisible(getSkipAndContinueButton(), 2)) {
		actionDriver.click(getSkipAndContinueButton());}
		
	}

}
