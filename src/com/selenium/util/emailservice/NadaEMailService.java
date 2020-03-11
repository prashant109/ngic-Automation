package com.selenium.util.emailservice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.selenium.testdata.RandomUtil;
import com.selenium.util.CommonUtil;

public class NadaEMailService {
	CommonUtil common;

	

	private static final String NADA_EMAIL_DOMAIN = "@getnada.com";
	private static final String INBOX_MESSAGE_KEY_NAME = "msgs";
	private static final String EMAIL_ID_ROUTE_PARAM = "email-id";
	private static final String MESSAGE_ID_ROUTE_PARAM = "message-id";
	private static final String NADA_EMAIL_INBOX_API = "https://getnada.com/api/v1/inboxes/{email-id}";
	private static final String NADA_EMAIL_MESSAGE_API = "https://getnada.com/api/v1/messages/{message-id}";
	private static final ObjectMapper MAPPER = new ObjectMapper();
	// private static final int EMAIL_CHARS_LENGTH = 10;

	private String emailId;

	private void generateEmailId() {
		this.emailId = RandomUtil.getRandomName().toLowerCase()
				+ String.valueOf(RandomUtil.getRandomNumber(3)).concat(NADA_EMAIL_DOMAIN);
	}

	// generates a random email for the first time.
	// call reset for a new random email
	public String getEmailId() {
		if (Objects.isNull(this.emailId)) {
			this.generateEmailId();
		}
		return emailId;
	}

	// to re-generate a new random email id
	public void reset() {
		this.emailId = null;
	}

	public List<InboxEmail> getInbox() throws Exception {
		String msgs = Unirest.get(NADA_EMAIL_INBOX_API).routeParam(EMAIL_ID_ROUTE_PARAM, this.getEmailId()).asJson()
				.getBody().getObject().getJSONArray(INBOX_MESSAGE_KEY_NAME).toString();
		return MAPPER.readValue(msgs, new TypeReference<List<InboxEmail>>() {
		});
	}

	public EmailMessage getMessageById(final String messageId) {
		String msgs;
		try {
			msgs = Unirest.get(NADA_EMAIL_MESSAGE_API).routeParam(MESSAGE_ID_ROUTE_PARAM, messageId).asJson().getBody()
					.getObject().toString();
			return MAPPER.readValue(msgs, EmailMessage.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public EmailMessage getMessageWithSubjectStartsWith(final String emailSubject) throws Exception {
		System.out.println(getEmailId());
		// System.out.println(this.getInbox().stream().filter(ie ->
		// ie.getSubject().toLowerCase().contains("welcome"))
		// .findFirst().map(InboxEmail::getMessageId).map(this::getMessageById)
		// .orElseThrow(IllegalArgumentException::new).getHtml());
		EmailMessage email = null;
		boolean isEmailReceived = false;
		for (int i = 0; i < 30; i++) {
			try {
				email = this.getInbox().stream()
						.filter(ie -> ie.getSubject().toLowerCase().contains(emailSubject.toLowerCase())).findFirst()
						.map(InboxEmail::getMessageId).map(this::getMessageById)
						.orElseThrow(IllegalArgumentException::new);
				isEmailReceived = true;
				break;
			} catch (Exception e) {
				common.log("Email not received. Trying again.");
				Thread.sleep(5000);
			}
		}
		if (!isEmailReceived) {
			throw new Exception("Issue : email not received.");
		}
		return email;
	}

	public String getEmailContent(String emailSubject) throws Exception {
		EmailMessage email = null;
		boolean isEmailFound = false;
		for (int i = 0; i < 5; i++) {
			try {
				email = this.getMessageWithSubjectStartsWith(emailSubject);
				isEmailFound = true;
				break;
			} catch (Exception e) {
				common.log("Email not found. Trying again.");
			}
		}
		if (!isEmailFound) {
			throw new Exception("Issue : " + emailSubject + " Email not found..");
		}
		String content = email.getHtml();
		return content;
	}

	public void verifyEmailReceived(String emailSubject) throws Exception {
		boolean isEmailFound = false;
		EmailMessage email = null;
		for (int i = 0; i < 5; i++) {
			try {
				email = this.getMessageWithSubjectStartsWith(emailSubject);
				isEmailFound = true;
				break;
			} catch (Exception e) {
				common.log("Email not found. Trying again.");
				Thread.sleep(2000);
			}
		}
		if (!isEmailFound) {
			throw new Exception("Issue : " + emailSubject + " Email not found..");
		}
		common.log(email.getHtml());
	}

	public String getSignInlinkFromWelcomeToWellfitEmail(String emailSubject) throws Exception {
		EmailMessage email = null;
		try {
			email = this.getMessageWithSubjectStartsWith(emailSubject);
		} catch (Exception e) {
			common.log(e.getMessage());
			throw new Exception("Issue : WELCOME TO WELLFIT email not received.");
		}
		String content = email.getHtml();
		StringBuffer buffer = new StringBuffer();
		String line = null;
		String url = null;
		common.log(content);
		if (content != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(IOUtils.toInputStream(content, "UTF-8")));
			while ((line = reader.readLine()) != null) {
				if ((line.contains("sendgrid.net/wf") && line.contains("<a") && line.contains("href="))) {
					buffer.append(line);
					break;
				}
			}
			String arr[] = buffer.toString().split("href=");
			String arrFinal[] = arr[1].toString().split(" ");
			url = arrFinal[0].replace("'", "");
			url = url.replace("\"", "");
			common.log("URL : " + url);
		}
		common.log(content);
		return url;
	}

	public String getLinkFromEmail(String emailSubject) throws Exception {
		EmailMessage email = null;
		try {
			email = this.getMessageWithSubjectStartsWith(emailSubject);
		} catch (Exception e) {
			common.log(e.getMessage());
			throw new Exception("Issue : Email with subject '" + emailSubject + "' not received.");
		}
		String content = email.getHtml();
		StringBuffer buffer = new StringBuffer();
		String line = null;
		String url = null;
		if (content != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(IOUtils.toInputStream(content, "UTF-8")));
			while ((line = reader.readLine()) != null) {
				if ((line.contains("sendgrid.net/wf") && line.contains("<a") && line.contains("href="))) {
					buffer.append(line);
					break;
				}
			}
			String arr[] = buffer.toString().split("href=");
			String arrFinal[] = arr[1].toString().split(" ");
			url = arrFinal[0].replace("'", "");
			url = url.replace("\"", "");
			common.log("URL : " + url);
		}
		common.log(content);
		return url;
	}

	public String verifyAccountActivatedEmail(String emailSubject) throws Exception {
		EmailMessage email = this.getMessageWithSubjectStartsWith(emailSubject);
		String content = email.getHtml();
		StringBuffer buffer = new StringBuffer();
		String line = null;
		String url = null;
		if (content != null) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(IOUtils.toInputStream(content, "UTF-8")));
			while ((line = reader.readLine()) != null) {
				if ((line.contains("sendgrid.net/wf") && line.contains("<a") && line.contains("href="))) {
					buffer.append(line);
					break;
				}
			}
			String arr[] = buffer.toString().split("href=");
			String arrFinal[] = arr[1].toString().split(" ");
			url = arrFinal[0].replace("'", "");
			System.out.println(url);

		}
		return url;

	}
}
