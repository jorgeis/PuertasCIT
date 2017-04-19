package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Area;

public interface AreaRepository extends CrudRepository<Area, Integer> {

	public List<Area> findByTipoAreaAndStatusArea(String tipoArea, String statusArea);
	
	public Area findByIdArea(int idArea);
}
