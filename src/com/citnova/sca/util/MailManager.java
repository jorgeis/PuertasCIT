package com.citnova.sca.util;

import java.text.DecimalFormat;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.citnova.sca.domain.Gratuito;

@Component("mailManager")
public class MailManager {
	
	/**
	 * Cambiar por correo real, configurar en dispatcher-servlet
	 * 
	 * */
//	private static final String correoEmisor = "test@novellius.com";
	private static final String correoEmisor = "edgar3345@terra.com";
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	public void sendEmailConfirmacion(String destino, String urlActivacion) {
		MimeMessage mime = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
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
			MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
			helper.setFrom(correoEmisor);
			helper.setTo(destino);
			helper.setSubject("Cambio de contraseña");
			String htmlText = "<h1>Has solicitado el cambio de tu clave de acceso</h1>"
					+ "<br/>"
					+ "Pulsa sobre este link para establecer una nueva contraseña para tu cuenta: "
					+ "<a href=\"" + url + "\">Cambiar contraseña</a>";
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
			MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
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
			MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
			helper.setFrom(correoEmisor);
			helper.setTo(destino);
			helper.setSubject(asunto);
			String htmlText =  "<h1>Notificación</h1> <br/> <br/>"
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

	
	public boolean sendOrgMemberInvite(String destino, String url, String nombreOrg) {
		MimeMessage mime = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
			helper.setFrom(correoEmisor);
			helper.setTo(destino);
			helper.setSubject("Invitación para ser miembro de " + nombreOrg);
			String htmlText = "<h1>Has sido invitado </h1>"
					+ "<br/>"
					+ nombreOrg + " te ha enviado una invitación para disfrutar de los beneficios del Parque Científico Tecnológico."
					+ "<br />Si deseas aceptar la invitación: "
					+ "<a href=\"" + url + "\">Haz click aquí</a>";
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
	
	
	public void sendEmailReservacionGratuita(String destino, Gratuito gratuito) {
		MimeMessage mime = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
			helper.setFrom(correoEmisor);
			helper.setTo(destino);
			helper.setSubject("Solicitud de reservación - CITNOVA");
			
			Calendar ci = Calendar.getInstance();
			Calendar cf = Calendar.getInstance();
			ci.setTime(gratuito.getFhInicioEveGra());
			cf.setTime(gratuito.getFhFinEveGra());
			DecimalFormat formatter = new DecimalFormat("00");
			
			String htmlText = "<h1>Has realizado una solicitud de reservación de espacio</h1>"
					+ "<br/>"
					+ "Has realizado una reservación con los siguientes datos:"
					+ "<br/>"
					+ "<br/>"
					+ "- Solicitante: " + gratuito.getNombreUsrGra() + " " + gratuito.getApPatUsrGra() + " " + gratuito.getApMatUsrGra()
					+ "<br/>"
					+ "- Evento: " + gratuito.getNombreEveGra()
					+ "<br/>"
					+ "- Área solicitada: " + gratuito.getArea().getNombreArea()
					+ "<br/>"
					+ "- Fecha: " + ci.get(Calendar.DATE) + "/" + formatter.format((double)ci.get(Calendar.MONTH)+1) + "/" + ci.get(Calendar.YEAR)
					+ "<br/>"
					+ "- Hora de inicio: " + formatter.format((double)ci.get(Calendar.HOUR_OF_DAY)) + ":00 Hrs"
					+ "<br/>"
					+ "- Hora de terminación: " + formatter.format((double)cf.get(Calendar.HOUR_OF_DAY)) + ":00 Hrs"
					+ "<br/>"
					+ "- Organización solicitante: " + gratuito.getOrganizacion().getNombreOrg()
					+ "<br/>"
					+ "- Responsable del evento: " + gratuito.getNombreRespGra() + " " + gratuito.getApPatRespGra() + " " + gratuito.getApMatRespGra()
					+ "<br/>"
					+ "<br/>"
					+ "La presente solicitud no asegura que la reservación está confirmada. Ponto recibirás un correo electrónico a "
					+ "esta dirección para informarte del resultado de tu solicitud. ";
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
	
	
	public void sendEmailConfirmaReservacionGratuita(String destino, Gratuito gratuito) {
		MimeMessage mime = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mime, true, "UTF-8");
			helper.setFrom(correoEmisor);
			helper.setTo(destino);
			helper.setSubject("Resultado de reservación - CITNOVA");
			
			Calendar ci = Calendar.getInstance();
			Calendar cf = Calendar.getInstance();
			ci.setTime(gratuito.getFhInicioEveGra());
			cf.setTime(gratuito.getFhFinEveGra());
			DecimalFormat formatter = new DecimalFormat("00");
			
			String htmlText = "<h1>Tenemos el resultado de tu solicitud de reservación</h1>"
					+ "<br/>"
					+ "La reservación que realizaste con los siguientes datos:"
					+ "<br/>"
					+ "<br/>"
					+ "- Solicitante: " + gratuito.getNombreUsrGra() + " " + gratuito.getApPatUsrGra() + " " + gratuito.getApMatUsrGra()
					+ "<br/>"
					+ "- Evento: " + gratuito.getNombreEveGra()
					+ "<br/>"
					+ "- Área solicitada: " + gratuito.getArea().getNombreArea()
					+ "<br/>"
					+ "- Fecha: " + ci.get(Calendar.DATE) + "/" + formatter.format((double)ci.get(Calendar.MONTH)+1) + "/" + ci.get(Calendar.YEAR)
					+ "<br/>"
					+ "- Hora de inicio: " + formatter.format((double)ci.get(Calendar.HOUR_OF_DAY)) + ":00 Hrs"
					+ "<br/>"
					+ "- Hora de terminación: " + formatter.format((double)cf.get(Calendar.HOUR_OF_DAY)) + ":00 Hrs"
					+ "<br/>"
					+ "- Organización solicitante: " + gratuito.getOrganizacion().getNombreOrg()
					+ "<br/>"
					+ "- Responsable del evento: " + gratuito.getNombreRespGra() + " " + gratuito.getApPatRespGra() + " " + gratuito.getApMatRespGra()
					+ "<br/>"
					+ "<br/>"
					+ "Ha sido: " + gratuito.getDecisionGra() + "." 
					+ "<br/>"
					+ "<br/>"
					+ "Se han hecho los siguientes comentarios: "
					+ gratuito.getComentariosGra()
					+ "<br/>"
					+ "Cualquier duda o comentario contáctanos a correo@correo.com(colocar correo de contacto)."
					;
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
	
}
