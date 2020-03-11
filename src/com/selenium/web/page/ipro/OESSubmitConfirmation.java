package com.selenium.web.page.ipro;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;

public class OESSubmitConfirmation{

	BaseActionDriver actionDriver;
	CommonUtil common;

	public OESSubmitConfirmation() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}
			
	private String OES_Submit_Confirmaiton_URL = "OAS/OAS_ApplicationStatus.aspx?";
	public static String OES_CONGRATULATIONS  = "Congratulations! Your application has been submitted.";
	private String bottomLogoutButton = "ctl00_CH_btnLogout";	


	public By getBottomLogoutButton() {
		return By.id(bottomLogoutButton);
	}


	public void submitConfirmationAndLogout() throws Exception {
		
		//common.log("verifying URL Text for OES Submit Confirmaiton page");
		common.verifyUrl(OES_Submit_Confirmaiton_URL, 20); 
						
		
		//common.log("verifying page title text on OES Submit Confirmaiton Page");
		actionDriver.waitForElementToBeVisible(OES_CONGRATULATIONS, 40);	
		
		common.log("clicking Logout button to complete the Smoke test");
		actionDriver.click(getBottomLogoutButton());	
		
		common.log("Smoke test completed successfully");
		
	}

}
