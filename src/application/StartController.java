package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;

    @FXML
    private Button ButtonOwners;

    @FXML
    private Button ButtonCars;

    @FXML
    private Button accidentsButton;

    @FXML
    private Button ButtonEmployees;

    @FXML
    private Button ButtonService;

    @FXML
    private Button ButtonHistoryes;

    @FXML
    private Button ButtonExit;

    @FXML
    void AccidentClick(ActionEvent event) {
    	try {
			root = FXMLLoader.load(getClass().getResource("AccidentSample.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void CarsClick(ActionEvent event) {
    	try {
			root = FXMLLoader.load(getClass().getResource("CarSample.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

    }

    @FXML
    void HistoryClick(ActionEvent event) {
    	try {
			root = FXMLLoader.load(getClass().getResource("History.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void EmployeesCilck(ActionEvent event) {

    	try {
			root = FXMLLoader.load(getClass().getResource("EmployeeSample.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void ExitClick(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void OwnersClick(ActionEvent event) {

    	try {
			root = FXMLLoader.load(getClass().getResource("OwnerSample.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void ServiceClick(ActionEvent event) {

    	try {
			root = FXMLLoader.load(getClass().getResource("ServiceSample.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }

}
