package com.selenium.util.emailservice;

import java.util.Properties;

import com.google.code.javax.activation.DataHandler;
import com.google.code.javax.activation.DataSource;
import com.google.code.javax.activation.FileDataSource;
import com.google.code.javax.mail.Message;
import com.google.code.javax.mail.Multipart;
import com.google.code.javax.mail.Session;
import com.google.code.javax.mail.Transport;
import com.google.code.javax.mail.internet.InternetAddress;
import com.google.code.javax.mail.internet.MimeBodyPart;
import com.google.code.javax.mail.internet.MimeMessage;
import com.google.code.javax.mail.internet.MimeMultipart;

// set CLASSPATH=%CLASSPATH%;activation.jar;mail.jar

import com.selenium.setup.SelTestCase;

/**
 * The Class SendMail.
 */
public final class SendMail extends SelTestCase{

    /**
     * Instantiates a new send mail.
     */
    private SendMail() {
    }

    /**
     * Call this method to send mail.
     */
    public static void callThisMethodToSendMail(String zipFolder) {/*

        ReportUtil.zip(zipFolder);

        String[] to = {};
        String[] cc = {};
        String[] bcc = {};

        if (getCONFIG().getProperty("toEmail").contains(",")) {
            to = getCONFIG().getProperty("toEmail").split(",");
       } else {
           if (getCONFIG().getProperty("toEmail").equalsIgnoreCase("")) {

           } else {
               to = getCONFIG().getProperty("toEmail").split(" ");
           }

       }
        if (getCONFIG().getProperty("ccEmail").contains(",")) {
            bcc = getCONFIG().getProperty("ccEmail").split(",");
       } else {
           if (getCONFIG().getProperty("ccEmail").equalsIgnoreCase("")) {
               //logs.debug("No email in ccEmail");
           } else {
               bcc = getCONFIG().getProperty("ccEmail").split(" ");
           }

       }

        if (getCONFIG().getProperty("bccEmail").contains(",")) {
            bcc = getCONFIG().getProperty("bccEmail").split(",");
       } else {
           if (getCONFIG().getProperty("bccEmail").equalsIgnoreCase("")) {
               //logs.debug("No email in bcc");
           } else {
               bcc = getCONFIG().getProperty("bccEmail").split(" ");
           }
       }

        if (to.length > 0) {
            //logs.debug("Sending Reports to the mentioned email id's");
             boolean emailConfirmation = SendMail.sendMail("er.himanshu07@gmail.com", "",
                        "smtp.gmail.com", "465", "true", "true", true,
                        "javax.net.ssl.SSLSocketFactory", "false", to, cc, bcc,
                        "Automation test Reports",
                        "Please find the reports attached.\n\n Regards\nWebMaster",
                        System.getProperty("user.dir") + "\\Reports.zip", "Reports.zip");

             if (emailConfirmation) {
                 //logs.debug("######Email Sent######");
             } else {

                 //logs.debug("Error : There is some problem with sending email. May be a network issue or email id is not correct");
             }
        } else {

           // logs.debug("Email id is not available");
        }


    */}

    /**
     * Send mail.
     *
     * @param userName
     *        the user name
     * @param passWord
     *        the pass word
     * @param host
     *        the host
     * @param port
     *        the port
     * @param starttls
     *        the starttls
     * @param auth
     *        the auth
     * @param debug
     *        the debug
     * @param socketFactoryClass
     *        the socket factory class
     * @param fallback
     *        the fallback
     * @param to
     *        the to
     * @param cc
     *        the cc
     * @param bcc
     *        the bcc
     * @param subject
     *        the subject
     * @param text
     *        the text
     * @param attachmentPath
     *        the attachment path
     * @param attachmentName
     *        the attachment name
     * @return true, if successful
     */
    public static boolean sendMail(String userName,
        String passWord,
        String host,
        String port,
        String starttls,
        String auth,
        boolean debug,
        String socketFactoryClass,
        String fallback,
        String[] to,
        String[] cc,
        String[] bcc,
        String subject,
        String text,
        String attachmentPath,
        String attachmentName) {

        Properties props = new Properties();

        // Properties props=System.getProperties();

        props.put("mail.smtp.user", userName);

        props.put("mail.smtp.host", host);

        if (!"".equals(port)) {

            props.put("mail.smtp.port", port);
        }
        if (!"".equals(starttls)) {

            props.put("mail.smtp.starttls.enable", starttls);
        }
        props.put("mail.smtp.auth", auth);
        // props.put("mail.smtps.auth", "true");

        if (debug) {

            props.put("mail.smtp.debug", "true");

        } else {

            props.put("mail.smtp.debug", "false");

        }

        if (!"".equals(port)) {

            props.put("mail.smtp.socketFactory.port", port);
        }
        if (!"".equals(socketFactoryClass)) {

            props.put("mail.smtp.socketFactory.class", socketFactoryClass);
        }
        if (!"".equals(fallback)) {

            props.put("mail.smtp.socketFactory.fallback", fallback);
        }
        try {

            Session session = Session.getDefaultInstance(props, null);

            session.setDebug(debug);

            MimeMessage msg = new MimeMessage(session);

            msg.setText(text);

            msg.setSubject(subject);
            // attachment start
            // create the message part

            Multipart multipart = new MimeMultipart();
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(attachmentPath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(attachmentName);
            multipart.addBodyPart(messageBodyPart);

            // attachment ends

            // Put parts in message
            msg.setContent(multipart);
            msg.setFrom(new InternetAddress(userName));

            for (int i = 0; i < to.length; i++) {

                msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    to[i]));

            }

            for (int i = 0; i < cc.length; i++) {

                msg.addRecipient(Message.RecipientType.CC, new InternetAddress(
                    cc[i]));

            }

            for (int i = 0; i < bcc.length; i++) {

                msg.addRecipient(Message.RecipientType.BCC,
                    new InternetAddress(bcc[i]));

            }

            msg.saveChanges();

            Transport transport = session.getTransport("smtp");

            transport.connect(host, userName, passWord);

            transport.sendMessage(msg, msg.getAllRecipients());

            transport.close();

            return true;

        } catch (Exception mex) {

            mex.printStackTrace();

            return false;

        }

    }

	@Override
	protected void cleanPageObjects() {
		// TODO Auto-generated method stub
		
	}

}
