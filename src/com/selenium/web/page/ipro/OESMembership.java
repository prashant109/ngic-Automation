package com.selenium.web.page.ipro;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;
import com.selenium.web.page.common.SettingsPage;

public class OESMembership extends SettingsPage{

	BaseActionDriver actionDriver;
	CommonUtil common;

	public OESMembership() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}
			
	private String OES_AppInfo_URL = "OAS/OAS_Generic2.aspx?";
	public static String OES_APPLICATION_INFO  = "Primary Applicant Info";
	public static String OES_BENEFICIARY_INFO  = "Beneficiary Information";
	private String beneficiaryInformaiton = "ctl00_CH_TopContentCustom1_lblMainContentCaption";
	
	private String firstNameInputBox = "//input[@datamapname='PrimaryApplicantFirstName']";
	private String lastNameInputBox = "//input[@datamapname='PrimaryApplicantLastName']";

	private String sSNInputBox = "(//input[contains(@datamapname,'PrimaryApplicantSSN')])";
		private String selectMaritalStatus = "//select[@datamapname='ApplicantMaritalStatus']";
	//private String maritalStatusSelection = "Single";
	private String streetAddressInputBox = "//input[@datamapname='ApplicantAddress']";
	private String cityInputBox = "//input[@datamapname='ApplicantCity']";
	private String homePhoneInputBox = "(//input[contains(@datamapname,'ContactPhoneNumber')])";
	private String workPhoneInputBox = "(//input[contains(@datamapname,'BusinessPhoneNumber')])";
	private String registerButton = "//input[contains(@name,'btnRegister')]";
	public static String Membership  = "Membership";
	public static String membershipCoverage = "ctl00_CH_ContentCtrl_s4_ctl00_c3128_rblMCQ_Response_1";
	public static String effectiveDateRadioButton = "ctl00_CH_ContentCtrl_s3_ctl00_c339_rblMCQ_Response_1";	
	public static String existingInsuranceRadioButton = "ctl00_CH_ContentCtrl_s6_ctl00_c314_rblMCQ_Response_1";
	public static String isApplicantPregnantRadioButton = "ctl00_CH_ContentCtrl_s6_ctl00_c292_rblMCQ_Response_1";
	public static String last5YearsMedicalHistoryRadioButton = "ctl00_CH_ContentCtrl_s6_ctl01_c293_rblMCQ_Response_1";
	public static String last5YearsHIVHistoryRadioButton = "ctl00_CH_ContentCtrl_s6_ctl02_c294_rblMCQ_Response_1";
	public static String last12MonthsMedicalHistoryRadioButton = "ctl00_CH_ContentCtrl_s6_ctl03_c296_rblMCQ_Response_1";
	public static String MaritalStatus  = "Single";
	private String beneficiaryNameInputBox = "//input[@datamapname='MI_BeneficiaryName']";
	private String relationshipInputBox = "//input[@datamapname='MI_BeneficiaryRelationship']";
	private String phoneTypeDropDown = "//select[@id='ctl00_CH_ContentCtrl_s4_ctl15_c289_ddlMCQ_Response']";
	private String heightFeetDropDown = "ctl00_CH_ContentCtrl_s7_ctl00_c413_ddlMCQ_Response";
	private String heightInchDropDown = "ctl00_CH_ContentCtrl_s7_ctl00_c426_ddlMCQ_Response";
	private String weightLbsInputBox = "ctl00_CH_ContentCtrl_s7_ctl01_c427_txtNQ_Response";



	public By getFirstNameInputBox() {
		return By.xpath(firstNameInputBox);
	}
	
	public By getLastNameInputBox() {
		return By.xpath(lastNameInputBox);
	}
	
	public By getSSNInputBox() {
		return By.xpath(sSNInputBox);
	}

	public By getSelectMaritalStatus() {
		return By.xpath(selectMaritalStatus);
	}

	public By getStreetAddressInputBox() {
		return By.xpath(streetAddressInputBox);
	}

	public By getCityInputBox() {
		return By.xpath(cityInputBox);
	}
	
	public By getHomePhoneInputBox() {
		return By.xpath(homePhoneInputBox);
	}
	
	public By getWorkPhoneInputBox() {
		return By.xpath(workPhoneInputBox);
	}

	public By getRegisterButton() {
		return By.xpath(registerButton);
	}
	
	public By getMembershipCoverage() {
		return By.id(membershipCoverage);
	}
	
	public By getEffectiveDateRadioButton() {
		return By.id(effectiveDateRadioButton);
	}
	
	public By getExistingInsuranceRadioButton() {
		return By.id(existingInsuranceRadioButton);
	}
	
	public By getIsApplicantPregnantRadioButton() {
		return By.id(isApplicantPregnantRadioButton);
	}	
	
	public By getLast5YearsMedicalHistoryRadioButton() {
		return By.id(last5YearsMedicalHistoryRadioButton);
	}	
	
	public By getLast5YearsHIVHistoryRadioButton() {
		return By.id(last5YearsHIVHistoryRadioButton);
	}	
	
	public By getLast12MonthsMedicalHistoryRadioButton() {
		return By.id(last12MonthsMedicalHistoryRadioButton);
	}	
		
	public By getBeneficiaryNameInputBox() {
		return By.xpath(beneficiaryNameInputBox);
	}
	
	public By getRelationshipInputBox() {
		return By.xpath(relationshipInputBox);
	}
	
	public By getPhoneTypeDropDown() {
		return By.xpath(phoneTypeDropDown);
	}
	
	public By getHeightFeetDropDown() {
		return By.id(heightFeetDropDown);
	}
	
	public By getHeightInchDropDown() {
		return By.id(heightInchDropDown);
	}

	public By getWeightLbsInputBox() {
		return By.id(weightLbsInputBox);
	}
	
	public void oesFormDental() throws Exception {
		
		common.log("59. Verifying URL Text for OES Application Information page");
		common.verifyUrl(OES_AppInfo_URL, 20);
		
		common.log("60. Verifying page title text on OES Application Information page");
		actionDriver.waitForElementToBeVisible(OES_APPLICATION_INFO, 40);

		//common.log("61. Inserting SSN");
		/*common.enterTestData(getSSNInputBox(), "230174210");

		common.log("Selecting marital status from drop-down");
		actionDriver.waitForElementToBeVisible(getSelectMaritalStatus());
		actionDriver.selectByText(getSelectMaritalStatus(), maritalStatusSelection);*/
		
		common.log("62. Inserting street address");
		common.enterTestData(getStreetAddressInputBox(), "123 Lane");
		
		common.log("63. Inserting City");
		common.enterTestData(getCityInputBox(), "Irvine");
		
		common.log("64. Inserting Home Phone Number");
		common.enterPhoneNumber(getHomePhoneInputBox(), "3034085542");
		//common.enterTestData(getHomePhoneInputBox(), "3034085542");
	
		//common.log("65. Inserting Home Phone Number");
		//common.enterTestData(getHomePhoneInputBox(), "3034085542");		
		
		common.log("66. Clicking Register button");
		actionDriver.click(getContinueButton());
		
		//common.log("67. Verifying page title text on OES Effective Date Page");
		//actionDriver.waitForElementToBeVisible(Membership, 40);

		common.log("68. Click membership Radio Button");
		if (actionDriver.isElementVisible(getMembershipCoverage(), 2)){
			actionDriver.click(getMembershipCoverage());}
		
		common.log("69. Click Effective Date Radio Button");
		if (actionDriver.isElementVisible(getEffectiveDateRadioButton(), 2)){	
			actionDriver.click(getEffectiveDateRadioButton());}
		
		common.log("70. Clicking Register button");
		actionDriver.click(getContinueButton());
		
		common.log("71. Do you currently have vision insurance");
		if (actionDriver.isElementVisible(getExistingInsuranceRadioButton(), 2)){	
			actionDriver.click(getExistingInsuranceRadioButton());}
		
		common.log("72. Clicking Register button");
		actionDriver.click(getContinueButton());	
	}
	
