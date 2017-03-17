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


@Entity
@Table(name = "Organizacion")
public class Organizacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrg;
	
	private String nombreOrg;
	private String siglasOrg;
	private String rfcOrg;
	private int numTrabajadoresOrg;
	private String telefonoOrg;
	private String webOrg;
	private String giroOrg;
	
	@ManyToOne
	@JoinColumn(name = "idDir")
	private Direccion direccion;
	
	@ManyToOne
	@JoinColumn(name = "idSE")
	private SectorEmp sectorEmp;
	
	@OneToMany(mappedBy = "organizacion")
	private Set<Gratuito> gratuitoSet;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.organizacion", cascade=CascadeType.ALL)
	private Set<OrganizacionCliente> organizacionClienteSet = new HashSet<OrganizacionCliente>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.organizacion", cascade=CascadeType.ALL)
	private Set<MembresiaOrganizacion> membresiaOrganizacionSet = new HashSet<MembresiaOrganizacion>();

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

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public SectorEmp getSectorEmp() {
		return sectorEmp;
	}

	public void setSectorEmp(SectorEmp sectorEmp) {
		this.sectorEmp = sectorEmp;
	}

	public Set<Gratuito> getGratuitoSet() {
		return gratuitoSet;
	}

	public void setGratuitoSet(Set<Gratuito> gratuitoSet) {
		this.gratuitoSet = gratuitoSet;
	}

	public Set<OrganizacionCliente> getOrganizacionClienteSet() {
		return organizacionClienteSet;
	}

	public void setOrganizacionClienteSet(Set<OrganizacionCliente> organizacionClienteSet) {
		this.organizacionClienteSet = organizacionClienteSet;
	}

	public Set<MembresiaOrganizacion> getMembresiaOrganizacionSet() {
		return membresiaOrganizacionSet;
	}

	public void setMembresiaOrganizacionSet(Set<MembresiaOrganizacion> membresiaOrganizacionSet) {
		this.membresiaOrganizacionSet = membresiaOrganizacionSet;
	}

	
}
