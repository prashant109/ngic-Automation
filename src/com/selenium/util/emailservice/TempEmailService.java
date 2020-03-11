package com.selenium.util.emailservice;

import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;
import com.selenium.util.actiondriver.WebActionDriver;
import com.selenium.util.enums.Browser;

public class TempEmailService {

	private BaseActionDriver actionDriver;
	private CommonUtil common;

	public TempEmailService(BaseActionDriver actionDriver, ExtentTest test) {
		this.actionDriver = actionDriver;
		common = new CommonUtil(actionDriver);
		common.initializeLogs(test);
	}

	public TempEmailService() throws Exception {
		this.actionDriver = new WebActionDriver();
		common = new CommonUtil(actionDriver);
		common.initializeLogs(SelTestCase.getTest());
		actionDriver.initializeLogging();
		common.initializeDriver(Browser.HEAD_LESS.toString());
	}

	public CommonUtil getCommon() {
		return common;
	}

	public BaseActionDriver getActionDriver() {
		return actionDriver;
	}

	public void resetTempEmailService() {
		common.log("Reset Temp Email Service !!!");
		actionDriver.closeBrowser();
		common = null;
		actionDriver = null;
		// driver = null;
	}

	private String url = "https://www.tempmailaddress.com/";
	private String emailLogo = "//img[@alt='Patolus Logo'] | //img[@id='company-logo'] | //img[@id='patolus-image']";
	private String fromEmail = "//*[text()='From:']/following-sibling::span";

	// EMAL SUBJECT TITLE
	public static String WELCOME_TO_WELLFIT = "Welcome to Wellfit";
	public static String ACCOUNT_ACTIVATED = "Account Activated";
	public static String ACTION_REQUIRED_TREATMENT_SENT_FOR_APPROVAL = "Action required: A treatment summary has been sent to you for your review and approval.";
	public static String TREATMENT_SUMMARY_READY_FOR_REVIEW = "A Treatment Summary is ready for you. Sign in to your account to review and approve.";
	public static String WELCOME_TO_MYSMILEDENTALPLAN = "Welcome to the My Smile Dental Plan";
	public static String VERIFY_EMAIL_ADDRESS = "Verify Email Address";
	public static String INVITED_TO_ACTIVATE_DENTAL_BENEFIT = "You have been invited to activate your dental benefit";
	public static String YOU_HAVE_BEEN_ADDED_AS_PRACTICEUSER = "been added as a Practice User";
//	public static String YOU_HAVE_BEEN_INVITED_FOR_PRACTICEUSER = "Been Invited to Join a Practice";
	public static String FORGOT_PASSWORD = "Someone requested a new password";
	public static String PASSWORD_RESET_SUCCESSFULLY = "Password reset successfully";
	public static String WELCOME_TO_SMILE_HEALTH_CORE_WELLNESSPLAN = "Welcome to the Smile Health Core Wellness Plan";
	public static String WELCOME_TO_SMILE_HEALTH_AllIN_WELLNESSPLAN = "Welcome to the Smile Health All-In Wellness Plan";
	public static String WELCOME_TO_SMILE_HEALTH_Select_WELLNESSPLAN = "Welcome to the Smile Health Select Wellness Plan";

	public static String CSP_INVITE = "Join Group";

	// TEXT VALIDATION EMAIL CONTENT
	public static String PDS_DENTAL_PLAN_TEXT = "PDS Team Dental Plan";
	public static String WDC_DENTAL_PLAN_TEXT = "WDC Team Dental Plan";
	public static String MYSMILE_DENTAL_PLAN_TEXT = "My Smile Dental Plan";
	public static String THANKYOU_FOR_ENROLLING_SMILEHEALTHCOREWELLNESSPLAN = "Thank you for enrolling in the Smile Health Core Wellness Plan";
	public static String THANKYOU_FOR_ENROLLING_SMILEHEALTH_ALLIN_WELLNESSPLAN = "Thank you for enrolling in the Smile Health All-In Wellness Plan";
	public static String THANKYOU_FOR_ENROLLING_SMILEHEALTH_SELECT_WELLNESSPLAN = "Thank you for enrolling in the Smile Health Select Wellness Plan";

