package com.selenium.web.page.ipro;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.testdata.RandomUtil;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;

public class OESRegistration {

	BaseActionDriver actionDriver;
	CommonUtil common;

	public OESRegistration() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}
			
	private String OES_URL = "UAP/UAP_Registration.aspx?BrokerCode=";
	public static String AUTO_BROKER  = "Auto Broker";
	private String emailAddressInputBox = "//input[contains(@name,'txtEmailAddress')]";
	private String retypeEmailAddressInputBox = "//input[contains(@name,'txtConfirmEmailAddress')]";
	private String passwordInputBox = "//input[contains(@name,'txtPassword')]";
	private String retypePasswordInputBox = "//input[contains(@name,'txtConfirmPassword')]";
	private String securityAnswerInputBox = "//input[contains(@name,'txtAnswer')]";
	private String securityAnswerText = "Test";
	private String registerButton = "//input[contains(@name,'btnRegister')]";

	private String randomEmail = RandomUtil.getRandomEmail();


	public By getEmailAddressInputBox() {
		return By.xpath(emailAddressInputBox);
	}

	public By getRetypeEmailAddressInputBox() {
		return By.xpath(retypeEmailAddressInputBox);
	}

	public By getPasswordInputBox() {
		return By.xpath(passwordInputBox);
	}

	public By getRetypePasswordInputBox() {
		return By.xpath(retypePasswordInputBox);
	}
	
	public By getSecurityAnswerInputBox() {
		return By.xpath(securityAnswerInputBox);
	}

	public By getRegisterButton() {
		return By.xpath(registerButton);
	}

	public void createNewIndividualContact() throws Exception {
		
		common.log("50. Verifying URL Text for OES page");
		common.verifyUrl(OES_URL, 20);
		
		common.log("51. Verifying page title text on OES Primary page");
		actionDriver.waitForElementToBeVisible(AUTO_BROKER, 40);

		common.log("52. Inserting email address");
		common.enterTestData(getEmailAddressInputBox(), randomEmail);
		
		common.log("53. Retyping email address again");
		common.enterTestData(getRetypeEmailAddressInputBox(), randomEmail);
		
		common.log("54. Inserting password");
		common.enterTestData(getPasswordInputBox(), "Test123!");
		
		common.log("55. Retyping password again");
		common.enterTestData(getRetypePasswordInputBox(), "Test123!");
		
		common.log("56. Inserting security answer");
		common.enterTestData(getSecurityAnswerInputBox(), securityAnswerText);

		common.log("57. Clicking Register button");
		actionDriver.clickAndWaitForElementToDisappear(getRegisterButton(), 20);		
		
	}

}
