package com.selenium.web.page.common;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;

public class SettingsPage {

	BaseActionDriver actionDriver;
	CommonUtil common;

	public SettingsPage() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}

	private String notificationsButton = "//*[@href='#/notification-preferences']/div";
	private String changePasswordButton = "//div[contains(text(),'CHANGE PASSWORD')]";
	private String continueButton = "//input[contains(@name,'btnContinue_bottom')]";

	public By getChangePasswordButton() {
		return By.xpath(changePasswordButton);
	}

	public By getNotificationsButton() {
		return By.xpath(notificationsButton);
	}

	public void clickChangePasswordButton() throws Exception {
		common.log("Click on Change Password button");
		actionDriver.clickAndWaitForElementToDisappear(getChangePasswordButton(), 40);
	}

	public void clickNotificationsButton() throws Exception {
		common.log("Click on Notifications button");
		actionDriver.clickAndWaitForElementToDisappear(getNotificationsButton(), 40);
	}
	
	public By getContinueButton() {
		return By.xpath(continueButton);
	}
}
