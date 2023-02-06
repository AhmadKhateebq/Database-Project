package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CompanyController {
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private TableColumn<?, ?> CompanyName;

    @FXML
    private TableColumn<?, ?> phonenumber;

    @FXML
    private TableColumn<?, ?> Email;

    @FXML
    private Button UpdateButton;

    @FXML
    private Button addButton;

    @FXML
    private TextField textname;

    @FXML
    private TextField EmailText;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button Exit;

    @FXML
    private Button HomeButton;

    @FXML
    void AddClick(ActionEvent event) {

    }

    @FXML
    void DeleteClick(ActionEvent event) {

    }

    @FXML
    void ExitClick(ActionEvent event) {

    }

    @FXML
    void HomeClick(ActionEvent event) {
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

    @FXML
    void UpdateClick(ActionEvent event) {

    }

}
