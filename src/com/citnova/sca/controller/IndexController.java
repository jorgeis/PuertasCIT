package com.citnova.sca.controller;

import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.citnova.sca.domain.Admin;
import com.citnova.sca.domain.Cliente;
import com.citnova.sca.service.AdminService;
import com.citnova.sca.service.ClienteService;
import com.citnova.sca.service.PersonaService;
import com.citnova.sca.util.Constants;
import com.citnova.sca.util.MailManager;
import com.citnova.sca.util.Util;

@Controller
public class IndexController {
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private MailManager mailManager;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Util util;

	@RequestMapping("/")
	public String showIndex() {
		System.out.println(adminService.findByEmailPer("juanperez@gmail.com"));
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

	@RequestMapping("/account/forgot")
	public String forgot() {
		return "account_mail";
	}
	
	/**
	 * Controlador para enviar correo electrónico de recuperación de contraseña 
	 * */
	@RequestMapping("/account/forgot/send")
	public String sendMail(@RequestParam("email") String email,
			HttpServletRequest request, Model model) {
		
		// Revisar si el correo electrónico tiene una o más cuentas de usuario asociada
		Admin admin = adminService.findByEmail(email);
		Cliente cliente = clienteService.findByEmail(email);
		boolean hasAccount = false;
		
		System.out.println("***** " + admin);
		System.out.println("xxxxx " + cliente);
		
		if(admin != null)
			hasAccount = true;
		else if(cliente != null)
			hasAccount = true;
		
		if(hasAccount){
			// Enviar correo electrónico para confirmar cuenta
			mailManager.sendPasswordResetEmail(email, 
					util.getRootUrl(request, "/account/forgot/send")
							.concat("/account/")
							.concat(email)
							.concat("/recovery")
						);
			model.addAttribute(Constants.RESULT, messageSource.getMessage("forgot_mail_sent", null, Locale.getDefault()));
		}
		else{
			model.addAttribute(Constants.RESULT, messageSource.getMessage("forgot_account_not_exists", null, Locale.getDefault()));
		}
		return "account_mail";
	}
	

	/**
	 * Controlador para mostrar la vista para reestablecer la contraseña
	 * */
	@RequestMapping("/account/{email}/recovery")
	public String restorePassword(
			Model model, HttpSession session, RedirectAttributes ra,
			@PathVariable("email") String email, Principal principal) {
		
		
		
		if(principal!= null) {
			System.out.println(principal.getName());
			session.invalidate();
		}
		
		// Revisar si el correo electrónico tiene una o más cuentas de usuario asociada
		Admin admin = adminService.findByEmail(email);
		Cliente cliente = clienteService.findByEmail(email);
		boolean hasAccount = false;
		
		System.out.println("***** " + admin);
		System.out.println("xxxxx " + cliente);
		
		if(admin != null)
			hasAccount = true;
		else if(cliente != null)
			hasAccount = true;
		
		if(hasAccount){
			//session.setAttribute(Constants.RECOVERY_MAIL, email);
			ra.addFlashAttribute(Constants.RECOVERY_MAIL, email);
			return "account_password";
		}
		else{
			model.addAttribute(Constants.RESULT, messageSource.getMessage("forgot_account_not_exists", null, Locale.getDefault()));
			return "notifications";
		}
	}
	
	
	/**
	 * Controlador para almacenar nueva contraseña
	 * */
	@RequestMapping("/account/confirm")
	public String accountConfirm(
			HttpSession session, Model model, RedirectAttributes ra,
			@RequestParam("password") String password) {
		
		//String email = (String) session.getAttribute(Constants.RECOVERY_MAIL);
		String email = (String) model.asMap().get(Constants.RECOVERY_MAIL);
		
		if(email != null){
			Admin admin = adminService.findByEmail(email);
			Cliente cliente = clienteService.findByEmail(email);
			
			if(admin != null){
				admin.setPassAd(passwordEncoder.encode(password));
				adminService.saveOrUpdate(admin, admin.getPersona());
			}
			else if(cliente != null){
				cliente.setPassCli(passwordEncoder.encode(password));
				clienteService.saveOrUpdate(cliente, cliente.getPersona(), cliente.getDireccion(), 
						cliente.getDireccion().getMunicipio());
			}
			model.addAttribute(Constants.RESULT, messageSource.getMessage("forgot_account_complete", null, Locale.getDefault()));
			return "notifications";
		}
		else{
			model.addAttribute(Constants.RESULT, messageSource.getMessage("forgot_account_restart_proccess", null, Locale.getDefault()));
			return "notifications";
		}
	}
	
	@RequestMapping("/afterlogin")
	public String showAfterLogin(Model model) {
		
		model.addAttribute(Constants.RESULT, messageSource.getMessage("login_msg", null, Locale.getDefault()));
		return "notifications";
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