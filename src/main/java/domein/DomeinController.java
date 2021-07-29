package domein;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDate;
import java.util.HashSet;
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
import repositories.PatientRepository;

public class DomeinController {

	PatientRepository patientrepo = new PatientRepository();
	public void addPatient(String gebruikersnaam,String pass) throws Exception {
		try {
			
			String hash = WachtwoordController.generatePasswordHash(pass);

			}catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
}

