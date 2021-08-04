package gui;

import domein.DomeinController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;


// Controller klasse waarin andere klassen zullen weergegeven worden
public class SchermController extends BorderPane {
	private DomeinController dc;
	private Button terugButton;
	private GridScherm previousPanel;
	public static double volumeslidergobaal;
	

	public SchermController() {
		
		
		
		dc = new DomeinController();
		// init taalscherm
		changePanel(new LoginSchermController(this, dc));
		
		// Null first so doesn't show on taaltestschermcontroller
	}


	// change the centerpanel of the schermcontroller
	public void changePanel(Pane panel) {
		
			this.setCenter(panel);
			setAlignment(panel, Pos.CENTER);
	}
}




