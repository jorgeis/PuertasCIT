package com.citnova.sca.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPer;
	private String nombrePer;
	private String apPatPer;
	private String apMatPer;
	private String emailPer;
	private String curpPer;
	private Timestamp fhCreaPer;

	@OneToOne(mappedBy = "persona")
	private Cliente cliente;

	@OneToOne(mappedBy = "persona")
	private Admin admin;
	

	public Persona() {
	}

	public Persona(String nombrePer, String apPatPer, String apMatPer, String emailPer, String curpPer,
			Timestamp fhCreaPer) {
		this.nombrePer = nombrePer;
		this.apPatPer = apPatPer;
		this.apMatPer = apMatPer;
		this.emailPer = emailPer;
		this.curpPer = curpPer;
		this.fhCreaPer = fhCreaPer;
	}

	public int getIdPer() {
		return idPer;
	}

	public void setIdPer(int idPer) {
		this.idPer = idPer;
	}

	public String getNombrePer() {
		return nombrePer;
	}

	public void setNombrePer(String nombrePer) {
		this.nombrePer = nombrePer;
	}

	public String getApPatPer() {
		return apPatPer;
	}

	public void setApPatPer(String apPatPer) {
		this.apPatPer = apPatPer;
	}

	public String getApMatPer() {
		return apMatPer;
	}

	public void setApMatPer(String apMatPer) {
		this.apMatPer = apMatPer;
	}

	public String getEmailPer() {
		return emailPer;
	}

	public void setEmailPer(String emailPer) {
		this.emailPer = emailPer;
	}

	public String getCurpPer() {
		return curpPer;
	}

	public void setCurpPer(String curpPer) {
		this.curpPer = curpPer;
	}

	public Timestamp getFhCreaPer() {
		return fhCreaPer;
	}

	public void setFhCreaPer(Timestamp fhCreaPer) {
		this.fhCreaPer = fhCreaPer;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Persona [idPer=" + idPer + ", nombrePer=" + nombrePer + ", apPatPer=" + apPatPer + ", apMatPer="
				+ apMatPer + ", emailPer=" + emailPer + ", curpPer=" + curpPer + ", fhCreaPer=" + fhCreaPer + "]";
	}
	
	

	

}
