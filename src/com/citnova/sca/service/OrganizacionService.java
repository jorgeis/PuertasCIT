package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Direccion;
import com.citnova.sca.domain.Municipio;
import com.citnova.sca.domain.Organizacion;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.domain.SectorEmp;
import com.citnova.sca.repository.DireccionRepository;
import com.citnova.sca.repository.OrganizacionRepository;

@Service
public class OrganizacionService {

	@Autowired
	OrganizacionRepository organizacionRepository;
	
	@Autowired
	DireccionRepository direccionRepository;
	
	public List<Organizacion> findBySiglasOrgLike(String siglasOrg) {
		return organizacionRepository.findBySiglasOrgLike(siglasOrg);
	}
	
	public void saveOrUpdate(Organizacion organizacion, Direccion direccion, Municipio municipio, SectorEmp sectorEmp){
		direccion.setMunicipio(municipio);
		direccionRepository.save(direccion);
		organizacion.setDireccion(direccion);
		organizacion.setSectorEmp(sectorEmp);
		organizacionRepository.save(organizacion);
	}

	public Organizacion findOne(int idOrg) {
		return organizacionRepository.findOne(idOrg);
	}
}
