package com.citnova.sca.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Reservacion")
public class Reservacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRes;
	
	private Timestamp fhInicioRes;
	private Timestamp fhFinRes;
	private String statusRes;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.reservacion", cascade=CascadeType.ALL)
//	private Set<ContratacionPaquete> contratacionPaqueteSet = new HashSet<ContratacionPaquete>();
	
//	@ManyToOne
//	@JoinColumn(name = "idCP")
//	private ContratacionPaquete contratacionPaquete;

	public int getIdRes() {
		return idRes;
	}

	public void setIdRes(int idRes) {
		this.idRes = idRes;
	}

	public Timestamp getFhInicioRes() {
		return fhInicioRes;
	}

	public void setFhInicioRes(Timestamp fhInicioRes) {
		this.fhInicioRes = fhInicioRes;
	}

	public Timestamp getFhFinRes() {
		return fhFinRes;
	}

	public void setFhFinRes(Timestamp fhFinRes) {
		this.fhFinRes = fhFinRes;
	}

	public String getStatusRes() {
		return statusRes;
	}

	public void setStatusRes(String statusRes) {
		this.statusRes = statusRes;
	}

	
	
	
}
