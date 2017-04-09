package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Integer> {
	
	public Persona findByEmailPer(String emailPer);
	
	public List<Persona> findByNombrePerLike (String nombrePer);
	
	@Query(value = "select * from (select idPer, nombrePer, apPatPer, apMatPer, emailPer, curpPer, fhCreaPer, "
			+ "concat(nombrePer, ' ', apPatPer, ' ', apMatPer) as 'Cosa' from Persona) "
			+ "as Cosilla where Cosa like %?1%", nativeQuery = true)
	public List<Persona> findByFullNameLike(String fullName);
	
	public Persona findByCurpPer(String curpPer);
}

