package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.citnova.sca.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	public Admin findByPassAd(String passAd);
	public List<Admin> findByStatusAd(String statusAd);
	public Page<Admin> findAll(Pageable pageable);
	public Page<Admin> findByStatusAd(String statusAd, Pageable pageable);
	public Admin findByPersona_EmailPer(String emailPer);
			
	// Busca registros en Admin cuyo nombre completo(Nombres, ApPat, ApMat) coincida con el parámetro de búsqueda
	// Y QUE SU STATUS SEA ACTIVO!!!
	@Query(	value = "SELECT * FROM Admin WHERE idPer IN (SELECT idPer FROM (SELECT idPer, CONCAT(nombrePer, ' ', "
			+ "apPatPer, ' ', apMatPer) AS Query1 FROM Persona) AS Query2 WHERE Query1 LIKE %?1%) AND statusAd='Activo'"
			+ "ORDER BY idPer ASC ",
//			+ "\n#pageable\n", 
		countQuery = "SELECT COUNT(*) FROM Admin WHERE idPer IN (SELECT idPer FROM (SELECT idPer, CONCAT(nombrePer, ' ', "
				+ "apPatPer, ' ', apMatPer) AS Query1 FROM Persona) AS Query2 WHERE Query1 LIKE %?1%) ORDER BY idPer ASC", 
		nativeQuery = true)
	public List<Admin> findByFullNameLikeAndStatusActivo(String fullName);
	
	// Busca registros en Admin cuyo nombre completo(Nombres, ApPat, ApMat) coincida con el parámetro de búsqueda
	// Y QUE SU STATUS SEA ACTIVO!!!
		@Query(	value = "SELECT * FROM Admin WHERE idPer IN (SELECT idPer FROM (SELECT idPer, CONCAT(nombrePer, ' ', "
				+ "apPatPer, ' ', apMatPer) AS Query1 FROM Persona) AS Query2 WHERE Query1 LIKE %?1%) AND statusAd='Activo'"
				+ "ORDER BY idPer ASC "
				+ "\n#pageable\n", 
			countQuery = "SELECT COUNT(*) FROM Admin WHERE idPer IN (SELECT idPer FROM (SELECT idPer, CONCAT(nombrePer, ' ', "
					+ "apPatPer, ' ', apMatPer) AS Query1 FROM Persona) AS Query2 WHERE Query1 LIKE %?1%) ORDER BY idPer ASC", 
			nativeQuery = true)
		public Page<Admin> findByFullNameLikeAndStatusActivoPage(String fullName, Pageable pageable);
}
