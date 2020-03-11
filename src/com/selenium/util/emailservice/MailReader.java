package com.selenium.util.emailservice;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import com.google.code.com.sun.mail.imap.IMAPFolder;
import com.google.code.javax.mail.Flags;
import com.google.code.javax.mail.Folder;
import com.google.code.javax.mail.Message;
import com.google.code.javax.mail.Multipart;
import com.google.code.javax.mail.Part;
import com.google.code.javax.mail.Session;
import com.google.code.javax.mail.Store;
import com.google.code.javax.mail.search.FlagTerm;
import com.selenium.setup.SelTestCase;
import com.selenium.util.CommonUtil;

public class MailReader {
	private IMAPFolder inbox;
	private IMAPFolder trash;
	private Store store;
	private Session session;
	private Properties props;
	
	private CommonUtil common;

	public Store getStore() {
		return store;
	}

	public Folder getInbox() {
		return inbox;
	}
	
	public MailReader() {
		this.common = SelTestCase.getCommon();
	}

	//ExtentTest test;

	public synchronized void connectEmail(String email, String password) throws Exception {
		//this.test = test;
		common.log("Creating connection : " + email);
		boolean isConnectionEstablished = false;
		Throwable error = null;
		for (int i = 0; i < 3; i++) {
			try {
				this.props = System.getProperties();
				props.setProperty("mail.store.protocol", "imaps");
				this.session = Session.getDefaultInstance(props, null);
				this.store = this.session.getStore("imaps");
				this.store.connect("imap.gmail.com", email, password);
				isConnectionEstablished = true;
				common.log("Connection created !!");
				break;
			} catch (Throwable t) {
				error = t;
				common.log("Getting following error while creating connection with gmail account. " + t.getMessage());
				common.log("Trying again");
				Thread.sleep(10000);
			}
		}
		if (!isConnectionEstablished) {
			throw new Exception("Failing script due to following error " + error.getMessage());
		}

	}

	public void deleteUnreadEmails() throws Exception {
		common.log("Deleting unread emails");
		// connectEmail();
		openInbox();
		Message messages[] = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
		common.log("Total Message:" + inbox.getMessageCount());
		common.log("No. of Unread Messages : " + messages.length);

		for (int i = 0; i < messages.length; i++) {
			messages[i].setFlag(Flags.Flag.DELETED, true);
		}
		common.log("Unread emails deleted");
	}

	public void deleteAllEmails(String email, String password) throws Exception {
		common.log("Deleting all existing emails");

		connectEmail(email, password);
		openInbox();
		Message messages[] = inbox.getMessages();
		common.log("Total Message:" + inbox.getMessageCount());
		trash = (IMAPFolder) store.getFolder("[Gmail]/Trash");
		// trash.open(Folder.READ_WRITE);
		inbox.copyMessages(messages, trash);
		/*
		 * for (int i = 0; i < messages.length; i++) {
		 * messages[i].setFlag(Flags.Flag.DELETED, true); }
		 */
		// inbox.expunge();
		// inbox.close(true);
		// trash.close(true);
		// store.close();
		deleteEmailsFromTrashFolder();
		common.log("All existing emails deleted");
	}

	public void deleteEmailsFromTrashFolder() throws Exception {
		// connectEmail(email, password);
		trash = (IMAPFolder) store.getFolder("[Gmail]/Trash");
		trash.open(Folder.READ_WRITE);
		Message messages[] = trash.getMessages();
		messages = trash.getMessages();
		for (int i = 0; i < 50; i++) {
			messages[i].setFlag(Flags.Flag.DELETED, true);
		}
		trash.expunge();
		trash.close(true);
		store.close();
	}

	public synchronized Message[] getUnReadEmails() throws Exception {
		openInbox();
		Message messages[] = inbox.search(new FlagTerm(new Flags(Flags.Flag.SEEN), false));
		common.log("Total Message : " + inbox.getMessageCount());
		common.log("No. of Unread Messages : " + messages.length);

		return messages;
	}

	public synchronized Message[] getInboxEmails() throws Exception {
		openInbox();
		Message messages[] = inbox.getMessages();
		common.log("Total Message : " + inbox.getMessageCount());
		return messages;
	}

	public synchronized void openInbox() throws Exception {
		inbox = (IMAPFolder) store.getFolder("INBOX");
		try {
			inbox.open(Folder.READ_WRITE);
		} catch (Exception e) {
			common.log(e.getMessage());
			common.log("Trying Again !!");
			Thread.sleep(3000);
			inbox.open(Folder.READ_WRITE);
		}
	}

	public String getEmailContent(Part p) throws Exception {

		String finalContents = "";
		if (p.getContent() instanceof String) {
			finalContents = (String) p.getContent();
		} else {
			Multipart mp = (Multipart) p.getContent();
			if (mp.getCount() > 0) {
				Part bp = mp.getBodyPart(0);
				try {
					finalContents = dumpPart(bp);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return finalContents.trim();
	}

	private String dumpPart(Part p) throws Exception {

		InputStream is = p.getInputStream();
		// If "is" is not already buffered, wrap a BufferedInputStream
		// around it.
		if (!(is instanceof BufferedInputStream)) {
			is = new BufferedInputStream(is);
		}
		return getStringFromInputStream(is);
	}

	private String getStringFromInputStream(InputStream is) {

		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		String line;
		try {
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}

	public void closeConnection() {
		//this.test = test;
		try {
			common.log("Closing connection...");
			if (this.getInbox().isOpen()) {
				this.getInbox().close(true);
				this.getStore().close();
				common.log("Connection Closed");
			}
		} catch (Throwable e) {
			if (!e.getMessage().contains("This operation is not allowed on a closed folder")) {
				common.log("Getting error while closing gmail connection. Handling.");
				common.log(e.toString());
			}
			e.printStackTrace();
		}
	}

}

// http://stackoverflow.com/questions/21174357/java-get-unread-emails
// https://www.google.com/settings/security/lesssecureapps