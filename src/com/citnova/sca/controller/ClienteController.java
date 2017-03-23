package com.citnova.sca.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citnova.sca.domain.Admin;
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
import com.citnova.sca.util.MailManager;
import com.citnova.sca.util.PassGen;
import com.citnova.sca.util.Util;

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
	
	@Autowired
	private MailManager mailManager;
	
	@Autowired
	private Util util;
	
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
			@RequestParam("otroOcupacion") String otroOcupacion, HttpServletRequest request,
			RedirectAttributes ra) {
		
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

		// Guardar nuevo registro
		
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
		
		// Revisa una la persona ya existe con el mismo email
		
		Persona persona2 = personaService.findByEmailPer(persona.getEmailPer());
		if(persona2 != null){
			model.addAttribute(Constants.RESULT, messageSource.getMessage("client_exists", null, Locale.getDefault()));
			return "/cliente_form";
		}
		
		System.out.println(persona);
		System.out.println(cliente);
		System.out.println(direccion);
		
		// Guardar los datos a través del servicio
		clienteService.saveOrUpdate(cliente, persona, direccion, municipio);
		
		// Enviar correo electrónico para confirmar cuenta
		mailManager.sendEmailConfirmacion(persona.getEmailPer(), 
				util.getRootUrl(request, "/clientesave")
						.concat("/clienteaccount/")
						.concat(String.valueOf(cliente.getIdCli()))
					);
		ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("cliente_saved", null, Locale.getDefault()));
		
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
	
	
	
	@RequestMapping("/clienteaccount/{idCli}/confirm")
	public String inputPassword(HttpSession session, Model model,  
			@PathVariable("idCli") int idCli) {
		
		session.setAttribute(Constants.ID_CLI, idCli);
		Cliente cliente = clienteService.findOne(idCli);
		
		if(cliente.getStatusCli().equals(Constants.STATUS_MUST_ACTIVATE)){
			Persona persona = cliente.getPersona();
			Direccion direccion = cliente.getDireccion();
			Municipio municipio = cliente.getDireccion().getMunicipio();
			
			cliente.setStatusCli(Constants.STATUS_ACTIVE);
			clienteService.saveOrUpdate(cliente, persona, direccion, municipio);
			
			model.addAttribute(Constants.RESULT, messageSource.getMessage("cliente_confirmed", null, Locale.getDefault()));
			
			session.invalidate();
			return "notifications";
		}
		else{
			model.addAttribute(Constants.RESULT, messageSource.getMessage("cliente_was_confirmed", null, Locale.getDefault()));
			return "notifications";
		}
	}
	

	
}
