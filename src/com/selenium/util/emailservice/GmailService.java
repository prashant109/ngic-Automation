package com.selenium.util.emailservice;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.code.javax.mail.Message;
import com.google.code.javax.mail.search.GmailRawSearchTerm;
import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;

public class GmailService {

	CommonUtil common;
	//ExtentTest test;

	public GmailService() {
		common = SelTestCase.getCommon();
	}

	public synchronized String getJoinNowUrl(String loginEmail, String loginPassword) throws Exception {
		common.log("Fetching Join now URL from email");
		String crvUrl = null;
		String emailContent = null;
		Message messages[];
		MailReader mail = new MailReader();
		String line;
		StringBuffer buffer;
		BufferedReader reader;
		int MESSAGE_COUNT = 0;
		int WAIT_TIME = 5000;
		Thread.sleep(WAIT_TIME);
		boolean isURLFound = false;
		String searchTem = "You've been added as a Practice User";
		common.log("searchTem : " + searchTem);
		for (int i = 1; i <= 40; i++) {
			try {
				mail.connectEmail(loginEmail, loginPassword);
				mail.openInbox();
				GmailRawSearchTerm rawTerm = new GmailRawSearchTerm(searchTem);
				messages = mail.getInbox().search(rawTerm);
				common.log("Messages found : " + messages.length);
				if (messages.length > 0) {
					for (int j = 0; j <= messages.length; j++) {
						common.log("Message " + ++MESSAGE_COUNT);
						common.log("Subject : " + messages[j].getSubject());
						if (messages[j].getSubject().contains(searchTem)) {
							emailContent = messages[j].getContent().toString();
							common.log("CONTENT:" + emailContent);
							break;
						}
					}
				}
				if (emailContent != null) {
					InputStream is = new ByteArrayInputStream(emailContent.getBytes());
					// read it with BufferedReader
					reader = new BufferedReader(new InputStreamReader(is));
					buffer = new StringBuffer();
					// reader = new BufferedReader(new
					// InputStreamReader(emailContent.getInputStream()));
					while ((line = reader.readLine()) != null) {
						if (line.contains("MANAGE RENTAL") && line.contains("<a href=")) {
							buffer.append(line.trim());
							break;
						}
					}
					String arr[] = buffer.toString().split("title");
					arr = arr[0].split("href=");
					crvUrl = arr[1].replace("\"", "");
					common.log(crvUrl);
					isURLFound = true;
					break;
				}
				common.log("Email not found. Trying again ");
				mail.closeConnection();
				emailContent = null;
				Thread.sleep(WAIT_TIME);
				MESSAGE_COUNT = 0;
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				mail.closeConnection();
			}
		}
		if (!isURLFound) {
			throw new Exception("Issue : Not able to get CRV URL from email. Need to investigate.");
		}

		return crvUrl;
	}

	public synchronized String getSignInURLFromWelcomeToMSDPPlanEmail(String loginEmail, String loginPassword,
			String memberFirstName) throws Exception {
		common.log("Get Sign In Url from Welcome to MSDP plan email");
		String url = null;
		String emailContent = null;
		Message messages[];
		MailReader mail = new MailReader();
		String line;
		StringBuffer buffer;
		BufferedReader reader;
		int MESSAGE_COUNT = 0;
		int WAIT_TIME = 5000;
		Thread.sleep(WAIT_TIME);
		boolean isURLFound = false;
		String expectedSubject = "Welcome to the MySmile Dental Plan";
		String searchTem = expectedSubject + " " + memberFirstName;
		common.log("searchTem : " + searchTem);
		for (int i = 1; i <= 40; i++) {
			try {
				mail.connectEmail(loginEmail, loginPassword);
				mail.openInbox();
				GmailRawSearchTerm rawTerm = new GmailRawSearchTerm(searchTem);
				messages = mail.getInbox().search(rawTerm);
				common.log("Messages found : " + messages.length);
				if (messages.length > 0) {
					for (int j = 0; j < messages.length; j++) {
						common.log("Message " + ++MESSAGE_COUNT);
						common.log("Subject : " + messages[j].getSubject());
						if (messages[j].getSubject().contains(expectedSubject)) {
							emailContent = messages[j].getContent().toString();
							common.log("CONTENT:" + emailContent);
							break;
						}
					}
				}
				if (emailContent != null) {
					InputStream is = new ByteArrayInputStream(emailContent.getBytes());
					// read it with BufferedReader
					reader = new BufferedReader(new InputStreamReader(is));
					buffer = new StringBuffer();
					// reader = new BufferedReader(new
					// InputStreamReader(emailContent.getInputStream()));
					while ((line = reader.readLine()) != null) {
						if (line.contains("<a href=") && line.contains("u6988752.ct.sendgrid.net")) {
							buffer.append(line.trim());
							break;
						}
					}
					String arr[] = buffer.toString().split("'");
					url = arr[1];
					common.log(url);
					isURLFound = true;
					break;
				}
				common.log("Email not found. Trying again ");
				mail.closeConnection();
				emailContent = null;
				Thread.sleep(WAIT_TIME);
				MESSAGE_COUNT = 0;
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				mail.closeConnection();
			}
		}
		if (!isURLFound) {
			throw new Exception("Issue : Not able to get CRV URL from email. Need to investigate.");
		}

		return url;
	}
}
