package com.selenium.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.selenium.setup.SelTestCase;
import com.selenium.util.actiondriver.BaseActionDriver;

public class ActionDriverExt {

	BaseActionDriver actionDriver;
	CommonUtil common;

	public ActionDriverExt() {
		actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}

	public void click(By locator) throws Exception {
		actionDriver.findElement(locator).click();
	}

	public void input(By locator, String testdata) throws Exception {
		common.log("sendkeys me");
		actionDriver.findElement(locator).sendKeys(testdata);
	}

	public WebElement waitForElementToBeVisible(final By locator) throws Exception {
		common.log("wait for me");
		common.log("Wait for element to be visible : " + locator);
		actionDriver.waitForAngularRequestsToFinish();
		WebDriverWait wait = actionDriver.getWebDriverWaitObject(SelTestCase.WAIT_TIMEOUT);
		WebElement element = null;
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

}
