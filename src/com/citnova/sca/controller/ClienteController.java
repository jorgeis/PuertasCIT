package com.citnova.sca.controller;



import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.domain.PersonaClienteWrapper;
import com.citnova.sca.service.ClienteService;
import com.citnova.sca.service.PersonaService;
import com.citnova.sca.util.Constants;
import com.citnova.sca.util.PassGen;

@Controller
public class ClienteController {
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private ClienteService clienteService;
	
	private Timestamp time = new Timestamp(new Date().getTime());
	
	@RequestMapping("/clienteform")
	public String showClientForm(Model model) {
		model.addAttribute("personaClienteWrapper", new PersonaClienteWrapper());
		return "client_form";
	}
	
	@RequestMapping("/clientesave")
	public String createCliente(Model model, PersonaClienteWrapper personaClienteWrapper, 
			HttpSession session, Principal principal, HttpServletRequest request) {
		
		boolean passUsed;
		List<Cliente> clienteList = clienteService.findAll();
		String passArea;
		
		do {
			passUsed = false;
			passArea = PassGen.generatePass();
			System.out.println("Pass generado: " + passArea);
			for(Cliente cliente : clienteList) {
	            if(passArea.equals(cliente.getPassAreaCli())) {
	            	System.out.println("La contraseña ya existe");
	            	passUsed = true;
	            }
	        }
			System.out.println(passUsed);
		}
		while (passUsed == true);
		
		

		
		Persona persona = new Persona(personaClienteWrapper.getNombrePer(),
				personaClienteWrapper.getApPatPer(),
				personaClienteWrapper.getApMatPer(),
				personaClienteWrapper.getEmailPer(),
				personaClienteWrapper.getCurpPer(),
				time);
		
		Cliente cliente = new Cliente(personaClienteWrapper.getEmailAltCli(),
				personaClienteWrapper.getSexoCli(),
				personaClienteWrapper.getTelFijoCli(),
				personaClienteWrapper.getTelMovilCli(),
				personaClienteWrapper.getfNacCli(),
				time,
				personaClienteWrapper.getOcupacionCli(),
				personaClienteWrapper.getObjetivoCli(),
				personaClienteWrapper.getAvatarCli(),
				Constants.STATUS_MUST_ACTIVATE,
				passArea);
		
		String a = null;
		
		return "redirect:cliente/queryall";
	}
	
	@RequestMapping("/cliente/queryall")
	public String queryAll(Model model) {
		List<Cliente> clienteList = clienteService.findAll();
		System.out.println(clienteList.size());
		for(Cliente cliente : clienteList) {
            System.out.println(cliente);
        }
		model.addAttribute("clienteList", clienteList);
		model.addAttribute("personaClienteWrapper", new PersonaClienteWrapper());
		
		return "cliente_queryall";
	}
	
	
	
	
	
	
	
	
	
//	@RequestMapping(value="/admin/save", method=RequestMethod.POST)
//	public String createPersona(Model model, PersonaAdminWrapper personaAdminWrapper,
//			HttpSession session, Principal principal, HttpServletRequest request) {
//		
//		Persona persona = new Persona(personaAdminWrapper.getNombrePer(), 
//				personaAdminWrapper.getApPatPer(), 
//				personaAdminWrapper.getApMatPer(), 
//				personaAdminWrapper.getEmailPer(), 
//				personaAdminWrapper.getCurpPer(), time);
//		
//		
//		Admin admin = new Admin(personaAdminWrapper.getAreaAd(),
//				personaAdminWrapper.getCargoAd(),
//				personaAdminWrapper.getTelefonoAd(), 
//				time, 
//				personaAdminWrapper.getRolAd(), 
//				Constants.STATUS_ACTIVE, 
//				principal.getName());
//		
//		
//		// Validar si ya existe la persona
//		Persona persona2 = personaService.findByEmailPer(persona.getEmailPer());
//		if(persona2 != null){
//			model.addAttribute(Constants.RESULT, messageSource.getMessage("admin_exists", null, Locale.getDefault()));
//			return "admin_queryall";
//		}
//		
//		personaService.save(persona);
//		admin.setPersona(persona);
//		adminService.save(admin);
//		
//		// Enviar correo electrónico para confirmar cuenta
//		mailManager.sendEmailConfirmacion(persona.getEmailPer(), 
//				util.getRootUrl(request, "/admin/save").concat("/admin/confirm"));
//		
//		return "redirect:/admin/queryall";
//	}

}
