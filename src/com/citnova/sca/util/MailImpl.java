package com.citnova.sca.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("mailImpl")
public class MailImpl {
	
	@Autowired
	private MailManager mailManager;
	
	
	
	
	
	public boolean sendEmailInfo(String destino, String titulo, String mensaje) {
		return mailManager.sendEmailInfo(destino, titulo, mensaje);
	}
	
	
	

}
