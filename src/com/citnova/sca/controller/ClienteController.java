package com.citnova.sca.controller;

import java.security.Principal;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Direccion;
import com.citnova.sca.domain.Estado;
import com.citnova.sca.domain.Municipio;
import com.citnova.sca.domain.Organizacion;
import com.citnova.sca.domain.OrganizacionCliente;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.domain.PersonaClienteDireccionWrapper;
import com.citnova.sca.service.ClienteService;
import com.citnova.sca.service.EstadoService;
import com.citnova.sca.service.MunicipioService;
import com.citnova.sca.service.OrganizacionClienteService;
import com.citnova.sca.service.OrganizacionService;
import com.citnova.sca.service.PersonaService;
import com.citnova.sca.util.Constants;
import com.citnova.sca.util.CurrentSessionUserRetriever;
import com.citnova.sca.util.MailManager;
import com.citnova.sca.util.PassGen;
import com.citnova.sca.util.Util;

@Controller
public class ClienteController {
	
	/** Atributos @Autowired */
	@Autowired
	private PersonaService personaService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EstadoService estadoService;
	@Autowired
	private MunicipioService municipioService;
	@Autowired
	private OrganizacionClienteService organizacionClienteService;
	@Autowired
	private OrganizacionService organizacionService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private MailManager mailManager;
	@Autowired
	private CurrentSessionUserRetriever currentUser;
	@Autowired
	private Util util;
	@Autowired
	private PasswordEncoder passwordEncoder;

	/** Atributos */
	private Timestamp time = new Timestamp(new Date().getTime());
	
