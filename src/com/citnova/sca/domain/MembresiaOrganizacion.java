package com.citnova.sca.domain;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "Membresia_Organizacion")
@AssociationOverrides({ 
		@AssociationOverride(name = "pk.membresia", joinColumns = @JoinColumn(name = "Membresia_idMem")),
		@AssociationOverride(name = "pk.organizacion", joinColumns = @JoinColumn(name = "Organizacion_idOrg")) })
public class MembresiaOrganizacion {
	
	
	private MembresiaOrganizacionId pk = new MembresiaOrganizacionId();
	
	private String statusMO;

	@EmbeddedId
	public MembresiaOrganizacionId getPk() {
		return pk;
	}

	public void setPk(MembresiaOrganizacionId pk) {
		this.pk = pk;
	}
	
	@Transient
	public Membresia getMembresia() {
		return getPk().getMembresia();
	}

	public void setMembresia(Membresia c) {
		getPk().setMembresia(c);
	}
	
	@Transient
	public Organizacion getOrganizacion() {
		return getPk().getOrganizacion();
	}

	public void setOrganizacion(Organizacion c) {
		getPk().setOrganizacion(c);
	}

	public String getStatusMO() {
		return statusMO;
	}

	public void setStatusMO(String statusMO) {
		this.statusMO = statusMO;
	}
}
