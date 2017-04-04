package com.citnova.sca.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.citnova.sca.domain.Admin;
import com.citnova.sca.domain.Cliente;
import com.citnova.sca.domain.Persona;
import com.citnova.sca.repository.PersonaRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private PersonaRepository personaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Persona persona = personaRepository.findByEmailPer(username);
		Admin admin = null;
		Cliente cliente = null;
		User user = null;

		if(persona != null){
			admin = persona.getAdmin();
			cliente = persona.getCliente();
		}
		
		if (persona != null && admin != null && cliente != null) {
			
		}
		
		if (persona != null && admin != null) {
			authorities.add(new SimpleGrantedAuthority(admin.getRolAd()));
			user = new User(persona.getEmailPer(), admin.getPassAd(), authorities);
			return user;			
		} 
		else if(persona != null && cliente != null){
			authorities.add(new SimpleGrantedAuthority("ROLE_CLIENT"));
			user = new User(persona.getEmailPer(), cliente.getPassCli(), authorities);
			return user;
		}
		else {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
	}
}
