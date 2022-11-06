package service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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

	public static void send(String destinatario, String oggetto, String allegato){

	Properties prop = new Properties();
	
	prop.put("mail.smtp.auth", true);
	prop.put("mail.smtp.starttls.enable", "false");
	prop.put("mail.smtp.host", "pop.3em.it");
	//prop.put("mail.smtp.host", "authsmtp.securemail.pro");
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
		
	} catch (Exception e1) {
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
	String msg = "Buongiorno,<br>" + 
			"\r\n" + 
			"Invio in allegato buste paga  come in oggetto.<br><br>" + 
			"\r\n" + 
			"Ti prego di provvedere alla compilazione del solito Form per le attestazioni di spese non documentate per l&#39;importo che trovi sotto la voce &#147;Rimborso spese pi&egrave; di lista&#147; in busta paga.<br>" + 
			"\r\n" + 
			"Attendo entrambi i documenti firmati con ogni cortese URGENZA. Qualora non avessi gia' provveduto all&#39;nvio degli stessi documenti per I MESI PRECEDENTI, ti ricordo ti inviare tutto CON URGENZA.<br>\r\n" + 
			"\r\n" + 
			"<br>Grazie per la collaborazione e saluti<br><br>"+
			"\r\n" + 
			"\r\n" + 
			"\r\n" + 
			"<br>Francesca scarmozzino<br>\r\n" + 
			"<sub style='color:grey;'>"+
			"Finance &amp; Administration<br>\r\n" + 
			"\r\n<br>" + 
			"<img src='http://3em.it/website/images/Home/3em_Logo_SMALL.png' width='80' height='80'><br><br>" + 
			"Via Antiniana, 2/G\r\n" + 
			"80078 Pozzuoli (NA)<br>\r\n" + 
			"ITALY<br><br>\r\n" + 
			"Phone:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+39 081 5234193<br>\r\n" + 
			"Fax:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+39 081 8531552\r\n<br>" + 
			"Mail:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;info@3em.it\r\n<br>" + 
			"Site:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;www.3em.it\r\n<br><br><br>" + 
			"\r\n" + 
			"GDPR 2016/679<br>\r\n" + 
			"\r\n" + 
			"Il presente messaggio e gli eventuali suoi allegati sono di natura aziendale, prevalentemente confidenziale e sono visionabili solo dal destinatario di posta elettronica. La risposta o l&#39;eventuale invio spontaneo da parte vostra di e-mail al nostro indirizzo potrebbero non assicurare la confidenzialita&#39; potendo essere viste da altri soggetti appartenenti all&#39;Azienda oltre che al firmatario della presente, per finalit&agrave; di sicurezza informatica, amministrative e allo scopo del continuo svolgimento dell&#39;attivit&agrave; aziendale. Qualora questo messaggio vi fosse pervenuto per errore, vi preghiamo di cancellarlo dal vostro sistema e vi chiediamo di volercene dare cortesemente comunicazione al mittente.\r\n<br>" + 
			"\r\n" + 
			"La Vs. mail &egrave; in ns. possesso in quanto da Voi fornitaci tramite comunicazione scritta, telefonica, telematica o direttamente oralmente. Essa &egrave; utilizzata esclusivamente per fornirVi informazioni sulla ns. attivit&agrave; e sui servizi da noi offerti. Non sar&agrave; ceduta a terzi in nessun caso salvo approvazione da parte Vostra. Il Titolare del trattamento &egrave;��<b>TRE EMME ENGINEERING S.R.L.</b> I ns. sistemi informativi e le ns. procedure interne sono conformi alle norme e garantiamo la presenza di adeguate misure tecniche ed organizzative costantemente aggiornate.<br>\r\n" + 
			"\r\n" + 
			"E' possibile in qualsiasi momento richiedere la cancellazione della Vs. mail tramite il semplice invio di una mail a.info@3em.it"
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
	MimeBodyPart attachmentBodyPart = new MimeBodyPart();
	try {
		attachmentBodyPart.attachFile(new File("./" + allegato));
		multipart.addBodyPart(attachmentBodyPart);
	} catch (IOException e) {
		GUI.logTextArea.append(" [ERRORE] ==> " + e.getMessage() + "\n\n");
		e.printStackTrace();
		GUI.errore = true;
	} catch (MessagingException e) {
		GUI.logTextArea.append(" [ERRORE] ==> " + e.getMessage() + "\n\n");
		e.printStackTrace();
		GUI.errore = true;
	}
	

	// invio messaggio
	try {
		Transport.send(message);
		GUI.logTextArea.append(" [INFO] ==> Email inviata correttamente\n\n");
	} catch (MessagingException e) {
		GUI.logTextArea.append(" [ERRORE] ==> " + e.getMessage() + "\n\n");
		e.printStackTrace();
		GUI.errore = true;
	}
	
	


	
}
	
	
	public static void setEmail(String email) {
		SendMail.email = email;
	}
	
	public static void setPassword(String password) {
		SendMail.password = password;
	}
	
	public static String getEmail() {
		return email;
	}
	
	
	public static void setCredenziali() {
		Properties p = new Properties();
		try {
			p.load(new FileReader(System.getenv("APPDATA") + "/bp3em.properties"));
			email = p.getProperty("email");
			password = p.getProperty("password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}