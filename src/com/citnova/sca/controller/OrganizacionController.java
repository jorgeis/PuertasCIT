package com.citnova.sca.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Transactional;
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
import com.citnova.sca.domain.OrganizacionDireccionWrapper;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.domain.SectorEmp;
import com.citnova.sca.service.ClienteService;
import com.citnova.sca.service.EstadoService;
import com.citnova.sca.service.MunicipioService;
import com.citnova.sca.service.OrganizacionClienteService;
import com.citnova.sca.service.OrganizacionService;
import com.citnova.sca.service.SectorEmpService;
import com.citnova.sca.util.Constants;
import com.citnova.sca.util.CurrentSessionUserRetriever;
import com.citnova.sca.util.MailManager;
import com.citnova.sca.util.PassGen;
import com.citnova.sca.util.Util;

@Controller
public class OrganizacionController {
	
	@Autowired
	private EstadoService estadoService;
	@Autowired
	private SectorEmpService sectorEmpService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private OrganizacionClienteService organizacionClienteService;
	@Autowired
	private OrganizacionService organizacionService;
	@Autowired
	private MunicipioService municipioService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private CurrentSessionUserRetriever currentUser;
	@Autowired
	private MailManager mailManager;
	@Autowired
	private Util util;
	
	/**
	 * Formulario de alta de organización
	 * @RequestMapping(value="/orgform")
	 * */
	@RequestMapping(value="/orgform")
	public String showOrganizacionForm(Model model) {
		
		// Consulta los sectores empresariales dados de alta en base de datos
		List<SectorEmp> sectorEmpList = sectorEmpService.findAll();
		model.addAttribute("sectorEmpList", sectorEmpList);
		
		// Consulta los Estados dados de alta en base de datos
		List<Estado> estadoList = estadoService.findAll();
		model.addAttribute("estadoList", estadoList);
		
		model.addAttribute("organizacionDireccionWrapper", new OrganizacionDireccionWrapper());
		
		return "organizacion_form";
	}
	
	
	/**
	 * Guardar nueva organización en BD
	 * @RequestMapping(value="/orgsave", method=RequestMethod.POST)
	 * */
	@RequestMapping(value="/orgsave", method=RequestMethod.POST)
	@Transactional(readOnly = true)
	public String createOrganizacion(Model model, OrganizacionDireccionWrapper organizacionDireccionWrapper, 
			HttpServletRequest request, RedirectAttributes ra, Principal principal) {
		
		int idOrg = organizacionDireccionWrapper.getIdOrg();

		// Guardar nuevo registro
		System.out.println("La organización traida es: " + idOrg);
		
		if(idOrg == 0){
			System.out.println("********* SAVE ORGANIZACION");
	
			// Guardar nuevo registro
			
			Organizacion organizacion = new Organizacion(organizacionDireccionWrapper.getNombreOrg(), 
					organizacionDireccionWrapper.getSiglasOrg(),
					organizacionDireccionWrapper.getRfcOrg(),
					organizacionDireccionWrapper.getNumTrabajadoresOrg(),
					organizacionDireccionWrapper.getTelefonoOrg(),
					organizacionDireccionWrapper.getWebOrg(),
					organizacionDireccionWrapper.getGiroOrg(), 
					new Timestamp(new Date().getTime()));
			
			Direccion direccion = new Direccion(organizacionDireccionWrapper.getCalleDir(),
					organizacionDireccionWrapper.getNumExtDir(),
					organizacionDireccionWrapper.getNumIntDir(),
					organizacionDireccionWrapper.getColoniaDir(),
					organizacionDireccionWrapper.getCpDir());
			
			Municipio municipio = municipioService.findByIdMun(organizacionDireccionWrapper.getIdMun());
			
			SectorEmp sectorEmp = sectorEmpService.findByIdSE(organizacionDireccionWrapper.getSectorEmp());
			
			System.out.println(organizacion);
			System.out.println(direccion);
			
			// Guardar los datos de la nueva organización a través del servicio
			organizacionService.saveOrUpdate(organizacion, direccion, municipio, sectorEmp);
	
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("org_saved", null, Locale.getDefault()));
	
			// Revisa si quien da de alta la organización es un cliente. De ser así, crea la relación OrganizaciónCliente 
			// con el cliente autentificado en el sistema actualmente como Responsable de la Organización.
			
			Cliente cliente = clienteService.findOne(currentUser.getIdCliente(principal));
			System.out.println("El cliente es: " + cliente);
			if(cliente != null) {
			
				// Genera un nuevo pass para passOC
				List<Cliente> clienteList = clienteService.findAll();
				List<OrganizacionCliente> orgCliList = organizacionClienteService.findAll();
				boolean passUsed;
				String passOC;
				do {
					passUsed = false;
					passOC = PassGen.generatePass();
					System.out.println("\nPass generado: " + passOC);
					for(Cliente cli : clienteList) {
			            if(passOC.equals(cli.getPassAreaCli())) {
			            	System.out.println("La contraseña ya existe");
			            	passUsed = true;
			            }
			        }
					for(OrganizacionCliente orgClie : orgCliList) {
			            if(passOC.equals(orgClie.getPassOC())) {
			            	System.out.println("La contraseña ya existe");
			            	passUsed = true;
			            }
			        }
					System.out.println(passUsed);
				}
				while (passUsed == true);
				
				System.out.println("El objeto cliente ha sido traido: " + cliente);
				System.out.println("La organización ingresada es: " + organizacion);
				OrganizacionCliente orgCli = new OrganizacionCliente();
				orgCli.setOrganizacion(organizacion);
				orgCli.setCliente(cliente);
				orgCli.setCargoOC(Constants.ORG_RESPONSABLE);
				orgCli.setPassOC(passOC);
				orgCli.setStatusOC(Constants.STATUS_ACTIVE);
				orgCli.setFhCreaOC(new Timestamp(new Date().getTime()));
				
				organizacionClienteService.save(orgCli);
			}
			// En caso de que no se encuentre un cliente autentificado en el sistema significa que la Organización está
			// siendo registrada por un Administrador, por lo que no crea la relación OrganizaciónCliente. Es posible designar
			// un Responsable mas tarde a través de la administración de Organizacion.
			return "redirect:/confirmscreen";
		}
		
