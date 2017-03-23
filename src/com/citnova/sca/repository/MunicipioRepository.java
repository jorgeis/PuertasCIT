package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Estado;
import com.citnova.sca.domain.Municipio;

public interface MunicipioRepository extends CrudRepository<Municipio, Integer> {

	public List<Municipio> findByEstado(Estado estado);
	
	public Municipio findByIdMun(int idMun);
}
