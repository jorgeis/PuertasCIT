package com.citnova.sca.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citnova.sca.domain.Admin;
import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Notificacion;
import com.citnova.sca.service.AdminService;
import com.citnova.sca.service.ClienteService;
import com.citnova.sca.service.NotificacionService;
import com.citnova.sca.util.Constants;
import com.citnova.sca.util.MailManager;
import com.citnova.sca.util.Util;

@Controller
public class NotificacionController {

	@Autowired
	private NotificacionService notificacionService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private MailManager mailManager;
	
	@Autowired
	private Util util;
	
	private Timestamp time = new Timestamp(new Date().getTime());
	
	
	@RequestMapping("/admin/notificacion/queryall/{index}")
	public String queryAll(Model model,
			@PathVariable("index") int index){
		
		Page<Notificacion> page = notificacionService.getPage(index - 1);
		
		int currentIndex = page.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
		
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalPages", page.getTotalPages());
		
		model.addAttribute("notificacionList", page.getContent());
		
		model.addAttribute(Constants.SHOW_PAGES, true);
		
		return "notificacion_queryall";
	}
	
	
	@RequestMapping("/admin/notificacion/search")
	public String search(Model model,
			@RequestParam("dateFrom") String dateFrom,
			@RequestParam("dateTo") String dateTo,
			@RequestParam("visibilidad") String visibilidad
			){
		
		// Convertir String a Timestamp
		dateFrom += " 12:00:00";
		dateTo += " 12:00:00";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date parsedDateFrom = null;
		Date parsedDateTo = null;
		try {
			parsedDateFrom = dateFormat.parse(dateFrom);
			parsedDateTo = dateFormat.parse(dateTo);
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
	    
		Timestamp tsFrom = new java.sql.Timestamp(parsedDateFrom.getTime());
		Timestamp tsTo = new java.sql.Timestamp(parsedDateTo.getTime());
	
		int index = 1;
		Page<Notificacion> page = notificacionService.findByFhCreaBetween(tsFrom, tsTo, visibilidad, index - 1);
		
		model.addAttribute(Constants.RESULT, messageSource.getMessage("notification_search_results", 
				new Object[]{page.getTotalElements(), visibilidad, dateFrom, dateTo}, Locale.getDefault()));
		
		int currentIndex = page.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
		
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalPages", page.getTotalPages());
		
		model.addAttribute("notificacionList", page.getContent());
		
		model.addAttribute(Constants.SHOW_PAGES, true);
		
		return "notificacion_queryall";
	}
	

	/**
	 * Servidor JSON para envío de correos asíncronamente
	 * */
	@RequestMapping(value="/admin/notificacion/send", produces="application/json")
	@ResponseBody
	public Map<String, Object> findAll(Principal principal,
			@RequestParam("tituloNot") String tituloNot,
			@RequestParam("contenidoNot") String contenidoNot,
			@RequestParam("visibilidadNot") String visibilidadNot
			) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		// Mensaje JSON dummy para mostrar éxito en la operación
		map.put("Key ", messageSource.getMessage("notifications_sent", null, Locale.getDefault()));
		
		// Salvar en BD registro del envío de las notificaciones (emails)
		Admin admin = adminService.findByEmail(principal.getName());
		
		Notificacion notificacion = new Notificacion();
		notificacion.setFhCrea(time);
		notificacion.setFhPubNot(time);
		notificacion.setStatus(Constants.STATUS_ACTIVE);
		notificacion.setTituloNot(tituloNot);
		notificacion.setContenidoNot(contenidoNot);
		notificacion.setVisibilidad(visibilidadNot);
		
		notificacionService.save(admin, notificacion);
		
		// Enviar correos electrónicos a Admin o Cliente
		if(visibilidadNot.equals(Constants.ADMIN)){
			List<Admin> adminList = adminService.findAll();
			
			for (Admin admin2 : adminList) {
				System.out.println("******  enviando email Admin: " + admin2.getPersona().getEmailPer());
				mailManager.sendEmailInfo(admin.getPersona().getEmailPer(), 
						tituloNot, 
						contenidoNot);
			}
		}
		else if(visibilidadNot.equals(Constants.CLIENTE)){
			List<Cliente> clienteList = clienteService.findAll();
			
			for (Cliente cliente : clienteList) {
				System.out.println("******  enviando email Cliente: " + cliente.getPersona().getEmailPer());
				mailManager.sendEmailInfo(cliente.getPersona().getEmailPer(), 
						tituloNot, 
						contenidoNot);
			}
		}
		
		// Respuesta exitosa a la función AJAX
		return map;
	}
}
