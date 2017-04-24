package com.citnova.sca.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Gratuito;

public interface GratuitoRepository extends CrudRepository<Gratuito, Integer> {

	public List<Gratuito> findByFhInicioEveGraBetween(Timestamp start, Timestamp end);
	
	public Page<Gratuito> findByStatusGra(String statusGra, Pageable pageable);

	public Page<Gratuito> findByDecisionGra(String decisionGra, Pageable pageable);
	
	public Page<Gratuito> findAll(Pageable pageable);
	
	public Page<Gratuito> findByFhInicioEveGraBetween(Timestamp start, Timestamp end, Pageable pageable);
}