		else{
			Organizacion organizacion = organizacionService.findOne(idOrg);
			Direccion direccion = organizacion.getDireccion();
			Municipio municipio = direccion.getMunicipio();
			SectorEmp sectorEmp = organizacion.getSectorEmp();
			
			System.out.println("********* UPDATE");
			
			organizacion.setNombreOrg(organizacionDireccionWrapper.getNombreOrg());
			organizacion.setSiglasOrg(organizacionDireccionWrapper.getSiglasOrg());
			organizacion.setRfcOrg(organizacionDireccionWrapper.getRfcOrg());
			organizacion.setNumTrabajadoresOrg(organizacionDireccionWrapper.getNumTrabajadoresOrg());
			organizacion.setTelefonoOrg(organizacionDireccionWrapper.getTelefonoOrg());
			organizacion.setWebOrg(organizacionDireccionWrapper.getWebOrg());
			organizacion.setGiroOrg(organizacionDireccionWrapper.getGiroOrg());
			
			direccion.setCalleDir(organizacionDireccionWrapper.getCalleDir());
			direccion.setNumExtDir(organizacionDireccionWrapper.getNumExtDir());
			direccion.setNumIntDir(organizacionDireccionWrapper.getNumIntDir());
			direccion.setColoniaDir(organizacionDireccionWrapper.getColoniaDir());
			direccion.setCpDir(organizacionDireccionWrapper.getCpDir());

			municipio = municipioService.findByIdMun(organizacionDireccionWrapper.getIdMun());
			
			sectorEmp = sectorEmpService.findByIdSE(organizacionDireccionWrapper.getSectorEmp());
			
			organizacionService.saveOrUpdate(organizacion, direccion, municipio, sectorEmp);
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("org_updated", null, Locale.getDefault()));
			
