package com.citnova.sca.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Contratacion")
public class Contratacion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCont;
	private Timestamp fhCont;
	private float costoCont;
	private float ivaCont;
	private String refCont;
	
	@ManyToMany(mappedBy = "contratacionList")
	private List<Membresia> membresiaList = new ArrayList<Membresia>();
	
	
	@ManyToOne
	@JoinColumn(name = "idCli")
	private Cliente cliente;


	public int getIdCont() {
		return idCont;
	}


	public void setIdCont(int idCont) {
		this.idCont = idCont;
	}


	public Timestamp getFhCont() {
		return fhCont;
	}


	public void setFhCont(Timestamp fhCont) {
		this.fhCont = fhCont;
	}


	public float getCostoCont() {
		return costoCont;
	}


	public void setCostoCont(float costoCont) {
		this.costoCont = costoCont;
	}


	public float getIvaCont() {
		return ivaCont;
	}


	public void setIvaCont(float ivaCont) {
		this.ivaCont = ivaCont;
	}


	public String getRefCont() {
		return refCont;
	}


	public void setRefCont(String refCont) {
		this.refCont = refCont;
	}


	public List<Membresia> getMembresiaList() {
		return membresiaList;
	}


	public void setMembresiaList(List<Membresia> membresiaList) {
		this.membresiaList = membresiaList;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}
