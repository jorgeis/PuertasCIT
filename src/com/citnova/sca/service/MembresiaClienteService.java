package com.citnova.sca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.MembresiaCliente;
import com.citnova.sca.repository.MembresiaClienteRepository;

@Service
public class MembresiaClienteService {
	
	@Autowired
	MembresiaClienteRepository membresiaClienteRepository;

	public void save(MembresiaCliente membresiaCliente) {
		membresiaClienteRepository.save(membresiaCliente);
	}

	
	
}