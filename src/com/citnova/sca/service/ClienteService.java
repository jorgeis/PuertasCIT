package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Admin;
import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.repository.ClienteRepository;
import com.citnova.sca.repository.PersonaRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PersonaRepository personaRepository;
	
	public void save(Cliente cliente){
		clienteRepository.save(cliente);
	}

	public List<Cliente> findAll() {
		return (List<Cliente>)clienteRepository.findAll();
	}
	
	public void saveOrUpdate(Cliente cliente, Persona persona){
		personaRepository.save(persona);
		cliente.setPersona(persona);
		clienteRepository.save(cliente);
	}
}
