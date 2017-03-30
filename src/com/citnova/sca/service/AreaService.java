package com.citnova.sca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Area;
import com.citnova.sca.repository.AreaRepository;

@Service
public class AreaService {

	@Autowired
	AreaRepository areaRepository;
	
	public List<Area> findAreaGratuitaActiva() {
		return areaRepository.findByTipoAreaAndStatusArea("Gratuito", "Activa");
	}
}
