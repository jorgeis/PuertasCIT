package com.citnova.sca.repository;

import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Integer>  {
	
	public Persona findByEmailPer(String emailPer);

}
