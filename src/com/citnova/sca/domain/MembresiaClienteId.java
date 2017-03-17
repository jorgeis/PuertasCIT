package com.citnova.sca.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class MembresiaClienteId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6806442013627877544L;

	private Membresia membresia;
	private Cliente cliente;

	@ManyToOne
	public Membresia getMembresia() {
		return membresia;
	}

	public void setMembresia(Membresia membresia) {
		this.membresia = membresia;
	}

	@ManyToOne
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}