			return "redirect:/confirmscreen";
		}
	}
	
	
	/**
	 * Actualizar organización en específico
	 * @RequestMapping(value="/org/update", method=RequestMethod.POST)
	 * */
	@RequestMapping(value="/org/update", method=RequestMethod.POST)
	public String update(Model model, Principal principal, 
			@RequestParam(value = "idOrgParam", required=false) Integer idOrgParam) {
		
		int idOrg;
		
		System.out.println("parámetro: " + idOrgParam);
		
		if(idOrgParam==null) {
			idOrg = 0;
		}
		else {
			idOrg = idOrgParam;
		}
		
		if(idOrg == 0) {
			return "redirect:/orgform";
		}
		
		// Consulta los sectores empresariales dados de alta en base de datos
		List<SectorEmp> sectorEmpList = sectorEmpService.findAll();
		model.addAttribute("sectorEmpList", sectorEmpList);
		
		// Consulta los Estados dados de alta en base de datos
		List<Estado> estadoList = estadoService.findAll();
		model.addAttribute("estadoList", estadoList);
		
		//int idCli = currentUser.getIdCliente(principal);
		OrganizacionDireccionWrapper organizacionDireccionWrapper = new OrganizacionDireccionWrapper();
		
		Organizacion organizacion = organizacionService.findOne(idOrg);
		Direccion direccion = organizacion.getDireccion();
		Municipio municipio = direccion.getMunicipio();
		SectorEmp sectorEmp = organizacion.getSectorEmp();
		
		organizacionDireccionWrapper.setIdOrg(organizacion.getIdOrg());
		organizacionDireccionWrapper.setNombreOrg(organizacion.getNombreOrg());
		organizacionDireccionWrapper.setSiglasOrg(organizacion.getSiglasOrg());
		organizacionDireccionWrapper.setRfcOrg(organizacion.getRfcOrg());
		organizacionDireccionWrapper.setNumTrabajadoresOrg(organizacion.getNumTrabajadoresOrg());
		organizacionDireccionWrapper.setTelefonoOrg(organizacion.getTelefonoOrg());
		organizacionDireccionWrapper.setWebOrg(organizacion.getWebOrg());
		organizacionDireccionWrapper.setGiroOrg(organizacion.getGiroOrg());
		
		organizacionDireccionWrapper.setIdDir(direccion.getIdDir());
		organizacionDireccionWrapper.setCalleDir(direccion.getCalleDir());
		organizacionDireccionWrapper.setNumExtDir(direccion.getNumExtDir());
		organizacionDireccionWrapper.setNumIntDir(direccion.getNumIntDir());
		organizacionDireccionWrapper.setColoniaDir(direccion.getColoniaDir());
		organizacionDireccionWrapper.setCpDir(direccion.getCpDir());
		
		organizacionDireccionWrapper.setSectorEmp(sectorEmp.getIdSE());
		
		organizacionDireccionWrapper.setIdEstado(municipio.getEstado().getIdEst());
		organizacionDireccionWrapper.setIdMun(municipio.getIdMun());
		
		municipio = municipioService.findByIdMun(municipio.getIdMun());
		
		model.addAttribute("organizacionDireccionWrapper", organizacionDireccionWrapper);
		
		System.out.println("El objeto puesto en el modelo es: " + organizacionDireccionWrapper);
		
		return "organizacion_form";
	}
	
	
	/**
	 * Consultar los miembros que pertenecen a una organización en específico
	 * @RequestMapping(value="/org/querymembers", method=RequestMethod.POST)
	 * */
	@RequestMapping(value="/org/querymembers", method=RequestMethod.POST)
	public String viewOrgMembers(Model model, Principal principal, RedirectAttributes ra,
			@RequestParam(value = "param1", required=false) Integer idOrgParam, 
			@RequestParam(value = "param2", required=false) String actionView) {
		
		int idOrg;
		
		System.out.println("parámetro: " + idOrgParam);
		
		if(idOrgParam==null) {
			idOrg = 0;
		}
		else {
			idOrg = idOrgParam;
		}
		
		if(idOrg == 0) {
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("org_error", null, Locale.getDefault()));
			return "redirect:/confirmscreen";
		}
		
		// Consulta de miembros de una organización en específico sin importar statusOC, esto se filtra en la vista
		
		List<OrganizacionCliente> orgCliList = organizacionClienteService.findAllByIdOrg(idOrg);
		
		List<Cliente>clienteList = new ArrayList<Cliente>();
		List<String>cargoList = new ArrayList<String>();
		List<String>statusList = new ArrayList<String>();
		
		for(int i=0; i<orgCliList.size(); i++) {
			System.out.println("Elemento " + i + " de la lista: " + orgCliList.get(i).getCliente());
			clienteList.add(orgCliList.get(i).getCliente());
			System.out.println("Cargo: " + orgCliList.get(i).getCargoOC());
			cargoList.add(orgCliList.get(i).getCargoOC());
			System.out.println("Status: " + orgCliList.get(i).getStatusOC());
			statusList.add(orgCliList.get(i).getStatusOC());
		}
		
		model.addAttribute("idOrg", idOrg);
		model.addAttribute("clienteList", clienteList);
		model.addAttribute("cargoList", cargoList);
		model.addAttribute("statusList", statusList);
		model.addAttribute(Constants.MESSAGE1, messageSource.getMessage("query_empty", null, Locale.getDefault()));
		model.addAttribute("siglasOrg", organizacionService.findOne(idOrg).getSiglasOrg());
		
		// Atributo para que a vista sepa si mostrar exclusivamente la parte para cambiar el responsable
		if(actionView != null) {
			if(actionView.equals("orgrespsc")) {
				model.addAttribute("actionView", "orgrespsc");
			}
		}
		
		return "organizacion_members";
	}
	
	
	/**
	 * Desasociar a un miembro de una organización (Cambiar status en OrganizacionCliente a Borrado)
	 * @RequestMapping(value="/org/deletemember", method=RequestMethod.POST)
	 * */
	@RequestMapping(value="/org/deletemember", method=RequestMethod.POST)
	public String deleteMember(Model model, RedirectAttributes ra, HttpSession session) {
		
		
		
		int idCli = (int) session.getAttribute("idCli");
		int idOrg = (int) session.getAttribute("idOrg");
		
		session.removeAttribute("idCli");
		session.removeAttribute("idOrg");
		
		OrganizacionCliente orgCli = organizacionClienteService.findOneByIdOrgAndIdCli(idOrg, idCli);		
		Persona persona = clienteService.findOne(idOrg).getPersona();
		
		orgCli.setStatusOC(Constants.STATUS_DELETED);
		organizacionClienteService.save(orgCli);
		
		model.addAttribute(Constants.RESULT, messageSource.getMessage("org_cliente_delete_confirm", 
				new Object[]{persona.getNombrePer() + " " + persona.getApPatPer() + " " + persona.getApMatPer(), 
						organizacionService.findOne(idOrg).getNombreOrg()}, Locale.getDefault()));
		
		model.addAttribute(Constants.CUSTOM_MAPPING, "/org/querymembers");
		model.addAttribute(Constants.CONFIRM_BUTTON, "Regresar");
		model.addAttribute(Constants.PAGE_TITLE, "Eliminar miembro de " + organizacionService.findOne(idOrg).getSiglasOrg());
		model.addAttribute(Constants.PARAM1, idOrg);
		
		return "confirm";
	}
	
	
	/**
	 * Cambiar el responsable de una organización
	 * @RequestMapping(value="/org/orgresp", method=RequestMethod.POST)
	 * */
	@Transactional
	@RequestMapping(value="/org/orgresp", method=RequestMethod.POST)
	public String orgResp(Model model, RedirectAttributes ra, HttpSession session, 
			@RequestParam("param1") int idCli,  @RequestParam("param2") int idOrg) {
		
		OrganizacionCliente orgCliNewResp = organizacionClienteService.findOneByIdOrgAndIdCli(idOrg, idCli);		
		Persona persona = clienteService.findOne(idCli).getPersona();
		
		if(orgCliNewResp.getCargoOC().equals(Constants.ORG_RESPONSABLE)) {
			model.addAttribute(Constants.RESULT, messageSource.getMessage("org_cliente_alreadyresp", 
					new Object[]{persona.getNombrePer() + " " + persona.getApPatPer() + " " + persona.getApMatPer(), 
							organizacionService.findOne(idOrg).getNombreOrg()}, Locale.getDefault()));
		}
		else {
			/* Buscar responsable de antes y cambiarlo a Miembro */
			OrganizacionCliente orgCliOldResp = organizacionClienteService.findOneByIdOrgAndCargoResp(idOrg);
			
			/* Revisa si el objeto buscado es nulo. De ser así, significa que no existe un Responsable anterior, 
			   debido a que la Organización fue dada de alta por un administrador. */
			if(orgCliOldResp != null) {
				orgCliOldResp.setCargoOC(Constants.ORG_MEMBER);
				organizacionClienteService.save(orgCliOldResp);
			}
			
			// Hacer nuevo responsable al cliente seleccionado
			orgCliNewResp.setCargoOC(Constants.ORG_RESPONSABLE);
			organizacionClienteService.save(orgCliNewResp);
			
			model.addAttribute(Constants.RESULT, messageSource.getMessage("org_cliente_resp_confirm", 
					new Object[]{persona.getNombrePer() + " " + persona.getApPatPer() + " " + persona.getApMatPer(), 
							organizacionService.findOne(idOrg).getNombreOrg()}, Locale.getDefault()));
		}
		
		model.addAttribute(Constants.CUSTOM_MAPPING, "/org/querymembers");
		model.addAttribute(Constants.CONFIRM_BUTTON, "Regresar");
		model.addAttribute(Constants.PAGE_TITLE, "Cambiar Responsable de " + organizacionService.findOne(idOrg).getSiglasOrg());
		model.addAttribute(Constants.PARAM1, idOrg);
		
		return "confirm";
	}
	
	
	/**
	 * Controlador para mostrar pantalla de confirmación para cambiar datos en relación OrganizacionCliente
	 * @RequestMapping("/org/{actionParam}membersc")
	 * */
	@RequestMapping("/org/{actionParam}membersc")
	public String actionScreen(Model model, HttpSession session, RedirectAttributes ra, Principal principal,
			@RequestParam("idOrgParam") int idOrg, @RequestParam("idCliParam") int idCli, 
			@PathVariable("actionParam") String action) {
		
		Persona persona = clienteService.findOne(idCli).getPersona();
		
		System.out.println("Parámetros: " + idOrg + ", " + idCli);
		
		session.setAttribute("idOrg", idOrg);
		session.setAttribute("idCli", idCli);
		
		// Borrar cliente de organización
		if(action.equals("delete")) {
			
			OrganizacionCliente orgCli = organizacionClienteService.findOneByIdOrgAndIdCli(idOrg, idCli);
			
			// Revisa si el cliente a borrar es el responsable de la organización. De ser así, no permite su eliminación
			if(orgCli.getCargoOC().equals(Constants.ORG_RESPONSABLE)) {
				model.addAttribute(Constants.MESSAGE1, messageSource.getMessage("org_cliente_responsable", null, Locale.getDefault()));
				model.addAttribute(Constants.CUSTOM_MAPPING, "/org/querymembers");
				model.addAttribute(Constants.CONFIRM_BUTTON, "Regresar");
				model.addAttribute(Constants.PAGE_TITLE, "No se puede eliminar responsable");
				model.addAttribute(Constants.PARAM1, idOrg);
			}
			// En caso de no ser responsable, procede a mostrar confirmación de eliminación
			else {
				model.addAttribute(Constants.RESULT, messageSource.getMessage("org_cliente_delete", 
						new Object[]{persona.getNombrePer() + " " + persona.getApPatPer() + " " + persona.getApMatPer(), 
								organizacionService.findOne(idOrg).getNombreOrg()}, Locale.getDefault()));
				model.addAttribute(Constants.CUSTOM_MAPPING, "/org/deletemember");
				model.addAttribute(Constants.CONFIRM_BUTTON, "Eliminar");
				model.addAttribute(Constants.PAGE_TITLE, "Eliminar usuario");
			}
		}
		
		// Volver a mandar invitación a cliente ya borrado
		else if(action.equals("reinvite")) {
			model.addAttribute(Constants.RESULT, messageSource.getMessage("org_cliente_reinvite", 
					new Object[]{persona.getNombrePer() + " " + persona.getApPatPer() + " " + persona.getApMatPer(), 
							organizacionService.findOne(idOrg).getNombreOrg()}, Locale.getDefault()));
			model.addAttribute(Constants.CUSTOM_MAPPING, "/org/sendinvite");
			model.addAttribute(Constants.CONFIRM_BUTTON, "Enviar Invitación");
			model.addAttribute(Constants.PAGE_TITLE, "Reactivar usuario");
			model.addAttribute(Constants.PARAM1, persona.getCurpPer());
			model.addAttribute(Constants.PARAM2, idOrg);
		}
		
		// Cambiar responsable de la organización
		else if(action.equals("resp")) {
			model.addAttribute(Constants.RESULT, messageSource.getMessage("org_cliente_newresp", 
					new Object[]{persona.getNombrePer() + " " + persona.getApPatPer() + " " + persona.getApMatPer(), 
							organizacionService.findOne(idOrg).getNombreOrg()}, Locale.getDefault()));
			model.addAttribute(Constants.CUSTOM_MAPPING, "/org/orgresp");
			model.addAttribute(Constants.CONFIRM_BUTTON, "Cambiar Responsable");
			model.addAttribute(Constants.PAGE_TITLE, "Cambio de Responsable");
			model.addAttribute(Constants.PARAM1, idCli);
			model.addAttribute(Constants.PARAM2, idOrg);
		}
		return "confirm";
	}
	
	
	/**
	 * Controlador para consulta de las organizaciones dadas de alta por el cliente identificado en el sistema
	 * 
	 *  - queryall: Devuelve todas las organizaciones
	 *  - queryown: Consulta todas las organizaciones dadas de alta por el cliente identificado en el sistema
	 * @RequestMapping("/org/query{searchParam}/{index}")
	 */
	@RequestMapping("/org/query{searchParam}/{index}")
	public String queryOwn(Model model,
			HttpSession session, Principal principal, @PathVariable("index") int index, 
			@PathVariable("searchParam") String searchParam) {
		
		if(searchParam.equals(null)) { searchParam = "all";}
		
		Page<Organizacion> page = null;
		
		// Búsqueda de organizaciones propias
		if(searchParam.equals("own")) {
			
			int idCli = currentUser.getIdCliente(principal);
			Cliente cliente = clienteService.findOne(idCli);
			page = organizacionService.getPageByIdCli(cliente.getIdCli(), index);
			model.addAttribute("searchParam", "own");
			model.addAttribute(Constants.PAGE_TITLE, "Buscar Organizaciones Propias");
		}
		
		// Búsqueda de todas las organizaciones
		if(searchParam.equals("all")) {
			page = organizacionService.findAllPage(index);
			model.addAttribute("searchParam", "all");
			model.addAttribute(Constants.PAGE_TITLE, "Consultar Organizaciones");
		}

		
		int currentIndex = page.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
		
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("orgList", page.getContent());
		
		
		model.addAttribute(Constants.SHOW_PAGES, true);
		session.setAttribute(Constants.SHOW_PAGES_FROM_SEARCH, false);	
		
		return"organizacion_query";
	}
	
	
	/**
	 * Controlador para mostrar formulario búsqueda de Organizacion
	 * @RequestMapping("/org/searchform")
	 * */
	@RequestMapping("/org/searchform")
	public String showOrgSearch(Model model) {
		
		model.addAttribute(Constants.PAGE_TITLE, "Búsqueda de Organizaciones");
		model.addAttribute(Constants.CUSTOM_MAPPING, "/org/search");
		model.addAttribute(Constants.JSON_SERVER, "/json/search/org");
		
		return "search";
	}
	
	
	/**
	 * Paginación como resultado de la búsqueda
	 * @RequestMapping(value="/org/search/{index}")
	 * */
	@RequestMapping(value="/org/search/{index}")
	public String searchPages(@PathVariable("index") int index,
			HttpSession session, Model model) {
		
		String searchKeyword = (String) session.getAttribute(Constants.SEARCH_KEYWORD);
		
		Page<Organizacion> page = organizacionService.findBySiglasOrgLikeOrNombreOrgLikePage(index - 1, "%" + searchKeyword + "%");
		System.out.println("/org/search/" + index  + " ****** " + page.getTotalElements());
		
		int currentIndex = page.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
		
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("orgList", page.getContent());
		
		model.addAttribute(Constants.SHOW_PAGES, false);
		session.setAttribute(Constants.SHOW_PAGES_FROM_SEARCH, true);
		model.addAttribute(Constants.PAGE_TITLE, messageSource.getMessage("org_query_all", null, Locale.getDefault()));
		model.addAttribute(Constants.RESULT, messageSource.getMessage("search_result", null, Locale.getDefault()));
		model.addAttribute(Constants.MESSAGE1, messageSource.getMessage("search_matches", new Object[]{searchKeyword}, Locale.getDefault()));
		
		return "organizacion_query";
	}
	
	
	/**
	 * Controlador para mostrar los resultados de la búsqueda de un administador
	 * @RequestMapping(value="/org/search", method=RequestMethod.POST)
	 * */
	@RequestMapping(value="/org/search", method=RequestMethod.POST)
	public String search(@RequestParam("busqueda") String  busqueda,
			RedirectAttributes ra, Model model, HttpSession session) {
		
		System.out.println("Parámetro de búsqueda: " + busqueda);
		
		List<Organizacion> listo = organizacionService.findBySiglasOrgLikeOrNombreOrgLike("%" + busqueda + "%");
		System.out.println("Tamaño de la listilla: " + listo.size());
		System.out.println("Listilla: " + listo);
				
		Page<Organizacion> page = organizacionService.findBySiglasOrgLikeOrNombreOrgLikePage(0, "%" + busqueda + "%");
		System.out.println("/org/search ***** " + page.getTotalElements());
		
		model.addAttribute(Constants.PAGE_TITLE, messageSource.getMessage("org_query_all", null, Locale.getDefault()));
		model.addAttribute("busqueda", busqueda);
		
		if(page.getTotalElements() > 0){
			
			int currentIndex = page.getNumber() + 1;
			int beginIndex = Math.max(1, currentIndex - 5);
			int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
			
			model.addAttribute("beginIndex",beginIndex);
			model.addAttribute("endIndex",endIndex);
			model.addAttribute("currentIndex",currentIndex);
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("orgList", page.getContent());
			
			model.addAttribute(Constants.SHOW_PAGES, false);
			session.setAttribute(Constants.SHOW_PAGES_FROM_SEARCH, true);
			session.setAttribute(Constants.SEARCH_KEYWORD, busqueda);
			model.addAttribute(Constants.RESULT, messageSource.getMessage("search_result", null, Locale.getDefault()));
			model.addAttribute(Constants.MESSAGE1, messageSource.getMessage("search_matches", new Object[]{busqueda}, Locale.getDefault()));
			
			return "organizacion_query";
		}
		else{
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("org_not_found", 
					new Object[]{busqueda}, Locale.getDefault()));
			
				return "redirect:/org/searchform";
		}		
	}
	
	
	/**
	 * Controlador para enviar correo electrónico de invitación a unirse a empresa.
	 @RequestMapping("/org/sendinvite")
	 * */
	@RequestMapping("/org/sendinvite")
	public String sendInviteMail(@RequestParam("param1") String curpPer, @RequestParam("param2") int idOrg,
			HttpServletRequest request, Model model, RedirectAttributes ra) {
		
		// Revisar si el CURP tiene una o más cuentas de usuario asociada
		
		Cliente cliente = clienteService.findByCurpPer(curpPer);
		if(cliente != null) {
						
			// Se almacena la relación entre la organización y la persona invitada, con un estado pendiente de aprobar.
						
			
				
			Organizacion organizacion = organizacionService.findOne(idOrg);
			
			// Genera un nuevo pass para passOC
			List<Cliente> clienteList = clienteService.findAll();
			List<OrganizacionCliente> orgCliList = organizacionClienteService.findAll();
			boolean passUsed = false;
			String passOC;
			do {
				passUsed = false;
				passOC = PassGen.generatePass();
				System.out.println("\nPass generado: " + passOC);
				for(Cliente cli : clienteList) {
		            if(passOC.equals(cli.getPassAreaCli())) {
		            	System.out.println("La contraseña ya existe");
		            	passUsed = true;
		            }
		        }
				for(OrganizacionCliente orgClie : orgCliList) {
		            if(passOC.equals(orgClie.getPassOC())) {
		            	System.out.println("La contraseña ya existe");
		            	passUsed = true;
		            }
		        }
				System.out.println(passUsed);
			}
			while (passUsed == true);
			
			System.out.println("El objeto cliente ingresado y guardado: " + cliente);
			System.out.println("La organización de idOrgParam es: " + organizacion);
			OrganizacionCliente orgCli = new OrganizacionCliente();
			orgCli.setOrganizacion(organizacion);
			orgCli.setCliente(cliente);
			orgCli.setCargoOC(Constants.ORG_MEMBER);
			orgCli.setPassOC(passOC);
			orgCli.setStatusOC(Constants.STATUS_PENDING);
			orgCli.setFhCreaOC(new Timestamp(new Date().getTime()));
			
			organizacionClienteService.save(orgCli);
				
			String email = cliente.getPersona().getEmailPer();
			// Enviar correo electrónico de invitación para ser miembro de una organización
			mailManager.sendOrgMemberInvite(email, 
					util.getRootUrl(request, "/org/sendinvite")
							.concat("/org/")
							.concat(email)
							.concat("/")
							.concat(Integer.toString(idOrg))
							.concat("/invite/confirm"), organizacionService.findOne(idOrg).getNombreOrg()
						);
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("invite_mail_sent", null, Locale.getDefault()));
		}
		else{
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("invite_account_not_exists", null, Locale.getDefault()));
		}
		return "redirect:/confirmscreen";
	}
	
	
	/**
	 * Confirmación de asociación de cliente existente a organización, enviado desde la URL en el email de confirmación de cuenta
	 * @RequestMapping("/org/{emailCli}/{idOrg}/invite/confirm")
	 * */
	@RequestMapping("/org/{emailCli}/{idOrg}/invite/confirm")
	public String inputPassword(HttpSession session, Model model,  
			@PathVariable("emailCli") String emailCli, @PathVariable("idOrg") int idOrg, 
			RedirectAttributes ra) {
		
		Cliente cliente = clienteService.findByEmail(emailCli);
		OrganizacionCliente orgCli = organizacionClienteService.findOneByIdOrgAndIdCli(idOrg, cliente.getIdCli());
		
		if(orgCli != null) {
			if(orgCli.getStatusOC().equals(Constants.STATUS_PENDING)) {
				
				orgCli.setStatusOC(Constants.STATUS_ACTIVE);
				organizacionClienteService.save(orgCli);
				ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("org_member_confirmed", 
						new Object[]{organizacionService.findOne(idOrg).getNombreOrg()}, Locale.getDefault()));
			}
			else{
				ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("org_member_was_confirmed", 
						null, Locale.getDefault()));	
			}
		}
		else {
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("data_input_error", 
					null, Locale.getDefault()));
		}
		return "redirect:/confirmscreen";
	}
	
	
	/**
	 * Servidor JSON para búsqueda de Organizaciones
	 * @RequestMapping(value="/json/search/org", produces="application/json")
	 * */
	@RequestMapping(value="/json/search/org", produces="application/json")
	@ResponseBody
	public Map<String, Object> findOrganizacion(@RequestParam("term") String term) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		List<Organizacion> organizacionList1 = organizacionService.findBySiglasOrgLike("%" + term + "%");
		List<Organizacion> organizacionList2 = organizacionService.findByNombreOrgLike("%" + term + "%");
		
		
		for (int j = 0; j < organizacionList1.size(); j++) {
			Organizacion org = organizacionList1.get(j);
			map.put(String.valueOf(org.getIdOrg()),
							org.getSiglasOrg());
		}
		for (int j = 0; j < organizacionList2.size(); j++) {
			Organizacion org = organizacionList2.get(j);
			map.put(String.valueOf(org.getIdOrg()) + organizacionList2.size(),
							org.getNombreOrg());
		}
		
		System.out.println(map.size());
		System.out.println(map);
		
		return map;
	}
}