	/**
	 * Mostrar formulario de registro de cliente
	 * @RequestMapping("/clienteform")
	 * */
	@RequestMapping("/clienteform")
	public String showClientForm(Model model, @RequestParam(value = "idOrgParam", required=false) Integer idOrgParam) {
		model.addAttribute("personaClienteDireccionWrapper", new PersonaClienteDireccionWrapper());
		
		// Consulta los Estados
		List<Estado> estadoList = estadoService.findAll();
		model.addAttribute("estadoList", estadoList);
		
		
		// Revisa si se recibió un parámetro con el ID de la organización. De ser así, lo agrega al modelo.
		// En la vista se comprueba si el parámetro es diferente de cero para mostrar u ocultar el campo de
		// password y cambiar el controlador al que se dirige cuando se pulsa submit, para saber si se agregó
		// un usuario normal, o un miembro de organización, lo cual hace que cambie la forma en la que se valida
		// el usuario.
		int idOrg;
		
		System.out.println("parámetro: " + idOrgParam);
		
		if(idOrgParam==null) {
			idOrg = 0;
		}
		else {
			idOrg = idOrgParam;
		}
		
		model.addAttribute("idOrgParam", idOrg);
		
		return "cliente_form";
	}

	
	/**
	 * Controlador para mostrar formulario búsqueda de clientes activos
	 * @RequestMapping("/cliente/searchform")
	 * */
	@RequestMapping("/cliente/searchform")
	public String showClienteSearch(Model model) {
		
		model.addAttribute(Constants.PAGE_TITLE, "Búsqueda de Clientes Activos");
		model.addAttribute(Constants.CUSTOM_MAPPING, "/cliente/search");
		model.addAttribute(Constants.JSON_SERVER, "/json/search/cliente");
		
		return "search";
	}
	
	
	/**
	 * Guardar nuevo cliente en BD
	 * @RequestMapping(value="/clientesave", method=RequestMethod.POST)
	 * */
	@RequestMapping(value="/clientesave", method=RequestMethod.POST)
	public String createCliente(Model model, PersonaClienteDireccionWrapper personaClienteDireccionWrapper, 
			@RequestParam("otroOcupacion") String otroOcupacion, HttpServletRequest request,
			RedirectAttributes ra, @RequestParam(value = "idOrgParam", required=false) Integer idOrgParam) {
		
		//idOrgParam es un parámetro enviado desde el formulario. Si su valor es cero significa que se está dando de alta
		//un nuevo usuario. Si su valor es diferente de cero significa que se está dando de alta un nuevo usuario que
		//por parte de un responsable de organización, y que el nuevo usuario es miembro de la nueva organización.
		
		// Se revisa idOrgParam. En caso de que sea diferente de cero, se asocia el nuevo usuario con la organización
		// cuyo id sea igual a idOrgParam.
		
		int idOrg;
		
		System.out.println("Parámetro idOrgParam en /clientesave: " + idOrgParam);
		
		if(idOrgParam==null) {	
			idOrg = 0;	
		}
		else {	
			idOrg = idOrgParam;	
		}

		model.addAttribute("idOrgParam", idOrg);
		
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
			List<OrganizacionCliente> orgCliList = organizacionClienteService.findAll();
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
				for(OrganizacionCliente orgCli : orgCliList) {
		            if(passArea.equals(orgCli.getPassOC())) {
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
			
			if(idOrg == 0) {
				cliente.setPassCli(passwordEncoder.encode(personaClienteDireccionWrapper.getPassCli()));
			}
			
			// Revisa si existe un cliente con el mismo CURP
			Cliente cliente2 = clienteService.findByCurpPer(persona.getCurpPer());
			if(cliente2 != null){
				System.out.println("Persona con el mismo curp: " + cliente2.getPersona().getCurpPer());
				
				model.addAttribute(Constants.RESULT, messageSource.getMessage("client_curp_exists", 
						new Object[]{cliente2.getPersona().getCurpPer()}, Locale.getDefault()));
				
				// Comportamiento cuando un cliente responsable de empresa registra a un cliente que ya existe en sistema
				// (con el mismo CURP) como miembro de la empresa.
				if(idOrg != 0) {
					
					// Verifica que la persona ingresada existe o no en la organización que se desea ingresar
					//List<Persona> miembros = 
					
					boolean existInOrg = false;
					
					List<OrganizacionCliente> orgCliList2 = organizacionClienteService.findAllByIdOrg(idOrg);
					
					System.out.println("Datos de la tupla a buscar: \nOrg: " + idOrg + "\nidCli: " + idCli);
					for(int i=0; i<orgCliList2.size(); i++) {
						System.out.println("Cliente en OrganizaciónCliente: " + orgCliList2.get(i).getCliente().getIdCli());
						if(persona.getCurpPer().equals(orgCliList2.get(i).getCliente().getPersona().getCurpPer())) {
							existInOrg = true;
							System.out.println("Coincidencia encontrada: Cliente " + orgCliList2.get(i).getCliente().getIdCli());
						}
					}
					
					
					// Si el usuario que se quiere registrar ya está dado de alta como miembro activo de la organización
					if(existInOrg == true) {
						model.addAttribute(Constants.MESSAGE1, messageSource.getMessage("org_cliente_exists", null, Locale.getDefault()));
						model.addAttribute(Constants.CUSTOM_MAPPING, "/org/querymembers");
						model.addAttribute(Constants.CONFIRM_BUTTON, "Consultar Miembros");
						model.addAttribute(Constants.PAGE_TITLE, "La persona ya existe");
						model.addAttribute(Constants.PARAM1, idOrg);
					}
					else {
						model.addAttribute(Constants.MESSAGE1, messageSource.getMessage("cliente_send_member_email", null, Locale.getDefault()));
						model.addAttribute(Constants.CUSTOM_MAPPING, "/org/sendinvite");
						model.addAttribute(Constants.CONFIRM_BUTTON, "Enviar solicitud");
						model.addAttribute(Constants.PAGE_TITLE, "Registro de Cliente");
						model.addAttribute(Constants.PARAM1, persona.getCurpPer());
						model.addAttribute(Constants.PARAM2, idOrg);
					}
				}

				
				// Comportamiento cuando se registra un cliente por su propia cuenta, sin relacion alguna con alguna
				// organización.
				else{
					model.addAttribute(Constants.MESSAGE1, messageSource.getMessage("cliente_trylogin", null, Locale.getDefault()));
					model.addAttribute(Constants.CUSTOM_MAPPING, "/login");
					model.addAttribute(Constants.CONFIRM_BUTTON, "Inicia Sesión");
					model.addAttribute(Constants.PAGE_TITLE, "Registro de Cliente");
				}
				
				
				
				return "/confirm";
			}
			
			
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
			
			// Se revisa idOrgParam. En caso de que sea diferente de cero, se asocia el nuevo usuario con la organización
			// cuyo id sea igual a idOrgParam.
			
			if(idOrg != 0) {
				
				Organizacion organizacion = organizacionService.findOne(idOrg);
				
				// Genera un nuevo pass para passOC
				List<Cliente> clienteListB = clienteService.findAll();
				List<OrganizacionCliente> orgCliListB = organizacionClienteService.findAll();
				boolean passUsedB = false;
				String passOC;
				do {
					passUsed = false;
					passOC = PassGen.generatePass();
					System.out.println("\nPass generado: " + passOC);
					for(Cliente cli : clienteListB) {
			            if(passOC.equals(cli.getPassAreaCli())) {
			            	System.out.println("La contraseña ya existe");
			            	passUsedB = true;
			            }
			        }
					for(OrganizacionCliente orgClie : orgCliListB) {
			            if(passOC.equals(orgClie.getPassOC())) {
			            	System.out.println("La contraseña ya existe");
			            	passUsedB = true;
			            }
			        }
					System.out.println(passUsedB);
				}
				while (passUsedB == true);
				
				System.out.println("El objeto cliente ingresado y guardado: " + cliente);
				System.out.println("La organización de idOrgParam es: " + organizacion);
				OrganizacionCliente orgCli = new OrganizacionCliente();
				orgCli.setOrganizacion(organizacion);
				orgCli.setCliente(cliente);
				orgCli.setCargoOC(Constants.ORG_MEMBER);
				orgCli.setPassOC(passOC);
				orgCli.setStatusOC(Constants.STATUS_ACTIVE);
				
				organizacionClienteService.save(orgCli);
				
				// Enviar correo electrónico para confirmar cuenta, soicitando la contraseña de la cuenta.
				mailManager.sendEmailConfirmacion(persona.getEmailPer(), 
						util.getRootUrl(request, "/clientesave")
								.concat("/clienteaccount/")
								.concat(String.valueOf(cliente.getIdCli()))
								.concat("/account/password")
							);
				
				ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("cliente_member_saved", null, Locale.getDefault()));
				return "redirect:/org/queryown/1";
			}
			
			if(	ocupacion.equals(Constants.OCUPACIONES[1]) || ocupacion.equals(Constants.OCUPACIONES[2]) ||
				ocupacion.equals(Constants.OCUPACIONES[3]) || ocupacion.equals(Constants.OCUPACIONES[4])) {
				
				ra.addFlashAttribute("idCli", cliente.getIdCli());
				
				// Enviar correo electrónico para confirmar cuenta (Revisar igual A = B)
				mailManager.sendEmailConfirmacion(persona.getEmailPer(), 
						util.getRootUrl(request, "/clientesave")
								.concat("/clienteaccount/")
								.concat(String.valueOf(cliente.getIdCli()))
								.concat("/confirm/")
							);
				
				ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("cliente_saved", null, Locale.getDefault()));
				return "redirect:/login";
			}
			
			else {
				
				// Enviar correo electrónico para confirmar cuenta (Revisar igual B = A)
				mailManager.sendEmailConfirmacion(persona.getEmailPer(), 
						util.getRootUrl(request, "/clientesave")
								.concat("/clienteaccount/")
								.concat(String.valueOf(cliente.getIdCli()))
								.concat("/confirm/")
							);
				ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("cliente_saved", null, Locale.getDefault()));
				return "redirect:/login";
			}
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
			
