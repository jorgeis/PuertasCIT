package com.citnova.sca.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citnova.sca.domain.Paquete;
import com.citnova.sca.service.PaqueteService;
import com.citnova.sca.util.Constants;

@Controller
public class PaqueteController {
	
	@Autowired
	private PaqueteService paqueteService;
	@Autowired
	private MessageSource messageSource;

	/**
	 * Controlador para mostrar el catálogo de paquetes
	 @RequestMapping("/catpaquetes")
	 * */
	@RequestMapping("/catpaquetes")
	public String showDatepick(Model model) {
		
		
		
		List<Paquete> coworkingFreelancer = paqueteService.findByNombreLikeAndStatusActivo(Constants.COWORKING_FREELANCER);
		List<Paquete> coworkingEmpresarial = paqueteService.findByNombreLikeAndStatusActivo(Constants.COWORKING_EMPRESARIAL);
		List<Paquete> coworkingUniversitario = paqueteService.findByNombreLikeAndStatusActivo(Constants.COWORKING_UNIVERSITARIO);
		List<Paquete> makerFreelancer = paqueteService.findByNombreLikeAndStatusActivo(Constants.MAKER_FREELANCER);
		List<Paquete> makerEmpresarial = paqueteService.findByNombreLikeAndStatusActivo(Constants.MAKER_EMPRESARIAL);
		List<Paquete> makerOtro = paqueteService.findByNombreLikeAndStatusActivo(Constants.MAKER_BASIC);
		 			  makerOtro.addAll(paqueteService.findByNombreLikeAndStatusActivo(Constants.MAKER_ASSEMBLER));
		 			  makerOtro.addAll(paqueteService.findByNombreLikeAndStatusActivo(Constants.MAKER_C));
		List<Paquete> decisionTheater = paqueteService.findByNombreLikeAndStatusActivo(Constants.DECISION_THEATER);
		List<Paquete> espacioDedicado = paqueteService.findByNombreLikeAndStatusActivo(Constants.ESPACIO_DEDICADO);
		List<Paquete> trainingRoom = paqueteService.findByNombreLikeAndStatusActivo(Constants.TRAINING_ROOM);
		List<Paquete> showRoom = paqueteService.findByNombreLikeAndStatusActivo(Constants.SHOW_ROOM);
		List<Paquete> salaDeJuntas = paqueteService.findByNombreLikeAndStatusActivo(Constants.SALA_DE_JUNTAS);
		List<Paquete> membresiaBlack = paqueteService.findByNombreLikeAndStatusActivo(Constants.MEMBRESIA_BLACK);
		List<Paquete> membresiaPlatinum = paqueteService.findByNombreLikeAndStatusActivo(Constants.MEMBRESIA_PLATINUM);
		List<Paquete> membresiaGold = paqueteService.findByNombreLikeAndStatusActivo(Constants.MEMBRESIA_GOLD);
		List<Paquete> membresiaPremium = paqueteService.findByNombreLikeAndStatusActivo(Constants.MEMBRESIA_PREMIUM);
		List<Paquete> printer = paqueteService.findByNombreLikeAndStatusActivo(Constants.PRINTER);
		
		System.out.println("Lista: " + coworkingFreelancer);
		
		model.addAttribute("coworkingFreelancer", coworkingFreelancer);
		model.addAttribute("coworkingEmpresarial", coworkingEmpresarial);
		model.addAttribute("coworkingUniversitario", coworkingUniversitario);
		model.addAttribute("makerFreelancer", makerFreelancer);
		model.addAttribute("makerEmpresarial", makerEmpresarial);
		model.addAttribute("makerOtro", makerOtro);
		model.addAttribute("decisionTheater", decisionTheater);
		model.addAttribute("espacioDedicado", espacioDedicado);
		model.addAttribute("trainingRoom", trainingRoom);
		model.addAttribute("showRoom", showRoom);
		model.addAttribute("salaDeJuntas", salaDeJuntas);
		model.addAttribute("membresiaBlack", membresiaBlack);
		model.addAttribute("membresiaPlatinum", membresiaPlatinum);
		model.addAttribute("membresiaGold", membresiaGold);
		model.addAttribute("membresiaPremium", membresiaPremium);
		model.addAttribute("printer", printer);
		
		model.addAttribute("coworkingFreelancerTit", Constants.COWORKING_FREELANCER);
		model.addAttribute("coworkingEmpresarialTit", Constants.COWORKING_EMPRESARIAL);
		model.addAttribute("coworkingUniversitarioTit", Constants.COWORKING_UNIVERSITARIO);
		model.addAttribute("makerFreelancerTit", Constants.MAKER_FREELANCER);
		model.addAttribute("makerEmpresarialTit", Constants.MAKER_EMPRESARIAL);
		model.addAttribute("makerOtroTit", "Maker Extra");
		model.addAttribute("decisionTheaterTit", Constants.DECISION_THEATER);
		model.addAttribute("espacioDedicadoTit", Constants.ESPACIO_DEDICADO);
		model.addAttribute("trainingRoomTit", Constants.TRAINING_ROOM);
		model.addAttribute("showRoomTit", Constants.SHOW_ROOM);
		model.addAttribute("salaDeJuntasTit", Constants.SALA_DE_JUNTAS);
		model.addAttribute("membresiaBlackTit", Constants.MEMBRESIA_BLACK);
		model.addAttribute("membresiaPlatinumTit", Constants.MEMBRESIA_PLATINUM);
		model.addAttribute("membresiaGoldTit", Constants.MEMBRESIA_GOLD);
		model.addAttribute("membresiaPremiumTit", Constants.MEMBRESIA_PREMIUM);
		model.addAttribute("printerTit", Constants.PRINTER);
		
		model.addAttribute(Constants.PAGE_TITLE, messageSource.getMessage("paquete_title", null, Locale.getDefault()));
		return "paquete_query";
	}
}