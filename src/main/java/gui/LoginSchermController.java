package gui;

import java.io.IOException;
import java.util.Optional;

import domein.DomeinController;
import domein.Rol;
import exceptions.MeldAanException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class LoginSchermController extends GridScherm {
	@FXML
	private Label welkomtxt;
	@FXML
	private Label txt_user;
	@FXML
	private Label txt_pass;
	@FXML
	private PasswordField pwdWachtwoord;
	@FXML
	private TextField txfGebruikersnaam;
	@FXML
	private Button btnAanmelden;
	@FXML
	private Button btnRegistreren;
	@FXML
	private Button btnCancel;
	@FXML
	private Label lblIncorrect;

	private SchermController sc;

	private DomeinController dc;

	public static int teller = 0;

	public LoginSchermController(SchermController sc, DomeinController dc) {
		this.sc = sc;
		this.dc = dc;
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginScherm.fxml"));
		loader.setController(this);
		loader.setRoot(this);
		try {
			loader.load();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
		welkomtxt.setText("Welkom");
		txt_user.setText("Gebruikersnaam");
		txt_pass.setText("Wachtwoord");
		btnAanmelden.setText("Aanmelden");
		//btnRegistreren.setText("txtRegistreren");
		lblIncorrect.setText("Gegevens incorrect");
	}

	// Event Listener on Button[#btnAanmelden].onAction
	@FXML
	public void btnAanmeldenAfhandeling(ActionEvent event){
		if (txfGebruikersnaam.getText().trim().isEmpty() || pwdWachtwoord.getText().trim().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Information Dialoog");
			alert.setHeaderText("Invoer error");
			alert.setContentText("gebruikersnaam of wachtwoord niet ingevult!");
			alert.showAndWait();
		}

		if (txfGebruikersnaam.getText().isEmpty() == false && pwdWachtwoord.getText().isEmpty() == false)
			try {
				dc.meldAan(txfGebruikersnaam.getText(), pwdWachtwoord.getText(),Rol.DOKTER);
				
				nextPanel(new DashboardSchermController(sc, dc));
				
				

			} catch (MeldAanException me) {
				if(me.getMessage().equals("geenGebruiker"))
				{
					lblIncorrect.setVisible(true);
					lblIncorrect.setText(me.getMessage()+" \""+txfGebruikersnaam.getText().trim()+"\"");
				}
				else
				{
					lblIncorrect.setVisible(true);
					lblIncorrect.setText(me.getMessage());
				}	
			}
	}


	@FXML
	public void btnRegistrerenAfhandeling(ActionEvent event) throws IOException {
		nextPanel(new RegistreerSchermController(sc, dc,this));
	}

	// Event Listener on Button[#btnCancel].onAction
	@FXML
	public void btnCancelAfhandeling(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Bevestig");
		alert.setHeaderText("Ben je zeker dat je het programma wil afsluiten");
		alert.setContentText("Wil je de applicatie afsluiten?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Stage stage = (Stage) btnCancel.getScene().getWindow();
			stage.close();
		} else
			// event wordt geannuleerd
			event.consume();
	}

	@FXML
	public void changeIncorrectLabel(KeyEvent event) {
		lblIncorrect.setVisible(false);
		if(event.getCode() == KeyCode.ENTER)
			btnAanmeldenAfhandeling(new ActionEvent());
	}

		

	@Override
	public void nextPanel(Pane panel) {
		sc.changePanel(panel);
	}

	@Override
	GridScherm geefPreviousPanel() {
		return null;
	}

}
