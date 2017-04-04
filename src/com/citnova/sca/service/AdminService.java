package com.citnova.sca.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Admin;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.domain.QAdmin;
import com.citnova.sca.domain.QPersona;
import com.citnova.sca.repository.AdminRepository;
import com.citnova.sca.repository.PersonaRepository;
import com.citnova.sca.util.Constants;
import com.mysema.query.jpa.impl.JPAQuery;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private EntityManager entityManager;
	
	
	public Admin findByPassAd(String passAd){
		return adminRepository.findByPassAd(passAd);
	}
	
	public void save(Admin admin){
		adminRepository.save(admin);
	}

	public List<Admin> findAll() {
		return (List<Admin>) adminRepository.findAll();
	}
	
	public Page<Admin> getPageByNombreOrApellidos(int index, String nombreOApellido) {
		return adminRepository.findAll(new PageRequest(index, Constants.ITEMS_PER_PAGE, Direction.ASC, "idAd"));
	}
	
	public Page<Admin> getPage(int index) {
		return adminRepository.findAll(new PageRequest(index, Constants.ITEMS_PER_PAGE, Direction.ASC, "idAd"));
	}
	
	public Page<Admin> getPageByStatus(String statusAd, int index) {
		return adminRepository.findByStatusAd(statusAd, new PageRequest(index, Constants.ITEMS_PER_PAGE, Direction.ASC, "idAd"));
	}

	public Admin findOne(int idAd) {
		return adminRepository.findOne(idAd);
	}
	
	public List<Admin> findAllByStatusAd(String statusAd) {
		return adminRepository.findByStatusAd(statusAd);
	}
	
	public void saveOrUpdate(Admin admin, Persona persona){
		personaRepository.save(persona);
		admin.setPersona(persona);
		adminRepository.save(admin);
	}

	public List<Admin> findByFullNameLikeAndStatusActivo(String fullName) {
		return adminRepository.findByFullNameLikeAndStatusActivo(fullName);
	}
	
	public Page<Admin> findByFullNameLikeAndStatusActivoPage(int index, String fullName){
		return adminRepository.findByFullNameLikeAndStatusActivoPage(fullName, new PageRequest(index, Constants.ITEMS_PER_PAGE));
	}

	public Admin findByEmail(String email) {
		QAdmin admin = QAdmin.admin;
		QPersona persona = QPersona.persona;
		
		return new JPAQuery(entityManager)
				.from(admin)
				.join(admin.persona, persona)
				.where(
						persona.emailPer.eq(email)
						)
				.uniqueResult(admin);
	}
	
	public Admin findByEmailPer(String emailPer){
		return adminRepository.findByPersona_EmailPer(emailPer);
	}
}