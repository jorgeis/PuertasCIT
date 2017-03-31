package com.citnova.sca.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.citnova.sca.util.PassGen;

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
	MunicipioService municipioService;
	
	@Autowired
	private MessageSource messageSource;
	
	private Timestamp time = new Timestamp(new Date().getTime());
	

	/**
	 * Formulario de alta de organización
	 * */
	@RequestMapping(value="/orgform")
	public String showOrganizacionForm(Model model) {
		
		// Consulta los sectores empresariales dados de alta en base de datos
		List<SectorEmp> sectorEmpList = sectorEmpService.findAll();
		model.addAttribute("sectorEmpList", sectorEmpList);
		
		// Consulta los Estados dados de alta en base de datos
		List<Estado> estadoList = estadoService.findAll();
		model.addAttribute("estadoList", estadoList);
		
		int idCli = 0;
		
		if(model.asMap().get("idCli") != null){
			idCli = (int) model.asMap().get("idCli");
		}
		model.addAttribute("idCli", idCli);
		
		model.addAttribute("organizacionDireccionWrapper", new OrganizacionDireccionWrapper());
		
		return "organizacion_form";
	}
	
	
	
	/**
	 * Formulario de solicitud datos de organización de reserva de espacio gratuito
	 * */
	@RequestMapping(value="/orgformgra")
	public String showOrgGratuitoForm(Model model) {
		
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
	 * */
	@RequestMapping(value="/orgsave", method=RequestMethod.POST)
	public String createOrganizacion(Model model, OrganizacionDireccionWrapper organizacionDireccionWrapper, 
			@RequestParam("idCli") int idCliente, HttpServletRequest request, RedirectAttributes ra) {
		
		int idOrg = organizacionDireccionWrapper.getIdOrg();
		//
		// Guardar nuevo registro
		//
		if(idOrg == 0){
			System.out.println("********* SAVE ORGANIZACION");
	
			// Guardar nuevo registro
			
			Organizacion organizacion = new Organizacion(organizacionDireccionWrapper.getNombreOrg(), 
					organizacionDireccionWrapper.getSiglasOrg(),
					organizacionDireccionWrapper.getRfcOrg(),
					organizacionDireccionWrapper.getNumTrabajadoresOrg(),
					organizacionDireccionWrapper.getTelefonoOrg(),
					organizacionDireccionWrapper.getWebOrg(),
					organizacionDireccionWrapper.getGiroOrg());
			
			Direccion direccion = new Direccion(organizacionDireccionWrapper.getCalleDir(),
					organizacionDireccionWrapper.getNumExtDir(),
					organizacionDireccionWrapper.getNumIntDir(),
					organizacionDireccionWrapper.getColoniaDir(),
					organizacionDireccionWrapper.getCpDir());
			
			Municipio municipio = municipioService.findByIdMun(organizacionDireccionWrapper.getIdMun());
			
			SectorEmp sectorEmp = sectorEmpService.findByIdSE(organizacionDireccionWrapper.getSectorEmp());
			
			// Revisa una la persona ya existe con el mismo email
			
//			Persona persona2 = personaService.findByEmailPer(persona.getEmailPer());
//			if(persona2 != null){
//				model.addAttribute(Constants.RESULT, messageSource.getMessage("client_exists", null, Locale.getDefault()));
//				return "/cliente_form";
//			}
			
			System.out.println(organizacion);
			System.out.println(direccion);
			
			// Guardar los datos a través del servicio
			organizacionService.saveOrUpdate(organizacion, direccion, municipio, sectorEmp);
			
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("org_saved", null, Locale.getDefault()));
			
			
			if(idCliente != 0) { // Se da de alta una organización para un cliente ya existente.
				System.out.println("El id del cliente es: " + idCliente);
				
				// Genera un nuevo pass para passArea
				List<Cliente> clienteList = clienteService.findAll();
				List<OrganizacionCliente> orgCliList = organizacionClienteService.findAll();
				boolean passUsed;
				String passOC;
				do {
					passUsed = false;
					passOC = PassGen.generatePass();
					System.out.println("\nPass generado: " + passOC);
					for(Cliente cliente : clienteList) {
			            if(passOC.equals(cliente.getPassAreaCli())) {
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
				
				OrganizacionCliente orgCli = new OrganizacionCliente("Activo", "Responsable", passOC);
				
			}
			else {
				System.out.println("En save no hay cliente");
			}
			
			
			return "redirect:/cliente/queryall/1";
			
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
			
			return "redirect:/cliente/queryall/1";
		}
	}
	
	
	
	/**
	 * Servidor JSON para búsqueda de Organizaciones
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