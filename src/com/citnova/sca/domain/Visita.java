package com.citnova.sca.domain;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Visita")
public class Visita {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idVis;
	
	private Timestamp fhEntradaVis;
	private Timestamp fhSalidaVis;
	private int tiempoVis;
	
	@ManyToOne
	@JoinColumn(name = "idUso")
	private Uso uso;

	public int getIdVis() {
		return idVis;
	}

	public void setIdVis(int idVis) {
		this.idVis = idVis;
	}

	public Timestamp getFhEntradaVis() {
		return fhEntradaVis;
	}

	public void setFhEntradaVis(Timestamp fhEntradaVis) {
		this.fhEntradaVis = fhEntradaVis;
	}

	public Timestamp getFhSalidaVis() {
		return fhSalidaVis;
	}

	public void setFhSalidaVis(Timestamp fhSalidaVis) {
		this.fhSalidaVis = fhSalidaVis;
	}

	public int getTiempoVis() {
		return tiempoVis;
	}

	public void setTiempoVis(int tiempoVis) {
		this.tiempoVis = tiempoVis;
	}

	public Uso getUso() {
		return uso;
	}

	public void setUso(Uso uso) {
		this.uso = uso;
	}
	
	

}
