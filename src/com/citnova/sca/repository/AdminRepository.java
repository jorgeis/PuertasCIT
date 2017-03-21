package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer>{

	public Admin findByPassAd(String passAd);
	public List<Admin> findByStatusAd(String statusAd);
	public Page<Admin> findAll(Pageable pageable);

}
