package com.citnova.sca.domain;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Paquete")
public class Paquete {
	
	@Id
	private int idPaq;
	private String nombrePaq;
	private float costo1Paq;
	private float costo2Paq;
	private float costo3Paq;
	private String descripcionPaq;
	private String statusPaq;
	
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.paquete", cascade=CascadeType.ALL)
//	private Set<PaqueteArea> paqueteAreaSet = new HashSet<PaqueteArea>();
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.paquete", cascade=CascadeType.ALL)
//	private Set<ContratacionPaquete> contratacionPaqueteSet = new HashSet<ContratacionPaquete>();

	public int getIdPaq() {
		return idPaq;
	}

	public void setIdPaq(int idPaq) {
		this.idPaq = idPaq;
	}

	public String getNombrePaq() {
		return nombrePaq;
	}

	public void setNombrePaq(String nombrePaq) {
		this.nombrePaq = nombrePaq;
	}

	public float getCosto1Paq() {
		return costo1Paq;
	}

	public void setCosto1Paq(float costo1Paq) {
		this.costo1Paq = costo1Paq;
	}

	public float getCosto2Paq() {
		return costo2Paq;
	}

	public void setCosto2Paq(float costo2Paq) {
		this.costo2Paq = costo2Paq;
	}

	public float getCosto3Paq() {
		return costo3Paq;
	}

	public void setCosto3Paq(float costo3Paq) {
		this.costo3Paq = costo3Paq;
	}

	public String getDescripcionPaq() {
		return descripcionPaq;
	}

	public void setDescripcionPaq(String descripcionPaq) {
		this.descripcionPaq = descripcionPaq;
	}

	public String getStatusPaq() {
		return statusPaq;
	}

	public void setStatusPaq(String statusPaq) {
		this.statusPaq = statusPaq;
	}

	
	
	
}
