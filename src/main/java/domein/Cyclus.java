package domein;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cyclus {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Enumerated(EnumType.STRING)
	private CyclusStatus status;
	private LocalDate startDatum;
	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.PERSIST,  CascadeType.REMOVE})
	private List<Registratie> registraties;
	public Cyclus(LocalDate date) {
		this.startDatum=date;
	}
	protected Cyclus() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDate getStartDatum() {
		return startDatum;
	}
	public void setStartDatum(LocalDate startDatum) {
		this.startDatum = startDatum;
	}
	public List<Registratie> getRegistraties() {
		return registraties;
	}
	public void setRegistraties(List<Registratie> registraties) {
		this.registraties = registraties;
	}
	@Override
	public String toString() {
		return this.startDatum.toString();
	}
	public void voegRegistratieToe(Registratie resultaat) {
		this.registraties.add(resultaat);
	}
	
}
