package com.citnova.sca.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Estado")
public class Estado {
	
	@Id
	private int idEst;
	
	private String nombreEst;

	@OneToMany(mappedBy = "estado")
	private Set<Municipio> municipioSet = new HashSet<Municipio>();

	public int getIdEst() {
		return idEst;
	}

	public void setIdEst(int idEst) {
		this.idEst = idEst;
	}

	public String getNombreEst() {
		return nombreEst;
	}

	public void setNombreEst(String nombreEst) {
		this.nombreEst = nombreEst;
	}

	public Set<Municipio> getMunicipioSet() {
		return municipioSet;
	}

	public void setMunicipioSet(Set<Municipio> municipioSet) {
		this.municipioSet = municipioSet;
	}

	@Override
	public String toString() {
		return "Estado [idEst=" + idEst + ", nombreEst=" + nombreEst + "]";
	}

	

}
