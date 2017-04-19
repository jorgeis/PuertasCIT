package com.citnova.sca.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Gratuito;
import com.citnova.sca.repository.GratuitoRepository;

@Service
public class GratuitoService {
	
	@Autowired
	GratuitoRepository gratuitoRepository;
	
	public void save(Gratuito gratuito){
		gratuitoRepository.save(gratuito);
	}
	
	public List<Gratuito> findByDay(String day) {
		
		System.out.println("Parámetro: " + day);
		
		String time = "00:00";
		Date parsedDate = null;
		Timestamp start = null;
		Timestamp end = null;
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			parsedDate = dateFormat.parse(day + " " + time);
		} catch (ParseException e) {
			System.out.println("Excepcion lanzada!");
		}
		
		start = new Timestamp(parsedDate.getTime());
		System.out.println("Fecha completa Date: " + parsedDate);
		System.out.println("Fecha completa Timestamp: " + start);
		
		Calendar c = Calendar.getInstance(); 
		c.setTime(parsedDate); 
		c.add(Calendar.DATE, 1);
		parsedDate = c.getTime();
		
		end = new Timestamp(parsedDate.getTime());
		System.out.println("Fecha completa Date: " + parsedDate);
		System.out.println("Fecha completa Timestamp: " + end);
		
		return gratuitoRepository.findByFhInicioEveGraBetween(start, end);
	}
}
