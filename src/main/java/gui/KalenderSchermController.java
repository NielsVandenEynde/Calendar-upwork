package gui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.awt.Robot;
import com.sun.glass.ui.GlassRobot;

import domein.Cyclus;
import domein.DomeinController;
import domein.Patient;
import domein.Registratie;
import domein.Resultaat;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Popup;
import javafx.util.Callback;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class KalenderSchermController extends GridPane {
	private Cyclus cyclus;
	private Patient patient;
	private DomeinController dc;

public KalenderSchermController(SchermController sc, DomeinController dc, PatientenSchermController pc,Cyclus cyclus,Patient patient) {
	this.cyclus=cyclus;
	this.patient=patient;
	this.dc=dc;
	FXMLLoader loader = new FXMLLoader(getClass().getResource("KalenderScherm.fxml"));
	loader.setController(this);
	loader.setRoot(this);
	try {
		loader.load();
	} catch (IOException ex) {
		throw new RuntimeException(ex);
	}
	toonKalender();
}

public void toonKalender() {
	List<Registratie> lijst= cyclus.getRegistraties();
	List<LocalDate> nattte=new ArrayList<LocalDate>();
	List<LocalDate> droge=new ArrayList<LocalDate>();
	List<LocalDate> druppel=new ArrayList<LocalDate>();
	lijst.forEach(e->{
		if (e.getResultaat()==Resultaat.NAT)
			nattte.add(e.getDate());
		else if (e.getResultaat()==Resultaat.DROOG)
			droge.add(e.getDate());
		else if (e.getResultaat()==Resultaat.DRUPPEL)
			druppel.add(e.getDate());
	});
	DatePicker picker=new DatePicker(cyclus.getStartDatum());
	
	picker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
		@Override
		public DateCell call(DatePicker param) {
			return new DateCell(){
				@Override
				public void updateItem(LocalDate item, boolean empty) {
					super.updateItem(item, empty);

					if (!empty && item != null) {
						if (item.isBefore(cyclus.getStartDatum()) || item.isAfter(cyclus.getStartDatum().plusWeeks(13))) {
							setDisable(true);
		                    setStyle("-fx-background-color: #ffc0cb;");
						}
						if(nattte.contains(item)) {
							this.setStyle("-fx-background-color: red");
						}
						else if(droge.contains(item)) {
							this.setStyle("-fx-background-color: green");
						}
						else  if(druppel.contains(item)) {
							this.setStyle("-fx-background-color: yellow");
						}
						
					}
				}
			};
		}
	});
	DatePickerSkin  skin= new DatePickerSkin(picker);
	Node popupContent = skin.getPopupContent();
	this.add(popupContent,1,1);
	ContextMenu menu= new ContextMenu();
	MenuItem item1 = new MenuItem("Nat");
	item1.setOnAction(e->{
		dc.voegRegistratieToe(cyclus, patient, new Registratie(picker.getValue(),Resultaat.NAT));
		toonKalender();
	});
	MenuItem item2 = new MenuItem("Druppels");
	item2.setOnAction(e->{
		dc.voegRegistratieToe(cyclus, patient, new Registratie(picker.getValue(),Resultaat.DRUPPEL));
		toonKalender();
	});
	MenuItem item3 = new MenuItem("Droog");
	item3.setOnAction(e->{
		dc.voegRegistratieToe(cyclus, patient, new Registratie(picker.getValue(),Resultaat.DROOG));
		
	});
	menu.getItems().addAll(item1, item2,item3);
	picker.valueProperty().addListener(e->{

		menu.show(this.getScene().getWindow());
	});
	picker.setContextMenu(menu);
	
	
	
	}

}
