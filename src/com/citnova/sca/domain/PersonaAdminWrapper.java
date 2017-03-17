package com.citnova.sca.domain;

import java.sql.Timestamp;

public class PersonaAdminWrapper {

	private String nombrePer;
	private String apPatPer;
	private String apMatPer;
	private String emailPer;
	private String curpPer;

	private String areaAd;
	private String cargoAd;
	private String telefonoAd;
	private String rolAd;

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

	@Override
	public String toString() {
		return "PersonaAdminWrapper [nombrePer=" + nombrePer + ", apPatPer=" + apPatPer + ", apMatPer=" + apMatPer
				+ ", emailPer=" + emailPer + ", curpPer=" + curpPer + ", areaAd=" + areaAd + ", cargoAd=" + cargoAd
				+ ", telefonoAd=" + telefonoAd + ", rolAd=" + rolAd + "]";
	}
	
	

}
