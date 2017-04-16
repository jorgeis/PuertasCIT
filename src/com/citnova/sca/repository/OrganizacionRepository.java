package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Organizacion;

public interface OrganizacionRepository extends CrudRepository<Organizacion, Integer> {

	public List<Organizacion> findBySiglasOrgLike (String siglasOrg);
	
	public List<Organizacion> findByNombreOrgLike (String nombreOrg);
	
	public List<Organizacion> findByorganizacionClienteSet_PkClienteIdCli(int idCli);
	
	public Page<Organizacion> findByorganizacionClienteSet_PkClienteIdCli(int idCli, Pageable pageable);
	
	public Page<Organizacion> findAll(Pageable pageable);
	
	public Page<Organizacion> findBySiglasOrgLikeOrNombreOrgLike(String param1, String param2, Pageable pageable);
	
	public List<Organizacion> findBySiglasOrgLikeOrNombreOrgLike(String param1, String param2);
}
