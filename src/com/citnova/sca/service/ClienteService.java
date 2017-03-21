package com.citnova.sca.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Admin;
import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.QAdmin;
import com.citnova.sca.domain.QCliente;
import com.citnova.sca.domain.QPersona;
import com.citnova.sca.repository.ClienteRepository;
import com.mysema.query.jpa.impl.JPAQuery;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.repository.ClienteRepository;
import com.citnova.sca.repository.PersonaRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private PersonaRepository personaRepository;
	
	public void save(Cliente cliente){
		clienteRepository.save(cliente);
	}

	public List<Cliente> findAll() {
		return (List<Cliente>)clienteRepository.findAll();
	}

	public Cliente findByEmail(String email) {
		QPersona persona = QPersona.persona;
		QCliente cliente = QCliente.cliente;
		
		return new JPAQuery(entityManager)
				.from(cliente)
				.join(cliente.persona, persona)
				.where(
						persona.emailPer.eq(email)
						)
				.uniqueResult(cliente);
	}
	
	public void saveOrUpdate(Cliente cliente, Persona persona){
		personaRepository.save(persona);
		cliente.setPersona(persona);
		clienteRepository.save(cliente);
	}
}
