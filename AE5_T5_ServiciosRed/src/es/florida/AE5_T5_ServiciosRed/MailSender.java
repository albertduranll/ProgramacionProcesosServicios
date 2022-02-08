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
	
	/**
	 * Método para enviar email a partir de los datos que facilitamos por parámetros.
	 * @param mensaje
	 * @param asunto
	 * @param email_remitente
	 * @param email_remitente_pass
	 * @param host_email
	 * @param port_email
	 * @param email_destino
	 * @param anexo
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	public static void envioMail(String mensaje, String asunto, String email_remitente, String email_remitente_pass, 
	String host_email, String port_email, String email_destino, String[] anexo) throws 
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
		addRecipients(message, Message.RecipientType.TO, email_destino);
		message.setSubject(asunto);
		
		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText(mensaje);
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart1);
		addAttachedElements(multipart, anexo); //Con este metodo adjuntamos 1 o más elementos.
		
		message.setContent(multipart);
		
		Transport transport = session.getTransport("smtp");
		transport.connect(host_email, email_remitente, email_remitente_pass);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		
		System.out.println("Message sent!");
	}
	
	/**
	 * Método para gestionar uno o más destinatarios del correo.
	 * @param message
	 * @param type
	 * @param recipients
	 * @throws MessagingException
	 */
	private static void addRecipients(MimeMessage message, RecipientType type, String recipients) throws MessagingException {
	  String[] addresses = recipients.split(";");
	  for (int i = 0; i < addresses.length; i++) {
	    message.addRecipients(type, addresses[i]);
	  }
	}
	
	/**
	 * Método para poder adjuntar uno o más archivos al correo.
	 * @param multipart
	 * @param anexo
	 * @throws MessagingException
	 */
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
