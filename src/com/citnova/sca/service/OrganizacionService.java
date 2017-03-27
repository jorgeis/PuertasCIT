package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Organizacion;
import com.citnova.sca.repository.OrganizacionRepository;

@Service
public class OrganizacionService {

	@Autowired
	OrganizacionRepository organizacionRepository;
	
	public List<Organizacion> findBySiglasOrgLike(String siglasOrg) {
		return organizacionRepository.findBySiglasOrgLike(siglasOrg);
	}
}
