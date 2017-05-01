package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Contratacion;

public interface ContratacionRepository extends CrudRepository<Contratacion, Integer> {

	public List<Contratacion> findAll();
}