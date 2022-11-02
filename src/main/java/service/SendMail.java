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
	prop.put("mail.smtp.starttls.enable", "false");
	prop.put("mail.smtp.host", "pop.3em.it");
	prop.put("mail.smtp.port", "25");
	prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	prop.put("mail.smtp.ssl.trust", "authsmtp.3em.it");
	prop.put("mail.smtp.ssl.trust", "authsmtp.securemail.pro");
	
	
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
	String msg = "Buongiorno,\r\n" + 
			"\r\n" + 
			"invio in allegato buste paga  come in oggetto.\r\n" + 
			"\r\n" + 
			"Ti prego di provvedere alla compilazione del solito Form per le attestazioni di spese non documentate per l’importo che trovi sotto la voce “Rimborso spese piè di lista” in busta paga.\r\n" + 
			"\r\n" + 
			"Attendo entrambi i documenti firmati con ogni cortese URGENZA. Qualora non avessi già provveduto all’invio degli stessi documenti per I MESI PRECEDENTI, ti ricordo ti inviare tutto CON URGENZA.\r\n" + 
			"\r\n" + 
			"Grazie per la collaborazione e saluti"+
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"Francesca scarmozzino\r\n" + 
			"<sub style='color:grey;'>"+
			"\r\n" + 
			"Software Developer\r\n" + 
			"\r\n" + 
			"Via Antiniana, 2/G\r\n" + 
			"80078 Pozzuoli  (NA)\r\n" + 
			"ITALY\r\n" + 
			"Phone:   	 +39 081 5234193\r\n" + 
			"Fax:        +39 081 8531552\r\n" + 
			"Mail:       info@3em.it\r\n" + 
			"Site:       www.3em.it\r\n" + 
			"\r\n" + 
			"GDPR  2016/679\r\n" + 
			"\r\n" + 
			"Il presente messaggio e gli eventuali suoi allegati sono di natura aziendale, prevalentemente confidenziale e sono visionabili solo dal destinatario di posta elettronica. La risposta o l’eventuale invio spontaneo da parte vostra di e-mail al nostro indirizzo potrebbero non assicurare la confidenzialità potendo essere viste da altri soggetti appartenenti all’Azienda oltre che al firmatario della presente, per finalità di sicurezza informatica, amministrative e allo scopo del continuo svolgimento dell’attività aziendale. Qualora questo messaggio vi fosse pervenuto per errore, vi preghiamo di cancellarlo dal vostro sistema e vi chiediamo di volercene dare cortesemente comunicazione al mittente.\r\n" + 
			"\r\n" + 
			"La Vs. mail è in ns. possesso in quanto da Voi fornitaci tramite comunicazione scritta, telefonica, telematica o direttamente oralmente. Essa è utilizzata esclusivamente per fornirVi informazioni sulla ns. attività e sui servizi da noi offerti. Non sarà ceduta a terzi in nessun caso salvo approvazione da parte Vostra. Il Titolare del trattamento  è  TRE EMME ENGINEERING S.R.L.  I ns. sistemi informativi e le ns. procedure interne sono conformi alle norme e garantiamo la presenza di adeguate misure tecniche ed organizzative costantemente aggiornate.\r\n" + 
			"\r\n" + 
			"E’ possibile in qualsiasi momento richiedere la cancellazione della Vs. mail tramite il semplice invio di una mail a info@3em.it"
			+"</sub>";

	
	
	
	
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
	

	GUI.logTextArea.append(" [INFO] ==> Mail inviata\n\n");
	
}
	
	
}