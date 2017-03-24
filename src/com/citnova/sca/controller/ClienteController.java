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
import org.springframework.data.domain.Page;
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
import com.citnova.sca.domain.PersonaAdminWrapper;
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
		
		// Consulta los Estados
		List<Estado> estadoList = estadoService.findAll();
		model.addAttribute("estadoList", estadoList);
		
		// Revisa si la ocupación fue seleccionada como "Otro" para tratarla
		String ocupacion;
		if(personaClienteDireccionWrapper.getOcupacionCli().equals("Otro")) {
			model.addAttribute("otroOcup", otroOcupacion);
			ocupacion = otroOcupacion;
		} else {
			ocupacion = personaClienteDireccionWrapper.getOcupacionCli();
		}
		System.out.println("\nOcupación: " + ocupacion + "\n");
		
		int idCli = personaClienteDireccionWrapper.getIdCli();
		//
		// Guardar nuevo registro
		//
		if(idCli == 0){
			System.out.println("********* SAVE CLIENTE");
		
			// Genera un nuevo pass para passArea
			List<Cliente> clienteList = clienteService.findAll();
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
							.concat("/confirm/")
						);
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("cliente_saved", null, Locale.getDefault()));
		
		}
		
		else{
			Cliente cliente = clienteService.findOne(idCli);
			Persona persona = cliente.getPersona();
			Direccion direccion = cliente.getDireccion();
			Municipio municipio = direccion.getMunicipio();
			
			System.out.println("********* UPDATE");
			
			persona.setNombrePer(personaClienteDireccionWrapper.getNombrePer());
			persona.setApPatPer(personaClienteDireccionWrapper.getApPatPer());
			persona.setApMatPer(personaClienteDireccionWrapper.getApMatPer());
			persona.setEmailPer(personaClienteDireccionWrapper.getEmailPer());
			persona.setCurpPer(personaClienteDireccionWrapper.getCurpPer());
			
			cliente.setSexoCli(personaClienteDireccionWrapper.getSexoCli());
			cliente.setTelFijoCli(personaClienteDireccionWrapper.getTelFijoCli());
			cliente.setTelMovilCli(personaClienteDireccionWrapper.getTelMovilCli());
			cliente.setfNacCli(personaClienteDireccionWrapper.getfNacCli());
			cliente.setOcupacionCli(ocupacion);
			cliente.setObjetivoCli(personaClienteDireccionWrapper.getObjetivoCli());
			cliente.setAvatarCli(personaClienteDireccionWrapper.getAvatarCli());
			
			direccion.setNumExtDir(personaClienteDireccionWrapper.getNumExtDir());
			direccion.setNumIntDir(personaClienteDireccionWrapper.getNumIntDir());
			direccion.setColoniaDir(personaClienteDireccionWrapper.getColoniaDir());
			direccion.setCpDir(personaClienteDireccionWrapper.getCpDir());

			municipio = municipioService.findByIdMun(personaClienteDireccionWrapper.getIdMun());
			
			clienteService.saveOrUpdate(cliente, persona, direccion, municipio);
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("cliente_updated", null, Locale.getDefault()));
		}
		
		return "redirect:/cliente/queryall";
	}
	
	
	
	/**
	 * Consulta de todos los clientes
	 * */
	@RequestMapping("/cliente/queryall/{index}")
	public String queryAll(Model model, @PathVariable("index") int index) {
		
		// Busca todos los clientes, activos o inactivos
		List<Cliente> clienteList = clienteService.findAll();

		model.addAttribute("clienteList", clienteList);
		model.addAttribute("personaClienteDireccionWrapper", new PersonaClienteDireccionWrapper());
		
		Page<Cliente> page = clienteService.getPage(index - 1);
		
		int currentIndex = page.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
		
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("clienteList", page.getContent());
		
		model.addAttribute(Constants.SHOW_PAGES, true);
		
		return "cliente_queryall";
	}
	
	/**
	 * Actualizar un cliente en específico
	 * */
	@RequestMapping("/cliente/update/{idCli}")
	public String update(Model model, @PathVariable("idCli") int idCli) {
		
		// Consulta los Estados y los coloca como atributos a la vista
		List<Estado> estadoList = estadoService.findAll();
		model.addAttribute("estadoList", estadoList);
				
		Cliente cliente = clienteService.findOne(idCli);
		Persona persona = cliente.getPersona();
		Direccion direccion = cliente.getDireccion();
		Municipio municipio = direccion.getMunicipio();
		
		PersonaClienteDireccionWrapper personaClienteDireccionWrapper = new PersonaClienteDireccionWrapper();
		
		personaClienteDireccionWrapper.setIdPer(persona.getIdPer());
		personaClienteDireccionWrapper.setNombrePer(persona.getNombrePer());
		personaClienteDireccionWrapper.setApPatPer(persona.getApPatPer());
		personaClienteDireccionWrapper.setApMatPer(persona.getApMatPer());
		personaClienteDireccionWrapper.setEmailPer(persona.getEmailPer());
		personaClienteDireccionWrapper.setCurpPer(persona.getCurpPer());
		personaClienteDireccionWrapper.setFhCreaPer(persona.getFhCreaPer());
		
		personaClienteDireccionWrapper.setIdCli(cliente.getIdCli());
		personaClienteDireccionWrapper.setEmailAltCli(cliente.getEmailAltCli());
		personaClienteDireccionWrapper.setPassCli(cliente.getPassCli());
		personaClienteDireccionWrapper.setSexoCli(cliente.getSexoCli());
		personaClienteDireccionWrapper.setTelFijoCli(cliente.getTelFijoCli());
		personaClienteDireccionWrapper.setTelMovilCli(cliente.getTelMovilCli());
		personaClienteDireccionWrapper.setfNacCli(cliente.getfNacCli());
		personaClienteDireccionWrapper.setFhCreaCli(cliente.getFhCreaCli());
		
		personaClienteDireccionWrapper.setObjetivoCli(cliente.getObjetivoCli());
		personaClienteDireccionWrapper.setAvatarCli(cliente.getAvatarCli());
		personaClienteDireccionWrapper.setStatusCli(cliente.getStatusCli());
		personaClienteDireccionWrapper.setPassAreaCli(cliente.getPassAreaCli());
		personaClienteDireccionWrapper.setIdDir(direccion.getIdDir());
		personaClienteDireccionWrapper.setCalleDir(direccion.getCalleDir());
		personaClienteDireccionWrapper.setNumExtDir(direccion.getNumExtDir());
		personaClienteDireccionWrapper.setNumIntDir(direccion.getNumIntDir());
		personaClienteDireccionWrapper.setColoniaDir(direccion.getColoniaDir());
		personaClienteDireccionWrapper.setCpDir(direccion.getCpDir());
		
		personaClienteDireccionWrapper.setIdEstado(municipio.getEstado().getIdEst());
		personaClienteDireccionWrapper.setIdMun(municipio.getIdMun());
		
		
		model.addAttribute("personaClienteDireccionWrapper", personaClienteDireccionWrapper);
		
		// Revisa el valor de ocupación y lo compara con un arreglo que contiene las ocupaciones
		// disponibles en el <select> de la vista para saber que valor darle a ocupaciónCli y al 
		// parámetro otroOcupacion
		
		String ocupacion = cliente.getOcupacionCli();
		String otroOcupacion = "";
		boolean isOtro = true;
		for(int i=0; i<Constants.OCUPACIONES.length; i++) {
			if(ocupacion.equals(Constants.OCUPACIONES[i])) {
				isOtro = false;
			}
		}
		
		if(isOtro==true) {
			personaClienteDireccionWrapper.setOcupacionCli("Otro");
			otroOcupacion = cliente.getOcupacionCli();
		}
		else {
			personaClienteDireccionWrapper.setOcupacionCli(cliente.getOcupacionCli());
		}
		
		model.addAttribute("otroOcup", otroOcupacion);
		
		return "cliente_form";
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
	
	
	/**
	 * Confirmación de cuenta de cliente, enviado desde la URL en el email de confirmación de cuenta
	 * */
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
