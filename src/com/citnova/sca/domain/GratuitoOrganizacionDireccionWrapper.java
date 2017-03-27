package com.citnova.sca.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class GratuitoOrganizacionDireccionWrapper {
	
	//Gratuito
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
	private String desicionGra;
	
	private int areaGra;
	
	// Organización
	private int idOrg;
	private String nombreOrg;
	private String siglasOrg;
	private String rfcOrg;
	private int numTrabajadoresOrg;
	private String telefonoOrg;
	private String webOrg;
	private String giroOrg;
	
	// Sector Empresarial
	private int sectorEmp;
	
	// Direccion
	private int idDir;
	private String calleDir;
	private String numExtDir;
	private String numIntDir;
	private String coloniaDir;
	private String cpDir;
	
	//Municipio
	private int idMun;
	private int idEstado;
	
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
	public String getDesicionGra() {
		return desicionGra;
	}
	public void setDesicionGra(String desicionGra) {
		this.desicionGra = desicionGra;
	}
	public int getIdOrg() {
		return idOrg;
	}
	public void setIdOrg(int idOrg) {
		this.idOrg = idOrg;
	}
	public String getNombreOrg() {
		return nombreOrg;
	}
	public void setNombreOrg(String nombreOrg) {
		this.nombreOrg = nombreOrg;
	}
	public String getSiglasOrg() {
		return siglasOrg;
	}
	public void setSiglasOrg(String siglasOrg) {
		this.siglasOrg = siglasOrg;
	}
	public String getRfcOrg() {
		return rfcOrg;
	}
	public void setRfcOrg(String rfcOrg) {
		this.rfcOrg = rfcOrg;
	}
	public int getNumTrabajadoresOrg() {
		return numTrabajadoresOrg;
	}
	public void setNumTrabajadoresOrg(int numTrabajadoresOrg) {
		this.numTrabajadoresOrg = numTrabajadoresOrg;
	}
	public String getTelefonoOrg() {
		return telefonoOrg;
	}
	public void setTelefonoOrg(String telefonoOrg) {
		this.telefonoOrg = telefonoOrg;
	}
	public String getWebOrg() {
		return webOrg;
	}
	public void setWebOrg(String webOrg) {
		this.webOrg = webOrg;
	}
	public String getGiroOrg() {
		return giroOrg;
	}
	public void setGiroOrg(String giroOrg) {
		this.giroOrg = giroOrg;
	}
	public int getSectorEmp() {
		return sectorEmp;
	}
	public void setSectorEmp(int sectorEmp) {
		this.sectorEmp = sectorEmp;
	}
	public int getIdDir() {
		return idDir;
	}
	public void setIdDir(int idDir) {
		this.idDir = idDir;
	}
	public String getCalleDir() {
		return calleDir;
	}
	public void setCalleDir(String calleDir) {
		this.calleDir = calleDir;
	}
	public String getNumExtDir() {
		return numExtDir;
	}
	public void setNumExtDir(String numExtDir) {
		this.numExtDir = numExtDir;
	}
	public String getNumIntDir() {
		return numIntDir;
	}
	public void setNumIntDir(String numIntDir) {
		this.numIntDir = numIntDir;
	}
	public String getColoniaDir() {
		return coloniaDir;
	}
	public void setColoniaDir(String coloniaDir) {
		this.coloniaDir = coloniaDir;
	}
	public String getCpDir() {
		return cpDir;
	}
	public void setCpDir(String cpDir) {
		this.cpDir = cpDir;
	}
	public int getIdMun() {
		return idMun;
	}
	public void setIdMun(int idMun) {
		this.idMun = idMun;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	public int getAreaGra() {
		return areaGra;
	}
	public void setAreaGra(int areaGra) {
		this.areaGra = areaGra;
	}
	@Override
	public String toString() {
		return "GratuitoOrganizacionDireccionWrapper [idGra=" + idGra + ", nombreUsrGra=" + nombreUsrGra
				+ ", apPatUsrGra=" + apPatUsrGra + ", apMatUsrGra=" + apMatUsrGra + ", cargoUsrGra=" + cargoUsrGra
				+ ", telefonoUsrGra=" + telefonoUsrGra + ", emailUsrGra=" + emailUsrGra + ", sexoUsrGra=" + sexoUsrGra
				+ ", nombreEveGra=" + nombreEveGra + ", objetivoEveGra=" + objetivoEveGra + ", impactoEveGra="
				+ impactoEveGra + ", numAsistEveGra=" + numAsistEveGra + ", fhInicioEveGra=" + fhInicioEveGra
				+ ", fhFinEveGra=" + fhFinEveGra + ", poblacionObjEveGra=" + poblacionObjEveGra + ", nombreRespGra="
				+ nombreRespGra + ", apPatRespGra=" + apPatRespGra + ", apMatRespGra=" + apMatRespGra
				+ ", cargoRespGra=" + cargoRespGra + ", statusGra=" + statusGra + ", comentariosGra=" + comentariosGra
				+ ", desicionGra=" + desicionGra + ", areaGra=" + areaGra + ", idOrg=" + idOrg + ", nombreOrg="
				+ nombreOrg + ", siglasOrg=" + siglasOrg + ", rfcOrg=" + rfcOrg + ", numTrabajadoresOrg="
				+ numTrabajadoresOrg + ", telefonoOrg=" + telefonoOrg + ", webOrg=" + webOrg + ", giroOrg=" + giroOrg
				+ ", sectorEmp=" + sectorEmp + ", idDir=" + idDir + ", calleDir=" + calleDir + ", numExtDir="
				+ numExtDir + ", numIntDir=" + numIntDir + ", coloniaDir=" + coloniaDir + ", cpDir=" + cpDir
				+ ", idMun=" + idMun + ", idEstado=" + idEstado + "]";
	}
}