public void oesFormCB() throws Exception {
		
		common.log("59. Verifying URL Text for OES Application Information page");
		common.verifyUrl(OES_AppInfo_URL, 20);
		
		common.log("60. Verifying page title text on OES Application Information page");
		actionDriver.waitForElementToBeVisible(OES_APPLICATION_INFO, 40);

		common.log("61. Inserting First Name");
		common.enterTestData(getFirstNameInputBox(), "Testzzz");
		
		common.log("62. Inserting Last name");
		common.enterTestData(getLastNameInputBox(), "Testzzz");
		
		common.log("63. Selecting marital status from drop-down");
		actionDriver.waitForElementToBeVisible(getSelectMaritalStatus());
		actionDriver.selectByText(getSelectMaritalStatus(), MaritalStatus);
		
		common.log("64. Inserting street address");
		common.enterTestData(getStreetAddressInputBox(), "123 Lane");
		
		common.log("65. Inserting City");
		common.enterTestData(getCityInputBox(), "Irvine");
		
		common.log("66. Inserting Home Phone Number");
		common.enterPhoneNumber(getHomePhoneInputBox(), "3034085542");
		
		common.log("67. Clicking Register button");
		actionDriver.click(getContinueButton());
		
		common.log("68. Verifying page title text on OES Effective Date Page");
		//actionDriver.waitForElementToBeVisible(Membership, 40);

		common.log("69. Click membership Radio Button");
		if (actionDriver.isElementVisible(getMembershipCoverage(), 2)){
			actionDriver.click(getMembershipCoverage());}
		
		common.log("70. Click Effective Date Radio Button");
		if (actionDriver.isElementVisible(getEffectiveDateRadioButton(), 2)){	
			actionDriver.click(getEffectiveDateRadioButton());}
		
		common.log("71. Clicking Continue button");
		actionDriver.click(getContinueButton());
		
		if (actionDriver.isElementVisible(getExistingInsuranceRadioButton(), 2)){	
			common.log("72. Do you currently have vision insurance?");
			actionDriver.click(getExistingInsuranceRadioButton());}
		
		if (actionDriver.isElementVisible(getIsApplicantPregnantRadioButton(), 2)){
			common.log("72a. Is any applicant now pregnant?");
			actionDriver.click(getIsApplicantPregnantRadioButton());}
		
		if (actionDriver.isElementVisible(getLast5YearsMedicalHistoryRadioButton(), 2)){
			common.log("72b. Has applicant received any medical treatment in last 5 years?");
			actionDriver.click(getLast5YearsMedicalHistoryRadioButton());}
				
		if (actionDriver.isElementVisible(getLast5YearsHIVHistoryRadioButton(), 2)){
			common.log("72c. Has applicant received any HIV treatment in last 5 years?");
			actionDriver.click(getLast5YearsHIVHistoryRadioButton());}
		
		if (actionDriver.isElementVisible(getLast12MonthsMedicalHistoryRadioButton(), 2)){
			common.log("72d. Has applicant received any medical treatment in last 12 months?");
			actionDriver.click(getLast12MonthsMedicalHistoryRadioButton());}
		
		if (actionDriver.isElementVisible(getHeightFeetDropDown(), 2)){
			common.log("72e. Select height in ft.");
			actionDriver.selectByValue(getHeightFeetDropDown(), "5");}
		
		if (actionDriver.isElementVisible(getHeightInchDropDown(), 2)){
			common.log("72f. Select height in Inch.");
			actionDriver.selectByValue(getHeightInchDropDown(), "0");}
		
		if (actionDriver.isElementVisible(getWeightLbsInputBox(), 2)){
			common.log("72g. Inserting weight in Lbs.");
			common.enterTestData(getWeightLbsInputBox(), "160");}

		if (actionDriver.isElementVisible(getWeightLbsInputBox(), 2)){
			common.log("73. Clicking Register button");
			actionDriver.click(getContinueButton());}		
				
	}

