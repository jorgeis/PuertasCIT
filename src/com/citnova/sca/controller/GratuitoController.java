package com.citnova.sca.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.citnova.sca.domain.Area;
import com.citnova.sca.domain.Estado;
import com.citnova.sca.domain.Gratuito;
import com.citnova.sca.domain.Organizacion;
import com.citnova.sca.domain.SectorEmp;
import com.citnova.sca.service.AdminService;
import com.citnova.sca.service.AreaService;
import com.citnova.sca.service.EstadoService;
import com.citnova.sca.service.GratuitoService;
import com.citnova.sca.service.OrganizacionService;
import com.citnova.sca.service.SectorEmpService;
import com.citnova.sca.util.Constants;
import com.citnova.sca.util.CurrentSessionUserRetriever;
import com.citnova.sca.util.MailManager;

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
	@Autowired
	private CurrentSessionUserRetriever currentUser;
	@Autowired
	private AdminService adminService;
	@Autowired
	private MailManager mailManager;
	
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
	 * @RequestMapping(value="/gratuitorg", method=RequestMethod.POST)
	 * 
	 * */
	@RequestMapping(value="/gratuitorg", method=RequestMethod.POST)
	public String saveOrRedirectToOrgGratuitoOrg(Model model, RedirectAttributes ra, Gratuito gratuito, 
			@RequestParam("siglasOrg") String siglasOrg, @RequestParam("idArea") int idArea, 
			@RequestParam("fInicioEveGra") String fInicioEveGra, @RequestParam("hInicioEveGra") String hInicioEveGra,
			@RequestParam("hFinEveGra") String hFinEveGra, 
			@RequestParam("nombreOrg") String nombreOrg, 
			@RequestParam(value = "idAd", required=false) Integer idAdParam,
			HttpServletRequest request) {
		
		int idAd;
		
		if(idAdParam==null) {
			idAd = 0;
		}
		else {
			idAd = idAdParam;
		}
		
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
		
		// Si existe un idAd, significa que un administrador está actualizando, por lo que no se les dan los valores
		// iniciales a status y decisión
		if(idAd != 0) {
			gratuito.setAdmin(adminService.findOne(idAd));
		} else {
			gratuito.setStatusGra(Constants.TO_BE_PERFORMED);
			gratuito.setDecisionGra(Constants.STATUS_PENDING);
		}
		
		// Revisa si los datos ingresados de Organización coinciden con algun registro en base de datos. 
		// Si coinciden, automáticamente coloca los datos de esa organización. De lo contrario solicita al usuario
		// ingresar los datos de la nueva Organización
		
		// A revisar: El método del servicio trae una lista, pero en teoría debe de traer solo una organizacion. 
		// Puede existir mas de una organización con siglas y/o nombre iguales?
		List<Organizacion> orgList = organizacionService.findBySiglasOrgAndNombreOrg(siglasOrg, nombreOrg);
		
		if(orgList.size() != 0) {
			Organizacion org = orgList.get(0);
			gratuito.setOrganizacion(org);
			gratuitoService.save(gratuito);
			if(idAd != 0) {
				ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("gratuito_desition", null, Locale.getDefault()));
				
				// Enviar correo de confirmación de resultado de espacio gratuito
				mailManager.sendEmailConfirmaReservacionGratuita(gratuito.getEmailUsrGra(), gratuito);
				
			} else {
				ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("gratuito_saved", null, Locale.getDefault()));
				
				// Enviar correo de confirmación de reservación de espacio gratuito
				mailManager.sendEmailReservacionGratuita(gratuito.getEmailUsrGra(), gratuito);
			}
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
	 * Controlador para realizar la búsqueda de reservacion de espacio gratuito en función del parámetro ingresado.
	 * 
	 *  - queryall: Devuelve todas las reservaciones con statusGra = 'Activo'
	 *  - querydeleted: Devuelve todas las reservaciones con statusGra = 'Borrado'
	 *  - querypending: Devuelve todas las reservaciones con decisionGra = 'Pendiente'
	 *  
	 *  @RequestMapping("/gra/query{searchParam}/{index}")
	 * */
	@RequestMapping("/gra/query{searchParam}/{index}")
	public String queryGratuito(Model model, @PathVariable("index") int index, @PathVariable("searchParam") String searchParam,
			HttpSession session) {
		
		model.addAttribute("Gratuito", new Gratuito());
		
		if(searchParam.equals(null)) { searchParam = "all";}
		Page<Gratuito> page = null;
		
		if(searchParam.equals("all")) {
			page = gratuitoService.getAllPage(index - 1);
			model.addAttribute(Constants.PAGE_TITLE, messageSource.getMessage("gratuito_query_all", null, Locale.getDefault()));
			model.addAttribute(Constants.RESULT, messageSource.getMessage("search_result", null, Locale.getDefault()));
		}
		else if(searchParam.equals("accepted")) {
			page = gratuitoService.getPageByDecision(Constants.DESITION_ACCEPTED, index - 1);
			model.addAttribute(Constants.PAGE_TITLE, messageSource.getMessage("gratuito_query_accepted", null, Locale.getDefault()));
			model.addAttribute(Constants.RESULT, messageSource.getMessage("search_result", null, Locale.getDefault()));
		}
		else if(searchParam.equals("rejected")) {
			page = gratuitoService.getPageByDecision(Constants.DESITION_REJECTED, index - 1);
			model.addAttribute(Constants.PAGE_TITLE, messageSource.getMessage("gratuito_query_rejected", null, Locale.getDefault()));
			model.addAttribute(Constants.RESULT, messageSource.getMessage("search_result", null, Locale.getDefault()));
		}
		else if(searchParam.equals("week")) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.DAY_OF_YEAR, -7);
			Timestamp end = new Timestamp(System.currentTimeMillis());
			Timestamp start = new Timestamp(c.getTime().getTime());
			
			System.out.println("Inicio: " + start + "/ Fin: " + end);
			
			page = gratuitoService.getPageByFechaInicioBetween(start, end, index - 1);
			List<Gratuito> list = gratuitoService.getByFechaInicioBetween(start, end);
			
			System.out.println("Lista: " + list);
			
			model.addAttribute(Constants.PAGE_TITLE, messageSource.getMessage("gratuito_query_last_week", null, Locale.getDefault()));
			model.addAttribute(Constants.RESULT, messageSource.getMessage("search_result", null, Locale.getDefault()));
		}
		else if(searchParam.equals("pending")) {
			page = gratuitoService.getPageByDecision(Constants.STATUS_PENDING, index - 1);
			model.addAttribute(Constants.PAGE_TITLE, messageSource.getMessage("gratuito_query_pending", null, Locale.getDefault()));
			model.addAttribute(Constants.RESULT, messageSource.getMessage("search_result", null, Locale.getDefault()));
		}
		
		int currentIndex = page.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
		
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("gratuitoList", page.getContent());
		model.addAttribute("searchParam", searchParam);
		
		model.addAttribute(Constants.SHOW_PAGES, true);
		session.setAttribute(Constants.SHOW_PAGES_FROM_SEARCH, false);
		
		return "gratuito_query";
	}

	
	/**
	 * Controlador actualizar una reservación gratuita en específico
	 * @RequestMapping("/gra/update/{idGra}")
	 * */
	@RequestMapping("/gra/update/{idGra}")
	public String update(Model model, @PathVariable("idGra") int idGra,
			HttpSession session, Principal principal, RedirectAttributes ra) {
		Gratuito gratuito = gratuitoService.findOne(idGra);
		
		model.addAttribute("gratuito", gratuito);
		model.addAttribute("idArea", gratuito.getArea().getIdArea());
		model.addAttribute("nombreArea", gratuito.getArea().getNombreArea());
		
		Calendar c = Calendar.getInstance();
		c.setTime(gratuito.getFhInicioEveGra());
		DecimalFormat formatter = new DecimalFormat("00");
		
		model.addAttribute("fInicioEveGra", c.get(Calendar.DATE) + "/" + formatter.format((double)c.get(Calendar.MONTH)+1) + "/" + c.get(Calendar.YEAR));
		model.addAttribute("hInicioEveGra", formatter.format((double)c.get(Calendar.HOUR_OF_DAY)) + ":00 Hrs");
		
		c.setTime(gratuito.getFhFinEveGra());
		model.addAttribute("hFinEveGra", formatter.format((double)c.get(Calendar.HOUR_OF_DAY)) + ":00 Hrs");
		
		model.addAttribute("siglasOrg", gratuito.getOrganizacion().getSiglasOrg());
		model.addAttribute("nombreOrg", gratuito.getOrganizacion().getNombreOrg());
		
		if(principal != null) {
			Admin admin = adminService.findOne(currentUser.getIdAdmin(principal));
			model.addAttribute("idAd", admin.getIdAd());
		} else {
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("autenthication_not_valid", null, Locale.getDefault()));
			return "redirect:/confirmscreen";
		}
		return "gratuito_adminform";
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
}