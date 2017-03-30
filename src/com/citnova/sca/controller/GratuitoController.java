package com.citnova.sca.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citnova.sca.domain.Area;
import com.citnova.sca.domain.Estado;
import com.citnova.sca.domain.Gratuito;
import com.citnova.sca.domain.SectorEmp;
import com.citnova.sca.service.AreaService;
import com.citnova.sca.service.EstadoService;
import com.citnova.sca.service.SectorEmpService;

@Controller
public class GratuitoController {
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private SectorEmpService sectorEmpService;
	
	@Autowired
	private EstadoService estadoService;
	
	private Timestamp time = new Timestamp(new Date().getTime());
	

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
	
	
//	
//	/**
//	 * Guardar nuevo cliente en BD
//	 * */
//	@RequestMapping(value="/clientesave", method=RequestMethod.POST)
//	public String createCliente(Model model, PersonaClienteDireccionWrapper personaClienteDireccionWrapper, 
//			@RequestParam("otroOcupacion") String otroOcupacion, HttpServletRequest request,
//			RedirectAttributes ra) {
//		
//		// Consulta los Estados
//		List<Estado> estadoList = estadoService.findAll();
//		model.addAttribute("estadoList", estadoList);
//		
//		// Revisa si la ocupación fue seleccionada como "Otro" para tratarla
//		String ocupacion;
//		if(personaClienteDireccionWrapper.getOcupacionCli().equals("Otro")) {
//			model.addAttribute("otroOcup", otroOcupacion);
//			ocupacion = otroOcupacion;
//		} else {
//			ocupacion = personaClienteDireccionWrapper.getOcupacionCli();
//		}
//		System.out.println("\nOcupación: " + ocupacion + "\n");
//		
//		int idCli = personaClienteDireccionWrapper.getIdCli();
//		//
//		// Guardar nuevo registro
//		//
//		if(idCli == 0){
//			System.out.println("********* SAVE CLIENTE");
//		
//			// Genera un nuevo pass para passArea
//			List<Cliente> clienteList = clienteService.findAll();
//			boolean passUsed;
//			String passArea;
//			do {
//				passUsed = false;
//				passArea = PassGen.generatePass();
//				System.out.println("\nPass generado: " + passArea);
//				for(Cliente cliente : clienteList) {
//		            if(passArea.equals(cliente.getPassAreaCli())) {
//		            	System.out.println("La contraseña ya existe");
//		            	passUsed = true;
//		            }
//		        }
//				System.out.println(passUsed);
//			}
//			while (passUsed == true);
//	
//			// Guardar nuevo registro
//			
//			Persona persona = new Persona(personaClienteDireccionWrapper.getNombrePer(),
//					personaClienteDireccionWrapper.getApPatPer(),
//					personaClienteDireccionWrapper.getApMatPer(),
//					personaClienteDireccionWrapper.getEmailPer(),
//					personaClienteDireccionWrapper.getCurpPer(),
//					time);
//			
//			Cliente cliente = new Cliente(personaClienteDireccionWrapper.getEmailAltCli(),
//					passwordEncoder.encode(personaClienteDireccionWrapper.getPassCli()),
//					personaClienteDireccionWrapper.getSexoCli(),
//					personaClienteDireccionWrapper.getTelFijoCli(),
//					personaClienteDireccionWrapper.getTelMovilCli(),
//					personaClienteDireccionWrapper.getfNacCli(),
//					time,
//					ocupacion,
//					personaClienteDireccionWrapper.getObjetivoCli(),
//					personaClienteDireccionWrapper.getAvatarCli(),
//					Constants.STATUS_MUST_ACTIVATE,
//					passArea);
//			
//			Direccion direccion = new Direccion(personaClienteDireccionWrapper.getCalleDir(),
//					personaClienteDireccionWrapper.getNumExtDir(),
//					personaClienteDireccionWrapper.getNumIntDir(),
//					personaClienteDireccionWrapper.getColoniaDir(),
//					personaClienteDireccionWrapper.getCpDir());
//			
//			Municipio municipio = municipioService.findByIdMun(personaClienteDireccionWrapper.getIdMun());
//			
//			// Revisa una la persona ya existe con el mismo email
//			
//			Persona persona2 = personaService.findByEmailPer(persona.getEmailPer());
//			if(persona2 != null){
//				model.addAttribute(Constants.RESULT, messageSource.getMessage("client_exists", null, Locale.getDefault()));
//				return "/cliente_form";
//			}
//			
//			System.out.println(persona);
//			System.out.println(cliente);
//			System.out.println(direccion);
//			
//			// Guardar los datos a través del servicio
//			clienteService.saveOrUpdate(cliente, persona, direccion, municipio);
//			
//			// Enviar correo electrónico para confirmar cuenta
//			mailManager.sendEmailConfirmacion(persona.getEmailPer(), 
//					util.getRootUrl(request, "/clientesave")
//							.concat("/clienteaccount/")
//							.concat(String.valueOf(cliente.getIdCli()))
//							.concat("/confirm/")
//						);
//			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("cliente_saved", null, Locale.getDefault()));
//		
//		}
//		
//		else{
//			Cliente cliente = clienteService.findOne(idCli);
//			Persona persona = cliente.getPersona();
//			Direccion direccion = cliente.getDireccion();
//			Municipio municipio = direccion.getMunicipio();
//			
//			System.out.println("********* UPDATE");
//			
//			persona.setNombrePer(personaClienteDireccionWrapper.getNombrePer());
//			persona.setApPatPer(personaClienteDireccionWrapper.getApPatPer());
//			persona.setApMatPer(personaClienteDireccionWrapper.getApMatPer());
//			persona.setEmailPer(personaClienteDireccionWrapper.getEmailPer());
//			persona.setCurpPer(personaClienteDireccionWrapper.getCurpPer());
//			
//			cliente.setSexoCli(personaClienteDireccionWrapper.getSexoCli());
//			cliente.setTelFijoCli(personaClienteDireccionWrapper.getTelFijoCli());
//			cliente.setTelMovilCli(personaClienteDireccionWrapper.getTelMovilCli());
//			cliente.setfNacCli(personaClienteDireccionWrapper.getfNacCli());
//			cliente.setOcupacionCli(ocupacion);
//			cliente.setObjetivoCli(personaClienteDireccionWrapper.getObjetivoCli());
//			cliente.setAvatarCli(personaClienteDireccionWrapper.getAvatarCli());
//			
//			direccion.setNumExtDir(personaClienteDireccionWrapper.getNumExtDir());
//			direccion.setNumIntDir(personaClienteDireccionWrapper.getNumIntDir());
//			direccion.setColoniaDir(personaClienteDireccionWrapper.getColoniaDir());
//			direccion.setCpDir(personaClienteDireccionWrapper.getCpDir());
//
//			municipio = municipioService.findByIdMun(personaClienteDireccionWrapper.getIdMun());
//			
//			clienteService.saveOrUpdate(cliente, persona, direccion, municipio);
//			ra.addFlashAttribute(Constants.RESULT, messageSource.getMessage("cliente_updated", null, Locale.getDefault()));
//		}
//		
//		return "redirect:/login";
//	}
}