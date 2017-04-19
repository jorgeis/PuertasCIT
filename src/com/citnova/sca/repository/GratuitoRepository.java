package com.citnova.sca.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Gratuito;

public interface GratuitoRepository extends CrudRepository<Gratuito, Integer> {

	public List<Gratuito> findByFhInicioEveGraBetween(Timestamp start, Timestamp end);
	
}
