package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import domein.Cyclus;
import domein.DomeinController;
import domein.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class KalenderOverviewSchermcontroller extends GridPane implements Initializable {
	private Patient patient;
	private DomeinController dc;
	private PatientenSchermController pc;
	@FXML
	private Button terugButton,nieuwButton,addButton;
	@FXML
	private ListView<Cyclus> list;
	@FXML
	private DatePicker picker;

	public KalenderOverviewSchermcontroller(Patient patient,DomeinController dc,PatientenSchermController pc) {
		this.patient=patient;
		this.dc=dc;
		this.pc=pc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("KalenderOverviewScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Cyclus> gewoneList =patient.getCyclussen();
		ObservableList<Cyclus> olist=FXCollections.observableList(gewoneList);
		list.setItems(olist);
		addButton.setVisible(false);
		picker.setVisible(false);
	}
	@FXML
	public void nieuwButtonPressed() {
		addButton.setVisible(true);
		picker.setVisible(true);
		
	}
	@FXML
	public void addButtonPressed() {
		LocalDate date= picker.getValue();
		if (date != null && (date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now()))){
			dc.voegCyclusToe(patient, date);
		}
		
		List<Cyclus> gewoneList =patient.getCyclussen();
		ObservableList<Cyclus> olist=FXCollections.observableList(gewoneList);
		list.setItems(olist);
	}
	@FXML
	public void listOnMouseClicked() {
		Cyclus cyc=list.getSelectionModel().getSelectedItem();
		if (cyc!=null)
			pc.createKalenderScherm(cyc, patient);
	}
}
