package com.citnova.sca.domain;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Municipio")
public class Municipio {

	@Id
	private int idMun;
	
	private String nombreMun;

	@ManyToOne
	@JoinColumn(name = "idEst")
	private Estado estado;
	
	@OneToMany(mappedBy = "municipio")
	private Set<Direccion> direccionSet;

	public int getIdMun() {
		return idMun;
	}

	public void setIdMun(int idMun) {
		this.idMun = idMun;
	}

	public String getNombreMun() {
		return nombreMun;
	}

	public void setNombreMun(String nombreMun) {
		this.nombreMun = nombreMun;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Set<Direccion> getDireccionSet() {
		return direccionSet;
	}

	public void setDireccionSet(Set<Direccion> direccionSet) {
		this.direccionSet = direccionSet;
	}
	
	

}
