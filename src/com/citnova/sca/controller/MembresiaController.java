package com.citnova.sca.controller;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citnova.sca.domain.CMembresia;
import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Contratacion;
import com.citnova.sca.domain.Membresia;
import com.citnova.sca.domain.MembresiaCliente;
import com.citnova.sca.service.CMembresiaService;
import com.citnova.sca.service.ClienteService;
import com.citnova.sca.service.ContratacionService;
import com.citnova.sca.service.MembresiaClienteService;
import com.citnova.sca.service.MembresiaService;
import com.citnova.sca.service.PaqueteService;
import com.citnova.sca.util.Constants;
import com.citnova.sca.util.CurrentSessionUserRetriever;
import com.citnova.sca.util.PassGen;

@Controller
public class MembresiaController {
	
	@Autowired
	private PaqueteService paqueteService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private CMembresiaService cMembresiaService;
	@Autowired
	private MembresiaService membresiaService;
	@Autowired
	private ContratacionService contratacionService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private MembresiaClienteService membresiaClienteService;
	@Autowired
	private CurrentSessionUserRetriever currentUser;

	/**
	 * Controlador para mostrar el catálogo de paquetes
	 @RequestMapping("/mem/contmem/{nombreCMem}")
	 * */
	@RequestMapping("/mem/contmem/{nombreCMem}")
	public String showContratarMembresía(Model model, @PathVariable("nombreCMem") String nombreCMemParam) {
		
		List<CMembresia> cMemActivos = cMembresiaService.findByStatusCMem(Constants.STATUS_ACTIVE);
		boolean paramExist = false;
		String membresiaContratada = null;
		
		for(int i=0; i<cMemActivos.size(); i++) {
			String param = cMemActivos.get(i).getNombreCMem().replaceAll("í", "i").replaceAll(" ", "").toLowerCase();
			System.out.println("Cadena x: " + param);
			if(nombreCMemParam.equals(param)) {
				paramExist = true;
				membresiaContratada = cMemActivos.get(i).getNombreCMem();
			}
		}
		
		if(paramExist == true) {
			model.addAttribute(Constants.CUSTOM_MAPPING, "/mem/createmem");
			model.addAttribute(Constants.CONFIRM_BUTTON, "Continuar");
			model.addAttribute(Constants.PAGE_TITLE, "Contratación de Membresía");
			model.addAttribute(Constants.RESULT, "Estás a punto de contratar la " + membresiaContratada);
			model.addAttribute(Constants.MESSAGE1, "A continuación se generará una orden de pago. Acude a tu banco para realizar el pago "
					+ "correspondiente y acude a las oficinas del CITNOVA con el ticket de pago para activar tu membresía");
			model.addAttribute(Constants.PARAM1, membresiaContratada);
			
			return "confirm";
		}
		
		else {
			model.addAttribute(Constants.RESULT, messageSource.getMessage("data_input_error", null, Locale.getDefault()));
			
			return "notifications";
		}
	}
	
	
	/**
	 * Controlador para crear la membresía, pendiente de validar por el administrador
	 @RequestMapping("/mem/createmem")
	 * */
	@Transactional
	@RequestMapping(value="/mem/createmem",  method=RequestMethod.POST)
	public String createMembresía(Model model,
			Principal principal, RedirectAttributes ra, @RequestParam("param1") String nombreCMem) {
		
		// Revisa si hay un cliente identificado en el sistema que quiere hacer esta acción.
		int idCli = 0;
		Cliente cliente;
		
		if(principal != null) {
			idCli = currentUser.getIdCliente(principal);
			System.out.println("El id del cliente es: " + idCli);
		}
		
		// Si es un cliente identificado en el sistema quien solicita esta acción:
		if(idCli != 0) {
			
			cliente = clienteService.findOne(idCli);
			
			// Crea un codigoMem único, revisando los actualmente existentes
			List<Membresia> membresiaList = membresiaService.findAll();
			boolean passUsed = false;
			String codigoMem;
			do {
				passUsed = false;
				codigoMem = PassGen.generatePass();
				System.out.println("\nCódigo de membresía generado: " + codigoMem);
				for(Membresia mem : membresiaList) {
		            if(codigoMem.equals(mem.getCodigoMem())) {
		            	System.out.println("Este codigo ya fue utilizado");
		            	passUsed = true;
		            }
		        }
				System.out.println(passUsed);
			}
			while (passUsed == true);
			
			// Crea un objeto CMembresia cuyo contenido es el tipo de membresía del catálogo que se está creando en Membresía
			CMembresia cMembContratada = cMembresiaService.findByNombreCMem(nombreCMem);
			
			// Crea la membresía
			Membresia membresia = new Membresia(
					Constants.PENDING_PAYMENT, 
					codigoMem, 
					new Timestamp(new Date().getTime()), 
					cMembContratada);
			
			// Crea una refCont única comparándola con las ya existentes
			List<Contratacion> contratacionList = contratacionService.findAll();
			boolean passUsed2 = false;
			String refCont;
			do {
				passUsed2 = false;
				refCont = PassGen.generatePass();
				System.out.println("\nReferencia de contratación generada: " + refCont);
				for(Contratacion con : contratacionList) {
		            if(refCont.equals(con.getRefCont())) {
		            	System.out.println("Este codigo ya fue utilizado");
		            	passUsed2 = true;
		            }
		        }
				System.out.println(passUsed2);
			}
			while (passUsed2 == true);
			
			// Crea la contratación sobre la cual se está generando la membresía
			Contratacion contratacion = new Contratacion(
					cliente,
					new Timestamp(new Date().getTime()), 
					cMembContratada.getCostoCMem(), 
					(float) (cMembContratada.getCostoCMem()*0.16), 
					refCont);
			
			// Setea la relación muchos a muchos entre Membresía y Contratación
			List<Contratacion> listaContratacion = new ArrayList<Contratacion>();
			listaContratacion.add(contratacion);
			membresia.setContratacionList(listaContratacion);
			
			List<Membresia> listaMembresia = new ArrayList<Membresia>();
			listaMembresia.add(membresia);
			contratacion.setMembresiaList(listaMembresia);
			
			
			// Crea la relación muchos a muchos entre Membresía y Cliente
			MembresiaCliente membresiaCliente = new MembresiaCliente();
			membresiaCliente.setMembresia(membresia);
			membresiaCliente.setCliente(cliente);
			membresiaCliente.setStatusMC(Constants.PENDING_PAYMENT);
			
			System.out.println("El objeto a guardar es: " + membresia);
			
			membresiaService.save(membresia);
			contratacionService.save(contratacion);
			membresiaClienteService.save(membresiaCliente);
			
			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("membresia_saved", null, Locale.getDefault()));
			return "redirect:/confirmscreen";
			
			
		// Si no es un cliente identificado en sistema quien solicita esta acción:
		} else {
			model.addAttribute(Constants.RESULT, messageSource.getMessage("data_input_error", null, Locale.getDefault()));
			
			return "notifications";
		}
		
		
	}
	
	
	
}