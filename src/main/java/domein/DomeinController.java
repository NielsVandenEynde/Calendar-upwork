package domein;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import domein.Address;
import domein.Bedrijf;
import domein.Contactpersoon;
import domein.Contract;
import domein.ContractStatus;
import domein.ContractType;
import domein.Doorlooptijd;
import domein.Klant;
import domein.Status;
import domein.WachtwoordController;
import domein.Klant.Builder;
import exceptions.ContractTypeException;
import exceptions.DatabankException;
import exceptions.KlantException;
import exceptions.MeldAanException;
import exceptions.RegistreerException;
import gui.Ticket;
import repositories.DokterRepository;
import repositories.PatientRepository;

public class DomeinController {

	private Dokter ingelogdeDokter;
	PatientRepository patientrepo = new PatientRepository();
	DokterRepository dokterrepo= new DokterRepository();
	public void addGebruiker(String gebruikersnaam,String voornaam,String achternaam,String pass, Rol rol,LocalDate geboorte) throws RegistreerException {
		try {
			
			String hash = WachtwoordController.generatePasswordHash(pass);
			if (rol==Rol.KIND) {
			
				if(!patientrepo.exists(gebruikersnaam)) {
					Patient patient= new Patient(voornaam,achternaam,gebruikersnaam,hash);
					patient.setGeboorteDatum(geboorte);
					
					patient.setDokter(ingelogdeDokter);
					patientrepo.startTransaction();
					patientrepo.create(patient);
					patientrepo.commitTransaction();
				}
			}
			else {
				System.out.println("method");
				if(!dokterrepo.exists(gebruikersnaam)) {
			
					Dokter dokter= new Dokter(voornaam,achternaam,gebruikersnaam,hash);
					dokterrepo.startTransaction();
					dokterrepo.create(dokter);
					dokterrepo.commitTransaction();
				}
				
			}}

			 catch (Exception e) {
	            throw new RegistreerException(e.getMessage());
	        }
		}

	
	public void meldAan(String user, String pass, Rol rol) throws MeldAanException {
		try {
			Dokter dokter = dokterrepo.get(user);
			String storedHashtemp=dokter.getHashww();
			
			if (!WachtwoordController.validatePassword(pass, storedHashtemp)) {
				
				throw new MeldAanException("Wachtwoord Fout");
			}
			ingelogdeDokter = dokter;

		} catch (NoSuchAlgorithmException e) {
			throw new MeldAanException(e.getMessage());
		} catch (InvalidKeySpecException e) {
			throw new MeldAanException(e.getMessage());
		} catch (Exception e) {
			throw new MeldAanException(e.getMessage());
		}
		
	}
	public void voegCyclusToe(Patient patient,LocalDate date) {
		patientrepo.startTransaction();
		patient.addCyclus(date);
		patientrepo.update(patient);
		patientrepo.commitTransaction();
	}


	public List<Patient> getPatienten() {
		return  patientrepo.getPatientenVanDokter(ingelogdeDokter.getGebruikersnaam());
	}
	public void voegRegistratieToe(Cyclus cyclus,Patient patientt, Registratie res) {
		patientrepo.startTransaction();
		cyclus.voegRegistratieToe(res);
		patientrepo.update(patientt);
		patientrepo.commitTransaction();
	}
	
}

