package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.projection.PersonaFullNameProjection;
import com.citnova.sca.repository.ClienteRepository;
import com.citnova.sca.repository.PersonaRepository;

@Service
public class PersonaService {
	
	@Autowired
	private PersonaRepository personaRepository;
	
	public Persona findByEmailPer(String emailPer){
		return personaRepository.findByEmailPer(emailPer);
	}

	public void save(Persona persona) {
		personaRepository.save(persona);
	}
	
	public List<Persona> findAllLike(String nombreOApellidos) {
		return personaRepository.findByNombrePerLike(nombreOApellidos);
	}
	
	public List<PersonaFullNameProjection> findByNombrePer(String nombrePer) {
		return personaRepository.findByNombrePer(nombrePer);
	}
}
