package com.citnova.sca.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Uso")
public class Uso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUso;
	
	private int tiempoRestanteUso;
	
	@OneToMany(mappedBy = "uso")
	private Set<Visita> visitaSet = new HashSet<Visita>();
	
//	@ManyToOne
//	@JoinColumn(name = "idPA")
//	private PaqueteArea paqueteArea;
	
	@ManyToOne
	@JoinColumn(name = "idCli")
	private Cliente cliente;

	public int getIdUso() {
		return idUso;
	}

	public void setIdUso(int idUso) {
		this.idUso = idUso;
	}

	public int getTiempoRestanteUso() {
		return tiempoRestanteUso;
	}

	public void setTiempoRestanteUso(int tiempoRestanteUso) {
		this.tiempoRestanteUso = tiempoRestanteUso;
	}

	public Set<Visita> getVisitaSet() {
		return visitaSet;
	}

	public void setVisitaSet(Set<Visita> visitaSet) {
		this.visitaSet = visitaSet;
	}

	

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

}
