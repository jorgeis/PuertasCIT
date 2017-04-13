package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.OrganizacionCliente;
import com.citnova.sca.repository.OrganizacionClienteRepository;
import com.citnova.sca.util.Constants;

@Service
public class OrganizacionClienteService {
	
	@Autowired
	private OrganizacionClienteRepository organizacionClienteRepository;

	public void save(OrganizacionCliente organizacionCliente){
		organizacionClienteRepository.save(organizacionCliente);
	}

	public List<OrganizacionCliente> findAll() {
		return (List<OrganizacionCliente>) organizacionClienteRepository.findAll();
	}
	
	public List<OrganizacionCliente> findAllByIdOrg(int idOrg) {
		return organizacionClienteRepository.findByPkOrganizacionIdOrg(idOrg);
	}
	
	public List<OrganizacionCliente> findByIdOrgAndStatusActivo(int idOrg) {
		return organizacionClienteRepository.findByPkOrganizacionIdOrgAndStatusOC(idOrg, Constants.STATUS_ACTIVE);
	}
	
	public OrganizacionCliente findOneByIdOrgAndIdCli(int idOrg, int idCli) {
		return organizacionClienteRepository.findByPkOrganizacionIdOrgAndPkClienteIdCli(idOrg, idCli);
	}
}
