package com.citnova.sca.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.citnova.sca.domain.Notificacion;
import com.citnova.sca.service.AdminService;
import com.citnova.sca.service.NotificacionService;
import com.citnova.sca.util.Constants;
import com.citnova.sca.util.Util;

@Controller
public class NotificacionController {

	@Autowired
	private NotificacionService notificacionService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private Util util;
	
	private Timestamp time = new Timestamp(new Date().getTime());
	
	
	@RequestMapping("/admin/notificacion/queryall/{index}")
	public String queryAll(Model model,
			@PathVariable("index") int index){
		
		model.addAttribute("notificacion", new Notificacion());
		
		Page<Notificacion> page = notificacionService.getPage(index - 1);
		
		int currentIndex = page.getNumber() + 1;
		int beginIndex = Math.max(1, currentIndex - 5);
		int endIndex = Math.min(beginIndex + 10, page.getTotalPages());
		
		model.addAttribute("beginIndex",beginIndex);
		model.addAttribute("endIndex",endIndex);
		model.addAttribute("currentIndex",currentIndex);
		model.addAttribute("totalPages", page.getTotalPages());
		
		model.addAttribute("notificacionList", page.getContent());
		
		model.addAttribute(Constants.SHOW_PAGES, true);
		
		return "notificacion_queryall";
	}
}
