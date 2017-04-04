package com.citnova.sca.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Area")
public class Area {
	@Id
	private int idArea;
	private String tipoArea;
	private String nombreArea;
	private String descripcionArea;
	private String statusArea;

	@OneToMany(mappedBy = "area")
	private Set<Gratuito> gratuitoSet = new HashSet<Gratuito>();

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.area", cascade = CascadeType.ALL)
//	private Set<PaqueteArea> paqueteAreaSet = new HashSet<PaqueteArea>();

	public int getIdArea() {
		return idArea;
	}

	public void setIdArea(int idArea) {
		this.idArea = idArea;
	}

	public String getTipoArea() {
		return tipoArea;
	}

	public void setTipoArea(String tipoArea) {
		this.tipoArea = tipoArea;
	}

	public String getNombreArea() {
		return nombreArea;
	}

	public void setNombreArea(String nombreArea) {
		this.nombreArea = nombreArea;
	}

	public String getDescripcionArea() {
		return descripcionArea;
	}

	public void setDescripcionArea(String descripcionArea) {
		this.descripcionArea = descripcionArea;
	}

	public String getStatusArea() {
		return statusArea;
	}

	public void setStatusArea(String statusArea) {
		this.statusArea = statusArea;
	}

	public Set<Gratuito> getGratuitoSet() {
		return gratuitoSet;
	}

	public void setGratuitoSet(Set<Gratuito> gratuitoSet) {
		this.gratuitoSet = gratuitoSet;
	}

	

}