public void oesBeneficiary() throws Exception {
	
		common.log("59. Verifying URL Text for OES Beneficiary Information page");
		common.verifyUrl(OES_AppInfo_URL, 20);
		
/*		common.log("60. Verifying page title text on OES Application Information page");
		actionDriver.waitForElementToBeVisible(OES_APPLICATION_INFO, 40);*/
		
		
		if (actionDriver.isElementVisible(getPhoneTypeDropDown(), 2)){
			common.log("XX. Select phone number type - home, mobile or work");
			actionDriver.selectByValue(getPhoneTypeDropDown(), "Home");}

		if (actionDriver.isElementVisible(getBeneficiaryNameInputBox(), 2)){
			common.log("61. Inserting Beneficiary Full name");
			common.enterTestData(getBeneficiaryNameInputBox(), "Wadhwa, Dependant ");}
	
		if (actionDriver.isElementVisible(getBeneficiaryNameInputBox(), 2)){
			common.log("62. Inserting Beneficiary's relationship to Applicant");
			common.enterTestData(getRelationshipInputBox(), "Child");}
		
		if (actionDriver.isElementVisible(getContinueButton(), 2)){
			common.log("67. Clicking Continue button");
			actionDriver.click(getContinueButton());}
		
		if (actionDriver.isElementVisible(getContinueButton(), 2)){
			common.log("67a. Clicking Continue button on Primary Applicant Info");
			actionDriver.click(getContinueButton());}
		
		if (actionDriver.isElementVisible(getIsApplicantPregnantRadioButton(), 2)){
			common.log("72a. Is any applicant now pregnant?");
			actionDriver.click(getIsApplicantPregnantRadioButton());}
		
		if (actionDriver.isElementVisible(getLast5YearsMedicalHistoryRadioButton(), 2)){
			common.log("72b. Has applicant received any medical treatment in last 5 years?");
			actionDriver.click(getLast5YearsMedicalHistoryRadioButton());}
				
		if (actionDriver.isElementVisible(getLast5YearsHIVHistoryRadioButton(), 2)){
			common.log("72c. Has applicant received any HIV treatment in last 5 years?");
			actionDriver.click(getLast5YearsHIVHistoryRadioButton());}
		
		if (actionDriver.isElementVisible(getLast12MonthsMedicalHistoryRadioButton(), 2)){
			common.log("72d. Has applicant received any medical treatment in last 12 months?");
			actionDriver.click(getLast12MonthsMedicalHistoryRadioButton());}
		
		if (actionDriver.isElementVisible(getHeightFeetDropDown(), 2)){
			common.log("72e. Select height in ft.");
			actionDriver.selectByValue(getHeightFeetDropDown(), "5");}
		
		if (actionDriver.isElementVisible(getHeightInchDropDown(), 2)){
			common.log("72f. Select height in Inch.");
			actionDriver.selectByValue(getHeightInchDropDown(), "0");}
		
		if (actionDriver.isElementVisible(getWeightLbsInputBox(), 2)){
			common.log("72g. Inserting weight in Lbs.");
			common.enterTestData(getWeightLbsInputBox(), "160");}

		if (actionDriver.isElementVisible(getWeightLbsInputBox(), 2)){
			common.log("73. Clicking Register button");
			actionDriver.click(getContinueButton());}		
		
	}
}
