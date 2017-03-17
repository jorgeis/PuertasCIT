package com.citnova.sca.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("mailManager")
public class MailManager {
	
	/**
	 * Cambiar por correo real, configurar en dispatcher-servlet
	 * 
	 * */
	private static final String correoEmisor = "test@novellius.com";
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	public void sendEmailConfirmacion(String destino, String urlActivacion) {
		MimeMessage mime = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mime, true);
			helper.setFrom(correoEmisor);
			helper.setTo(destino);
			helper.setSubject("Activa tu cuenta ");
			String htmlText = "<h1>Activa de cuenta de usuario </h1>"
					+ "<br/>"
					+ "Pulsa sobre este link para activar tu cuenta: "
					+ "<a href=\"" + urlActivacion + "\">Activar</a>";
			helper.setText(htmlText, true);
			mailSender.send(mime);
		}
		catch (MailException e) {
			e.printStackTrace();
		}
		catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	
	public boolean sendPasswordResetEmail(String destino, String url) {
		MimeMessage mime = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mime, true);
			helper.setFrom(correoEmisor);
			helper.setTo(destino);
			helper.setSubject("Cambio de contrase��a");
			String htmlText = "<h1>Has solicitado el cambio de tu contrase��a</h1>"
					+ "<br/>"
					+ "Pulsa sobre este link para establecer una nueva contrase��a para tu cuenta: "
					+ "<a href=\"" + url + "\">Cambiar contrase��a</a>";
			helper.setText(htmlText, true);
			mailSender.send(mime);
			return true;
		}
		catch (MailException e) {
			e.printStackTrace();
			return false;
		}
		catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean sendEmailContacto(String nombre, String correo, String comentario) {
		MimeMessage mime = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mime, true);
			helper.setFrom(correoEmisor);
			helper.setTo("contacto@novellius.com");
			helper.setSubject("Has recibido un nuevo correo de contacto");
			String htmlText = "<h1>Nuevo correo de contacto</h1>"
					+ "<br/> ... <br/>"
					+ "Nombre: " + nombre + "<br/>"
					+ "Correo: " + correo + "<br/>"
					+ "Comentario: " + comentario + "<br/>"
					;
			helper.setText(htmlText, true);
			mailSender.send(mime);
			return true;
		}
		catch (MailException e) {
			e.printStackTrace();
			return false;
		}
		catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean sendEmailInfo(String destino, String asunto, String mensaje) {
		MimeMessage mime = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mime, true);
			helper.setFrom(correoEmisor);
			helper.setTo(destino);
			helper.setSubject(asunto);
			String htmlText =  "<br/> ... <br/>"
					+ " " + mensaje + "<br/>"
					;
			helper.setText(htmlText, true);
			mailSender.send(mime);
			return true;
		}
		catch (MailException e) {
			e.printStackTrace();
			return false;
		}
		catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}

	
}
