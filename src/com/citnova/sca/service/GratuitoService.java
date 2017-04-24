package com.citnova.sca.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Gratuito;
import com.citnova.sca.repository.GratuitoRepository;
import com.citnova.sca.util.Constants;

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

	public Page<Gratuito> getPageByStatus(String statusGra, int index) {
		return gratuitoRepository.findByStatusGra(statusGra, new PageRequest(index, Constants.ITEMS_PER_PAGE, Direction.ASC, "idGra"));
	}
	
	public Page<Gratuito> getPageByDecision(String decisionGra, int index) {
		return gratuitoRepository.findByDecisionGra(decisionGra, new PageRequest(index, Constants.ITEMS_PER_PAGE, Direction.ASC, "idGra"));
	}
	
	public Page<Gratuito> getAllPage(int index) {
		return gratuitoRepository.findAll(new PageRequest(index, Constants.ITEMS_PER_PAGE, Direction.ASC, "idGra"));
	}

	public Gratuito findOne(int idGra) {
		return gratuitoRepository.findOne(idGra);
	}
	
	public Page<Gratuito> getPageByFechaInicioBetween(Timestamp start, Timestamp end, int index) {
		return gratuitoRepository.findByFhInicioEveGraBetween(start, end, new PageRequest(index, Constants.ITEMS_PER_PAGE, Direction.ASC, "idGra"));
	}
	
	public List<Gratuito> getByFechaInicioBetween(Timestamp start, Timestamp end) {
		return gratuitoRepository.findByFhInicioEveGraBetween(start, end);
	}
}
