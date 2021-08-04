package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.controlsfx.control.table.TableFilter;

import domein.Cyclus;
import domein.DomeinController;
import domein.Patient;
import exceptions.TicketException;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;


public class PatientenSchermController extends GridPane implements Initializable {
	@FXML
	private Button btnFilterOpenstaand;
	@FXML
	private Button btnTicketToevoegen;
	@FXML
	private Button btnFilterAfgehandeld;
	@FXML
	private Label lblError;

	@FXML
	private TableView<Patient> tableView;
	
	@FXML
	private TableColumn<Patient, String> gebruikersnaamColumn,voornaamColumn,achternaamColumn;
	@FXML
	private TableColumn<Patient, Integer> leeftijdColumn;
	

	private DomeinController dc;
	private SchermController sc;

	public PatientenSchermController(SchermController sc, DomeinController dc) {
		this.sc = sc;
		this.dc = dc;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("TicketScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		btnTicketToevoegen.setText("Patient toevoegen");
		
		

		toonPatienten();
	}

	public void toonPatienten() {


		List<Patient> patienten;
		

		try {
			patienten = dc.getPatienten();
			patienten.forEach(p -> System.out.println(p.getAchternaam()));
			System.out.println(patienten);
			
			if (patienten != null) {
				ObservableList<Patient> olist= FXCollections.observableArrayList(patienten);
				tableView.setItems(olist);

			}
		} catch (Exception e) {
			lblError.setText(e.getMessage());
		}
		//TableFilter.forTableView(tableView).apply();
	}

	@FXML
	public void btnTicketToevoegenPressed() {
		createDetailScherm(null);
	}

	private void createDetailScherm(Patient patient) {
		removeRechterkant();
		PatientToevoegSchermController tvc;
		
		tvc = new PatientToevoegSchermController(sc, dc, this);
	
		this.add(tvc, 2, 0);
		GridPane.setRowSpan(tvc, 5);
	}
	public void createKalenderScherm(Cyclus cyclus,Patient patient) {
		removeRechterkant();
		KalenderSchermController kc;
		kc = new KalenderSchermController(sc, dc, this, cyclus,patient);
		this.add(kc, 2, 0);
		GridPane.setRowSpan(kc, 5);
	}

	

	public void initialize(URL location, ResourceBundle resources) {
		bindTableViewToPatienten();


	}

	private void bindTableViewToPatienten() {
		
		gebruikersnaamColumn.setCellValueFactory(new PropertyValueFactory("gebruikersnaam"));
		voornaamColumn.setCellValueFactory(new PropertyValueFactory<>("voornaam"));
		achternaamColumn.setCellValueFactory(new PropertyValueFactory<>("achternaam"));
		leeftijdColumn.setCellValueFactory(patient-> new SimpleIntegerProperty(patient.getValue().getLeeftijd()).asObject());



		tableView.setRowFactory(tv -> {
			TableRow<Patient> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY) {

					Patient clickedRow = row.getItem();
					createCyclusOverview(clickedRow);

				}
			});
			return row;

		});

	}

	public void createCyclusOverview(Patient patientt) {
		removeRechterkant();
		KalenderOverviewSchermcontroller kc=new KalenderOverviewSchermcontroller(patientt, dc, this);
		this.add(kc, 2, 0);
		GridPane.setRowSpan(kc, 5);
		
		
	}

	// helper method for removing the ticket editor screen
	public void removeRechterkant() {
		ObservableList<Node> childrens = this.getChildren();
		for (Node node : childrens) {
			if (node instanceof Pane && GridPane.getColumnIndex(node) != null && GridPane.getColumnIndex(node) == 2) {
				this.getChildren().remove(node);
				break;
			}
		}
	}
}
