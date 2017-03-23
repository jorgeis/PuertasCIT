package com.citnova.sca.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Notificacion")
public class Notificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNot;
	private String tituloNot;
	private String contenidoNot;

	@Column(name = "visibilidadNot")
	private String visibilidad;

	@Column(name = "fhCreaNot")
	private Timestamp fhCrea;
	private Timestamp fhPubNot;

	@Column(name = "statusNot")
	private String status;

	@ManyToOne
	@JoinColumn(name = "idAd")
	private Admin admin;

	public int getIdNot() {
		return idNot;
	}

	public void setIdNot(int idNot) {
		this.idNot = idNot;
	}

	public String getTituloNot() {
		return tituloNot;
	}

	public void setTituloNot(String tituloNot) {
		this.tituloNot = tituloNot;
	}

	public String getContenidoNot() {
		return contenidoNot;
	}

	public void setContenidoNot(String contenidoNot) {
		this.contenidoNot = contenidoNot;
	}

	public String getVisibilidad() {
		return visibilidad;
	}

	public void setVisibilidad(String visibilidad) {
		this.visibilidad = visibilidad;
	}

	public Timestamp getFhCrea() {
		return fhCrea;
	}

	public void setFhCrea(Timestamp fhCrea) {
		this.fhCrea = fhCrea;
	}

	public Timestamp getFhPubNot() {
		return fhPubNot;
	}

	public void setFhPubNot(Timestamp fhPubNot) {
		this.fhPubNot = fhPubNot;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Notificacion [idNot=" + idNot + ", tituloNot=" + tituloNot + ", contenidoNot=" + contenidoNot
				+ ", visibilidadNot=" + visibilidad + ", fhCreaNot=" + fhCrea + ", fhPubNot=" + fhPubNot
				+ ", status=" + status + "]";
	}

}
