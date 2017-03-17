package com.citnova.sca.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@OneToOne
	@JoinColumn(name = "idPer")
	private Persona persona;

	@OneToMany(mappedBy = "admin")
	private Set<Gratuito> gratuitoSet = new HashSet<Gratuito>();

	@OneToMany(mappedBy = "admin")
	private Set<Notificacion> notificacionSet = new HashSet<Notificacion>();

	@OneToMany(mappedBy = "admin")
	private Set<Membresia> membresiaSet = new HashSet<Membresia>();

	public Admin() {
	}

	public Admin(String areaAd, String cargoAd, String telefonoAd, Timestamp fhCreaAd, String rolAd, String statusAd,
			String creadoAd) {
		this.areaAd = areaAd;
		this.cargoAd = cargoAd;
		this.telefonoAd = telefonoAd;
		this.fhCreaAd = fhCreaAd;
		this.rolAd = rolAd;
		this.statusAd = statusAd;
		this.creadoAd = creadoAd;
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

	public Timestamp getFhCreaAd() {
		return fhCreaAd;
	}

	public void setFhCreaAd(Timestamp fhCreaAd) {
		this.fhCreaAd = fhCreaAd;
	}

	public String getRolAd() {
		return rolAd;
	}

	public void setRolAd(String rolAd) {
		this.rolAd = rolAd;
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Set<Gratuito> getGratuitoSet() {
		return gratuitoSet;
	}

	public void setGratuitoSet(Set<Gratuito> gratuitoSet) {
		this.gratuitoSet = gratuitoSet;
	}

	public Set<Notificacion> getNotificacionSet() {
		return notificacionSet;
	}

	public void setNotificacionSet(Set<Notificacion> notificacionSet) {
		this.notificacionSet = notificacionSet;
	}

	public Set<Membresia> getMembresiaSet() {
		return membresiaSet;
	}

	public void setMembresiaSet(Set<Membresia> membresiaSet) {
		this.membresiaSet = membresiaSet;
	}

	@Override
	public String toString() {
		return "Admin [idAd=" + idAd + ", passAd=" + passAd + ", areaAd=" + areaAd + ", cargoAd=" + cargoAd
				+ ", telefonoAd=" + telefonoAd + ", fhCreaAd=" + fhCreaAd + ", rolAd=" + rolAd + ", statusAd="
				+ statusAd + ", creadoAd=" + creadoAd + ", fhAccesoAd=" + fhAccesoAd + "]";
	}

}
