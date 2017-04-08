package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Organizacion;
import com.citnova.sca.domain.OrganizacionCliente;

public interface OrganizacionClienteRepository extends CrudRepository<OrganizacionCliente, Integer> {
	
	public List<OrganizacionCliente> findByPkOrganizacionIdOrg(int idOrg);

}
