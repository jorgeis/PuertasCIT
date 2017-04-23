package com.citnova.sca.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.citnova.sca.domain.Area;
import com.citnova.sca.domain.Estado;
import com.citnova.sca.domain.Gratuito;
import com.citnova.sca.domain.Organizacion;
import com.citnova.sca.domain.SectorEmp;
import com.citnova.sca.service.AreaService;
import com.citnova.sca.service.EstadoService;
import com.citnova.sca.service.GratuitoService;
import com.citnova.sca.service.OrganizacionService;
import com.citnova.sca.service.SectorEmpService;
import com.citnova.sca.util.Constants;

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
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private GratuitoService gratuitoService;

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
	public String redirectGratuitoOrg(Model model, RedirectAttributes ra, Gratuito gratuito, 
			@RequestParam("siglasOrg") String siglasOrg, @RequestParam("idArea") int idArea, 
			@RequestParam("fInicioEveGra") String fInicioEveGra, @RequestParam("hInicioEveGra") String hInicioEveGra,
			@RequestParam("hFinEveGra") String hFinEveGra, 
			@RequestParam("nombreOrg") String nombreOrg, HttpServletRequest request) {
		
		// Consulta los Estados dados de alta en base de datos
		List<Estado> estadoList = estadoService.findAll();
		model.addAttribute("estadoList", estadoList);
		
		// Coloca la fecha y hora en formato correcto y los introduce al objeto Gratuito
		Timestamp fhInicioEveGra = null;
		Timestamp fhFinEveGra = null;
		try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		    Date parsedDate = dateFormat.parse(fInicioEveGra + " " + hInicioEveGra);
		    fhInicioEveGra = new Timestamp(parsedDate.getTime());
		    parsedDate = dateFormat.parse(fInicioEveGra + " " + hFinEveGra);
		    fhFinEveGra = new Timestamp(parsedDate.getTime());
		}catch(Exception e){
			System.out.println("Error al procesar la fecha");
		}
		
		gratuito.setFhInicioEveGra(fhInicioEveGra);
		gratuito.setFhFinEveGra(fhFinEveGra);
		gratuito.setArea(areaService.findByIdArea(idArea));
		
		// Revisa si los datos ingresados de Organización coinciden con algun registro en base de datos. 
		// Si coinciden, automáticamente coloca los datos de esa organización. De lo contrario solicita al usuario
		// ingresar los datos de la nueva Organización
		
		// A revisar: El método del servicio trae una lista, pero en teoría debe de traer solo una organizacion. 
		// Puede existir mas de una organización con siglas y/o nombre iguales?
		List<Organizacion> orgList = organizacionService.findBySiglasOrgAndNombreOrg(siglasOrg, nombreOrg);
		
		if(orgList.size() != 0) {
			Organizacion org = orgList.get(0);
			gratuito.setOrganizacion(org);
			gratuito.setStatusGra(Constants.STATUS_PENDING);
			gratuitoService.save(gratuito);
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("gratuito_saved", null, Locale.getDefault()));
			return "redirect:/confirmscreen";
		}
		else {
			System.out.println("Se llegó hasta aqui");
			ra.addFlashAttribute("siglasOrg", siglasOrg);
			ra.addFlashAttribute("nombreOrg", nombreOrg);
			ra.addFlashAttribute("gratuito", gratuito);
			return "redirect:/orgformgra";
		}
	}
	
	

	/**
	 * Servidor JSON que devuelve las horas que ya están ocupadas por reservaciones de Gratuito
	 * @RequestMapping(value="/json/search/daygratuito, produces="application/json")
	 * */
	@RequestMapping(value="/json/search/daygratuito", produces="application/json")
	@ResponseBody
	public Map<String, Object> findGratuitoByDay(@RequestParam("term") String term) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		int index = 0;
		List<Gratuito> gratuitoList = gratuitoService.findByDay(term);
		
		for (int j = 0; j < gratuitoList.size(); j++) {
			Gratuito gratuito = gratuitoList.get(j);
			
			Calendar inicio = Calendar.getInstance();
			inicio.setTime(gratuito.getFhInicioEveGra());
			
			Calendar fin = Calendar.getInstance();
			fin.setTime(gratuito.getFhFinEveGra());
			
			for(int i=inicio.get(Calendar.HOUR_OF_DAY); i<fin.get(Calendar.HOUR_OF_DAY); i++) {
				map.put(String.valueOf(index), i);
				index++;
			}
		}
		
		System.out.println("Tamaño del mapa JSON: " + map.size());
		System.out.println("Cadena JSON: " + map);
		
		return map;
	}
	
	
	
	/**
	 * Servidor JSON para búsqueda de reservaciones e Gratuito por día
	 * @RequestMapping(value="/json/search/daygratuito, produces="application/json")
	 * */
	@RequestMapping(value="/json/search/daygratuitor", produces="application/json")
	@ResponseBody
	public Map<String, Object> findGratuitoByDayR(@RequestParam("term") String term) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		List<Gratuito> gratuitoList = gratuitoService.findByDay(term);
		
		for (int j = 0; j < gratuitoList.size(); j++) {
			Gratuito gratuito = gratuitoList.get(j);
			
			Calendar inicio = Calendar.getInstance();
			inicio.setTime(gratuito.getFhInicioEveGra());
			
			Calendar fin = Calendar.getInstance();
			fin.setTime(gratuito.getFhFinEveGra());
			
			System.out.println("Hora de inicio: " + inicio.get(Calendar.HOUR_OF_DAY));
			System.out.println("Hora de fin: " + fin.get(Calendar.HOUR_OF_DAY));
			
			map.put(String.valueOf(gratuito.getIdGra()),
					inicio.get(Calendar.HOUR_OF_DAY) + "?" + fin.get(Calendar.HOUR_OF_DAY));
		}
		
		System.out.println("Tamaño del mala JSON: " + map.size());
		System.out.println("Cadena JSON: " + map);
		
		return map;
	}
	
	
}