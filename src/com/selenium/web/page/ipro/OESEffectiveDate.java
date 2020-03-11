package com.selenium.web.page.ipro;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;
import com.selenium.web.page.common.SettingsPage;

public class OESEffectiveDate extends SettingsPage{

	BaseActionDriver actionDriver;
	CommonUtil common;

	public OESEffectiveDate() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}
			
	//private String OES_EDATE_URL = "OAS/OAS_EffectiveDateList.aspx?";
	public static String OES_EFFECTIVE  = "Please select a new effective date.";
	private String effectiveDateRadioButton = "ctl00_CH_ctl00_rblEffectiveDate_0";	


	public By getEffectiveDateRadioButton() {
		return By.id(effectiveDateRadioButton);
	}


	public void selectNewEffectiveDate() throws Exception {
		
		//common.log("verifying URL Text for OES effective date page");
		//common.verifyUrl(OES_EDATE_URL, 20); 
						
		
		//common.log("verifying page title text on OES Effective Date Page");
		//actionDriver.waitForElementToBeVisible(OES_EFFECTIVE, 40);

		//common.log("click Effective Date Radio Button");
		//actionDriver.click(getEffectiveDateRadioButton());		
		
		common.log("58. Clicking Continue button");
		actionDriver.click(getContinueButton());	
		
	}

}
