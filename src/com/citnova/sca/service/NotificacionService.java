package com.citnova.sca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citnova.sca.repository.NotificacionRepository;

@Service
public class NotificacionService {

	@Autowired
	private NotificacionRepository notificacionRepository;
	
	
	
}
