package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer>{
	
	public Page<Cliente> findAll(Pageable pageable);
	public Page<Cliente> findByStatusCli(String statusCli, Pageable pageable);
	
	
	@Query(	value = "SELECT * FROM Cliente WHERE idPer IN (SELECT idPer FROM (SELECT idPer, CONCAT(nombrePer, ' ', "
			+ "apPatPer, ' ', apMatPer) AS Query1 FROM Persona) AS Query2 WHERE Query1 LIKE %?1%) AND statusCli='Activo'"
			+ "ORDER BY idPer ASC "
			+ "\n#pageable\n", 
		countQuery = "SELECT COUNT(*) FROM Cliente WHERE idPer IN (SELECT idPer FROM (SELECT idPer, CONCAT(nombrePer, ' ', "
				+ "apPatPer, ' ', apMatPer) AS Query1 FROM Persona) AS Query2 WHERE Query1 LIKE %?1%) AND statusCli='Activo'"
				+ "ORDER BY idPer ASC", 
		nativeQuery = true)
	public Page<Cliente> findByFullNameLikePageAndStatusActivoPage(String fullName, Pageable pageable);
	
	@Query(	value = "SELECT * FROM Cliente WHERE idPer IN (SELECT idPer FROM (SELECT idPer, CONCAT(nombrePer, ' ', "
			+ "apPatPer, ' ', apMatPer) AS Query1 FROM Persona) AS Query2 WHERE Query1 LIKE %?1%) AND statusCli='Activo'"
			+ "ORDER BY idPer ASC", 
		nativeQuery = true)
	public List<Cliente> findByFullNameLikeAndStatusActivo(String fullName);
}