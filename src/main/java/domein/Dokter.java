package domein;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dokter {

	private String voornaam;
	private String achternaam;
	@Id 
	private String gebruikersnaam;
	private LocalDate startDatum;
	private String hashww;
	protected Dokter() {}
	public Dokter(String voornaam,String achternaam,String gebruikersnaam,String hashww) {
		this.voornaam=voornaam;
		this.achternaam=achternaam;
		this.gebruikersnaam=gebruikersnaam;
		this.startDatum=startDatum;
		this.hashww=hashww;
		this.startDatum=LocalDate.now();
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
	public String getHashww() {
		return hashww;
	}
	public void setHashww(String hashww) {
		this.hashww = hashww;
	}
}
