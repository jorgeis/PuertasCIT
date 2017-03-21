package com.citnova.sca.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Direccion")
public class Direccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDir;
	
	private String calleDir;
	private String numExtDir;
	private String numIntDir;
	private String coloniaDir;
	private String cpDir;
	
	@ManyToOne
	@JoinColumn(name = "idMun")
	private Municipio municipio;
	
	@OneToMany(mappedBy = "direccion")
	private Set<Organizacion> organizacionSet = new HashSet<Organizacion>();
	
	@OneToMany(mappedBy = "direccion")
	private Set<Cliente> clienteSet = new HashSet<Cliente>();
	
	public Direccion(int idDir, String calleDir, String numExtDir, String numIntDir, String coloniaDir, String cpDir) {
		super();
		this.idDir = idDir;
		this.calleDir = calleDir;
		this.numExtDir = numExtDir;
		this.numIntDir = numIntDir;
		this.coloniaDir = coloniaDir;
		this.cpDir = cpDir;
	}
	
	public Direccion() {
		
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

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public Set<Organizacion> getOrganizacionSet() {
		return organizacionSet;
	}

	public void setOrganizacionSet(Set<Organizacion> organizacionSet) {
		this.organizacionSet = organizacionSet;
	}

	public Set<Cliente> getClienteSet() {
		return clienteSet;
	}

	public void setClienteSet(Set<Cliente> clienteSet) {
		this.clienteSet = clienteSet;
	}
	
	

}
