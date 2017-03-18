package com.citnova.sca.domain;

import java.sql.Timestamp;

public class PersonaAdminWrapper {

	// Persona
	private int idPer;
	private String nombrePer;
	private String apPatPer;
	private String apMatPer;
	private String emailPer;
	private String curpPer;
	private Timestamp fhCreaPer;

	// Admin
	private int idAd;
	private String passAd;
	private String areaAd;
	private String cargoAd;
	private String telefonoAd;
	private Timestamp fhCreaAd;
	private String rolAd;
	private String statusAd;
	private String creadoAd;
	private Timestamp fhAccesoAd;

	public PersonaAdminWrapper() {
	}

	public PersonaAdminWrapper(int idPer, String nombrePer, String apPatPer, String apMatPer, String emailPer,
			String curpPer, String areaAd, String cargoAd, String telefonoAd, String rolAd) {
		this.idPer = idPer;
		this.nombrePer = nombrePer;
		this.apPatPer = apPatPer;
		this.apMatPer = apMatPer;
		this.emailPer = emailPer;
		this.curpPer = curpPer;
		this.areaAd = areaAd;
		this.cargoAd = cargoAd;
		this.telefonoAd = telefonoAd;
		this.rolAd = rolAd;
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

	public String getAreaAd() {
		return areaAd;
	}

	public void setAreaAd(String areaAd) {
		this.areaAd = areaAd;
	}

	public String getCargoAd() {
		return cargoAd;
	}

	public void setCargoAd(String cargoAd) {
		this.cargoAd = cargoAd;
	}

	public String getTelefonoAd() {
		return telefonoAd;
	}

	public void setTelefonoAd(String telefonoAd) {
		this.telefonoAd = telefonoAd;
	}

	public String getRolAd() {
		return rolAd;
	}

	public void setRolAd(String rolAd) {
		this.rolAd = rolAd;
	}

	public int getIdPer() {
		return idPer;
	}

	public void setIdPer(int idPer) {
		this.idPer = idPer;
	}

	public Timestamp getFhCreaPer() {
		return fhCreaPer;
	}

	public void setFhCreaPer(Timestamp fhCreaPer) {
		this.fhCreaPer = fhCreaPer;
	}

	public int getIdAd() {
		return idAd;
	}

	public void setIdAd(int idAd) {
		this.idAd = idAd;
	}

	public String getPassAd() {
		return passAd;
	}

	public void setPassAd(String passAd) {
		this.passAd = passAd;
	}

	public Timestamp getFhCreaAd() {
		return fhCreaAd;
	}

	public void setFhCreaAd(Timestamp fhCreaAd) {
		this.fhCreaAd = fhCreaAd;
	}

	public String getStatusAd() {
		return statusAd;
	}

	public void setStatusAd(String statusAd) {
		this.statusAd = statusAd;
	}

	public String getCreadoAd() {
		return creadoAd;
	}

	public void setCreadoAd(String creadoAd) {
		this.creadoAd = creadoAd;
	}

	public Timestamp getFhAccesoAd() {
		return fhAccesoAd;
	}

	public void setFhAccesoAd(Timestamp fhAccesoAd) {
		this.fhAccesoAd = fhAccesoAd;
	}

	@Override
	public String toString() {
		return "PersonaAdminWrapper [idPer=" + idPer + ", nombrePer=" + nombrePer + ", apPatPer=" + apPatPer
				+ ", apMatPer=" + apMatPer + ", emailPer=" + emailPer + ", curpPer=" + curpPer + ", fhCreaPer="
				+ fhCreaPer + ", idAd=" + idAd + ", passAd=" + passAd + ", areaAd=" + areaAd + ", cargoAd=" + cargoAd
				+ ", telefonoAd=" + telefonoAd + ", fhCreaAd=" + fhCreaAd + ", rolAd=" + rolAd + ", statusAd="
				+ statusAd + ", creadoAd=" + creadoAd + ", fhAccesoAd=" + fhAccesoAd + "]";
	}

}
