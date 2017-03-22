package com.citnova.sca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Admin;
import com.citnova.sca.domain.Notificacion;
import com.citnova.sca.repository.NotificacionRepository;
import com.citnova.sca.util.Constants;

@Service
public class NotificacionService {

	@Autowired
	private NotificacionRepository notificacionRepository;
	
	
	public Page<Notificacion> getPage(int index) {
		return notificacionRepository.findByStatus(Constants.STATUS_ACTIVE, new PageRequest(index, Constants.ITEMS_PER_PAGE, Direction.ASC, "idNot"));
	}
	
	public void save(Admin admin, Notificacion notificacion){
		notificacion.setAdmin(admin);
		notificacionRepository.save(notificacion);
	}
}
