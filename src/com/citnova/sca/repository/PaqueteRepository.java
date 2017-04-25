package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Paquete;

public interface PaqueteRepository extends CrudRepository<Paquete, Integer> {

	public List<Paquete> findByNombrePaqLikeAndStatusPaq(String match, String statusPaq);

}
