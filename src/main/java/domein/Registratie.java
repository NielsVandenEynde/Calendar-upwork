package domein;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Registratie {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Enumerated(EnumType.STRING)
	private Resultaat resultaat;
	private LocalDate date;
	public Registratie(LocalDate date, Resultaat res) {
		this.date=date;
		this.resultaat=res;
	}
	protected Registratie() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Resultaat getResultaat() {
		return resultaat;
	}
	public void setResultaat(Resultaat resultaat) {
		this.resultaat = resultaat;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
