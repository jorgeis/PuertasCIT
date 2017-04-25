package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Paquete;
import com.citnova.sca.repository.PaqueteRepository;
import com.citnova.sca.util.Constants;

@Service
public class PaqueteService {

	@Autowired
	PaqueteRepository paqueteRepository;
	
	public List<Paquete> findByNombreLikeAndStatusActivo(String match) {
		return paqueteRepository.findByNombrePaqLikeAndStatusPaq("%" + match + "%", Constants.STATUS_ACTIVE);
	}
	
	
	
	
	
	
//	public List<Organizacion> findBySiglasOrgLike(String siglasOrg) {
//		return organizacionRepository.findBySiglasOrgLike(siglasOrg);
//	}
//	
//	public List<Organizacion> findByNombreOrgLike(String nombreOrg) {
//		return organizacionRepository.findByNombreOrgLike(nombreOrg);
//	}
//	
//	public void saveOrUpdate(Organizacion organizacion, Direccion direccion, Municipio municipio, SectorEmp sectorEmp){
//		direccion.setMunicipio(municipio);
//		direccionRepository.save(direccion);
//		organizacion.setDireccion(direccion);
//		organizacion.setSectorEmp(sectorEmp);
//		organizacionRepository.save(organizacion);
//	}
//
//	public Organizacion findOne(int idOrg) {
//		return organizacionRepository.findOne(idOrg);
//	}
//	
//	public List<Organizacion>findByIdCli(int idCli) {
//		return organizacionRepository.findByorganizacionClienteSet_PkClienteIdCli(idCli);
//	}
//	
//	public Page<Organizacion> getPageByIdCli(int idCli, int index) {
//		return organizacionRepository.findByorganizacionClienteSet_PkClienteIdCli(idCli, new PageRequest(index-1, Constants.ITEMS_PER_PAGE, Direction.ASC, "idOrg"));
//	}
//	
//	public Page<Organizacion> findAllPage(int index) {
//		return organizacionRepository.findAll(new PageRequest(index-1, Constants.ITEMS_PER_PAGE, Direction.ASC, "idOrg"));
//	}
//	
//	public Page<Organizacion> findBySiglasOrgLikeOrNombreOrgLikePage(int index, String param) {
//		return organizacionRepository.findBySiglasOrgLikeOrNombreOrgLike(param, param, new PageRequest(index, Constants.ITEMS_PER_PAGE, Direction.ASC, "idOrg"));
//	}
//	
//	public List<Organizacion>findBySiglasOrgLikeOrNombreOrgLike(String param) {
//		return organizacionRepository.findBySiglasOrgLikeOrNombreOrgLike(param, param);
//	}
//	
//	public List<Organizacion>findBySiglasOrgAndNombreOrg(String siglasOrg, String nombreOrg) {
//		return organizacionRepository.findBySiglasOrgAndNombreOrg(siglasOrg, nombreOrg);
//	}
}
