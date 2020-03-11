package com.selenium.util.pdf;

import com.selenium.util.CommonUtil;
import com.selenium.util.CommonUtil.TimeZoneId;

public class TreatmentSummaryPDF {

	CommonUtil common;

	public TreatmentSummaryPDF(CommonUtil common) throws Exception {
		this.common = common;
	}

	public static String CVV_VERIFICATION_APPROVAL_METHOD = "CVV Verification";
	public static String IN_OFFICE_APPROVAL_METHOD = "In Office";
	public static String SMS_APPROVAL_METHOD = "SMS Approval";
	public static String MEMBER_PORTAL_APPROVAL_METHOD = "Member Portal";
	public static String EXTERNAL_PAYMENT_NOTES = "Paid Outside Wellfit";

	// Provider portal Treatment - export PDF
	public void verifyApprovalDate(String pdfContent, String timeZone) throws Exception {
		common.log("Verify Approval Date in PDF");
		String zonalDate = null;
		String zonalTime = null;
		if (timeZone.contains("Eastern")) {
			zonalDate = common.getCurrentDate(TimeZoneId.EASTERNTIME, "M/d/yyy");
			zonalTime = common.getCurrentDate(TimeZoneId.EASTERNTIME, "h:m:ss a");
		} else {
			zonalDate = common.getCurrentDate(TimeZoneId.PACIFICTIME, "M/d/yyy");
			zonalTime = common.getCurrentDate(TimeZoneId.PACIFICTIME, "h:m:ss a");
		}
		common.log("Date : " + zonalDate);
		common.log("Time : " + zonalTime);
		String requiredLine = null;
		String[] lines = pdfContent.split("\\n");
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Approval Date: ")) {
				requiredLine = lines[i];
				common.log(requiredLine);
				break;
			}
		}
		requiredLine = requiredLine.replace("Approval Date: ", "").trim();
		String[] array = requiredLine.split(" ");
		String date = array[0].trim();
		String time = array[1].trim();
		String am_pm = array[2].trim();
		common.log(date);
		common.log(time);
		common.log(am_pm);
		common.compareDateTime("M/d/yyy", zonalDate, date);
		// try {
		// common.compareText(time.split(":")[0], zonalTime.split(":")[0]);
		// common.compareText(am_pm, zonalTime.split(" ")[1]);
		// } catch (Exception e) {
		// throw new Exception("Issue : Treatment approve time is incorrect in PDF");
		// }

	}

	public void verifyApprovalMethod(String pdfContent, String approvalMethod) throws Exception {
		common.log("Verify Approval method in PDF");
		common.log("Expected Approval Method : " + approvalMethod);
		boolean isApprovalMethodVerified = false;
		String[] lines = pdfContent.split("\\n");
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Approval Method: " + approvalMethod)) {
				common.log(lines[i]);
				isApprovalMethodVerified = true;
				break;
			}
		}
		if (!isApprovalMethodVerified) {
			throw new Exception("Issue : Failed while verifying approval method in pdf. Need to investigate");
		}
	}

	public void verifyCardNumber(String pdfContent, String cardNumberLastFourDigit) throws Exception {
		common.log("Verify Card Number");
		common.log("Expected Card Number: " + cardNumberLastFourDigit);
		boolean isCardNumber = false;
		String[] lines = pdfContent.split("\\n");
		for (int i = 0; i < lines.length; i++) {
			if ((lines[i].contains("My Visa (ending in " + cardNumberLastFourDigit + ")")
					|| (lines[i].contains("Temporary Card (" + cardNumberLastFourDigit + ")")))) {
				common.log(lines[i]);
				isCardNumber = true;
				break;
			}
		}
		if (!isCardNumber) {
			throw new Exception("Issue : Failed while verifying Card Number in pdf. Need to investigate");
		}
	}

	public void verifyApprovedTreatmentDetails(String pdfContent, String procedure, String providerName, String fee,
			String providerDiscount, String employerBenefit, String yourShare) throws Exception {
		boolean isProcedureNameVerified = false;
		boolean isProviderNAmeVerified = false;
		boolean isFeeVerified = false;
		boolean isDiscountVerified = false;
		boolean isYourShareVerified = false;
		int counter = 0;
		String expected = null;
		String actual = null;
		String[] lines = pdfContent.split("\\n");
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Treatment Details")) {
				common.log(lines[i]);
				counter = i;
				break;
			}
		}
		if (lines[counter + 1].toLowerCase().contains(procedure.toLowerCase())) {
			isProcedureNameVerified = true;
			common.log("Procedure code verified");
		}
		if (providerName != null) {
			expected = "Provider: Dr. ".toLowerCase() + providerName.toLowerCase();
			actual = lines[counter + 1].toLowerCase();
			if (actual.contains(expected)) {
				common.log("Provider Name verified");
				isProviderNAmeVerified = true;
			}
		} else {
			isProviderNAmeVerified = true;
		}

		fee = common.addDollarSign(fee);
		expected = "Fee: " + fee.trim();
		actual = lines[counter + 2].trim();
		common.log("Expected : " + expected);
		common.log("actual : " + actual);
		if (actual.equalsIgnoreCase(expected)) {
			common.log("Fee verified");
			isFeeVerified = true;
		}

		providerDiscount = common.addDollarSign(providerDiscount);
		expected = "Provider Discount: " + providerDiscount.trim();
		actual = lines[counter + 3].trim();
		common.log("Expected : " + expected);
		common.log("actual : " + actual);
		if (actual.equalsIgnoreCase(expected)) {
			common.log("Provider discount verified");
			isDiscountVerified = true;
		}
		yourShare = common.addDollarSign(yourShare);
		expected = "Your Share: " + yourShare.trim();
		actual = lines[counter + 5].trim();
		common.log("Expected : " + expected);
		common.log("actual : " + actual);
		if (actual.equalsIgnoreCase(expected)) {
			common.log("Your Share verified");
			isYourShareVerified = true;
		}
		if (!(isProcedureNameVerified && isFeeVerified && isProviderNAmeVerified && isDiscountVerified
				&& isYourShareVerified)) {
			throw new Exception("Issue : Failed while verifying Treatment Details in pdf. Need to investigate");
		}
	}

	public void verifyCanceledTreatmentDetails(String pdfContent, String procedure, String providerName,
			String yourShare) throws Exception {
		boolean isProcedureNameVerified = false;
		boolean isProviderNAmeVerified = false;
		boolean isFeeVerified = false;
		boolean isYourShareVerified = false;
		boolean isCancelledLableVerified = false;
		int treatmentDetailsCounter = 0;
		int treatmentSummaryCounter = 0;
		boolean treatmentDetailsCounterFound = false;
		boolean treatmentSummaryCounterFound = false;
		String[] lines = pdfContent.split("\\n");
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Treatment Details")) {
				common.log(lines[i]);
				treatmentDetailsCounter = i;
				treatmentDetailsCounterFound = true;
			}
			if (lines[i].contains("Total Discounts and Costs")) {
				treatmentSummaryCounter = i;
				treatmentSummaryCounterFound = true;
			}
			if (treatmentSummaryCounterFound && treatmentDetailsCounterFound) {
				break;
			}
		}
		if (lines[treatmentDetailsCounter + 1].toLowerCase().contains(procedure.toLowerCase())) {
			isProcedureNameVerified = true;
		}
		String expected = "Provider: Dr. ".toLowerCase() + providerName.toLowerCase();
		String actual = lines[treatmentDetailsCounter + 1].toLowerCase();
		common.log("expected : " + expected);
		common.log("actual : " + actual);
		if (actual.contains(expected)) {
			common.log("Provider Name verified");
			isProviderNAmeVerified = true;
		}
		expected = "treatment canceled";
		actual = lines[treatmentDetailsCounter + 1].toLowerCase();
		if (actual.contains(expected)) {
			expected = "This procedure has been marked as canceled.  You will not be charged  " + yourShare;
			actual = lines[treatmentDetailsCounter + 2].trim();
			common.log("expected : " + expected);
			common.log("actual : " + actual);
			if (actual.equalsIgnoreCase(expected)) {
				common.log("Treatment cancelled label verified");
				isCancelledLableVerified = true;
			}
		}

		expected = "Fee: " + "$0.00";
		actual = lines[treatmentSummaryCounter + 1].trim();
		common.log("expected : " + expected);
		common.log("actual : " + actual);
		if (actual.equalsIgnoreCase(expected)) {
			common.log("Fee verified");
			isFeeVerified = true;
		}

		expected = "Amount Due: " + "$0.00";
		actual = lines[treatmentSummaryCounter + 3].trim();
		common.log("expected : " + expected);
		common.log("actual : " + actual);
		if (actual.equalsIgnoreCase(expected)) {
			common.log("Amount Due verified");
			isYourShareVerified = true;
		}
		if (!(isProcedureNameVerified && isFeeVerified && isProviderNAmeVerified && isCancelledLableVerified
				&& isYourShareVerified)) {
			throw new Exception("Issue : Failed while verifying Treatment Details in pdf. Need to investigate");
		}
	}

	public void verifyToothNumber(String pdfContent, String toothNumber, String toothCDTCode) throws Exception {
		boolean isToothNumberFound = false;
		String[] lines = pdfContent.split("\\n");
		int counter = 0;
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Tooth #")) {
				common.log(lines[i]);
				counter = i;
				break;
			}
		}
		common.log("Targeted content : " + lines[counter + 1]);
		if (lines[counter + 1].contains(toothNumber + " " + toothCDTCode)) {
			isToothNumberFound = true;
		}

		if (!isToothNumberFound) {
			throw new Exception("Issue : Tooth Number or ToothCDT Code is incorrect in pdf. Need to investigate");
		}
	}

	public void verifyMSDPPlanDetails(String pdfContent, String planType, String planCost, String planPurchaseDate,
			String planExpiryDate, String cardNumber, boolean isCardTemporary, String earlyRenewalDiscount)
			throws Exception {
		common.log("Verify MSDP PLan details in PDF");
		int COUNTER = 0;
		String[] lines = pdfContent.split("\\n");
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("My Smile Dental Plan™")) {
				common.log(lines[i]);
				COUNTER = i;
				break;
			}
		}
		common.compareText(lines[1 + COUNTER], planType + " $" + planCost);
		if (earlyRenewalDiscount == null) {
			common.compareText(lines[2 + COUNTER], "Date Charged: " + planPurchaseDate);
			if (isCardTemporary) {
				// common.compareText(lines[4 + COUNTER],
				// "Temporary Card (" + cardNumber.substring(cardNumber.length() - 4) + ")");
				verifyCardNumber(pdfContent, cardNumber.substring(cardNumber.length() - 4));
			} else {
				verifyCardNumber(pdfContent, cardNumber.substring(cardNumber.length() - 4));
			}
			common.compareText(lines[5 + COUNTER], "Plan Expiration Date: " + planExpiryDate);
		} else {
			common.compareText(lines[2 + COUNTER], "Early Renewal " + earlyRenewalDiscount);
			if (isCardTemporary) {
				common.compareText(lines[5 + COUNTER],
						"Temporary Card (" + cardNumber.substring(cardNumber.length() - 4) + ")");
			} else {
				verifyCardNumber(pdfContent, cardNumber.substring(cardNumber.length() - 4));
			}
			common.compareText(lines[6 + COUNTER], "Plan Expiration Date: " + planExpiryDate);
		}
	}

	public void verifyExternalPaymentNotes(String pdfContent, String notes) throws Exception {
		common.log("Verify External Payment Notes in PDF");
		common.log("Expected External Payment Notes : " + notes);
		boolean isExternalPaymentNotesVerified = false;
		String[] lines = pdfContent.split("\\n");
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("External Payment Notes:")) {
				for (int j = 1; j <= 10; j++) {
					System.out.println(lines[i + j]);
					if (lines[i + j].toLowerCase().contains(notes.toLowerCase())) {
						isExternalPaymentNotesVerified = true;
						break;
					}
				}
			}
		}
		if (!isExternalPaymentNotesVerified) {
			throw new Exception("Issue : Failed while verifying Extenral Payment Notes in pdf. Need to investigate");
		}
	}

}
