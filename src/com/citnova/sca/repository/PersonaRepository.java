package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Persona;
import com.citnova.sca.projection.PersonaFullNameProjection;

public interface PersonaRepository extends CrudRepository<Persona, Integer> {
	
	public Persona findByEmailPer(String emailPer);
	
	public List<Persona> findByNombrePerLike (String nombrePer);

	public List<PersonaFullNameProjection> findByNombrePer(String nombrePer);
}
