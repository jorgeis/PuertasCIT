package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Estado;
import com.citnova.sca.domain.Municipio;
import com.citnova.sca.repository.MunicipioRepository;

@Service
public class MunicipioService {

	@Autowired
	MunicipioRepository municipioRepository;
	
	public List<Municipio> findByEstado(Estado estado) {
		return municipioRepository.findByEstado(estado);
	}
	
	public Municipio findByIdMun(int idMun) {
		return municipioRepository.findByIdMun(idMun);
	}
}
