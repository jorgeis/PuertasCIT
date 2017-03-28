package com.citnova.sca.repository;

import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.SectorEmp;

public interface SectorEmpRepository extends CrudRepository<SectorEmp, Integer> {

	public SectorEmp findByIdSE(int idSE);

}
