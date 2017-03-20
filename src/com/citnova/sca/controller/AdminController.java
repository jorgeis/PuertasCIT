package com.citnova.sca.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citnova.sca.domain.Admin;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.domain.PersonaAdminWrapper;
import com.citnova.sca.service.AdminService;
import com.citnova.sca.service.PersonaService;
import com.citnova.sca.util.Constants;
import com.citnova.sca.util.MailManager;
import com.citnova.sca.util.Util;

@Controller
public class AdminController {
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private MailManager mailManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Util util;

	
	private Timestamp time = new Timestamp(new Date().getTime());
	

	/**
	 * Controlador para guardar o actualizar datos del Administrador
	 * */
	@RequestMapping(value="/admin/save", method=RequestMethod.POST)
	public String createPersona(Model model, PersonaAdminWrapper personaAdminWrapper,
			HttpSession session, Principal principal, HttpServletRequest request,
			RedirectAttributes ra) {
		
		int idAd = personaAdminWrapper.getIdAd();
		//
		// Guardar nuevo registro
		//
		if(idAd == 0){
			System.out.println("********* SAVE");
			
			Persona persona = new Persona(
					personaAdminWrapper.getNombrePer(), 
					personaAdminWrapper.getApPatPer(), 
					personaAdminWrapper.getApMatPer(), 
					personaAdminWrapper.getEmailPer(), 
					personaAdminWrapper.getCurpPer(), time);
			
			Admin admin = new Admin(personaAdminWrapper.getAreaAd(),
					personaAdminWrapper.getCargoAd(),
					personaAdminWrapper.getTelefonoAd(), 
					time, 
					personaAdminWrapper.getRolAd(), 
					Constants.STATUS_MUST_ACTIVATE, 
					principal.getName());
			
			
			// Validar si ya existe la persona
			Persona persona2 = personaService.findByEmailPer(persona.getEmailPer());
			if(persona2 != null){
				ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("admin_exists", null, Locale.getDefault()));
				return "admin_queryall";
			}
			
			// Guardar datos de relación @OneToOne
			adminService.saveOrUpdate(admin, persona);
			
			// Enviar correo electrónico para confirmar cuenta
			mailManager.sendEmailConfirmacion(persona.getEmailPer(), 
					util.getRootUrl(request, "/admin/save")
							.concat("/adminaccount/")
							.concat(String.valueOf(admin.getIdAd()))
							.concat("/account/password")
						);
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("admin_saved", null, Locale.getDefault()));
		}
		//
		// Actualizar registro
		//
		else{
			Admin admin = adminService.findOne(idAd);
			Persona persona = admin.getPersona();
			
			System.out.println("********* UPDATE");
			
			persona.setNombrePer(personaAdminWrapper.getNombrePer());
			persona.setApPatPer(personaAdminWrapper.getApPatPer());
			persona.setApMatPer(personaAdminWrapper.getApMatPer());
			persona.setEmailPer(personaAdminWrapper.getEmailPer());
			persona.setCurpPer(personaAdminWrapper.getCurpPer());

			admin.setAreaAd(personaAdminWrapper.getAreaAd());
			admin.setCargoAd(personaAdminWrapper.getCargoAd());
			admin.setTelefonoAd(personaAdminWrapper.getTelefonoAd());
			admin.setRolAd(personaAdminWrapper.getRolAd());
			
			adminService.saveOrUpdate(admin, persona);
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("admin_updated", null, Locale.getDefault()));
		}
		return "redirect:/admin/queryall";
	}
	
	
	@RequestMapping("/admin/queryall")
	public String queryAll(Model model) {
		// Consultar sólo los administradores activos
		List<Admin> adminList = adminService.findAllByStatusAd(Constants.STATUS_ACTIVE);
		model.addAttribute("adminList", adminList);
		
		model.addAttribute("personaAdminWrapper", new PersonaAdminWrapper());
		
		return "admin_queryall";
	}
	
	
	@RequestMapping("/admin/update/{idAd}")
	public String update(Model model, @PathVariable("idAd") int idAd) {
		Admin admin = adminService.findOne(idAd);
		Persona persona = admin.getPersona();
		
		PersonaAdminWrapper personaAdminWrapper = new PersonaAdminWrapper();
		
		personaAdminWrapper.setIdPer(persona.getIdPer());
		personaAdminWrapper.setNombrePer(persona.getNombrePer());
		personaAdminWrapper.setApPatPer(persona.getApPatPer());
		personaAdminWrapper.setApMatPer(persona.getApMatPer());
		personaAdminWrapper.setEmailPer(persona.getEmailPer());
		personaAdminWrapper.setCurpPer(persona.getCurpPer());
		personaAdminWrapper.setFhCreaPer(persona.getFhCreaPer());
		
		personaAdminWrapper.setIdAd(admin.getIdAd());
		personaAdminWrapper.setPassAd(admin.getPassAd());
		personaAdminWrapper.setAreaAd(admin.getAreaAd());
		personaAdminWrapper.setCargoAd(admin.getCargoAd());
		personaAdminWrapper.setTelefonoAd(admin.getTelefonoAd());
		personaAdminWrapper.setFhCreaAd(admin.getFhCreaAd());
		personaAdminWrapper.setRolAd(admin.getRolAd());
		personaAdminWrapper.setStatusAd(admin.getStatusAd());
		personaAdminWrapper.setCreadoAd(admin.getCreadoAd());
		personaAdminWrapper.setFhAccesoAd(admin.getFhAccesoAd());
		
		List<Admin> adminList = adminService.findAllByStatusAd(Constants.STATUS_ACTIVE);
		model.addAttribute("adminList", adminList);
		model.addAttribute("personaAdminWrapper", personaAdminWrapper);
		
		return "admin_queryall";
	}
	
	
	/**
	 * Controlador para cambiar status de Administrador a Borrado
	 * */
	@RequestMapping("/admin/delete/{idAd}")
	public String delete(HttpSession session, RedirectAttributes ra,  
			@PathVariable("idAd") int idAd) {
		
		Admin admin = adminService.findOne(idAd);
		admin.setStatusAd(Constants.STATUS_DELETED);
		adminService.saveOrUpdate(admin, admin.getPersona());
		
		ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("admin_deleted", null, Locale.getDefault()));
		
		return "redirect:/admin/queryall";
	}
	
	
	/**
	 * Controlador para mostrar los resultados de la búsqueda de un
	 * administador
	 * */
	@RequestMapping(value="/admin/search", method=RequestMethod.POST)
	public String search(@RequestParam("busqueda") String  busqueda,
			RedirectAttributes ra, Model model) {
		
		List<Admin> adminList = new ArrayList<>();
		Admin admin = null;
		int id = 0;
		try{
			id = Integer.parseInt(busqueda.replaceAll("[A-Za-z. ]+", "")); 
		}
		catch(Exception e){}
		
		admin = adminService.findOne(id);
		System.out.println("********* " + admin);
		
		if(admin != null){
			adminList.add(admin);
		}
		else{
			model.addAttribute(Constants.RESULT, messageSource.getMessage("admin_not_found", 
					new Object[]{busqueda}, Locale.getDefault()));
			
			adminList = adminService.findAllByStatusAd(Constants.STATUS_ACTIVE);
		}
		
		model.addAttribute("adminList", adminList);
		model.addAttribute("personaAdminWrapper", new PersonaAdminWrapper());
		
		return "admin_queryall";
	}
	

	/**
	 * Vista para una vez creada la cuenta, capturar contraseña 
	 * */
	@RequestMapping("/adminaccount/{idAd}/account/password")
	public String inputPassword(HttpSession session, Model model,  
			@PathVariable("idAd") int idAd) {
		
		session.setAttribute(Constants.ID_AD, idAd);
		Admin admin = adminService.findOne(idAd);
		
		if(admin.getStatusAd().equals(Constants.STATUS_MUST_ACTIVATE)){
			return "admin_password";
		}
		else{
			model.addAttribute(Constants.RESULT, messageSource.getMessage("admin_was_confirmed", null, Locale.getDefault()));
			return "notifications";
		}
	}
	
	
	/**
	 * Controlador para guardar contraseña y activar la cuenta de Administrador
	 * */
	@RequestMapping("/adminaccount/confirm")
	public String confirmAccount(Model model, @RequestParam("password") String rawPassword, 
			HttpSession session) {
		
		int idAd = (int) session.getAttribute(Constants.ID_AD);
		Admin admin = adminService.findOne(idAd);
		Persona persona = admin.getPersona();
		
		admin.setPassAd(passwordEncoder.encode(rawPassword));
		admin.setStatusAd(Constants.STATUS_ACTIVE);
		adminService.saveOrUpdate(admin, persona);
		
		model.addAttribute(Constants.RESULT, messageSource.getMessage("admin_confirmed", null, Locale.getDefault()));
		
		session.invalidate();
		return "notifications";
	}
	
	
	/**
	 * Servidor JSON para búsqueda de Administradores
	 * */
	@RequestMapping(value="/json/search/admin", produces="application/json")
	@ResponseBody
	public Map<String, Object> findAll(@RequestParam("term") String term) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Admin> adminList = adminService.findAllLikeNombreOApellido("%" + term + "%");
		
		for (int j = 0; j < adminList.size(); j++) {
			Admin admin = adminList.get(j);
			map.put(String.valueOf(admin.getIdAd()), 
										admin.getIdAd() + ". " + 
										admin.getPersona().getNombrePer() + "  " +
										admin.getPersona().getApPatPer() + "  " +
										admin.getPersona().getApMatPer());
		}
		
		System.out.println(map.size());
		System.out.println(map);
		
		return map;
	}
}
