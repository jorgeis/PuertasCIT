package com.citnova.sca.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Gratuito")
public class Gratuito {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGra;
	
	private String nombreUsrGra;
	private String apPatUsrGra;
	private String apMatUsrGra;
	private String cargoUsrGra;
	private String telefonoUsrGra;
	private String emailUsrGra;
	private String sexoUsrGra;
	private String nombreEveGra;
	private String objetivoEveGra;
	private String impactoEveGra;
	private int numAsistEveGra;
	private Timestamp fhInicioEveGra;
	private Timestamp fhFinEveGra;
	private String poblacionObjEveGra;
	private String nombreRespGra;
	private String apPatRespGra;
	private String apMatRespGra;
	private String cargoRespGra;
	private String statusGra;
	private String comentariosGra;
	private String decisionGra;
	
	@ManyToOne
	@JoinColumn(name = "idOrg")
	private Organizacion organizacion;
	
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "idAd")
	private Admin admin;
	
	@ManyToOne
	@JoinColumn(name = "idArea")
	private Area area;

	public int getIdGra() {
		return idGra;
	}

	public void setIdGra(int idGra) {
		this.idGra = idGra;
	}

	public String getNombreUsrGra() {
		return nombreUsrGra;
	}

	public void setNombreUsrGra(String nombreUsrGra) {
		this.nombreUsrGra = nombreUsrGra;
	}

	public String getApPatUsrGra() {
		return apPatUsrGra;
	}

	public void setApPatUsrGra(String apPatUsrGra) {
		this.apPatUsrGra = apPatUsrGra;
	}

	public String getApMatUsrGra() {
		return apMatUsrGra;
	}

	public void setApMatUsrGra(String apMatUsrGra) {
		this.apMatUsrGra = apMatUsrGra;
	}

	public String getCargoUsrGra() {
		return cargoUsrGra;
	}

	public void setCargoUsrGra(String cargoUsrGra) {
		this.cargoUsrGra = cargoUsrGra;
	}

	public String getTelefonoUsrGra() {
		return telefonoUsrGra;
	}

	public void setTelefonoUsrGra(String telefonoUsrGra) {
		this.telefonoUsrGra = telefonoUsrGra;
	}

	public String getEmailUsrGra() {
		return emailUsrGra;
	}

	public void setEmailUsrGra(String emailUsrGra) {
		this.emailUsrGra = emailUsrGra;
	}

	public String getSexoUsrGra() {
		return sexoUsrGra;
	}

	public void setSexoUsrGra(String sexoUsrGra) {
		this.sexoUsrGra = sexoUsrGra;
	}

	public String getNombreEveGra() {
		return nombreEveGra;
	}

	public void setNombreEveGra(String nombreEveGra) {
		this.nombreEveGra = nombreEveGra;
	}

	public String getObjetivoEveGra() {
		return objetivoEveGra;
	}

	public void setObjetivoEveGra(String objetivoEveGra) {
		this.objetivoEveGra = objetivoEveGra;
	}

	public String getImpactoEveGra() {
		return impactoEveGra;
	}

	public void setImpactoEveGra(String impactoEveGra) {
		this.impactoEveGra = impactoEveGra;
	}

	public int getNumAsistEveGra() {
		return numAsistEveGra;
	}

	public void setNumAsistEveGra(int numAsistEveGra) {
		this.numAsistEveGra = numAsistEveGra;
	}

	public Timestamp getFhInicioEveGra() {
		return fhInicioEveGra;
	}

	public void setFhInicioEveGra(Timestamp fhInicioEveGra) {
		this.fhInicioEveGra = fhInicioEveGra;
	}

	public Timestamp getFhFinEveGra() {
		return fhFinEveGra;
	}

	public void setFhFinEveGra(Timestamp fhFinEveGra) {
		this.fhFinEveGra = fhFinEveGra;
	}

	public String getPoblacionObjEveGra() {
		return poblacionObjEveGra;
	}

	public void setPoblacionObjEveGra(String poblacionObjEveGra) {
		this.poblacionObjEveGra = poblacionObjEveGra;
	}

	public String getNombreRespGra() {
		return nombreRespGra;
	}

	public void setNombreRespGra(String nombreRespGra) {
		this.nombreRespGra = nombreRespGra;
	}

	public String getApPatRespGra() {
		return apPatRespGra;
	}

	public void setApPatRespGra(String apPatRespGra) {
		this.apPatRespGra = apPatRespGra;
	}

	public String getApMatRespGra() {
		return apMatRespGra;
	}

	public void setApMatRespGra(String apMatRespGra) {
		this.apMatRespGra = apMatRespGra;
	}

	public String getCargoRespGra() {
		return cargoRespGra;
	}

	public void setCargoRespGra(String cargoRespGra) {
		this.cargoRespGra = cargoRespGra;
	}

	public String getStatusGra() {
		return statusGra;
	}

	public void setStatusGra(String statusGra) {
		this.statusGra = statusGra;
	}

	public String getComentariosGra() {
		return comentariosGra;
	}

	public void setComentariosGra(String comentariosGra) {
		this.comentariosGra = comentariosGra;
	}

	public String getDecisionGra() {
		return decisionGra;
	}

	public void setDecisionGra(String decisionGra) {
		this.decisionGra = decisionGra;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}	
}