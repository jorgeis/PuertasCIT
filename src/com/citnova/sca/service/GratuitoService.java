package com.citnova.sca.service;

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
}
