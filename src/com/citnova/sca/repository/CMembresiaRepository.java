package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.CMembresia;

public interface CMembresiaRepository extends CrudRepository<CMembresia, Integer> {

	public List<CMembresia> findByTipoCMemAndStatusCMem(String match, String statusCMem);
	public List<CMembresia> findByStatusCMem(String statusCMem);
	
}
