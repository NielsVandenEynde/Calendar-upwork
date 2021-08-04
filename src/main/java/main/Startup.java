package main;

import domein.DomeinController;
import gui.SchermController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Startup extends Application {

public static void main(String[] args) {
		
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		DomeinController dc = new DomeinController();
		SchermController root = new SchermController();
		Scene scene = new Scene(root, 1760, 990);
		//primaryStage.getIcons().add(new Image("/images/vinci_logo_small.png"));
		primaryStage.setScene(scene);
		primaryStage.setMaximized(true);
		primaryStage.setMinWidth(1760);
		primaryStage.setMinHeight(990);
		primaryStage.setTitle("Plaskalender dokter");
		primaryStage.show();
		
	}
}
