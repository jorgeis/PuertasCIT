package com.citnova.sca.domain;
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
import javax.persistence.Table;




@Entity
@Table(name = "Membresia")
public class Membresia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMem;
	private String statusMem;
	private String codigoMem;
	private Timestamp fhCreaMem;
	private Timestamp fhValidaMem;
	
	@ManyToOne
	@JoinColumn(name = "idCMem")
	private CMembresia cMembresia;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.membresia", cascade=CascadeType.ALL)
	private Set<MembresiaOrganizacion> membresiaOrganizacionSet = new HashSet<MembresiaOrganizacion>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.membresia", cascade=CascadeType.ALL)
	private Set<MembresiaCliente> membresiaClienteSet = new HashSet<MembresiaCliente>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinTable(name = "Contratacion_Membresia",
				joinColumns = { @JoinColumn(name = "idMem") },
				inverseJoinColumns = { @JoinColumn(name = "idCont") })
	private List<Contratacion> contratacionList = new ArrayList<Contratacion>();
	
	
	@ManyToOne
	@JoinColumn(name = "Admin_idAd")
	private Admin admin;

	
	public Membresia() {
		
	}
	
	
	public Membresia(String statusMem, String codigoMem, Timestamp fhCreaMem, CMembresia cMembresia) {
		super();
		this.statusMem = statusMem;
		this.codigoMem = codigoMem;
		this.fhCreaMem = fhCreaMem;
		this.cMembresia = cMembresia;
	}


	public int getIdMem() {
		return idMem;
	}


	public void setIdMem(int idMem) {
		this.idMem = idMem;
	}


	public String getStatusMem() {
		return statusMem;
	}


	public void setStatusMem(String statusMem) {
		this.statusMem = statusMem;
	}


	public String getCodigoMem() {
		return codigoMem;
	}


	public void setCodigoMem(String codigoMem) {
		this.codigoMem = codigoMem;
	}


	public Timestamp getFhCreaMem() {
		return fhCreaMem;
	}


	public void setFhCreaMem(Timestamp fhCreaMem) {
		this.fhCreaMem = fhCreaMem;
	}


	public Timestamp getFhValidaMem() {
		return fhValidaMem;
	}


	public void setFhValidaMem(Timestamp fhValidaMem) {
		this.fhValidaMem = fhValidaMem;
	}


	public CMembresia getcMembresia() {
		return cMembresia;
	}


	public void setcMembresia(CMembresia cMembresia) {
		this.cMembresia = cMembresia;
	}


	public Set<MembresiaOrganizacion> getMembresiaOrganizacionSet() {
		return membresiaOrganizacionSet;
	}


	public void setMembresiaOrganizacionSet(Set<MembresiaOrganizacion> membresiaOrganizacionSet) {
		this.membresiaOrganizacionSet = membresiaOrganizacionSet;
	}


	public Set<MembresiaCliente> getMembresiaClienteSet() {
		return membresiaClienteSet;
	}


	public void setMembresiaClienteSet(Set<MembresiaCliente> membresiaClienteSet) {
		this.membresiaClienteSet = membresiaClienteSet;
	}


	public List<Contratacion> getContratacionList() {
		return contratacionList;
	}


	public void setContratacionList(List<Contratacion> contratacionList) {
		this.contratacionList = contratacionList;
	}


	public Admin getAdmin() {
		return admin;
	}


	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	@Override
	public String toString() {
		return "Membresia [idMem=" + idMem + ", statusMem=" + statusMem + ", codigoMem=" + codigoMem + ", fhCreaMem="
				+ fhCreaMem + ", fhValidaMem=" + fhValidaMem + "]";
	}
	
	
	

}
