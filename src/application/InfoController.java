package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InfoController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private Button LoginPageButton;

	@FXML
	void LoginClick(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Sample.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
