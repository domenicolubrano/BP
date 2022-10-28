package service;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {
	
	private static String email = "d.lubranolobianco@3em.it";
	private static String password = "DmcLbLb@12";

	public static void send(String destinatario, String oggetto) throws AddressException, MessagingException {
	Properties prop = new Properties();
	
	prop.put("mail.smtp.auth", true);
	prop.put("mail.smtp.starttls.enable", "true");
	prop.put("mail.smtp.host", "pop.3em.it");
	prop.put("mail.smtp.port", "25");
//	prop.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
	
	Session session = Session.getInstance(prop, new Authenticator() {
	    @Override
	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(email, password);
	    }
	});
	
	
	Message message = new MimeMessage(session);
	try {
		message.setFrom(new InternetAddress(email));
	} catch (AddressException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (MessagingException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	message.setRecipients(
	  Message.RecipientType.TO, InternetAddress.parse(destinatario));
	try {
		message.setSubject(oggetto);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	String msg = "This is my first email using JavaMailer";

	MimeBodyPart mimeBodyPart = new MimeBodyPart();
	try {
		mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	Multipart multipart = new MimeMultipart();
	try {
		multipart.addBodyPart(mimeBodyPart);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	try {
		message.setContent(multipart);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	
	
//	MimeBodyPart attachmentBodyPart = new MimeBodyPart();
//	try {
//		attachmentBodyPart.attachFile(new File("path/to/file"));
//		multipart.addBodyPart(attachmentBodyPart);
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
	
	try {
		Transport.send(message);
	} catch (MessagingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	String msgStyled = "This is my <b style='color:red;'>bold-red email</b> using JavaMailer";

	
}
	
	
}