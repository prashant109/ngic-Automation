package com.selenium.web.page.ipro;

import java.util.List;

import org.openqa.selenium.By;

import com.selenium.setup.SelTestCase;
import com.selenium.testdata.RandomUtil;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;

public class SelectPlans {

	BaseActionDriver actionDriver;
	CommonUtil common;

	public SelectPlans() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}

	private String planLogo = "//*[@alt='Oscar']";
	public static String SELECT_PLANS  = "Select Plans";
	public static String CHOOSE_PACKAGE  = "Choose Package";
	private String addPlan1ToCart ="(//button[@ng-click='addPlan(plan);'])[1]"; 
	private String addPlan2ToCart ="(//button[@ng-click='addPlan(plan);'])[2]";
	private String createProposalButton = "//button[contains(text(),'PROPOSAL')]";
	private String createProposalButton1 = "//button[contains(text(),'Create Proposal')]";
	private String enrollNowButton = "//button[contains(text(),'Enroll Now')]";
	private String emailAddressInputBox = "txtEmail";
	private String okButton = "(//button[contains(text(),'OK')])[2]";
	private String randomEmail = RandomUtil.getRandomEmail();


	public By getPlanLogo() {
		return By.xpath(planLogo);
	}

	public By getAddPlan1ToCart() {
		return By.xpath(addPlan1ToCart);
	}
	
	public By getAddPlan2ToCart() {
		return By.xpath(addPlan2ToCart);
	}
	
	public By getCreateProposalButton() {
		return By.xpath(createProposalButton);
	}
	
	public By getCreateProposalButton1() {
		return By.xpath(createProposalButton1);
	}
	
	public By getEnrollNowButton() {
		return By.xpath(enrollNowButton);
	}
	
	public By getEmailAddressInputBox() {
		return By.id(emailAddressInputBox);
	}
	
	public By getOkButton() {
		return By.xpath(okButton);
	}
	
	public void AddPlansAndCreateProposal() throws Exception {
		
		common.log("30. Verifying page title text on Select Plans");
		actionDriver.waitForElementToBeVisible(SELECT_PLANS, 40);
		
		common.log("31. Scrolling to the plan to add");
		actionDriver.scrollToWebElement(getAddPlan1ToCart());
		
		common.log("32. Clicking add button to add plan1 to cart");
		actionDriver.click(getAddPlan1ToCart());
		
		
		common.log("33. Scrolling to the plan to add");
		//actionDriver.scrollToWebElement(getPlanLogo()); //doesn't work
		
		
		common.log("34. Clicking add button to add plan2 to cart");
		actionDriver.click(getAddPlan2ToCart());
		
		common.log("35. Clicking New Proposal button and waiting for it to dissappear");
		actionDriver.clickAndWaitForElementToDisappear(getCreateProposalButton(), 20);
		
	}
	
	public void ChoosePackageAndCreateProposal() throws Exception {
		
		common.log("25. Verifying page title text on Choose Package");
		actionDriver.waitForElementToBeVisible(CHOOSE_PACKAGE, 40);
		
			
		common.log("26. Clicking Enroll now button and waiting for it to dissappear");
		actionDriver.click(getEnrollNowButton());
		
		common.log("27. Inserting email address");
		common.enterTestData(getEmailAddressInputBox(), randomEmail);
		
		common.log("28. Clicking OK button and waiting for it to dissappear");
		actionDriver.clickAndWaitForElementToDisappear(getOkButton(), 20);
		
		List<String> windowId = common.getWindowsId();
		actionDriver.switchTo().window(windowId.get(1));
		actionDriver.maximizeBrowser();
		String url = actionDriver.getCurrentUrl();
		common.log(url);
	}

}
