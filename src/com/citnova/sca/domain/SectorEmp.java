package com.citnova.sca.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SectorEmp")
public class SectorEmp {
	
	@Id
	private int idSE;
	private String nombreSE;
	private String descripcionSE;
	
	@OneToMany(mappedBy = "sectorEmp")
	Set<Organizacion> organizacionSet;

	public int getIdSE() {
		return idSE;
	}

	public void setIdSE(int idSE) {
		this.idSE = idSE;
	}

	public String getNombreSE() {
		return nombreSE;
	}

	public void setNombreSE(String nombreSE) {
		this.nombreSE = nombreSE;
	}

	public String getDescripcionSE() {
		return descripcionSE;
	}

	public void setDescripcionSE(String descripcionSE) {
		this.descripcionSE = descripcionSE;
	}

	public Set<Organizacion> getOrganizacionSet() {
		return organizacionSet;
	}

	public void setOrganizacionSet(Set<Organizacion> organizacionSet) {
		this.organizacionSet = organizacionSet;
	}

	
}
