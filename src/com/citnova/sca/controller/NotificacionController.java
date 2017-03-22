package com.citnova.sca.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;

import com.citnova.sca.service.AdminService;
import com.citnova.sca.util.Util;

@Controller
public class NotificacionController {

//	@Autowired
//	private NotificacionService notificacionService;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private Util util;
	
	private Timestamp time = new Timestamp(new Date().getTime());
}
