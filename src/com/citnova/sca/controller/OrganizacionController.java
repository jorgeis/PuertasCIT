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
		
		Cliente cliente = clienteService.findOne(currentUser.getIdCliente(principal));
		int idOrg = organizacionDireccionWrapper.getIdOrg();
		//
		// Guardar nuevo registro
		//
		
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
			
			// Guardar los datos a través del servicio
			organizacionService.saveOrUpdate(organizacion, direccion, municipio, sectorEmp);
			
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("org_saved", null, Locale.getDefault()));
			
			
			System.out.println("El cliente es: " + cliente);
			
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
			orgCli.setCargoOC("Responsable");
			orgCli.setPassOC(passOC);
			orgCli.setStatusOC("Activo");
			orgCli.setFhCreaOC(new Timestamp(new Date().getTime()));
			
			organizacionClienteService.save(orgCli);
				
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
	 * Actualizar organización  en específico
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
	public String viewOrg(Model model, Principal principal, RedirectAttributes ra,
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
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("org_error", null, Locale.getDefault()));
			return "redirect:/confirmscreen";
		}
		
		List<OrganizacionCliente> orgCliList = organizacionClienteService.findByIdOrg(idOrg);
		
		List<Cliente>clienteList = new ArrayList<Cliente>();
		List<String>cargoList = new ArrayList<String>();
		
		for(int i=0; i<orgCliList.size(); i++) {
			System.out.println("Elemento " + i + " de la lista: " + orgCliList.get(i).getCliente());
			clienteList.add(orgCliList.get(i).getCliente());
			System.out.println("Elemento " + i + " de la lista: " + orgCliList.get(i).getCargoOC());
			cargoList.add(orgCliList.get(i).getCargoOC());
		}
		
		model.addAttribute("idOrg", idOrg);
		model.addAttribute("clienteList", clienteList);
		model.addAttribute("cargoList", cargoList);
		model.addAttribute("siglasOrg", organizacionService.findOne(idOrg).getSiglasOrg());
		
		return "organizacion_members";
	}
	
	
	
	/**
	 * Controlador para consulta de las organizaciones dadas de alta por el cliente identificado en el sistema
	 * @RequestMapping("/org/queryown/{index}")
	 */
	@RequestMapping("/org/queryown/{index}")
	public String queryAll(Model model,
			HttpSession session, Principal principal, @PathVariable("index") int index) {
		
		int idCli = currentUser.getIdCliente(principal);
		
		Cliente cliente = clienteService.findOne(idCli);
		Page<Organizacion> page = organizacionService.getPageByIdCli(cliente.getIdCli(), index);
		List<Organizacion> list = organizacionService.findByIdCli(idCli);
		
		System.out.println("El tamaño de la página es: " + page.getSize());
		System.out.println(page.getContent());
		
		System.out.println("\n El tamaño de la lista es: " + list.size());
		System.out.println(list);
		
		int currentIndex = page.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
		
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("orgList", page.getContent());
		model.addAttribute("searchParam", "own");
		
		model.addAttribute(Constants.SHOW_PAGES, true);
		session.setAttribute(Constants.SHOW_PAGES_FROM_SEARCH, false);
		
		return"organizacion_query";
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
			orgCli.setCargoOC("Miembro");
			orgCli.setPassOC(passOC);
			orgCli.setStatusOC("Pendiente");
			orgCli.setFhCreaOC(new Timestamp(new Date().getTime()));
			
			organizacionClienteService.save(orgCli);
				
			String email = cliente.getPersona().getEmailPer();
			// Enviar correo electrónico de invitación para ser miembro de una organización
			mailManager.sendOrgMemberInvite(email, 
					util.getRootUrl(request, "/org/sendinvite")
							.concat("/org/")
							.concat(email)
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
	 * @RequestMapping("/clienteaccount/{idCli}/confirm")
	 * */
	@RequestMapping("/org/{emailCli}/{idOrg}/invite/confirm")
	public String inputPassword(HttpSession session, Model model,  
			@PathVariable("emailCli") String emailCli, @PathVariable("idOrg") int idOrg, 
			RedirectAttributes ra) {
		
		Cliente cliente = clienteService.findByEmail(emailCli);
		OrganizacionCliente orgCli = organizacionClienteService.findOneByIdOrgAndIdCli(idOrg, cliente.getIdCli());
		
		if(orgCli.getStatusOC().equals(Constants.STATUS_PENDING)) {
			
			orgCli.setStatusOC(Constants.STATUS_MUST_ACTIVATE);
			organizacionClienteService.save(orgCli);
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("org_member_confirmed", 
					new Object[]{organizacionService.findOne(idOrg).getNombreOrg()}, Locale.getDefault()));
			return "redirect:/confirmscreen";
		}
		
		else{
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("org_member_was_confirmed", null, Locale.getDefault()));
			return "redirect:/confirmscreen";
		}
	}
	
	
	/**
	 * Servidor JSON para búsqueda de Organizaciones
	 * @RequestMapping(value="/json/search/siglasorg", produces="application/json")
	 * */
	@RequestMapping(value="/json/search/siglasorg", produces="application/json")
	@ResponseBody
	public Map<String, Object> findOrganizacion(@RequestParam("term") String term) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		List<Organizacion> organizacionList = organizacionService.findBySiglasOrgLike("%" + term + "%");
		
		for (int j = 0; j < organizacionList.size(); j++) {
			Organizacion org = organizacionList.get(j);
			map.put(String.valueOf(org.getIdOrg()),
							org.getSiglasOrg());
		}
		
		System.out.println(map.size());
		System.out.println(map);
		
		return map;
	}
}