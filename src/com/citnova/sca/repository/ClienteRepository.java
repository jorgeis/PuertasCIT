package com.citnova.sca.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer>{
	
	public Page<Cliente> findAll(Pageable pageable);

}
