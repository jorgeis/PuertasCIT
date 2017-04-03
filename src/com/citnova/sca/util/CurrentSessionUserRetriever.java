package com.citnova.sca.util;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;

import com.citnova.sca.service.AdminService;
import com.citnova.sca.service.ClienteService;
import com.citnova.sca.service.PersonaService;

public class CurrentSessionUserRetriever {
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private AdminService adminService;
	
	/**
	 * Método para obtener el ID del objeto Persona identificado actualmente en el sistema.
	 * En caso de que retorne cero como resultado, la persona no existe.
	 * */
	public int getIdPersona(Principal principal) {
		
		int result = 0;
		
		if(personaService.findByEmailPer(principal.getName()) != null) 
		{
			result = personaService.findByEmailPer(principal.getName()).getIdPer();
		}
		return result;
	}
	
	/**
	 * Método para obtener el ID del objeto Cliente identificado actualmente en el sistema.
	 * En caso de que retorne cero como resultado, el cliente no existe.
	 * */
	public int getIdCliente(Principal principal) {
		
		int result = 0;
		
		if(clienteService.findByEmail(principal.getName()) != null) 
		{
			result = clienteService.findByEmail(principal.getName()).getIdCli();
		}
		return result;
	}
	
	public int getIdAdmin(Principal principal) {
		
		int result = 0;
		
		if(adminService.findByEmail(principal.getName()) != null) 
		{
			result = adminService.findByEmail(principal.getName()).getIdAd();
		}
		return result;
	}
}