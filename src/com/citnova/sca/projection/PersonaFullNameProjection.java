package com.citnova.sca.projection;

import org.springframework.beans.factory.annotation.Value;

public interface PersonaFullNameProjection {
	
	@Value("#{target.nombrePer} #{target.apPatPer} #{target.apMatPer}")
	public String getFullName();
}
