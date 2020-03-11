package com.selenium.web.test.ipro;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.selenium.util.CommonUtil;
import com.selenium.util.KEY;
import com.selenium.util.RetryAnalyzer;
import com.selenium.util.enums.AppUrl;

public class Ipro extends IproSetup {

	// private IproLoginPage iproLoginPage;

	@BeforeClass(groups = { "smoke" })
	public void initialSetUp() throws Exception {
		initializeWebTest("Ipro", AppUrl.IPROPORTAL);
	}

	// Smoketest 1 - Text for scenario
	/*
	 * Automation covered for : Text for scenario
	 * 
	 * Steps:
	 * 
	 * - Text for scenario
	 * 
	 * - Text for scenario
	 * 
	 * - Text for scenario
	 * 
	 * - Text for scenario
	 */
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true, groups = { "smoke" })
	public void test01_Ipro_Epro_OES() throws Exception {

		String login_email = CommonUtil.CONFIG.getProperty(KEY.IPRO_EMAIL);
		String login_password = CommonUtil.CONFIG.getProperty(KEY.IPRO_PASSWORD);
		String proposalType1 = "Dental Plan Benefit Detail";
		String proposalStyle1 = "Standard";
		String proposalType2 = "Web-Based Proposal";
		String proposalStyle2 = "Standard";

		// IPro testing:
		getIproLoginPage().loginIntoApplication(login_email, login_password);
		getIproHomePage().clickNewContactButton();
		getCreateNewContactForm().createNewIndividualContact();
		getContactInfo().clickNewProposalFromContactInfoPage();
		getCreateProposal().chooseProductTypeAndClickShowPlans();
		getSelectPlans().AddPlansAndCreateProposal();

		// Ipro Crystal Reports :
		getBuildYourProposal().selectProposalTypeAndStyle(proposalType1, proposalStyle1);
		getBuildYourProposal().viewPDFProposal();
		getBuildYourProposal().selectProposalTypeAndStyle(proposalType2, proposalStyle2);
		getBuildYourProposal().viewWebProposal();
		getBuildYourProposal().addPlanToCart();

		//Oes testing:
		getOESRegistration().createNewIndividualContact();
		getOESEffectiveDate().selectNewEffectiveDate();
		getOESMembership().oesFormDental();
		getOESPayment().paymentForm();
		getOESReview().reviewDetailsAndContinue();
		getOESSignAndSubmit().eSignAndSubmit();
		if (!CommonUtil.TEST_ENV.getProperty("testEnv").equalsIgnoreCase("PROD")) {
			getOESSubmitConfirmation().submitConfirmationAndLogout();
		}
		
		closeBrowser();

	}

	// Smoketest 2 - Text for scenario
	/*
	 * Automation covered for : Text for scenario
	 * 
	 * Steps:
	 * 
	 * - Text for scenario
	 * 
	 * - Text for scenario
	 * 
	 * - Text for scenario
	 * 
	 * - Text for scenario
	 */
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true, groups = { "smoke" })
	public void test02_Ipro_Coverage_Builder() throws Exception {

		String login_email_cb = CommonUtil.CONFIG.getProperty(KEY.IPRO_EMAIL_CB);
		String login_password_cb = CommonUtil.CONFIG.getProperty(KEY.IPRO_PASSWORD_CB);

		// IPro testing:
		getIproLoginPage().loginIntoApplication(login_email_cb, login_password_cb);
		getIproHomePage().enterZipCode();
		getCreateNewContactForm().selectGenderDOBAndCoverage();
		getSelectPlans().ChoosePackageAndCreateProposal();
		
		//Oes testing:
		getOESMembership().oesFormCB();
		getOESMembership().oesBeneficiary();
		getOESPayment().paymentFormCoverageBuilder();
		getOESReview().reviewDetailsAndContinueCovBuilder();
		
		if (!CommonUtil.TEST_ENV.getProperty("testEnv").equalsIgnoreCase("PROD")) {
			getOESSignAndSubmit().completingCBApplication();
			getOESSignAndSubmit().logoutFromAccountDashboard();		
			
		}
		
		closeBrowser();

	}

	// Smoketest 2 - Text for scenario
	/*
	 * Automation covered for : Text for scenario
	 * 
	 * Steps:
	 * 
	 * - Text for scenario
	 * 
	 * - Text for scenario
	 * 
	 * - Text for scenario
	 * 
	 * - Text for scenario
	 */
	@Test(retryAnalyzer = RetryAnalyzer.class, enabled = true, groups = { "smoke" })
	public void test03_AgentCubed() throws Exception {

		String login_a3 = CommonUtil.CONFIG.getProperty(KEY.A3_USERNAME);
		String password_a3 = CommonUtil.CONFIG.getProperty(KEY.A3_PASSWORD);

		// IPro testing:
		//getIproLoginPage().loginIntoApplication(login_email_cb, login_password_cb);
		getIproHomePage().enterZipCode();
		getCreateNewContactForm().selectGenderDOBAndCoverage();
		getSelectPlans().ChoosePackageAndCreateProposal();
		
		//Oes testing:
		getOESMembership().oesFormCB();
		getOESMembership().oesBeneficiary();
		getOESPayment().paymentFormCoverageBuilder();
		getOESReview().reviewDetailsAndContinueCovBuilder();
		getOESSignAndSubmit().completingCBApplication();
		getOESSignAndSubmit().logoutFromAccountDashboard();

	}

}
