package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Admin;
import com.citnova.sca.domain.Cliente;
import com.citnova.sca.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public void save(Cliente cliente){
		clienteRepository.save(cliente);
	}

	public List<Cliente> findAll() {
		return (List<Cliente>)clienteRepository.findAll();
	}

}
