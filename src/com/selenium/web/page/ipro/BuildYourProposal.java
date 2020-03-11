package com.selenium.web.page.ipro;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.actiondriver.BaseActionDriver;

public class BuildYourProposal {

	BaseActionDriver actionDriver;
	CommonUtil common;

	public BuildYourProposal() {
		this.actionDriver = SelTestCase.getActionDriver();
		common = SelTestCase.getCommon();
	}

	public static String BUILD_YOUR_PROPOSAL  = "Build your proposal with different format and color options.";
	private String selectProposalType = "ddlReportType";
	private String selectProposalStyle = "ddlReportStyle";
	private String viewProposalButton = "//button[contains(text(),'View Proposal')]";
	private String iProCrystalReport_URL = "/crystal/crServiceGateWay.asp";
	private String eProReport_URL = "/quotit/apps/epro/EproReport";
	public static String COMPARE_AND_SHOP_PLANS  = "compare & shop plans";
	private String addToCart = "(//*[contains(text(),'Add to Cart')])[1]";
	private String viewCart = "(//*[text()='View Cart'])[1]";
	public static String HERE_IS_YOUR_QUOTE  = "Here is your quote";
	public static String checkoutButton = "//span[contains(text(),'Checkout')]";
	
	public static String emailOESBox = "//input[contains(@name,'txtEmailAddress')]";
	public static String retypeEmailOESBox = "//input[contains(@name,'txtConfirmEmailAddress')]";
	public static String passwordOESBox = "//input[contains(@name, 'txtPassword')]";
	public static String retypePasswordOESBox = "//input[contains(@name, 'txtConfirmPassword')]";
	public static String yourAnswer = "//input[contains(@name,'txtAnswer')]";
	public static String oes_UAP_URL  = "UAP/UAP_Registration.aspx";


	public By getEmailOESBox() {
		return By.xpath(emailOESBox);
	}
	
	public By getRetypeEmailOESBox() {
		return By.xpath(retypeEmailOESBox);
	}	
	
	public By getSelectProposalType() {
		return By.id(selectProposalType);
	}

	public By getSelectProposalStyle() {
		return By.id(selectProposalStyle);
	}	
	
	public By getViewProposalButton() {
		return By.xpath(viewProposalButton);
	}

	public By getAddToCart() {
		return By.xpath(addToCart);
	}

	public By getViewCart() {
		return By.xpath(viewCart);
	}

	public By getCheckoutButton() {
		return By.xpath(checkoutButton);
	}
	
	
	public void selectProposalTypeAndStyle(String proposalType, String proposalStyle) throws Exception {
		
		common.log("36. Verifying page title text on Build your proposal with diff......options");
		actionDriver.waitForElementToBeVisible(BUILD_YOUR_PROPOSAL, 40);
		
		common.log("37. Selecting proposal type from drop-down");
		actionDriver.waitForElementToBeVisible(getSelectProposalType());
		actionDriver.selectByText(getSelectProposalType(), proposalType);
		
		common.log("38. Selecting proposal style from drop-down");
		actionDriver.waitForElementToBeVisible(getSelectProposalStyle());
		actionDriver.selectByText(getSelectProposalStyle(), proposalStyle);

		common.log("39. Clicking New Proposal button");
		actionDriver.click(getViewProposalButton());
		Thread.sleep(3000);
		
	}
	
	public void viewPDFProposal() throws Exception {	
		
		List<String> windowId = common.getWindowsId();
		actionDriver.switchTo().window(windowId.get(1));
		String url = actionDriver.getCurrentUrl();
		common.log(url);
		common.compareText(url, iProCrystalReport_URL);
		Assert.assertTrue(common.isFileDownloaded("YourReport"), "Issue : PDF file is not downloaded. Script times out ");
		actionDriver.close();
		actionDriver.switchTo().window(windowId.get(0));
		common.log(actionDriver.getCurrentUrl());
	}
	
	public void viewWebProposal() throws Exception {	
		
		List<String> windowId = common.getWindowsId();
		actionDriver.switchTo().window(windowId.get(1));
		actionDriver.maximizeBrowser();
		String url = actionDriver.getCurrentUrl();
		common.log(url);
		common.compareText(url, eProReport_URL);		
		
	}

	public void addPlanToCart() throws Exception {
		String url = actionDriver.getCurrentUrl();
		common.log(url);
		common.log("40. Verifying page title text - Let's compare and shop plans");
		actionDriver.waitForElementToBeVisible(COMPARE_AND_SHOP_PLANS, 40);
		
		common.log("41. Clicking add to cart button");
		actionDriver.click(getAddToCart());
		Thread.sleep(1000);
		common.log("42. Clicking view cart button");
		actionDriver.waitForElementToBeVisible(getViewCart(), 40);

		actionDriver.clickAndWaitForElementToDisappear(getViewCart(), 40);
		
		common.log("43. Verifying page title text - Here is your Quote....");
		actionDriver.waitForElementToBeVisible(HERE_IS_YOUR_QUOTE, 40);

		common.log("44. Clicking New Proposal button and waiting for it to dissappear");
		actionDriver.click(getCheckoutButton());		
		
	}

	public void createAnAccount() throws Exception {
		//String url = actionDriver.getCurrentUrl();
		//common.log(url);
		common.log("45. Verifying page title text - Let's compare and shop plans");
		common.verifyUrl(oes_UAP_URL, 20);
		
		common.log("46. Clicking add to cart button");
		actionDriver.click(getAddToCart());
		Thread.sleep(1000);
		common.log("47. Clicking view cart button");
		actionDriver.clickAndWaitForElementToDisappear(getViewCart(), 40);
		
		common.log("48. Verifying page title text - Here is your Quote....");
		actionDriver.waitForElementToBeVisible(HERE_IS_YOUR_QUOTE, 40);

		common.log("49. Clicking New Proposal button and waiting for it to dissappear");
		actionDriver.click(getCheckoutButton());		
		
	}
}
