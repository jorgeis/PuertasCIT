package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.citnova.sca.domain.Admin;
import com.citnova.sca.domain.Cliente;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	public Admin findByPassAd(String passAd);
	public List<Admin> findByStatusAd(String statusAd);
	public Page<Admin> findAll(Pageable pageable);
	
//	@Query("from ReleaseDateType AS rdt WHERE cm.rdt.cacheMedia.id = ?1")
	public Page<Admin> findByPersona_NombrePerLikeOrPersona_ApPatPerLikeOrPersona_ApMatPerLike(
			String nombre, String apPat, String apMat, Pageable pageable);
	
	
	@Query(	value = "SELECT * FROM Admin WHERE idPer IN (SELECT idPer FROM (SELECT idPer, CONCAT(nombrePer, ' ', "
			+ "apPatPer, ' ', apMatPer) AS Query1 FROM Persona) AS Query2 WHERE Query1 LIKE %?1%) ORDER BY idPer ASC "
			+ "\n#pageable\n", 
		countQuery = "SELECT COUNT(*) FROM Admin WHERE idPer IN (SELECT idPer FROM (SELECT idPer, CONCAT(nombrePer, ' ', "
				+ "apPatPer, ' ', apMatPer) AS Query1 FROM Persona) AS Query2 WHERE Query1 LIKE %?1%) ORDER BY idPer ASC", 
		nativeQuery = true)
	public Page<Admin> findByFullNameLike(String fullName, Pageable pageable);

}
