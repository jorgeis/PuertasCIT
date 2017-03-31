package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.OrganizacionCliente;
import com.citnova.sca.repository.OrganizacionClienteRepository;

@Service
public class OrganizacionClienteService {
	
	@Autowired
	private OrganizacionClienteRepository organizacionClienteRepository;

	public void save(OrganizacionCliente organizacionCliente){
		organizacionClienteRepository.save(organizacionCliente);
	}

	public List<OrganizacionCliente> findAll() {
		return (List<OrganizacionCliente>) organizacionClienteRepository.findAll();
	}
}
