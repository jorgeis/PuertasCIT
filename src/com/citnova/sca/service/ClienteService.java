package com.citnova.sca.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Admin;
import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Direccion;
import com.citnova.sca.domain.Municipio;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.domain.QCliente;
import com.citnova.sca.domain.QPersona;
import com.citnova.sca.repository.ClienteRepository;
import com.citnova.sca.repository.DireccionRepository;
import com.citnova.sca.repository.PersonaRepository;
import com.citnova.sca.util.Constants;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EntityManager entityManager;

	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private DireccionRepository direccionRepository;
	
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
	
	public void saveOrUpdate(Cliente cliente, Persona persona, Direccion direccion, Municipio municipio){
		direccion.setMunicipio(municipio);
		direccionRepository.save(direccion);
		personaRepository.save(persona);
		cliente.setPersona(persona);
		cliente.setDireccion(direccion);
		clienteRepository.save(cliente);
	}
	
	public Cliente findOne(int idCli) {
		return clienteRepository.findOne(idCli);
	}
	
	public Page<Cliente> getAllClientesPage(int index) {
		return clienteRepository.findAll(new PageRequest(index, Constants.ITEMS_PER_PAGE, Direction.ASC, "idCli"));
	}
	
	public Page<Cliente> getActiveClientesPage(int index) {
		return clienteRepository.findByStatusCli(Constants.STATUS_ACTIVE, new PageRequest(index, Constants.ITEMS_PER_PAGE, Direction.ASC, "idCli"));
	}
}
