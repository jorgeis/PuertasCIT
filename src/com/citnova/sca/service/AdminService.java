package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Admin;
import com.citnova.sca.repository.AdminRepository;
import com.citnova.sca.repository.PersonaRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	public Admin findByPassAd(String passAd){
		return adminRepository.findByPassAd(passAd);
	}
	
	public void save(Admin admin){
		adminRepository.save(admin);
	}

	public List<Admin> findAll() {
		return (List<Admin>) adminRepository.findAll();
	}

}
