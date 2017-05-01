package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.CMembresia;
import com.citnova.sca.repository.CMembresiaRepository;
import com.citnova.sca.util.Constants;

@Service
public class CMembresiaService {

	@Autowired
	CMembresiaRepository cMembresiaRepository;
	
	public List<CMembresia> findByTipoAndStatusActivo(String match) {
		return cMembresiaRepository.findByTipoCMemAndStatusCMem(match, Constants.STATUS_ACTIVE);
	}
	
	public List<CMembresia> findByStatusCMem(String statusCMem) {
		return cMembresiaRepository.findByStatusCMem(statusCMem);
	}
	
	public CMembresia findByNombreCMem(String nombreCMem) {
		return cMembresiaRepository.findByNombreCMem(nombreCMem);
	}
}
