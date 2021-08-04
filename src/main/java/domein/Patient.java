package domein;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Patient {
	private Dokter dokter;
	private String voornaam;
	private String achternaam;
	@Id 
	private String gebruikersnaam;
	private LocalDate startDatum;
	private LocalDate geboorteDatum;
	private String hashww;
	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.PERSIST,  CascadeType.REMOVE})
	private List<Cyclus> cyclussen;
	protected Patient() {}
	public Patient(String voornaam,String achternaam,String gebruikersnaam,String hashww) {
		this.voornaam=voornaam;
		this.cyclussen=new ArrayList<Cyclus>();
		this.achternaam=achternaam;
		this.gebruikersnaam=gebruikersnaam;
		this.startDatum=startDatum;
		this.hashww=hashww;
		this.startDatum=LocalDate.now();
	}
	
	public int getLeeftijd() {
		LocalDate end = LocalDate.now(); // use for age-calculation: LocalDate.now()
		long years = ChronoUnit.YEARS.between(geboorteDatum, end);
		return (int) years;
	}
	
	public void addCyclus(LocalDate date) {
		this.cyclussen.add(new Cyclus(date));
	}
	public List<Cyclus> getCyclussen() {
		return cyclussen;
	}
	public Cyclus getActieveCyclus() {
		return cyclussen.get(cyclussen.size() - 1);
	}
	public Dokter getDokter() {
		return dokter;
	}
	public void setDokter(Dokter dokter) {
		this.dokter = dokter;
	}
	public String getVoornaam() {
		return voornaam;
	}
	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	public String getAchternaam() {
		return achternaam;
	}
	public void setAchternaam(String achternaam) {
		this.achternaam = achternaam;
	}
	public String getGebruikersnaam() {
		return gebruikersnaam;
	}
	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}
	public LocalDate getStartDatum() {
		return startDatum;
	}
	public void setStartDatum(LocalDate startDatum) {
		this.startDatum = startDatum;
	}
	public LocalDate getGeboorteDatum() {
		return geboorteDatum;
	}
	public void setGeboorteDatum(LocalDate geboorteDatum) {
		this.geboorteDatum = geboorteDatum;
	}
	public String getHashww() {
		return hashww;
	}
	public void setHashww(String hashww) {
		this.hashww = hashww;
	}
	
}
