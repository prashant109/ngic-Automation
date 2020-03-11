package com.selenium.web.page.ipro;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;
import com.selenium.web.page.common.SettingsPage;

public class OESReview extends SettingsPage {

	BaseActionDriver actionDriver;
	CommonUtil common;

	public OESReview() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}
			
	private String OES_EDATE_URL = "OAS/OAS_ReviewVerify.aspx?";
	private String APPLICATION_SUMMARY_URL = "OAS/OAS_ConsolidatorReviewVerify.aspx";
	public static String OES_REVIEW  = "Want to double check your answers? Take a second to review the details";
	public static String OES_REVIEW_COVERAGE_BUILDER  = "Please review your applicatio";


	public void reviewDetailsAndContinue() throws Exception {
		
		common.log("81. Verifying URL OES Review page");
		common.verifyUrl(OES_EDATE_URL, 20); 
						
		common.log("82. Verifying page title text on OES Review Page");
		actionDriver.waitForElementToBeVisible(OES_REVIEW, 40);	

		common.log("83. Clicking Continue button");
		actionDriver.click(getContinueButton());		
	}
	
	public void reviewDetailsAndContinueCovBuilder() throws Exception {
		
		common.log("81. Verifying URL OES Review page for coverage builder");
		common.verifyUrl(APPLICATION_SUMMARY_URL, 20); 
						
		common.log("82. Verifying page title text on OES Review Page  for coverage builder");
		actionDriver.waitForElementToBeVisible(OES_REVIEW_COVERAGE_BUILDER, 40);	

		common.log("83. Clicking Continue button");
		actionDriver.click(getContinueButton());		
	}
}
