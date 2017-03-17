package com.citnova.sca.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class MembresiaOrganizacionId implements Serializable {

	private static final long serialVersionUID = -3743513259813122971L;

	private Membresia membresia;
	private Organizacion organizacion;

	@ManyToOne
	public Membresia getMembresia() {
		return membresia;
	}

	public void setMembresia(Membresia membresia) {
		this.membresia = membresia;
	}

	@ManyToOne
	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

}
