package com.selenium.web.test.ipro;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

import com.selenium.setup.SelTestCase;
//import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;
import com.selenium.util.TestNgListeners;
import com.selenium.util.actiondriver.BaseActionDriver;
import com.selenium.web.page.common.SettingsPage;
import com.selenium.web.page.ipro.BuildYourProposal;
import com.selenium.web.page.ipro.ContactInfo;
import com.selenium.web.page.ipro.CreateNewContactForm;
import com.selenium.web.page.ipro.CreateProposal;
import com.selenium.web.page.ipro.IproHomePage;
import com.selenium.web.page.ipro.IproLoginPage;
import com.selenium.web.page.ipro.OESEffectiveDate;
import com.selenium.web.page.ipro.OESMembership;
import com.selenium.web.page.ipro.OESPayment;
import com.selenium.web.page.ipro.OESRegistration;
import com.selenium.web.page.ipro.OESReview;
import com.selenium.web.page.ipro.OESSignAndSubmit;
import com.selenium.web.page.ipro.OESSubmitConfirmation;
import com.selenium.web.page.ipro.SelectPlans;

@Listeners(TestNgListeners.class)
public class IproSetup extends SelTestCase {

	public IproSetup(BaseActionDriver actionDriver) {
		this.actionDriver = actionDriver;
		this.common = new CommonUtil(actionDriver);
	}

	public IproSetup() {

	}

	// IPRO PORTAL
/*	private AdminLoginPage loginPage;
	private AdminHomePage homePage;*/
	
	private IproLoginPage loginPage;
	private SettingsPage settingsPage;

	private IproHomePage homePage;
	private CreateNewContactForm createNewContactForm;
	private ContactInfo contactInfo;
	private CreateProposal createProposal;
	private SelectPlans selectPlans;
	private BuildYourProposal buildYourProposal;
	private OESRegistration oESRegistration;	
	private OESEffectiveDate oESEffectiveDate;
	private OESMembership oESMembership;
	private OESPayment oESPayment;
	private OESReview oESReview;
	private OESSignAndSubmit oESSignAndSubmit;
	private OESSubmitConfirmation oESSubmitConfirmation; 


	public IproLoginPage getIproLoginPage() {
		if (loginPage == null) {
			loginPage = new IproLoginPage();
		}
		return loginPage;
	}

	public SettingsPage getSettingsPage() {
		if (settingsPage == null) {
			settingsPage = new SettingsPage();
		}
		return settingsPage;
	}		
	
	public IproHomePage getIproHomePage() {
		if (homePage == null) {
			homePage = new IproHomePage();
		}
		return homePage;
	}
	
	public CreateNewContactForm getCreateNewContactForm() {
		if (createNewContactForm == null) {
			createNewContactForm = new CreateNewContactForm();
		}
		return createNewContactForm;
	}	
	
	public ContactInfo getContactInfo() {
		if (contactInfo == null) {
			contactInfo = new ContactInfo();
		}
		return contactInfo;
	}		
	
	public CreateProposal getCreateProposal() {
		if (createProposal == null) {
			createProposal = new CreateProposal();
		}
		return createProposal;
	}		

	public SelectPlans getSelectPlans() {
		if (selectPlans == null) {
			selectPlans = new SelectPlans();
		}
		return selectPlans;
	}		

	public BuildYourProposal getBuildYourProposal() {
		if (buildYourProposal == null) {
			buildYourProposal = new BuildYourProposal();
		}
		return buildYourProposal;
	}	
	
	public OESRegistration getOESRegistration() {
		if (oESRegistration == null) {
			oESRegistration = new OESRegistration();
		}
		return oESRegistration;
	}	

	public OESEffectiveDate getOESEffectiveDate() {
		if (oESEffectiveDate == null) {
			oESEffectiveDate = new OESEffectiveDate();
		}
		return oESEffectiveDate;
	}	
	
	public OESMembership getOESMembership() {
		if (oESMembership == null) {
			oESMembership = new OESMembership();
		}
		return oESMembership;
	}	

	public OESReview getOESReview() {
		if (oESReview == null) {
			oESReview = new OESReview();
		}
		return oESReview;
	}	

	public OESPayment getOESPayment() {
		if (oESPayment == null) {
			oESPayment = new OESPayment();
		}
		return oESPayment;
	}	
	
	public OESSignAndSubmit getOESSignAndSubmit() {
		if (oESSignAndSubmit == null) {
			oESSignAndSubmit = new OESSignAndSubmit();
		}
		return oESSignAndSubmit;
	}	
	
	public OESSubmitConfirmation getOESSubmitConfirmation() {
		if (oESSubmitConfirmation == null) {
			oESSubmitConfirmation = new OESSubmitConfirmation();
		}
		return oESSubmitConfirmation;
	}	
	
	public void cleanPageObjects() {
		common.log("Cleaning Page Objects !!!");
		loginPage = null;
		homePage= null;
		createNewContactForm= null;
		contactInfo= null;
		createProposal= null;
		selectPlans= null;
		buildYourProposal= null;
	}

	@AfterMethod(groups = { "smoke", "prod" }, alwaysRun = true)
	public synchronized void tearDown(ITestResult results) {
		closeBrowser();
		reports.flush();
	}

	@AfterClass(groups = { "smoke", "prod" }, alwaysRun = true)
	public void postScriptsCleaning() {
		// Removed Thread Local objects
		actionDriverThread.remove();
		commonThread.remove();
		testThread.remove();
	}

}
