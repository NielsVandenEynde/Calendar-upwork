package gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.controlsfx.control.textfield.TextFields;

import domein.DomeinController;
import domein.Patient;
import domein.Rol;
import exceptions.DatabankException;
import exceptions.GebruikersException;
import exceptions.RegistreerException;
import exceptions.TicketException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;


public class PatientToevoegSchermController<TicketSchermController> extends GridScherm {


	@FXML
	private Text txtAanmaken;
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private TextField txfVoornaam;
	@FXML
	private TextField txfAchternaam;
	@FXML
	private DatePicker datePicker;
	@FXML
	private Button btnAanmaken;
	@FXML
	private Button btnCancel;


	private SchermController sc;
	private DomeinController dc;
	private PatientenSchermController tsc;
	private Patient patient;
	private boolean editMode;


	public PatientToevoegSchermController(SchermController sc, DomeinController dc, PatientenSchermController tsc) {
		this.sc = sc;
		this.dc = dc;
		this.tsc = tsc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientToevoegScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}



	// Event Listener on Button[#btnAanmaken].onAction Klant klant, String
	// onderwerp, String titel, String beschrijving, String status, int prioriteit,
	// Werknemer werknemer
	@FXML
	public void btnAanmaakAction(ActionEvent event) {
		try {
			
			dc.addGebruiker(txfGebruikersnaam.getText(), txfVoornaam.getText(), txfAchternaam.getText(), "12345Hoera", Rol.KIND,datePicker.getValue());
			tsc.toonPatienten();
		}catch (RegistreerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void btnCancelAction(ActionEvent event) {
		tsc.removeRechterkant();
	}

	public void nextPanel(Pane panel) {
		sc.changePanel(panel);
	}



	@Override
	GridScherm geefPreviousPanel() {
		return null;
	}
	//custom listcell class to allow download button on listview items

}
