package com.citnova.sca.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.citnova.sca.domain.Area;
import com.citnova.sca.domain.Estado;
import com.citnova.sca.domain.Municipio;

public interface AreaRepository extends CrudRepository<Area, Integer> {

	public List<Area> findByTipoAreaAndStatusArea(String tipoArea, String statusArea);
}
