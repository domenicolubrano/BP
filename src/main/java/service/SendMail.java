package service;

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

	public static void send(String destinatario, String oggetto){
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
	
	// chi invia la mail
	try {
		message.setFrom(new InternetAddress(email));
		
	} catch (AddressException e1) {
		GUI.logTextArea.append(" [ERRORE] ==> " + e1.getMessage() + "\n\n");
		e1.printStackTrace();
		GUI.errore = true;
	} catch (MessagingException e1) {
		GUI.logTextArea.append(" [ERRORE] ==> " + e1.getMessage() + "\n\n");
		e1.printStackTrace();
		GUI.errore = true;
	}
	
	// destinatario della mail
	try {
		message.setRecipients(
		  Message.RecipientType.TO, InternetAddress.parse(destinatario));
		
	} catch (AddressException e1) {
		GUI.logTextArea.append(" [ERRORE] ==> " + e1.getMessage() + "\n\n");
		e1.printStackTrace();
		GUI.errore = true;
	} catch (MessagingException e1) {
		GUI.logTextArea.append(" [ERRORE] ==> " + e1.getMessage() + "\n\n");
		e1.printStackTrace();
		GUI.errore = true;
	}
	
	
	// oggetto della mail
	try {
		message.setSubject(oggetto);
		
	} catch (MessagingException e) {
		GUI.logTextArea.append(" [ERRORE] ==> " + e.getMessage() + "\n\n");
		e.printStackTrace();
		GUI.errore = true;
	}

	
	
	// corpo della mail
	String msg = "This is my first email using JavaMailer";
	MimeBodyPart mimeBodyPart = new MimeBodyPart();
	try {
		mimeBodyPart.setContent(msg, "text/html; charset=utf-8");
		
	} catch (MessagingException e) {
		GUI.logTextArea.append(" [ERRORE] ==> " + e.getMessage() + "\n\n");
		e.printStackTrace();
		GUI.errore = true;
	}
	Multipart multipart = new MimeMultipart();
	try {
		multipart.addBodyPart(mimeBodyPart);
	} catch (MessagingException e) {
		GUI.logTextArea.append(" [ERRORE] ==> " + e.getMessage() + "\n\n");
		e.printStackTrace();
		GUI.errore = true;
	}
	try {
		message.setContent(multipart);
	} catch (MessagingException e) {
		GUI.logTextArea.append(" [ERRORE] ==> " + e.getMessage() + "\n\n");
		e.printStackTrace();
		GUI.errore = true;
	}

	
	
	// allegati della mial
//	MimeBodyPart attachmentBodyPart = new MimeBodyPart();
//	try {
//		attachmentBodyPart.attachFile(new File("./" + allegato));
//		multipart.addBodyPart(attachmentBodyPart);
//	} catch (IOException e) {
//		GUI.logTextArea.append(" [ERRORE] ==> " + e.getMessage() + "\n\n");
//		e.printStackTrace();
//		GUI.errore = true;
//	}
	
	
	// invio messaggio
	try {
		Transport.send(message);
	} catch (MessagingException e) {
		GUI.logTextArea.append(" [ERRORE] ==> " + e.getMessage() + "\n\n");
		e.printStackTrace();
		GUI.errore = true;
	}
	
	
	//String msgStyled = "This is my <b style='color:red;'>bold-red email</b> using JavaMailer";

	
}
	
	
}