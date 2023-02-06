package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SampleController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private TextField UserName;

	@FXML
	private TextField PassWord;

	@FXML
	private Button Exit;

	@FXML
	private Button loginButton;

	@FXML
	private Button infoButton;

	@FXML
	void ExitClick(ActionEvent event) {
		System.exit(0);

	}

	@FXML
	void btnLogimAction(ActionEvent event) {

		if (UserName.getText().equalsIgnoreCase("admin")) {
			if (PassWord.getText().equalsIgnoreCase("123")) {
				try {
					root = FXMLLoader.load(getClass().getResource("Start.fxml"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				stage = new Stage();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}

		}
	}

	@FXML
	void InfoClick(ActionEvent event) {

		try {
			root = FXMLLoader.load(getClass().getResource("InfoSample.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
}
