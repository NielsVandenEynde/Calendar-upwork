package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import resourceBundles.TaalKeuze;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

import domein.Address;
import domein.DomeinController;
import domein.Patient;
import domein.Rol;
import domein.Telefoonnummer;
import domein.WachtwoordController;
import domein.Werknemer;
import exceptions.GebruikersException;
import exceptions.KlantException;
import exceptions.RegistreerException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;

public class RegistreerSchermController extends GridScherm {

	@FXML
	private Label txtRegistreer;
	@FXML
	private Label txtGebruikersnaam;

	@FXML
	private Label txtVoornaam;
	@FXML
	private Label txtAchternaam;
	@FXML
	private Label txtWachtwoord;
	@FXML
	private Label txtEmail;
	@FXML
	private Button btnTerug;
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private TextField txfVoornaam;
	@FXML
	private TextField txfAchternaam;
	@FXML
	private TextField txfEmail;
	@FXML
	private TextField txfLine1, txfLine2, txfCity, txfState, txfCountry, txfPostcode;
	@FXML
	private Label txtRol, txtLine1, txtBus, txtStad, txtPostcode, txtProvincie, txtLand,txtTelefoonnummers;
	@FXML
	private PasswordField pwdWachtwoord;
	@FXML
	private Button btnRegistreer;
	@FXML
	private Button btnCancel;
	@FXML
	private Label LblRegistreer;
	@FXML
	private Label lblIncorrect;
	@FXML
	private TextField txfNummer;
	@FXML
	private Label txtDatum;
	@FXML
	private Label datumField;
	@FXML
	private ListView<String> listNummers;
	@FXML
	private ComboBox<Rol> cbxRol;
	
	private TextField pwdTxf;
	private SchermController sc;
	private DomeinController dc;
	private LoginSchermController wsc;
	private boolean editMode;


	// werknemer that is being edited
	private Patient huidigePatient;

	public RegistreerSchermController(SchermController sc, DomeinController dc, LoginSchermController wsc) {
		this.wsc=wsc;
		this.sc = sc;
		this.dc = dc;
		
		try {
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("RegistreerScherm.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

		
		//txtDatum.setVisible(false);
		//datumField.setVisible(false);
		//pwdWachtwoord.setVisible(false);
		pwdTxf = new TextField();
		this.add(pwdTxf, 2, 5);
		GridPane.setColumnSpan(pwdTxf, 2);
		pwdTxf.setText(WachtwoordController.generatePassword());
		pwdTxf.setEditable(false);

		btnRegistreer.setText("registreer");

		editMode=false;

	}


	// second constructor for creating an instance that edits a werknemer


	// method for filling the text fields with werknemer data when editing

	// TODO Auto-generated constructor stub

	// Event Listener on Button[#btnTerug].onAction
	@FXML
	public void btnTerugOnAction(ActionEvent event) {

	}




	// Event Listener on Button[#btnRegistreer].onAction
	@FXML
	public void btnRegistreerAction(ActionEvent event) {
		System.out.println("hello");
		try {
			dc.addGebruiker(txfGebruikersnaam.getText(),txfVoornaam.getText(), txfAchternaam.getText(),pwdTxf.getText(),Rol.DOKTER,null);
			sc.changePanel(wsc);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (e.getClass().equals(RegistreerException.class)) {
				StringBuilder sb = new StringBuilder(e.getMessage());
				sb.deleteCharAt(sb.length() - 1);
				sb.deleteCharAt(0);
				sb.toString().replaceAll("\\s+", "");
				String[] temps = sb.toString().replaceAll("\\s+", "").split(",");
				txtGebruikersnaam.setTextFill(Color.color(0, 0, 0));
				txtVoornaam.setTextFill(Color.color(0, 0, 0));
				txtAchternaam.setTextFill(Color.color(0, 0, 0));
				txtEmail.setTextFill(Color.color(0, 0, 0));
				txtWachtwoord.setTextFill(Color.color(0, 0, 0));
				
				txtLine1.setTextFill(Color.color(0, 0, 0));
				txtBus.setTextFill(Color.color(0, 0, 0));
				txtStad.setTextFill(Color.color(0, 0, 0));
				txtPostcode.setTextFill(Color.color(0, 0, 0));
				txtProvincie.setTextFill(Color.color(0, 0, 0));
				txtLand.setTextFill(Color.color(0, 0, 0));
				
				for (int i = 0; i < temps.length; i++) {
					if (temps[i].equals("familienaam")) {
						txtAchternaam.setTextFill(Color.color(1, 0, 0));
					}
					if (temps[i].equals("voornaam")) {
						txtVoornaam.setTextFill(Color.color(1, 0, 0));
					}
					if (temps[i].equals("email")) {
						txtEmail.setTextFill(Color.color(1, 0, 0));
					}

					if (temps[i].equals("gebruikersnaam")) {
						txtGebruikersnaam.setTextFill(Color.color(1, 0, 0));
					}
					
				}
			}
			
		}
		// update werknemer tableview with new werknemer

	}

	@FXML
	public void changeIncorrectLabel(KeyEvent event) {
		lblIncorrect.setVisible(false);
		if (event.getCode() == KeyCode.ENTER)
			btnRegistreerAction(new ActionEvent());
	}

	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void btnCancelAction(ActionEvent event) {
		wsc.removeDetailScherm();
	}

	public void nextPanel(Pane panel) {
		sc.changePanel(panel);
	}

	@Override
	public GridScherm geefPreviousPanel() {
		return new LoginSchermController(sc, dc);
	}
}
