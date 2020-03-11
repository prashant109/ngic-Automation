package com.selenium.web.page.ipro;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;

public class OESSignAndSubmit{

	BaseActionDriver actionDriver;
	CommonUtil common;

	public OESSignAndSubmit() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}
			
	private String OES_Sign_Submit_URL = "OAS/OAS_EsignatureSubmission.aspx?";
	private String Completing_CB_Application_URL = "OAS/OAS_ConsolidatorThankYou.aspx?";
	private String Account_Dashboard_URL = "OAS/OAS_MyAccount.aspx?";
	public static String OES_eSignature_Submission  = "Congrats on finishing your application and thanks so much for all your hard work!";
	public static String Completing_CB_Application  = "Completing the application";
	public static String Account_Dashboard  = "Manage your policy or account information";
	private String agreeToBox1 = "ctl00_CH_ccOnlineContent_s16_ctl00_c164_chkSCQ_Response";
	private String agreeToBox2 = "ctl00_CH_ccOnlineContent_s16_ctl01_c310_chkSCQ_Response";
	private String firstNameInputBox1 = "ctl00_CH_electronicSignatureUserControl0_txtSignFirstName";
	private String firstName1 = "Testzzz";
	private String lastNameInputBox1 = "ctl00_CH_electronicSignatureUserControl0_txtSignLastName";
	private String lastName1 = "Wadhwa";
	private String firstNameInputBox2 = "ctl00_CH_electronicSignatureUserControl0_txtConfirmFirstName";
	private String lastNameInputBox2 = "ctl00_CH_electronicSignatureUserControl0_txtConfirmLastName";
	private String submitApplicationButton = "ctl00_CH_btnSubmitOnline_bottom";
	private String accountDashboard = "ctl00_CH_btnMyAccount";
	private String logoutFromAccountDashboard = "ctl00_btnLogout1";
	

	public By getAgreeToBox1() {
		return By.id(agreeToBox1); 
	}
	
	public By getAgreeToBox2() {
		return By.id(agreeToBox2); 
	}

	public By getFirstNameInputBox1() {
		return By.id(firstNameInputBox1);
	}

	public By getLastNameInputBox1() {
		return By.id(lastNameInputBox1);
	}

	public By getFirstNameInputBox2() {
		return By.id(firstNameInputBox2);
	}
	
	public By getLastNameInputBox2() {
		return By.id(lastNameInputBox2);
	}
	
	public By getSubmitApplicationButton() {
		return By.id(submitApplicationButton);
	}
	
	public By getAccountDashboard() {
		return By.id(accountDashboard);
	}
	
	public By getLogoutFromAccountDashboard() {
		return By.id(logoutFromAccountDashboard);
	}
	

	public void eSignAndSubmit() throws Exception {
		
		common.log("84. Verifying URL Text for Sign and submit page");
		common.verifyUrl(OES_Sign_Submit_URL, 20);
		
		common.log("85. Verifying page title text on Sign and submit page");
		actionDriver.waitForElementToBeVisible(OES_eSignature_Submission, 40);

		common.log("86. Clicking Checkbox 1");
		actionDriver.click(getAgreeToBox1());
		
		common.log("87. Clicking Checkbox 2");
		actionDriver.click(getAgreeToBox2());
		
		common.log("88. Inserting first name");
		common.enterTestData(getFirstNameInputBox1(), firstName1);
		
		common.log("89. Inserting last name");
		common.enterTestData(getLastNameInputBox1(), lastName1);
		
		common.log("90. Inserting first name again");
		common.enterTestData(getFirstNameInputBox2(), firstName1);
		
		common.log("91. Inserting last name again");
		common.enterTestData(getLastNameInputBox2(), lastName1);
			
		common.log("92. Clicking Submit Application button");
		actionDriver.click(getSubmitApplicationButton());		
	}
	
	public void completingCBApplication() throws Exception {
		
		common.log("84. Verifying URL Text for Completing the application");
		common.verifyUrl(Completing_CB_Application_URL, 20);
		
		common.log("85. Verifying page title text on Sign and submit page");
		actionDriver.waitForElementToBeVisible(Completing_CB_Application, 40);

		common.log("86. Clicking Account Dashboard button");
		actionDriver.click(getAccountDashboard());
	}

	public void logoutFromAccountDashboard() throws Exception {
		
		common.log("84. Verifying URL Text for Account Dashboardn");
		common.verifyUrl(Account_Dashboard_URL, 20);
		
		common.log("85. Verifying page title text on Account Dashboard");
		actionDriver.waitForElementToBeVisible(Account_Dashboard, 40);

		common.log("86. Clicking Logout button on top-right of Account Dashboard page");
		actionDriver.click(getLogoutFromAccountDashboard());
	}

	
}
