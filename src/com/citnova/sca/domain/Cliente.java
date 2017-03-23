package com.citnova.sca.domain;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Cliente")
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCli;
	private String emailAltCli;
	private String passCli;
	private String sexoCli;
	private String telFijoCli;
	private String telMovilCli;
	private Date fNacCli;
	private Timestamp fhCreaCli;
	private String ocupacionCli;
	private String objetivoCli;
	private String avatarCli;
	private String statusCli;
	private String passAreaCli;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.cliente", cascade=CascadeType.ALL)
	private Set<OrganizacionCliente> organizacionClienteSet = new HashSet<OrganizacionCliente>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.cliente", cascade=CascadeType.ALL)
	private Set<MembresiaCliente> membresiaClienteSet = new HashSet<MembresiaCliente>();
	
	@OneToOne
	@JoinColumn(name = "idPer")
	private Persona persona;
	
	@ManyToOne
	@JoinColumn(name = "idDir")
	private Direccion direccion;

	@OneToMany(mappedBy = "cliente")
	private Set<Contratacion> contratacionSet = new HashSet<Contratacion>();
	
//	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
//	@JoinTable(name = "Cliente_Contratacion_Paquete",
//				joinColumns = { @JoinColumn(name = "idCli") },
//				inverseJoinColumns = { @JoinColumn(name = "idCP") })
//	private List<ContratacionPaquete> contratacionPaqueteList = new ArrayList<ContratacionPaquete>();
	
	@OneToMany(mappedBy = "cliente")
	private Set<Uso> usoSet = new HashSet<Uso>();
	
	public Cliente() {
		
	}
	
	public Cliente(String emailAltCli, String passCli, String sexoCli, String telFijoCli, String telMovilCli,
			Date fNacCli, Timestamp fhCreaCli, String ocupacionCli, String objetivoCli, String avatarCli,
			String statusCli, String passAreaCli) {
		this.emailAltCli = emailAltCli;
		this.passCli = passCli;
		this.sexoCli = sexoCli;
		this.telFijoCli = telFijoCli;
		this.telMovilCli = telMovilCli;
		this.fNacCli = fNacCli;
		this.fhCreaCli = fhCreaCli;
		this.ocupacionCli = ocupacionCli;
		this.objetivoCli = objetivoCli;
		this.avatarCli = avatarCli;
		this.statusCli = statusCli;
		this.passAreaCli = passAreaCli;
	}

	public int getIdCli() {
		return idCli;
	}

	public void setIdCli(int idCli) {
		this.idCli = idCli;
	}

	public String getEmailAltCli() {
		return emailAltCli;
	}

	public void setEmailAltCli(String emailAltCli) {
		this.emailAltCli = emailAltCli;
	}

	public String getPassCli() {
		return passCli;
	}

	public void setPassCli(String passCli) {
		this.passCli = passCli;
	}

	public String getSexoCli() {
		return sexoCli;
	}

	public void setSexoCli(String sexoCli) {
		this.sexoCli = sexoCli;
	}

	public String getTelFijoCli() {
		return telFijoCli;
	}

	public void setTelFijoCli(String telFijoCli) {
		this.telFijoCli = telFijoCli;
	}

	public String getTelMovilCli() {
		return telMovilCli;
	}

	public void setTelMovilCli(String telMovilCli) {
		this.telMovilCli = telMovilCli;
	}

	public Date getfNacCli() {
		return fNacCli;
	}

	public void setfNacCli(Date fNacCli) {
		this.fNacCli = fNacCli;
	}

	public Timestamp getFhCreaCli() {
		return fhCreaCli;
	}

	public void setFhCreaCli(Timestamp fhCreaCli) {
		this.fhCreaCli = fhCreaCli;
	}

	public String getOcupacionCli() {
		return ocupacionCli;
	}

	public void setOcupacionCli(String ocupacionCli) {
		this.ocupacionCli = ocupacionCli;
	}

	public String getObjetivoCli() {
		return objetivoCli;
	}

	public void setObjetivoCli(String objetivoCli) {
		this.objetivoCli = objetivoCli;
	}

	public String getAvatarCli() {
		return avatarCli;
	}

	public void setAvatarCli(String avatarCli) {
		this.avatarCli = avatarCli;
	}

	public String getStatusCli() {
		return statusCli;
	}

	public void setStatusCli(String statusCli) {
		this.statusCli = statusCli;
	}

	public String getPassAreaCli() {
		return passAreaCli;
	}

	public void setPassAreaCli(String passAreaCli) {
		this.passAreaCli = passAreaCli;
	}

	public Set<OrganizacionCliente> getOrganizacionClienteSet() {
		return organizacionClienteSet;
	}

	public void setOrganizacionClienteSet(Set<OrganizacionCliente> organizacionClienteSet) {
		this.organizacionClienteSet = organizacionClienteSet;
	}

	public Set<MembresiaCliente> getMembresiaClienteSet() {
		return membresiaClienteSet;
	}

	public void setMembresiaClienteSet(Set<MembresiaCliente> membresiaClienteSet) {
		this.membresiaClienteSet = membresiaClienteSet;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Set<Contratacion> getContratacionSet() {
		return contratacionSet;
	}

	public void setContratacionSet(Set<Contratacion> contratacionSet) {
		this.contratacionSet = contratacionSet;
	}

	

	public Set<Uso> getUsoSet() {
		return usoSet;
	}

	public void setUsoSet(Set<Uso> usoSet) {
		this.usoSet = usoSet;
	}

	@Override
	public String toString() {
		return "Cliente [idCli=" + idCli + ", emailAltCli=" + emailAltCli + ", passCli=" + passCli + ", sexoCli="
				+ sexoCli + ", telFijoCli=" + telFijoCli + ", telMovilCli=" + telMovilCli + ", fNacCli=" + fNacCli
				+ ", fhCreaCli=" + fhCreaCli + ", ocupacionCli=" + ocupacionCli + ", objetivoCli=" + objetivoCli
				+ ", avatarCli=" + avatarCli + ", statusCli=" + statusCli + ", passAreaCli=" + passAreaCli + "]";
	}

	
	
	
	
	
}
