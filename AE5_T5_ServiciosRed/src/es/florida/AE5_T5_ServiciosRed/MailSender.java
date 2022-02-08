package es.florida.AE5_T5_ServiciosRed;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {

//	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
//		// TODO Auto-generated method stub
//		
//		String mensaje = "Hola jeje, te quiero mucho. Voy a hacer la comida, vale?";
//		String asunto = "IMPORTANTE MENSAJE PARA LADY LAURA";
//		String email_remitente = "albertfloridadam@gmail.com";
//		String email_remitente_pass = "lagar1992";
//		String host = "smtp.gmail.com";
//		String port = "587";
//		String[] email_destino =  {"albertduranll@gmail.com", "laulozun@gmail.com"};
//		String[] anexo =  {"C:\\idea.jpeg", "C:\\jeje.jpeg"};
//		
//		envioMail(
//				mensaje,
//				asunto,
//				email_remitente,
//				email_remitente_pass,
//				host,
//				port,
//				email_destino,
//				anexo
//				);
//
//	}
	
	public static void envioMail(String mensaje, String asunto, String email_remitente, String email_remitente_pass, 
	String host_email, String port_email, String[] email_destino, String[] anexo) throws 
	UnsupportedEncodingException, MessagingException{
		
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host_email);
		props.put("mail.smtp.user", email_remitente);
		props.put("mail.smtp.clave", email_remitente_pass);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", port_email);
		
		Session session = Session.getDefaultInstance(props);
		
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(email_remitente));
//		message.addRecipients(Message.RecipientType.TO, email_destino[0]);
		addRecipients(message, Message.RecipientType.TO, "albertduranll@gmail.com");
		message.setSubject(asunto);
		
		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText(mensaje);
		
		//Con exto adjuntariamos un elemento.
//		BodyPart messageBodyPart2 = new MimeBodyPart();
//		DataSource src = new FileDataSource(anexo[0]);
//		messageBodyPart2.setDataHandler(new DataHandler(src));
//		messageBodyPart2.setFileName(anexo[0]);
	
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart1);
//		multipart.addBodyPart(messageBodyPart2);
		addAttachedElements(multipart, anexo); //Con este metodo adjuntamos 1 o más elementos.
		
		

		
		message.setContent(multipart);
		
		Transport transport = session.getTransport("smtp");
		transport.connect(host_email, email_remitente, email_remitente_pass);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		
		System.out.println("Message sent!");
;	}
	
	private static void addRecipients(MimeMessage message, RecipientType type, String recipients) throws MessagingException {
	  String[] addresses = recipients.split(";");
	  for (int i = 0; i < addresses.length; i++) {
	    message.addRecipients(type, addresses[i]);
	  }
	}
	
	private static void addAttachedElements(Multipart multipart, String[] anexo) throws MessagingException {
		
		if (anexo != null && anexo.length > 0) {
            for (String filePath : anexo) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
	}
}
