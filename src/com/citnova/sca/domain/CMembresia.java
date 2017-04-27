package com.citnova.sca.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CMembresia")
public class CMembresia {
	
	@Id
	private int idCMem;
	private String nombreCMem;
	private String tipoCMem;
	private String descripcionCMem;
	private float costoCMem;
	private Timestamp fhCreaCMem;
	private String statusCMem;
	
	@OneToMany(mappedBy = "cMembresia")
	private Set<Membresia> membresiaSet = new HashSet<Membresia>();

	public int getIdCMem() {
		return idCMem;
	}

	public void setIdCMem(int idCMem) {
		this.idCMem = idCMem;
	}

	public String getNombreCMem() {
		return nombreCMem;
	}

	public void setNombreCMem(String nombreCMem) {
		this.nombreCMem = nombreCMem;
	}

	public String getTipoCMem() {
		return tipoCMem;
	}

	public void setTipoCMem(String tipoCMem) {
		this.tipoCMem = tipoCMem;
	}

	public String getDescripcionCMem() {
		return descripcionCMem;
	}

	public void setDescripcionCMem(String descripcionCMem) {
		this.descripcionCMem = descripcionCMem;
	}

	public float getCostoCMem() {
		return costoCMem;
	}

	public void setCostoCMem(float costoCMem) {
		this.costoCMem = costoCMem;
	}

	public Timestamp getFhCreaCMem() {
		return fhCreaCMem;
	}

	public void setFhCreaCMem(Timestamp fhCreaCMem) {
		this.fhCreaCMem = fhCreaCMem;
	}

	public String getStatusCMem() {
		return statusCMem;
	}

	public void setStatusCMem(String statusCMem) {
		this.statusCMem = statusCMem;
	}

	public Set<Membresia> getMembresiaSet() {
		return membresiaSet;
	}

	public void setMembresiaSet(Set<Membresia> membresiaSet) {
		this.membresiaSet = membresiaSet;
	}

	@Override
	public String toString() {
		return "CMembresia [idCMem=" + idCMem + ", nombreCMem=" + nombreCMem + ", tipoCMem=" + tipoCMem
				+ ", descripcionCMem=" + descripcionCMem + ", costoCMem=" + costoCMem + ", fhCreaCMem=" + fhCreaCMem
				+ ", statusCMem=" + statusCMem + "]";
	}
	
	
}
