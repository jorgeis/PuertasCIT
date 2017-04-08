package com.citnova.sca.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class OrganizacionClienteId implements Serializable {

	private static final long serialVersionUID = -3760275272291259552L;

	private Organizacion organizacion;
	private Cliente cliente;

	@ManyToOne
	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@ManyToOne
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "OrganizacionClienteId [organizacion=" + organizacion + ", cliente=" + cliente + "]";
	}

}
