package com.citnova.sca.config;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.citnova.sca.domain.Admin;
import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.repository.AdminRepository;
import com.citnova.sca.repository.PersonaRepository;
import com.citnova.sca.service.AdminService;
import com.citnova.sca.util.Constants;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PersonaRepository personaRepository;

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		// Datos del form de login
		String principal = authentication.getName();
		String credentials = (String) authentication.getCredentials();

		User user = (User) customUserDetailsService.loadUserByUsername(principal);
//		User user = null;
		
		if (user != null) {
			// Comprobar contraseña
			if (passwordEncoder.matches(credentials, user.getPassword())) {
//				System.out.println("Login correcto" + user.getAuthorities());
				
				// Guardar fecha y hora de último acceso
				Persona persona = personaRepository.findByEmailPer(principal);
				Admin admin = persona.getAdmin();
				Cliente cliente = persona.getCliente();
				
				if(admin != null){
					if(admin.getStatusAd().equals(Constants.STATUS_ACTIVE)){
						Timestamp time = new Timestamp(new Date().getTime());
						admin.setFhAccesoAd(time);
						adminService.save(admin);
						return new UsernamePasswordAuthenticationToken(principal, user.getPassword(), user.getAuthorities());
					}
					else{
						System.out.println("Error de login 1: " + principal);
						throw new BadCredentialsException("Error de login 1");
					}
				}
				if(cliente != null){
					if(cliente.getStatusCli().equals(Constants.STATUS_ACTIVE)){
						return new UsernamePasswordAuthenticationToken(principal, user.getPassword(), user.getAuthorities());
					}
					else{
						System.out.println("Error de login 2: " + principal);
						throw new BadCredentialsException("Error de login 2");
					}
				}
				return new UsernamePasswordAuthenticationToken(principal, user.getPassword(), user.getAuthorities());
			}
			else{
				System.out.println("Error de login 3: " + principal);
				throw new BadCredentialsException("Error de login 3");
			}
		} 
		else {
			System.out.println("Error de login 4: " + principal);
			throw new BadCredentialsException("Error de login 4");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
