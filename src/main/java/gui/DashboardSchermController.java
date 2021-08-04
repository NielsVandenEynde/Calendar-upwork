package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domein.DomeinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class DashboardSchermController extends BorderPane{

	private SchermController sc;
	private DomeinController dc;
	@FXML
	private VBox vbox;
	@FXML
	private ToggleButton btnPatienten;

	@FXML
	private Button werkNemerToevoegen;
	@FXML
	private Label txtLogedIn;

	
	private List<ToggleButton> buttonList;

	public DashboardSchermController(SchermController sc, DomeinController dc) {
		this.sc = sc;
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);

		try {
			loader.load();

		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		this.setCenter(new PatientenSchermController(sc, dc));
		
		this.btnPatienten.setSelected(true);
		
		//txtLogedIn.setText(TaalKeuze.vertaal("loggedIn") + dc.werknemerController.getIngelogdeWerknemer().getRol());
		
//		this.btnTickets.setStyle("-fx-background-color: #0F74A8;");
//		this.btnTickets.setStyle("-fx-text-fill: white;");

//		System.out.println("hello");
		// toonOverzicht();
		
		buttonList = Arrays.asList(new ToggleButton[] {btnPatienten});

	}

	@FXML
	public void btnTicketsPressed() {
		this.setCenter(new PatientenSchermController(sc, dc));
		switchSelectedButton(btnPatienten);
	}



	
	public void switchSelectedButton(ToggleButton activeButton) {
		buttonList.stream().forEach(b -> {b.setSelected(false);});
		activeButton.setSelected(true);
	}

}
