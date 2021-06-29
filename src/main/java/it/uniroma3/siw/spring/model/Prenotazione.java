package it.uniroma3.siw.spring.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findAllPrenotazione", query = "SELECT a FROM Prenotazione a")
public class Prenotazione {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private LocalDate data;

	@Column(nullable=false)
	private int oraInizio;

	@Column(nullable=false)
	private int oraFine;
	
	@ManyToOne
	private Maestro maestro;
	
	@ManyToOne
	private Utente utente;
	
	@ManyToOne
	private Campo campo;
	
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public int getOraInizio() {
		return oraInizio;
	}
	public void setOraInizio(int oraInizio) {
		this.oraInizio = oraInizio;
	}
	public int getOraFine() {
		return oraFine;
	}
	public void setOraFine(int oraFine) {
		this.oraFine = oraFine;
	}
	public Maestro getMaestro() {
		return maestro;
	}
	public void setMaestro(Maestro maestro) {
		this.maestro = maestro;
	}
	public Utente getUtente() {
		return utente;
	}
	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	public Campo getCampo() {
		return campo;
	}
	public void setCampo(Campo campo) {
		this.campo = campo;
	}
	public long getId() {
		return id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prenotazione other = (Prenotazione) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

}
