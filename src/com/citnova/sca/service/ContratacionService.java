package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Contratacion;
import com.citnova.sca.repository.ContratacionRepository;

@Service
public class ContratacionService {

	@Autowired
	ContratacionRepository contratacionRepository;
	
	public List<Contratacion> findAll() {
		return contratacionRepository.findAll();
	}
	
	public void save(Contratacion contratacion){
		contratacionRepository.save(contratacion);
	}
}
