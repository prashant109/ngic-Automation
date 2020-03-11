package com.selenium.util;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;

public class CommonUtilExt extends CommonUtil {
	
	private BaseActionDriver actionDriver;
	
	public CommonUtilExt() {
		super(SelTestCase.getActionDriver());
		initializeLogs(SelTestCase.getTest());
		this.actionDriver = SelTestCase.getActionDriver();
	}
	
	public void verifyInputBoxNotNull(By locator) throws Exception {
		log("In verifyInputBoxNotNull function : ");
		boolean check = true;
		for (int i = 0; i < 20; i++) {
			log(actionDriver.getInputBoxValue(locator));
			if (actionDriver.getInputBoxValue(locator).length() > 2) {
				check = false;
				break;
			}
			log("Value is null. Trying again !!");
			Thread.sleep(1000);
		}
		if (check) {
			throw new Exception("Value should not be empty. Refer screenshot");
		}
	}
}
