package com.citnova.sca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Direccion;
import com.citnova.sca.repository.DireccionRepository;

@Service
public class DireccionService {
	
	@Autowired
	private DireccionRepository direccionRepository;

	public Direccion findOne(int id){
		return direccionRepository.findOne(id);
	}
}
