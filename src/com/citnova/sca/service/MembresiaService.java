package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Membresia;
import com.citnova.sca.repository.MembresiaRepository;

@Service
public class MembresiaService {

	@Autowired
	MembresiaRepository membresiaRepository;
	
	public Membresia findByCodigoMem(String codigoMem) {
		return membresiaRepository.findByCodigoMem(codigoMem);
	}
	
	public Membresia findByIdMem(int idMem) {
		return membresiaRepository.findByIdMem(idMem);
	}
	
	public List<Membresia> findByStatusMem(String statusMem) {
		return membresiaRepository.findByStatusMem(statusMem);
	}
	
	public List<Membresia> findByCMembresiaNombreCMem(String nombreCMem) {
		return membresiaRepository.findByCMembresiaNombreCMem(nombreCMem);
	}
	
	public List<Membresia> findByCMembresiaTipoCMem(String tipoCMem) {
		return membresiaRepository.findByCMembresiaTipoCMem(tipoCMem);
	}
	
	public List<Membresia> findAll() {
		return membresiaRepository.findAll();
	}
	
	public void save(Membresia membresia){
		membresiaRepository.save(membresia);
	}
}