	public static String I_DID_NOT_MAKE_THIS_CHANGE = "I DID NOT MAKE THIS CHANGE";

	public static String NO_REPLY_EMAIL = "no-reply@wellfit.com";

	public static String FROM_EMAIL = "Wellfit";

	public By getEmailLogo() {
		return By.id(emailLogo);
	}

	public By getFromEmail() {
		return By.xpath(fromEmail);
	}

	public By getDeleteButton() {
		return By.xpath("(//a[@href='/delete'])[2]");
	}

	public By getEmail() {
		return By.xpath("//*[@id='email']");
	}

	public String getTemprorayEmail() throws TimeoutException, Exception {
		By welcomeLocator = By.xpath("//*[contains(text(),'Temp Mail Address')]");
		actionDriver.get(url);
		actionDriver.waitForElementToBePresent(welcomeLocator);
		actionDriver.waitForElementToBeVisible(By.xpath("//ul[@class='list-inline noBorder']//a[@href='/delete']"), 5);
		actionDriver.click(By.xpath("//ul[@class='list-inline noBorder']//a[@href='/delete']"));
		String email = actionDriver.getText(getEmail());
		for (int i = 0; i < 5; i++) {
			if (email == null) {
				actionDriver.refreshBrowser();
				actionDriver.waitForElementToBeVisible(welcomeLocator);
				email = actionDriver.getText(getEmail());
			} else {
				break;
			}
		}
		common.log("email : " + email);
		return email;
	}

	public void verifyEmailCount(String emailSubject, int count) throws Exception {
		common.log("Verify Email count!!");
		actionDriver.get(url);
		By locator = By.xpath("//*[contains(text(),'" + emailSubject + "')]");
		for (int i = 0; i < 5; i++) {
			if (actionDriver.getElements(locator, 4).size() == count) {
				common.log("Email count verified!!");
				break;
			} else {
				actionDriver.refreshBrowser();
			}
			common.log("Email count verification failed. Trying again");
			Thread.sleep(2000);
		}
	}

	public void retrieveLatestEmail(String emailSubject) throws Exception {
		common.log("Retrieving latest email from Temp email service");
		actionDriver.get(url);
		By locator = By.xpath("//*[contains(text(),'" + emailSubject + "')]");
		for (int i = 0; i < 7; i++) {
			if (actionDriver.isElementPresent(locator, 4)) {
				break;
			} else {
				actionDriver.refreshBrowser();
			}
		}
		actionDriver.waitForElementToBePresent(locator, 1);
		actionDriver.clickUsingJavaScript(locator);
		actionDriver.waitForElementToBeVisible(By.xpath("//*[@id='iframeMail']"));
		actionDriver.setImplicitWaitOnDriver(1);
		actionDriver.switchTo().frame("iframeMail");
		actionDriver.setImplicitWaitOnDriver(SelTestCase.WAIT_TIMEOUT);
	}

	public void retrieveLatestEmail(String emailSubject, int numberOfEmails) throws Exception {
		actionDriver.get(url);
		By locator = By.xpath("//*[contains(text(),'" + emailSubject + "')]");
		for (int i = 0; i < 10; i++) {
			if (actionDriver.isElementVisible(locator, 10)) {
				if (actionDriver.getElements(locator, 1).size() == numberOfEmails) {
					break;
				} else {
					actionDriver.refreshBrowser();
					Thread.sleep(5000);
				}
			} else {
				actionDriver.refreshBrowser();
				Thread.sleep(5000);
			}
		}
		actionDriver.waitForElementToBePresent(locator, 1);
		actionDriver.clickUsingJavaScript(locator);
		actionDriver.waitForElementToBeVisible(By.xpath("//*[@id='iframeMail']"));
		actionDriver.setImplicitWaitOnDriver(1);
		actionDriver.switchTo().frame("iframeMail");
		actionDriver.setImplicitWaitOnDriver(SelTestCase.WAIT_TIMEOUT);
	}

