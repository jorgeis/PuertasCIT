package com.citnova.sca.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Direccion;
import com.citnova.sca.domain.Estado;
import com.citnova.sca.domain.Municipio;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.domain.PersonaClienteDireccionWrapper;
import com.citnova.sca.service.ClienteService;
import com.citnova.sca.service.EstadoService;
import com.citnova.sca.service.MunicipioService;
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
	private MunicipioService municipioService;
	
	@Autowired
	private MessageSource messageSource;
	
	private Timestamp time = new Timestamp(new Date().getTime());
	
	@RequestMapping("/clienteform")
	public String showClientForm(Model model) {
		model.addAttribute("personaClienteDireccionWrapper", new PersonaClienteDireccionWrapper());
		
		// Consulta los Estados
		List<Estado> estadoList = estadoService.findAll();
		model.addAttribute("estadoList", estadoList);
		
		return "cliente_form";
	}
	
	
	/**
	 * Guardar nuevo cliente en BD
	 * */
	@RequestMapping(value="/clientesave", method=RequestMethod.POST)
	public String createCliente(Model model, PersonaClienteDireccionWrapper personaClienteDireccionWrapper, 
			@RequestParam("otroOcupacion") String otroOcupacion) {
		
		List<Cliente> clienteList = clienteService.findAll();
		
		// Consulta los Estados
		List<Estado> estadoList = estadoService.findAll();
		model.addAttribute("estadoList", estadoList);
		
		// Genera un nuevo pass para passArea
		boolean passUsed;
		String passArea;
		do {
			passUsed = false;
			passArea = PassGen.generatePass();
			System.out.println("\nPass generado: " + passArea);
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
		if(personaClienteDireccionWrapper.getOcupacionCli().equals("Otro")) {
			model.addAttribute("otroOcup", otroOcupacion);
			ocupacion = otroOcupacion;
		} else {
			ocupacion = personaClienteDireccionWrapper.getOcupacionCli();
		}
		System.out.println("\nOcupación: " + ocupacion + "\n");

		
		Persona persona = new Persona(personaClienteDireccionWrapper.getNombrePer(),
				personaClienteDireccionWrapper.getApPatPer(),
				personaClienteDireccionWrapper.getApMatPer(),
				personaClienteDireccionWrapper.getEmailPer(),
				personaClienteDireccionWrapper.getCurpPer(),
				time);
		
		Cliente cliente = new Cliente(personaClienteDireccionWrapper.getEmailAltCli(),
				personaClienteDireccionWrapper.getPassCli(),
				personaClienteDireccionWrapper.getSexoCli(),
				personaClienteDireccionWrapper.getTelFijoCli(),
				personaClienteDireccionWrapper.getTelMovilCli(),
				personaClienteDireccionWrapper.getfNacCli(),
				time,
				ocupacion,
				personaClienteDireccionWrapper.getObjetivoCli(),
				personaClienteDireccionWrapper.getAvatarCli(),
				Constants.STATUS_MUST_ACTIVATE,
				passArea);
		
		Direccion direccion = new Direccion(personaClienteDireccionWrapper.getCalleDir(),
				personaClienteDireccionWrapper.getNumExtDir(),
				personaClienteDireccionWrapper.getNumIntDir(),
				personaClienteDireccionWrapper.getColoniaDir(),
				personaClienteDireccionWrapper.getCpDir());
		
		Municipio municipio = municipioService.findByIdMun(personaClienteDireccionWrapper.getIdMun());
		
		Persona persona2 = personaService.findByEmailPer(persona.getEmailPer());
		if(persona2 != null){
			model.addAttribute(Constants.RESULT, messageSource.getMessage("client_exists", null, Locale.getDefault()));
			return "/cliente_form";
		}
		
		System.out.println(persona);
		System.out.println(cliente);
		System.out.println(direccion);
		
		clienteService.saveOrUpdate(cliente, persona, direccion, municipio);
		
		return "redirect:/cliente/queryall";
	}
	
	
	
	/**
	 * Consulta de todos los clientes
	 * */
	@RequestMapping("/cliente/queryall")
	public String queryAll(Model model) {
		List<Cliente> clienteList = clienteService.findAll();
//		System.out.println(clienteList.size());
//		for(Cliente cliente : clienteList) {
//            System.out.println(cliente);
//        }
		model.addAttribute("clienteList", clienteList);
		model.addAttribute("personaClienteDireccionWrapper", new PersonaClienteDireccionWrapper());
		
		return "cliente_queryall";
	}
	
	
	/**
	 * Servidor JSON para búsqueda de Municipios
	 * */
	@RequestMapping(value="/json/search/mun", produces="application/json")
	@ResponseBody
	public Map<String, Object> findMunicipio(@RequestParam("term") String idEstado) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		Estado estado = new Estado();
		if(idEstado == "") {
			estado.setIdEst(0);
		} else {
			estado.setIdEst(Integer.parseInt(idEstado));
		}
		
		List<Municipio> municipios = municipioService.findByEstado(estado);
		
		for (int i = 0; i < municipios.size(); i++) {
			Municipio municipio = municipios.get(i);
			map.put(Integer.toString(municipio.getIdMun()), municipio.getNombreMun());
		}
		return map;
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
