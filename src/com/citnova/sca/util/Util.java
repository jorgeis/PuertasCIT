package com.citnova.sca.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;


@Component("util")
public class Util {
	
	private static Logger log = Logger.getLogger(Util.class);
	
	@Autowired
	MessageSource messageSource;

	/**
	 * Retorna un String que contiene la url completa de la petición menos 
	 * el mapping del controlador actual.
	 * p.e. www.novellius.com/cliente/add retorna www.novellius.com
	 *
	 * @param request Objeto HttpServletRequest el cual contiene la peticion
	 * @param requestMappingValue El mapping de la petición del controlador Spring p.e. /cliente/add
	 * 
	 * */
	public String getRootUrl(HttpServletRequest request, String requestMappingValue) {
		return StringUtils.replace(request.getRequestURL().toString(), requestMappingValue, "");
	}
	
	public String getContextPath(HttpServletRequest request, String requestMappingValue) {
		return StringUtils.replace(request.getRequestURL().toString(), requestMappingValue, "");
	}
	
	
	

	public boolean isValidDate(String date) {
		return (date.matches("^[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}$")) ? true : false;
	}

	
	public String getClaveRec(int size) {
		Random r = new Random();
		String claveRec = "";
		int j = 0;

		String caracteres[] = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q",
				"r", "s", "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
				"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z" };

		for (int i = 0; i <= size; i++) {
			j = r.nextInt(60);
			claveRec += caracteres[j];
		}
		return claveRec;
	}

	/**
	 * Devuelve una lista de String, la cual contiene todos los roles
	 * asociados de la cuenta.
	 * */
	public List<String> getStringAuths(String rols) {
		List<String> auths = new ArrayList<String>();
		if (rols != null) {
			StringTokenizer stringTokenizer = new StringTokenizer(rols, ",");

			while (stringTokenizer.hasMoreElements()) {
				auths.add((String) stringTokenizer.nextElement());
			}
		}
		return auths;
	}

	public boolean isValidPass(String pass) {
		if (pass == null) {
			return false;
		}
		if (pass.equals("")) {
			return false;
		}
		if (!pass.matches("[a-zA-Z0-9]{6,}")) {
			return false;
		}
		return true;
	}
	
	
	public boolean isValidEmail(String email) {
		EmailValidator emailValidator = EmailValidator.getInstance();
		return (emailValidator.isValid(email)) ? true : false;
	}
	
	
	public String getMessage(String key) {
        try {
            return messageSource.getMessage(key, null, Locale.getDefault());
        }
        catch (Exception e) {
        	log.error("Unresolved key");
            return "Unresolved key: " + key;
        }
    }
	
	
	
	
	

	
	public String getCurrentTimestamp() {
		Timestamp ts = new Timestamp(new Date().getTime());
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(ts);
	}
	
	
	public Timestamp getTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	
	
	
	/**
	 * Retorna un objeto Timestamp con formato yyyy-MM-dd hh:mm:ss.SSS a partir de un
	 * String el cual contiene la fecha en el formato establecido.
	 * @author Abraham Ramirez
	 * @param dateTime String que contiene una fecha con formato yyyy-MM-dd hh:mm:ss.SSS
	 * 
	 * */
	public Timestamp stringToTimestamp(String dateTime) {
	    Date parsedDate = null;
	    Timestamp timestamp = null;
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			parsedDate = dateFormat.parse(dateTime);
			timestamp = new java.sql.Timestamp(parsedDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    
	    return timestamp;
	}

}
