package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.SectorEmp;
import com.citnova.sca.repository.SectorEmpRepository;

@Service
public class SectorEmpService {

	@Autowired
	SectorEmpRepository sectorEmpRepository;
	
	public List<SectorEmp> findAll() {
		return (List<SectorEmp>) sectorEmpRepository.findAll();
	}

	public SectorEmp findByIdSE(int seID) {
		return sectorEmpRepository.findByIdSE(seID);
	}
}
