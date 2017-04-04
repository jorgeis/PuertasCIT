package com.citnova.sca.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Membresia_Cliente")
@AssociationOverrides({ 
		@AssociationOverride(name = "pk.membresia", joinColumns = @JoinColumn(name = "idMem")),
		@AssociationOverride(name = "pk.cliente", joinColumns = @JoinColumn(name = "idCli")) })
public class MembresiaCliente {
	
	
	private MembresiaClienteId pk = new MembresiaClienteId();
	private String statusMC;

	@EmbeddedId
	public MembresiaClienteId getPk() {
		return pk;
	}

	public void setPk(MembresiaClienteId pk) {
		this.pk = pk;
	}

	public String getStatusMC() {
		return statusMC;
	}

	public void setStatusMC(String statusMC) {
		this.statusMC = statusMC;
	}
	
	@Transient
	public Membresia getMembresia() {
		return getPk().getMembresia();
	}

	public void setMembresia(Membresia membresia) {
		getPk().setMembresia(membresia);
	}

	@Transient
	public Cliente getCliente() {
		return getPk().getCliente();
	}

	public void setCliente(Cliente cliente) {
		getPk().setCliente(cliente);
	}
	
	
	
	

}
