package com.selenium.web.page.common;

import java.io.BufferedReader;
import java.io.FileReader;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.util.ActionDriverExt;
import com.selenium.util.CommonUtil;
import com.selenium.util.DIR;
import com.selenium.util.actiondriver.BaseActionDriver;

public class LoginPage {

	BaseActionDriver actionDriver;
	CommonUtil common;
	ActionDriverExt actionDriverExt;

	public LoginPage() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
		this.actionDriverExt = new ActionDriverExt();
	}

	private String emailInputBox = "//input[@id='input-email-address']";
	private String passwordInputBox = "//input[@id='input-password']";
	private String loginButton = "//button[contains(text(),'CONTINUE')]";
	private String privacyPolicyButton = "//*[contains(@class,'privacy-policy')]";
	private String termsOfUseButton = "//*[text()='Terms of Use']";
	private String policyDetails = "//*[@id='privacyPolicy']";
	private String termsOfUseContent = "//div[@id='termsOfUse']";

	// forgot password
	private String forgotPasswordButton = "//*[text()='Forgot Password']";
	private String memberForgotPassword_emailInputbox = "//input[@id='forgot-pw-email-input']";
	private String providerForgotPassword_emailInputbox = "//input[@id='input-email-address']";
	private String forgotPassword_continueButton = "//*[text()='CONTINUE']";
	private String emailSent_okButton = "//*[@id=\"txtConfirmBtn\"]";

	private String dismissButton = "//button[text()='Dismiss']";

	public static String ENTERED_EMAIL_NOTFOUND_ERROR_MSG = "The entered email address could not be found.  Please try again or contact support.";
	public static String YOUR_EMAIL_CHANGE_CANCELLED = "Your email change has been cancelled";
	public static String INVALID_USERNAME_PASSWORD = "Invalid username and/or password";

	public By getDismissButton() {
		return By.xpath(dismissButton);
	}

	public By getTermsOfUseContent() {
		return By.xpath(termsOfUseContent);
	}

	public By getPolicyDetails() {
		return By.xpath(policyDetails);
	}

	public By getTermsOfUseButton() {
		return By.xpath(termsOfUseButton);
	}

	public By getPrivacyPolicyButton() {
		return By.xpath(privacyPolicyButton);
	}

	public By getMemberForgotPassword_emailInputbox() {
		return By.xpath(memberForgotPassword_emailInputbox);
	}

	public By getProviderForgotPassword_emailInputbox() {
		return By.xpath(providerForgotPassword_emailInputbox);
	}

	public By getForgotPassword_continueButton() {
		return By.xpath(forgotPassword_continueButton);
	}

	public By getEmailSent_okButton() {
		return By.xpath(emailSent_okButton);
	}

	public By getForgotPasswordButton() {
		return By.xpath(forgotPasswordButton);
	}

	public By getEmailInputBox() {
		return By.xpath(emailInputBox);
	}

	public By getPasswordInputBox() {
		return By.xpath(passwordInputBox);
	}

	public By getLoginButton() {
		return By.xpath(loginButton);
	}

	public void enterCredentials(String email, String password) throws Exception {
		actionDriver.waitForElementToBeVisible(getEmailInputBox(), 20);
		common.enterTestData(getEmailInputBox(), email);
		common.enterTestData(getPasswordInputBox(), password);
	}

	public void clickLoginButton() throws Exception {
		actionDriver.waitForElementToBeVisible(getLoginButton());
		if (actionDriver.isElementVisible(By.xpath("//img[contains(@src,'close')]"), 2)) {
			actionDriver.click(By.xpath("//img[contains(@src,'close')]"));
		}
		actionDriver.click(getLoginButton());
	}

	public void insertUsernamePassword(String email, String password) throws Exception {
		common.log("Signing in to application | ");
		System.out.println("browserName " + actionDriver.getBrowserName());
		common.log("Login Email : " + email);
		actionDriver.waitForElementToBeVisible(getEmailInputBox(), 20);
		common.enterTestData(getEmailInputBox(), email);
		common.enterTestData(getPasswordInputBox(), password);
	}

	public void insertUsernamePasswordClickContinue(String email, String password) throws Exception {
		common.log("Signing in to application | ");
		System.out.println("browserName " + actionDriver.getBrowserName());
		common.log("Login Email : " + email);
		actionDriver.waitForElementToBeVisible(getEmailInputBox(), 20);
		common.enterTestData(getEmailInputBox(), email);
		common.enterTestData(getPasswordInputBox(), password);
		actionDriver.click(getLoginButton());
	}

	public void insertLoginsUntilLockout(String email, String password) throws Exception {
		common.log("In verifyProviderAccountLocked function ");
		By locator = By.xpath(common.getXpath(
				"You are locked out. Please wait thirty minutes before attempting to login again or contact an administrator."));
		By locator2 = By.xpath(common.getXpath("The Email field is not a valid e-mail address."));

		boolean isElementFound = false;
		Throwable error = null;
		for (int i = 0; i < 5; i++) {
			actionDriver.refreshBrowser();
			try {
				common.log("Incorrect login attempt :" + i);
				System.out.println("browserName " + actionDriver.getBrowserName());
				common.log("Login attempt # : " + i);
				common.log("Login Email : " + email);
				common.log("Login password : " + password + i);

				actionDriver.waitForElementToBeVisible(getEmailInputBox(), 20);
				common.enterTestData(getEmailInputBox(), email);
				common.enterTestData(getPasswordInputBox(), password + i);
				actionDriver.click(getLoginButton());

				if ((actionDriver.isElementVisible(locator, 2)) && (!actionDriver.isElementVisible(locator2, 2))) {
					isElementFound = true;
					break;
				}

			} catch (Throwable t) {
				common.log(t.getMessage());
				error = t;
				common.log("Trying to click again !!");
			}
		}
		if (!isElementFound) {
			throw new Exception(error);
		}
	}

	public void forgotPassword(String email) throws Exception {
		actionDriver.click(getForgotPasswordButton());

		if (actionDriver.isElementVisible(getMemberForgotPassword_emailInputbox(), 2)) {
			common.enterTestData(getMemberForgotPassword_emailInputbox(), email);
			actionDriver.clickAndWaitForElementToDisappear(getForgotPassword_continueButton(), 40);
			common.verifyText("Email Sent");
		} else if (actionDriver.isElementVisible(getProviderForgotPassword_emailInputbox(), 2)) {
			common.enterTestData(getProviderForgotPassword_emailInputbox(), email);
			actionDriver.clickAndWaitForNextElementToBePresent(getForgotPassword_continueButton(),
					getEmailSent_okButton());
			common.verifyText("We sent you an email with instructions to reset your password");
			actionDriver.clickAndWaitForElementToDisappear(getEmailSent_okButton(), 2);

		} else {
			common.log("Something is wrong with forgot pw");
		}
	}

	public void verifyPrivacyPolicy() throws Exception {
		common.log("Verify Privacy Policy");
		String actualPrivacyPolicy = actionDriver.getText(getPolicyDetails());
		System.out.println(actualPrivacyPolicy);
		String actualText[] = actualPrivacyPolicy.split("\\n");
		BufferedReader br = new BufferedReader(new FileReader(DIR.TESTDATA_FOLDER_PATH + "WellfitPrivacyPolicy.txt"));
		try {
			String line = br.readLine();
			int i = 0;
			while (line != null) {
				common.compareText(actualText[i], line);
				line = br.readLine();
				i++;
			}
		} finally {
			br.close();
		}
	}

	public void verifyTermsOfUse() throws Exception {
		common.log("Verify Terms Of Use");
		String text = actionDriver.getText(getTermsOfUseContent());
		System.out.println(text);
		String actualText[] = text.split("\\n");
		BufferedReader br = new BufferedReader(new FileReader(DIR.TESTDATA_FOLDER_PATH + "TermsOfUse.txt"));
		try {
			String line = br.readLine();
			int i = 0;
			while (line != null) {
				common.compareText(actualText[i], line);
				line = br.readLine();
				i++;
			}
		} finally {
			br.close();
		}
	}

}
