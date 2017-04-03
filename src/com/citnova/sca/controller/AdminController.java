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
import org.springframework.data.domain.Page;
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
import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.domain.PersonaAdminWrapper;
import com.citnova.sca.domain.PersonaClienteDireccionWrapper;
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
				model.addAttribute(Constants.RESULT, messageSource.getMessage("admin_exists", null, Locale.getDefault()));
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
		ra.addFlashAttribute(Constants.SHOW_PAGES, true);
		return "redirect:/admin/queryall/1";
	}
	
	
	/**
	 * Controlador para realizar la búsqueda de administradores en función del parámetro ingresado.
	 * 
	 *  - queryall: Devuelve todos los administradores con statusAd = 'Activo'
	 *  - querydeleted: Devuelve todos los administradores con statusAd = 'Borrado'
	 *  - querypending: Devuelve todos los administradores con statusAd = 'activar'
	 * */
	@RequestMapping("/admin/query{searchParam}/{index}")
	public String queryAll(Model model, @PathVariable("index") int index, @PathVariable("searchParam") String searchParam,
			HttpSession session) {
		// Consultar sólo los administradores activos
		
		model.addAttribute("personaAdminWrapper", new PersonaAdminWrapper());
		
		if(searchParam.equals(null)) { searchParam = "all";}
		Page<Admin> page = null;
		String returnParam = null;
		
		if(searchParam.equals("all")) {
			page = adminService.getPageByStatus(Constants.STATUS_ACTIVE, index - 1);
			returnParam = "admin_queryall";
		}
		else if(searchParam.equals("deleted")) {
			page = adminService.getPageByStatus(Constants.STATUS_DELETED, index - 1);
			returnParam = "admin_querydeleted";
		}
		else if(searchParam.equals("pending")) {
			page = adminService.getPageByStatus(Constants.STATUS_MUST_ACTIVATE, index - 1);
			returnParam = "admin_querydeleted";
		}
		
		
		int currentIndex = page.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
		
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("adminList", page.getContent());
		
		model.addAttribute(Constants.SHOW_PAGES, true);
		session.setAttribute(Constants.SHOW_PAGES_FROM_SEARCH, false);
		
		return returnParam;
	}
	

	/**
	 * Controlador para cambiar status de Administrador a Activo
	 * */
	@RequestMapping("/admin/reactivate/{idAd}")
	public String reactivate(HttpSession session, RedirectAttributes ra,  
			@PathVariable("idAd") int idAd) {
		
		Admin admin = adminService.findOne(idAd);
		admin.setStatusAd(Constants.STATUS_ACTIVE);
		adminService.saveOrUpdate(admin, admin.getPersona());
		
		ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("admin_activated", null, Locale.getDefault()));
		
		return "redirect:/admin/queryall/1";
	}
	
	
	@RequestMapping("/admin/update/{idAd}")
	public String update(Model model, @PathVariable("idAd") int idAd,
			HttpSession session) {
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
		
		model.addAttribute("personaAdminWrapper", personaAdminWrapper);
		
		Page<Admin> page = adminService.getPage(0);
		
		int currentIndex = page.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
		
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("adminList", page.getContent());
		
		model.addAttribute(Constants.SHOW_PAGES, true);
		session.setAttribute(Constants.SHOW_PAGES_FROM_SEARCH, false);
		
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
		
		return "redirect:/admin/queryall/1";
	}
	
	
	/**
	 * Paginación como resultado de la búsqueda
	 * */
	@RequestMapping(value="/admin/search/{index}")
	public String searchPages(@PathVariable("index") int index,
			HttpSession session, Model model) {
		
		String searchKeyword = (String) session.getAttribute(Constants.ADMIN_SEARCH_KEYWORD);
		
		Page<Admin> page = adminService.findActiveByFullNameLike(index - 1, "%" + searchKeyword + "%");
		System.out.println("/admin/search/" + index  + " ****** " + page.getTotalElements());
		
		model.addAttribute("personaAdminWrapper", new PersonaAdminWrapper());
		
		int currentIndex = page.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
		
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("adminList", page.getContent());
		
		model.addAttribute(Constants.SHOW_PAGES, false);
		session.setAttribute(Constants.SHOW_PAGES_FROM_SEARCH, true);
		
		return "admin_queryall";
	}
	
	
	/**
	 * Controlador para mostrar los resultados de la búsqueda de un
	 * administador
	 * */
	@RequestMapping(value="/admin/search", method=RequestMethod.POST)
	public String search(@RequestParam("busqueda") String  busqueda,
			RedirectAttributes ra, Model model, HttpSession session) {
		
		System.out.println("Parámetro de búsqueda: " + busqueda);
		
		Page<Admin> page = adminService.findActiveByFullNameLike(0, "%" + busqueda + "%");
		System.out.println("/admin/search ***** " + page.getTotalElements());
		
		if(page.getTotalElements() > 0){
			
			int currentIndex = page.getNumber() + 1;
			int beginIndex = Math.max(1, currentIndex - 5);
			int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
			
			model.addAttribute("beginIndex",beginIndex);
			model.addAttribute("endIndex",endIndex);
			model.addAttribute("currentIndex",currentIndex);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("adminList", page.getContent());
			
			model.addAttribute(Constants.SHOW_PAGES, false);
			session.setAttribute(Constants.SHOW_PAGES_FROM_SEARCH, true);
			session.setAttribute(Constants.ADMIN_SEARCH_KEYWORD, busqueda);
			model.addAttribute("personaAdminWrapper", new PersonaAdminWrapper());
			
			return "admin_queryall";
		}
		else{
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("admin_not_found", 
					new Object[]{busqueda}, Locale.getDefault()));
			
				return "redirect:/admin/queryall/1";
		}		
		
		
		
		
		
		
		
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
	 * Servidor JSON para búsqueda de Administradores con statusAd="Activo"
	 * */
	@RequestMapping(value="/json/search/admin", produces="application/json")
	@ResponseBody
	public Map<String, Object> findAll(@RequestParam("term") String term) {

		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Admin> adminList = adminService.findActiveLikeNombreOApellido("%" + term + "%");
		
		for (int j = 0; j < adminList.size(); j++) {
			Admin admin = adminList.get(j);
			map.put(String.valueOf(admin.getIdAd()), 
										admin.getPersona().getNombrePer() + " " +
										admin.getPersona().getApPatPer() + " " +
										admin.getPersona().getApMatPer());
		}
		
		System.out.println(map.size());
		System.out.println(map);
		
		return map;
	}
}
