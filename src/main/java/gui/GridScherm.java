package gui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

// Class to guarantee proper use of previous panel implementation.
public abstract class GridScherm extends GridPane {
	
	// Method for getting previous panel, if screen should not have a previous return null.
	abstract GridScherm geefPreviousPanel();
	
	abstract void nextPanel(Pane panel);
	
}
