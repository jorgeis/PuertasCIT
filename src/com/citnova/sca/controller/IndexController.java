package com.citnova.sca.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citnova.sca.domain.Direccion;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.service.DireccionService;
import com.citnova.sca.service.PersonaService;
import com.citnova.sca.util.MailManager;

@Controller
public class IndexController {
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private DireccionService direccionService;
	
	@Autowired
	private MailManager mailManager;

	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/logout")
	public String logout() {
		return "/";
	}
	
	@RequestMapping("/formex")
	public String showOneColumn() {
		return "formResp";
	}
	
	@RequestMapping("/twocolumn1")
	public String showTwoColumn1() {
		return "twocolumn1";
	}
	
	@RequestMapping("/twocolumn2")
	public String showTwoColumn2() {
		return "twocolumn2";
	}
}