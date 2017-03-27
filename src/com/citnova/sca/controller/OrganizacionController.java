package com.citnova.sca.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Estado;
import com.citnova.sca.domain.Organizacion;
import com.citnova.sca.domain.OrganizacionDireccionWrapper;
import com.citnova.sca.domain.SectorEmp;
import com.citnova.sca.service.AreaService;
import com.citnova.sca.service.EstadoService;
import com.citnova.sca.service.OrganizacionService;
import com.citnova.sca.service.SectorEmpService;

@Controller
public class OrganizacionController {
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private SectorEmpService sectorEmpService;
	
	@Autowired
	private OrganizacionService organizacionService;
	
	private Timestamp time = new Timestamp(new Date().getTime());
	

	/**
	 * Formulario de solicitud de reserva de espacio gratuito
	 * */
	@RequestMapping(value="/organizacionform")
	public String showGratuitoForm(Model model) {
		
		// Consulta los sectores empresariales dados de alta en base de datos
		List<SectorEmp> sectorEmpList = sectorEmpService.findAll();
		model.addAttribute("sectorEmpList", sectorEmpList);
		
		// Consulta los Estados dados de alta en base de datos
		List<Estado> estadoList = estadoService.findAll();
		model.addAttribute("estadoList", estadoList);
		
		model.addAttribute("organizacionDireccionWrapper", new OrganizacionDireccionWrapper());
		
		return "organizacion_form";
	}
	
	
	/**
	 * Servidor JSON para búsqueda de Organizaciones
	 * */
	@RequestMapping(value="/json/search/siglasorg", produces="application/json")
	@ResponseBody
	public Map<String, Object> findOrganizacion(@RequestParam("term") String term) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		
		List<Organizacion> organizacionList = organizacionService.findBySiglasOrgLike("%" + term + "%");
		
		for (int j = 0; j < organizacionList.size(); j++) {
			Organizacion org = organizacionList.get(j);
			map.put(String.valueOf(org.getIdOrg()),
							org.getSiglasOrg());
		}
		
		System.out.println(map.size());
		System.out.println(map);
		
		return map;
	}
}