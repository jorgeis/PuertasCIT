package com.citnova.sca.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	private Util util;
	
	
	private Timestamp time = new Timestamp(new Date().getTime());
	
	@RequestMapping("/admin/persona/save")
	public String showIndex() {
		return "admin_persona_create";
	}
	
	
	@RequestMapping(value="/admin/save", method=RequestMethod.POST)
	public String createPersona(Model model, PersonaAdminWrapper personaAdminWrapper,
			HttpSession session, Principal principal, HttpServletRequest request) {
		
		Persona persona = new Persona(personaAdminWrapper.getNombrePer(), 
				personaAdminWrapper.getApPatPer(), 
				personaAdminWrapper.getApMatPer(), 
				personaAdminWrapper.getEmailPer(), 
				personaAdminWrapper.getCurpPer(), time);
		
		
		Admin admin = new Admin(personaAdminWrapper.getAreaAd(),
				personaAdminWrapper.getCargoAd(),
				personaAdminWrapper.getTelefonoAd(), 
				time, 
				personaAdminWrapper.getRolAd(), 
				Constants.STATUS_ACTIVE, 
				principal.getName());
		
		
		// Validar si ya existe la persona
		Persona persona2 = personaService.findByEmailPer(persona.getEmailPer());
		if(persona2 != null){
			model.addAttribute(Constants.RESULT, messageSource.getMessage("admin_exists", null, Locale.getDefault()));
			return "admin_queryall";
		}
		
		personaService.save(persona);
		admin.setPersona(persona);
		adminService.save(admin);
		
		// Enviar correo electrónico para confirmar cuenta
		mailManager.sendEmailConfirmacion(persona.getEmailPer(), 
				util.getRootUrl(request, "/admin/save").concat("/admin/confirm"));
		
		return "redirect:/admin/queryall";
	}
	
	
	@RequestMapping(value="/admin/save.do", method=RequestMethod.POST)
	public String createAdmin(Model model, Admin admin,
			HttpSession session, Principal principal) {
		
		Persona persona = (Persona) session.getAttribute(Constants.PERSONA_ADMIN);
		persona.setFhCreaPer(time);
		personaService.save(persona);
		
		admin.setFhCreaAd(time);
		admin.setStatusAd(Constants.STATUS_ACTIVE);
		admin.setCreadoAd(principal.getName());
		
		admin.setPersona(persona);
		
		adminService.save(admin);
		
//		return "admin_queryall";
//		return "redirect:/secure/user/queryall";
        return "redirect:/admin/queryall";
	}
	
	
	@RequestMapping("/admin/queryall")
	public String queryAll(Model model) {
		
		List<Admin> adminList = adminService.findAll();
		System.out.println(adminList);
		model.addAttribute("adminList", adminList);
		model.addAttribute("personaAdminWrapper", new PersonaAdminWrapper());
		
		return "admin_queryall";
	}

}
