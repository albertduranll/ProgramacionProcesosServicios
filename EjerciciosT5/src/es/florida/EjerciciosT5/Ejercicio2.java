package es.florida.EjerciciosT5;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Ejercicio2 {

	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException {
		// TODO Auto-generated method stub
		
		String mensaje = "Hola jeje, te quiero mucho. Voy a hacer la comida, vale?";
		String asunto = "IMPORTANTE MENSAJE PARA LADY LAURA";
		String email_remitente = "albertfloridadam@gmail.com";
		String email_remitente_pass = "lagar1992";
		String host = "smtp.gmail.com";
		String port = "587";
		String[] email_destino =  {"albertduranll@gmail.com"};
		String[] anexo =  {"C:\\idea.jpeg"};
		
		envioMail(
				mensaje,
				asunto,
				email_remitente,
				email_remitente_pass,
				host,
				port,
				email_destino,
				anexo
				);

	}
	
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
		message.addRecipients(Message.RecipientType.TO, email_destino[0]);
		message.setSubject(asunto);
		
		BodyPart messageBodyPart1 = new MimeBodyPart();
		messageBodyPart1.setText(mensaje);
		
		BodyPart messageBodyPart2 = new MimeBodyPart();
		DataSource src = new FileDataSource(anexo[0]);
		messageBodyPart2.setDataHandler(new DataHandler(src));
		messageBodyPart2.setFileName(anexo[0]);
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart1);
		multipart.addBodyPart(messageBodyPart2);
		
		message.setContent(multipart);
		
		Transport transport = session.getTransport("smtp");
		transport.connect(host_email, email_remitente, email_remitente_pass);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		
		System.out.println("Message sent!");
;	}

}
