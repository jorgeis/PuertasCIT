package com.citnova.sca.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citnova.sca.domain.Area;
import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Direccion;
import com.citnova.sca.domain.Estado;
import com.citnova.sca.domain.Gratuito;
import com.citnova.sca.domain.Municipio;
import com.citnova.sca.domain.Organizacion;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.domain.SectorEmp;
import com.citnova.sca.service.AreaService;
import com.citnova.sca.service.EstadoService;
import com.citnova.sca.service.OrganizacionService;
import com.citnova.sca.service.SectorEmpService;
import com.citnova.sca.util.Constants;
import com.citnova.sca.util.PassGen;

@Controller
public class GratuitoController {
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private SectorEmpService sectorEmpService;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private OrganizacionService organizacionService;
	

	/**
	 * Formulario de solicitud de reserva de espacio gratuito
	 * */
	@RequestMapping(value="/gratuitoform")
	public String showGratuitoForm(Model model) {

		// Consulta las áreas dadas de alta como gratuitas y que estén activas
		List<Area> areaGratuitaList = areaService.findAreaGratuitaActiva();
		model.addAttribute("areaGratuitaList", areaGratuitaList);
		
		// Consulta los sectores empresariales dados de alta en base de datos
		List<SectorEmp> sectorEmpList = sectorEmpService.findAll();
		model.addAttribute("sectorEmpList", sectorEmpList);
		
		// Consulta los Estados dados de alta en base de datos
		List<Estado> estadoList = estadoService.findAll();
		model.addAttribute("estadoList", estadoList);
		
		model.addAttribute("gratuito", new Gratuito());
		
		return "gratuito_form";
	}
	
	
	
	/**
	 * Guardar nueva solicitud de reserva de espacio gratuito en BD
	 * 
	 * */
	@RequestMapping(value="/gratuitorg", method=RequestMethod.POST)
	public String createCliente(Model model, RedirectAttributes ra, Gratuito gratuito, HttpSession session, 
			@RequestParam("siglasOrg") String siglasOrg, 
			@RequestParam("nombreOrg") String nombreOrg, HttpServletRequest request) {
		
		// Consulta los Estados dados de alta en base de datos
		List<Estado> estadoList = estadoService.findAll();
		model.addAttribute("estadoList", estadoList);
		
		// Revisa si los datos ingresados de Organización coinciden con algun registro en base de datos. 
		// Si coinciden, automáticamente coloca los datos de esa organización. De lo contrario solicita al usuario
		// ingresar los datos de la nueva Organización
		
		// A revisar: El método del servicio trae una lista, pero en teoría debe de traer solo una organizacion. 
		// Puede existir mas de una organización con siglas y/o nombre iguales?
		Organizacion organizacion = organizacionService.findBySiglasOrgAndNombreOrg(siglasOrg, nombreOrg).get(0);
		
		if(organizacion != null) {
			gratuito.setOrganizacion(organizacion);
			return "redirect:/";
		}
		else {
			return "redirect:/orgform";
		}
	}
}