package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Membresia;

public interface MembresiaRepository extends CrudRepository<Membresia, Integer> {

	public Membresia findByCodigoMem(String codigoMem);
	public List<Membresia> findByStatusMem(String statusMem);
	public Membresia findByIdMem(int idMem);
	public List<Membresia> findByCMembresiaNombreCMem(String nombreCMem);
	public List<Membresia> findByCMembresiaTipoCMem(String tipoCMem);
	public List<Membresia> findAll();
}