			direccion.setCalleDir(personaClienteDireccionWrapper.getCalleDir());
			direccion.setNumExtDir(personaClienteDireccionWrapper.getNumExtDir());
			direccion.setNumIntDir(personaClienteDireccionWrapper.getNumIntDir());
			direccion.setColoniaDir(personaClienteDireccionWrapper.getColoniaDir());
			direccion.setCpDir(personaClienteDireccionWrapper.getCpDir());

			municipio = municipioService.findByIdMun(personaClienteDireccionWrapper.getIdMun());
			
			clienteService.saveOrUpdate(cliente, persona, direccion, municipio);
			ra.addFlashAttribute(Constants.MESSAGE1, messageSource.getMessage("cliente_updated", null, Locale.getDefault()));
			
			return "redirect:/cliente/queryall/1";
		}
	}
	
	
	/**
	 * Controlador para realizar la búsqueda de clientes en función del parámetro ingresado.
	 *  - queryall: Devuelve todos los administradores con statusAd = 'Activo'
	 *  - querydeleted: Devuelve todos los administradores con statusAd = 'Borrado'
	 *  - querypending: Devuelve todos los administradores con statusAd = 'Activar'
	 *  @RequestMapping("/cliente/query{searchParam}/{index}")
	 * */
	@RequestMapping("/cliente/query{searchParam}/{index}")
	public String queryCliente(Model model, @PathVariable("index") int index, @PathVariable("searchParam") String searchParam,
			HttpSession session) {
		
		model.addAttribute("personaClienteDireccionWrapper", new PersonaClienteDireccionWrapper());
		
		if(searchParam.equals(null)) { searchParam = "all";}
		Page<Cliente> page = null;
		
		if(searchParam.equals("all")) {
			page = clienteService.getPageByStatus(Constants.STATUS_ACTIVE, index - 1);
			model.addAttribute(Constants.PAGE_TITLE, messageSource.getMessage("cliente_query_all", null, Locale.getDefault()));
			model.addAttribute(Constants.RESULT, messageSource.getMessage("search_result", null, Locale.getDefault()));
		}
		else if(searchParam.equals("deleted")) {
			page = clienteService.getPageByStatus(Constants.STATUS_DELETED, index - 1);
			model.addAttribute(Constants.PAGE_TITLE, messageSource.getMessage("cliente_query_deleted", null, Locale.getDefault()));
			model.addAttribute(Constants.RESULT, messageSource.getMessage("search_result", null, Locale.getDefault()));
		}
		else if(searchParam.equals("pending")) {
			page = clienteService.getPageByStatus(Constants.STATUS_MUST_ACTIVATE, index - 1);
			model.addAttribute(Constants.PAGE_TITLE, messageSource.getMessage("cliente_query_pending", null, Locale.getDefault()));
			model.addAttribute(Constants.RESULT, messageSource.getMessage("search_result", null, Locale.getDefault()));
		}
		
		int currentIndex = page.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
		
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("clienteList", page.getContent());
		model.addAttribute("searchParam", searchParam);
		
		model.addAttribute(Constants.SHOW_PAGES, true);
		session.setAttribute(Constants.SHOW_PAGES_FROM_SEARCH, false);
		
		return "cliente_query";
	}
	
	
	/**
	 * Actualizar un cliente en específico
	 * @RequestMapping("/cliente/update/{idCli}")
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
	 * Actualizar el cliente identificado actualmente en el sistema
	 * @RequestMapping("/cliente/update")
	 * */
	@RequestMapping("/cliente/update")
	public String updateCurrentClient(Model model, Principal principal) {
		
		// Consulta los Estados y los coloca como atributos a la vista
		List<Estado> estadoList = estadoService.findAll();
		model.addAttribute("estadoList", estadoList);
				
		Cliente cliente = clienteService.findOne(currentUser.getIdCliente(principal));
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
	 * Controlador para cambiar status de Cliente a Borrado
	 * @RequestMapping("/cliente/delete/{idCli}")
	 * */
	@RequestMapping("/cliente/delete/{idCli}")
	public String delete(HttpSession session, RedirectAttributes ra,  
			@PathVariable("idCli") int idCli) {
		
		Cliente cliente = clienteService.findOne(idCli);
		cliente.setStatusCli(Constants.STATUS_DELETED);
		clienteService.saveOrUpdate(cliente, cliente.getPersona(), cliente.getDireccion(), cliente.getDireccion().getMunicipio());
		
		ra.addFlashAttribute(Constants.MESSAGE1, messageSource.getMessage("cliente_deleted", null, Locale.getDefault()));
		
		return "redirect:/cliente/queryall/1";
	}

	
	/**
	 * Controlador para mostrar pantalla de confirmación para cambiar status de Cliente a Borrado en cuenta propia
	 * @RequestMapping("/cliente/deletesc")
	 * */
	@RequestMapping("/cliente/deletesc")
	public String deleteScreen(Model model, HttpSession session, RedirectAttributes ra, Principal principal) {
		
		model.addAttribute(Constants.RESULT, messageSource.getMessage("cliente_confirm_delete", null, Locale.getDefault()));
		model.addAttribute(Constants.CUSTOM_MAPPING, "/cliente/delete");
		model.addAttribute(Constants.CONFIRM_BUTTON, "Eliminar");
		model.addAttribute(Constants.PAGE_TITLE, "Eliminar cuenta");
		
		return "confirm";
	}
	

	/**
	 * Controlador para cambiar status de Cliente a Borrado en cuenta propia
	 * @RequestMapping("/cliente/delete")
	 * */
	@RequestMapping("/cliente/delete")
	public String deleteAccount(HttpSession session, RedirectAttributes ra, Principal principal) {
		
		Cliente cliente = clienteService.findOne(currentUser.getIdCliente(principal));
		cliente.setStatusCli(Constants.STATUS_DELETED);
		clienteService.saveOrUpdate(cliente, cliente.getPersona(), cliente.getDireccion(), cliente.getDireccion().getMunicipio());
		
		
		
		ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("cliente_deleted", null, Locale.getDefault()));
		
		return "redirect:/logout";
	}
	
	
	/**
	 * Confirmación de cuenta de cliente, enviado desde la URL en el email de confirmación de cuenta
	 * @RequestMapping("/clienteaccount/{idCli}/confirm")
	 * */
	@RequestMapping("/clienteaccount/{idCli}/confirm")
	public String inputPassword(HttpSession session, Model model,  
			@PathVariable("idCli") int idCli, RedirectAttributes ra) {
		
		session.setAttribute(Constants.ID_CLI, idCli);
		Cliente cliente = clienteService.findOne(idCli);
		
		if(cliente.getStatusCli().equals(Constants.STATUS_MUST_ACTIVATE)){
			Persona persona = cliente.getPersona();
			Direccion direccion = cliente.getDireccion();
			Municipio municipio = cliente.getDireccion().getMunicipio();
			
			cliente.setStatusCli(Constants.STATUS_ACTIVE);
			clienteService.saveOrUpdate(cliente, persona, direccion, municipio);
			
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("cliente_confirmed", null, Locale.getDefault()));
			
			
			session.invalidate();
			return "redirect:/login";
		}
		else{
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("cliente_was_confirmed", null, Locale.getDefault()));
			return "redirect:/login";
		}
	}
	
	
	/**
	 * Controlador para mostrar los resultados de la búsqueda de un cliente
	 * @RequestMapping(value="/cliente/search", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	 * */
	@RequestMapping(value="/cliente/search", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public String search(@RequestParam("busqueda") String busqueda,
			RedirectAttributes ra, Model model, HttpSession session){
		
		System.out.println("Parámetro de búsqueda: " + busqueda);

		Page<Cliente> page = clienteService.findByFullNameLikeAndStatusActivoPage(0, busqueda);
		
		model.addAttribute(Constants.PAGE_TITLE, messageSource.getMessage("cliente_query_all", null, Locale.getDefault()));
		model.addAttribute("busqueda", busqueda);
		
		if(page.getTotalElements() > 0){
			
			int currentIndex = page.getNumber() + 1;
			int beginIndex = Math.max(1, currentIndex - 5);
			int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
			
			model.addAttribute("beginIndex",beginIndex);
			model.addAttribute("endIndex",endIndex);
			model.addAttribute("currentIndex",currentIndex);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("clienteList", page.getContent());
			
			model.addAttribute(Constants.SHOW_PAGES, false);
			session.setAttribute(Constants.SHOW_PAGES_FROM_SEARCH, true);
			session.setAttribute(Constants.SEARCH_KEYWORD, busqueda);
			model.addAttribute("personaClienteDireccionWrapper", new PersonaClienteDireccionWrapper());
			
			model.addAttribute(Constants.RESULT, messageSource.getMessage("search_result", null, Locale.getDefault()));
			model.addAttribute(Constants.MESSAGE1, messageSource.getMessage("search_matches", new Object[]{busqueda}, Locale.getDefault()));
			
			return "cliente_query";
		}
		else{
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("cliente_not_found", 
					new Object[]{busqueda}, Locale.getDefault()));
			
			return "redirect:/cliente/searchform";
		}		
	}
	
	
	/**
	 * Paginación como resultado de la búsqueda de clientes
	 * @RequestMapping(value="/cliente/search/{index}")
	 * */
	@RequestMapping(value="/cliente/search/{index}")
	public String searchPages(@PathVariable("index") int index,
			HttpSession session, Model model) {
		
		String searchKeyword = (String) session.getAttribute(Constants.SEARCH_KEYWORD);
		
		Page<Cliente> page = clienteService.findByFullNameLikeAndStatusActivoPage(index - 1, "%" + searchKeyword + "%");
		System.out.println("/cliente/search/" + index  + " ****** " + page.getTotalElements());
		
		int currentIndex = page.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
		
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("clienteList", page.getContent());
		
		model.addAttribute(Constants.SHOW_PAGES, false);
		session.setAttribute(Constants.SHOW_PAGES_FROM_SEARCH, true);
		model.addAttribute(Constants.PAGE_TITLE, messageSource.getMessage("cliente_query_all", null, Locale.getDefault()));
		model.addAttribute(Constants.RESULT, messageSource.getMessage("search_result", null, Locale.getDefault()));
		model.addAttribute(Constants.MESSAGE1, messageSource.getMessage("search_matches", new Object[]{searchKeyword}, Locale.getDefault()));
			
		return "cliente_query";
	}
	
		
	/**
	 * Controlador para cambiar status de Cliente a Activo
	 * @RequestMapping("/cliente/activate/{idCli}")
	 * */
	@RequestMapping("/cliente/activate/{idCli}")
	public String activateAccount(HttpSession session, RedirectAttributes ra,  
			@PathVariable("idCli") int idCli) {
		
		System.out.println(idCli);
		
		Cliente cliente = clienteService.findOne(idCli);
		cliente.setStatusCli(Constants.STATUS_ACTIVE);
		clienteService.saveOrUpdate(cliente, cliente.getPersona(), cliente.getDireccion(), cliente.getDireccion().getMunicipio());
		
		ra.addFlashAttribute(Constants.MESSAGE1, messageSource.getMessage("cliente_activated", null, Locale.getDefault()));
		
		return "redirect:/cliente/queryall/1";
	}
	
	
	/**
	 * Vista para una vez creada la cuenta, capturar contraseña (para cuando se da de alta un nuevo cliente
	 * por parte de responsable de empresa)
	 * @RequestMapping("/clienteaccount/{idCli}/account/password")
	 * */
	@RequestMapping("/clienteaccount/{idCli}/account/password")
	public String inputPassword(HttpSession session, Model model,  
			@PathVariable("idCli") int idCli) {
		
		session.setAttribute(Constants.ID_CLI, idCli);
		Cliente cliente = clienteService.findOne(idCli);
		
		if(cliente.getStatusCli().equals(Constants.STATUS_MUST_ACTIVATE)){
			return "cliente_password";
		}
		else{
			model.addAttribute(Constants.RESULT, messageSource.getMessage("cliente_was_confirmed", null, Locale.getDefault()));
			return "notifications";
		}
	}
	
	
	/**
	 * Controlador para guardar contraseña y activar la cuenta de Cliente (para cuando se da de alta un nuevo cliente
	 * por parte de responsable de empresa)
	 * @RequestMapping("/clienteaccount/confirm")
	 * */
	@RequestMapping("/clienteaccount/confirm")
	public String confirmAccount(Model model, @RequestParam("password") String rawPassword, 
			HttpSession session) {
		
		int idCli = (int) session.getAttribute(Constants.ID_CLI);
		Cliente cliente = clienteService.findOne(idCli);
		Persona persona = cliente.getPersona();
		
		cliente.setPassCli(passwordEncoder.encode(rawPassword));
		cliente.setStatusCli(Constants.STATUS_ACTIVE);
		clienteService.saveOrUpdate(cliente, persona, cliente.getDireccion(), cliente.getDireccion().getMunicipio());
		
		model.addAttribute(Constants.RESULT, messageSource.getMessage("cliente_confirmed", null, Locale.getDefault()));
		
		session.invalidate();
		return "notifications";
	}
	
	
	/**
	 * Servidor JSON para búsqueda de Municipios
	 * @RequestMapping(value="/json/search/mun", produces="application/json")
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
	 * Servidor JSON para búsqueda de Clientes con statusCli='Activo'
	 * @RequestMapping(value="/json/search/cliente", produces="application/json")
	 * */
	@RequestMapping(value="/json/search/cliente", produces="application/json")
	@ResponseBody
	public Map<String, Object> findAll(@RequestParam("term") String term) {
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		List<Cliente> clienteList = clienteService.findByFullNameLikeAndStatusActivo(term);
		
		for (int j = 0; j < clienteList.size(); j++) {
			Cliente cliente = clienteList.get(j);
			map.put(String.valueOf(cliente.getIdCli()), 
										cliente.getPersona().getNombrePer() + " " +
										cliente.getPersona().getApPatPer() + " " +
										cliente.getPersona().getApMatPer());
		}
		
		System.out.println(map.size());
		System.out.println(map);
		
		return map;
	}
}