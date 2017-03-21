package com.citnova.sca.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Estado;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.domain.PersonaClienteDireccionWrapper;
import com.citnova.sca.service.ClienteService;
import com.citnova.sca.service.EstadoService;
import com.citnova.sca.service.PersonaService;
import com.citnova.sca.util.Constants;
import com.citnova.sca.util.PassGen;

@Controller
public class ClienteController {
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private MessageSource messageSource;
	
	private Timestamp time = new Timestamp(new Date().getTime());
	
	@RequestMapping("/clienteform")
	public String showClientForm(Model model) {
		model.addAttribute("personaClienteWrapper", new PersonaClienteDireccionWrapper());
		
		// Consulta los Estados de Direccion
		List<Estado> estadoList = estadoService.findAll();
		System.out.println(estadoList.size());
		for(Estado estado : estadoList) {
            System.out.println(estado);
        }
		model.addAttribute("estadoList", estadoList);
		
		return "cliente_form";
	}
	
	
	@RequestMapping(value="/clientesave", method=RequestMethod.POST)
	public String createCliente(Model model, PersonaClienteDireccionWrapper personaClienteWrapper, 
			@RequestParam("otroOcupacion") String otroOcupacion) {
		
		List<Cliente> clienteList = clienteService.findAll();
		
		// Genera un nuevo pass para passArea
		boolean passUsed;
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
		
		// Revisa si la ocupación fue seleccionada como "Otro" para tratarla
		String ocupacion;
		if(otroOcupacion != null) {
			ocupacion = otroOcupacion;
		} else {
			ocupacion = personaClienteWrapper.getOcupacionCli();
		}
		System.out.println(ocupacion);

		
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
				ocupacion,
				personaClienteWrapper.getObjetivoCli(),
				personaClienteWrapper.getAvatarCli(),
				Constants.STATUS_MUST_ACTIVATE,
				passArea);
		
		Persona persona2 = personaService.findByEmailPer(persona.getEmailPer());
		if(persona2 != null){
			model.addAttribute(Constants.RESULT, messageSource.getMessage("admin_exists", null, Locale.getDefault()));
			return "cliente_queryall";
		}
		
		clienteService.saveOrUpdate(cliente, persona);
		
		return "cliente_queryall";
	}
	
	
	
	
	@RequestMapping("/cliente/queryall")
	public String queryAll(Model model) {
		List<Cliente> clienteList = clienteService.findAll();
		System.out.println(clienteList.size());
		for(Cliente cliente : clienteList) {
            System.out.println(cliente);
        }
		model.addAttribute("clienteList", clienteList);
		model.addAttribute("personaClienteWrapper", new PersonaClienteDireccionWrapper());
		
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
