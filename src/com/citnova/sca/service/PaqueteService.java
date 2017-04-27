package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Paquete;
import com.citnova.sca.repository.PaqueteRepository;
import com.citnova.sca.util.Constants;

@Service
public class PaqueteService {

	@Autowired
	PaqueteRepository paqueteRepository;
	
	public List<Paquete> findByNombreLikeAndStatusActivo(String match) {
		return paqueteRepository.findByNombrePaqLikeAndStatusPaq("%" + match + "%", Constants.STATUS_ACTIVE);
	}
}