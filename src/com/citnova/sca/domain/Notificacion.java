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
@Table(name = "Notificacion")
public class Notificacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idNot;
	private String tituloNot;
	private String contenidoNot;
	private String visibilidadNot;
	private Timestamp fhCreaNot;
	private Timestamp fhPubNot;
	private String statusNot;
	
	
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


	public String getVisibilidadNot() {
		return visibilidadNot;
	}


	public void setVisibilidadNot(String visibilidadNot) {
		this.visibilidadNot = visibilidadNot;
	}


	public Timestamp getFhCreaNot() {
		return fhCreaNot;
	}


	public void setFhCreaNot(Timestamp fhCreaNot) {
		this.fhCreaNot = fhCreaNot;
	}


	public Timestamp getFhPubNot() {
		return fhPubNot;
	}


	public void setFhPubNot(Timestamp fhPubNot) {
		this.fhPubNot = fhPubNot;
	}


	public String getStatusNot() {
		return statusNot;
	}


	public void setStatusNot(String statusNot) {
		this.statusNot = statusNot;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	

}
