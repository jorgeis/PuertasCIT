package com.citnova.sca.domain;

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

public class OrganizacionDireccionWrapper {
	
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
	@Override
	public String toString() {
		return "OrganizacionDireccionWrapper [idOrg=" + idOrg + ", nombreOrg=" + nombreOrg + ", siglasOrg=" + siglasOrg
				+ ", rfcOrg=" + rfcOrg + ", numTrabajadoresOrg=" + numTrabajadoresOrg + ", telefonoOrg=" + telefonoOrg
				+ ", webOrg=" + webOrg + ", giroOrg=" + giroOrg + ", sectorEmp=" + sectorEmp + ", idDir=" + idDir
				+ ", calleDir=" + calleDir + ", numExtDir=" + numExtDir + ", numIntDir=" + numIntDir + ", coloniaDir="
				+ coloniaDir + ", cpDir=" + cpDir + ", idMun=" + idMun + ", idEstado=" + idEstado + "]";
	}
}