	public void selectLinkFromEmail() throws Exception {
		String url = actionDriver.getElement(By.xpath("//a")).getAttribute("href");
		actionDriver.get(url);
	}

	public void verifyEmailTitle(String title) throws Exception {
		common.verifyText(title, 2);
	}

	public String getLinkFromEmail() throws Exception {
		String url = actionDriver.getElement(By.xpath("//a")).getAttribute("href");
		return url;
	}

	public String getSignInLink() throws Exception {
		List<WebElement> link = actionDriver.getElements(By.xpath("//a[contains(@href,'sendgrid')]"), 40);
		for (WebElement url : link) {
			if (url.getText().toLowerCase().contains("sign")) {
				return url.getAttribute("href");
			}
		}
		return null;
	}

	public void verifyTreatmentReadyForReviewEmail(String memberName, String practiceName, String yourShare)
			throws TimeoutException, Exception {
		common.verifyText(TempEmailService.TREATMENT_SUMMARY_READY_FOR_REVIEW);
		common.verifyText("A Treatment Summary is ready for you. Sign in to your account to review and approve.");
		By locator = By.xpath("//*[contains(text(),'Member')]/../../..");
		common.verifyText("Member Name " + memberName, locator);
		common.verifyText("Treatment Plan $" + yourShare.replace("$", ""), locator);
		common.verifyText("For general inquiries or help, please call");
		common.verifyText("1-855-728-6587", By.xpath("//a[contains(@href,'tel:')]"));
		common.verifyText("support@wellfit.com", By.xpath("//a[contains(@href,'mailto')]"));
	}

	public void verifyFromEmail(String email) throws Exception {
		actionDriver.switchTo().defaultContent();
		common.verifyText(email, getFromEmail());
		actionDriver.setImplicitWaitOnDriver(1);
		actionDriver.switchTo().frame("iframeMail");
		actionDriver.setImplicitWaitOnDriver(SelTestCase.WAIT_TIMEOUT);
	}

	public void verifyWelcomeToMSDPEmail(String firstName, String lastName, String msdpCost, String cardNumber)
			throws Exception {
		common.verifyText(firstName);
		common.verifyText(lastName);
		By totalPaidLocator = By.xpath("//*[contains(text(),'Total Paid')]/following-sibling::td");
		common.compareNumericValue(actionDriver.getTextWithoutSpecialCharacters(totalPaidLocator), msdpCost);
		if (cardNumber != null) {
			cardNumber = cardNumber.substring(cardNumber.length() - 4);
			common.compareText(actionDriver.getText(By.xpath("//*[contains(text(),'Ending in')]")), cardNumber);
		}
	}

	public void verifyWellfitLogoDisplayed() throws Exception {
		By logoLocator = By.xpath("//img[contains(@title,'Logo')]");
		String url = actionDriver.getElement(logoLocator).getAttribute("src");
		Assert.assertTrue(common.isLinkBroken(new URL(url)), "Issue : Plan Logo is not displayed");
	}

	public void verifyWelcomeToSmileHealthPlanEmail(String fromEmail, String firstName, String lastName,
			String emailContent, String totalPaid, String cardNumber) throws Exception {
		verifyFromEmail(fromEmail);
		common.verifyText("Hello, " + firstName + " " + lastName);
		common.verifyText(emailContent);
		common.compareText(
				actionDriver.getText(By.xpath("//*[contains(text(),'Total Paid')]/../following-sibling::td")),
				totalPaid);
		cardNumber = cardNumber.substring(cardNumber.length() - 4);
		common.compareText(actionDriver.getText(By.xpath("//*[contains(text(),'Ending in')]")), cardNumber);
	}
	
	
	public void verifyCardLogo() throws Exception {
		By logoLocator = By.xpath("//*[contains(text(),'Ending in')]/../parent::tr//img");
		String url = actionDriver.getElement(logoLocator).getAttribute("src");
		Assert.assertTrue(common.isLinkBroken(new URL(url)), "Issue : Card Logo is not displayed");
	}

}
