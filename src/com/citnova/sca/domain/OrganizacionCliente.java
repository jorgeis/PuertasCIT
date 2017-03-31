package com.citnova.sca.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Organizacion_Cliente")
@AssociationOverrides({ 
	@AssociationOverride(name = "pk.organizacion", joinColumns = @JoinColumn(name = "idOrg")),
	@AssociationOverride(name = "pk.cliente", joinColumns = @JoinColumn(name = "idCli")) })
public class OrganizacionCliente {

	private OrganizacionClienteId pk = new OrganizacionClienteId();

	// Campos extras
	private String statusOC;
	private String cargoOC;
	private String passOC;

	public OrganizacionCliente(String statusOC, String cargoOC, String passOC) {
		this.statusOC = statusOC;
		this.cargoOC = cargoOC;
		this.passOC = passOC;
	}

	@EmbeddedId
	public OrganizacionClienteId getPk() {
		return pk;
	}

	public void setPk(OrganizacionClienteId pk) {
		this.pk = pk;
	}

	@Transient
	public Organizacion getOrganizacion() {
		return getPk().getOrganizacion();
	}

	public void setOrganizacion(Organizacion organizacion) {
		getPk().setOrganizacion(organizacion);
	}

	@Transient
	public Cliente getCliente() {
		return getPk().getCliente();
	}

	public void setCliente(Cliente cliente) {
		getPk().setCliente(cliente);
	}

	public String getStatusOC() {
		return statusOC;
	}

	public void setStatusOC(String statusOC) {
		this.statusOC = statusOC;
	}

	public String getCargoOC() {
		return cargoOC;
	}

	public void setCargoOC(String cargoOC) {
		this.cargoOC = cargoOC;
	}

	public String getPassOC() {
		return passOC;
	}

	public void setPassOC(String passOC) {
		this.passOC = passOC;
	}
}
