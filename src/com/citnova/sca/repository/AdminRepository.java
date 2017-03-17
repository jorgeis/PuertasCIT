package com.citnova.sca.repository;

import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer>{

	public Admin findByPassAd(String passAd);

}
