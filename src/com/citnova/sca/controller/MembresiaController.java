package com.citnova.sca.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citnova.sca.domain.CMembresia;
import com.citnova.sca.domain.Paquete;
import com.citnova.sca.service.CMembresiaService;
import com.citnova.sca.service.PaqueteService;
import com.citnova.sca.util.Constants;

@Controller
public class MembresiaController {
	
	@Autowired
	private PaqueteService paqueteService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private CMembresiaService cMembresiaService;

	/**
	 * Controlador para mostrar el catálogo de paquetes
	 @RequestMapping("/contmem")
	 * */
	@RequestMapping("/contmem/{nombreCMem}")
	public String showContratarMembresía(Model model, @PathVariable("nombreCMem") String nombreCMemParam) {
		
		List<CMembresia> cMemActivos = cMembresiaService.findByStatusCMem(Constants.STATUS_ACTIVE);
		boolean paramExist = false;
		
		for(int i=0; i<cMemActivos.size(); i++) {
			String param = cMemActivos.get(i).getNombreCMem().replaceAll("í", "i").replaceAll(" ", "").toLowerCase();
			System.out.println("Cadena x: " + param);
			if(nombreCMemParam.equals(param)) {
				paramExist = true;
			}
		}
		
		if(paramExist == true) {
			model.addAttribute(Constants.CUSTOM_MAPPING, "/org/querymembers");
			model.addAttribute(Constants.CONFIRM_BUTTON, "Continuar");
			model.addAttribute(Constants.PAGE_TITLE, "Contratación de Membresía");
			model.addAttribute(Constants.RESULT, "Cosa uno");
			model.addAttribute(Constants.PARAM1, "Cosa 2");
			
			return "confirm";
		}
		
		else {
			model.addAttribute(Constants.RESULT, messageSource.getMessage("data_input_error", null, Locale.getDefault()));
			
			return "notifications";
		}
		
		
	}
}