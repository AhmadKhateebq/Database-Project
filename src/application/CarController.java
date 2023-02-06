package application;

import com.mysql.jdbc.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	int index = -1;
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	private ArrayList<CarData> data;
	private ObservableList<CarData> dataList = FXCollections.observableArrayList();

	@FXML
	private TableView<CarData> Table;

	@FXML
	private TableColumn<CarData, String> CarNum;

	@FXML
	private TableColumn<CarData, String> CarTybe;

	@FXML
	private TableColumn<CarData, String> CarModel;

	@FXML
	private TableColumn<CarData, String> personalIdCol;
	@FXML
	private Button UpdateButton;

	@FXML
	private Button addButton;

	@FXML
	private TextField carNumText;

	@FXML
	private TextField TybeText;

	@FXML
	private TextField ModelText;

	@FXML
	private TextField SearchText;

	@FXML
	private TextField personalIDtext;

	@FXML
	private Button DeleteButton;

	@FXML
	private Button Exit;

	@FXML
	private Button HomeButton;

	@FXML
	private Button SearchButton;

	@FXML
	private Button Edit;

	@FXML
	private TextField deleteCarNumberText;

	@FXML
	void ShowDataClick(ActionEvent event) {

		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		Table.setEditable(true);
		personalIdCol.setCellValueFactory(new PropertyValueFactory<CarData, String>("personalID"));
		CarTybe.setCellValueFactory(new PropertyValueFactory<CarData, String>("carTybe"));
		CarModel.setCellValueFactory(new PropertyValueFactory<CarData, String>("carModel"));
		CarNum.setCellValueFactory(new PropertyValueFactory<CarData, String>("carNumber"));
		getData();
		Table.setItems(dataList);
	}

	private ObservableList<CarData> getData() {
		String SQL = "select * from car";
		try {

			// ObservableList<OwnerData> list = FXCollections.observableArrayList();
			Link.a.connectDB();
			Statement state = (Statement) Link.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				CarData C = new CarData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				dataList.add(C);
			}
			rs.close();
			state.close();
			Link.a.connectDB().close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataList;
	}

	@FXML
	void AddClick(ActionEvent event) throws ClassNotFoundException, SQLException {
		conn = Link.a.connectDB();

		String sql = "insert into car (carmodel , carnumber , cartype , ownerID)values(?,?,?,? )";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(2, carNumText.getText());
			pst.setString(1, ModelText.getText());
			pst.setString(4, personalIDtext.getText());
			pst.setString(3, TybeText.getText());
			pst.execute();

			JOptionPane.showMessageDialog(null, "Car Add succes for" + personalIDtext.getText());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	@FXML
	void DeleteClick(ActionEvent event) throws ClassNotFoundException, SQLException {

		conn = Link.a.connectDB();
		String sql = "delete from car where carnumber = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, deleteCarNumberText.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Deleted the car Number: " + carNumText.getText());

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	@FXML
	void ExitClick(ActionEvent event) {
		System.exit(0);

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
	void SearchClick(ActionEvent event) {
		personalIdCol.setCellValueFactory(new PropertyValueFactory<CarData, String>("personalID"));
		CarTybe.setCellValueFactory(new PropertyValueFactory<CarData, String>("carTybe"));
		CarModel.setCellValueFactory(new PropertyValueFactory<CarData, String>("carModel"));
		CarNum.setCellValueFactory(new PropertyValueFactory<CarData, String>("carNumber"));

		Table.setItems(dataList);
		FilteredList<CarData> filteredData = new FilteredList<>(dataList, b -> true);
		SearchText.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(car -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (car.getCarTybe().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches
				} else if (String.valueOf(car.getCarNumber()).indexOf(lowerCaseFilter) != -1)
					return true;
				else
					return false; // Does not match.
				// Does not match.
			});
		});
		SortedList<CarData> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(Table.comparatorProperty());
		Table.setItems(sortedData);
	}

	@FXML
	void UpdateClick(ActionEvent event) {

		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		Table.setEditable(true);
		personalIdCol.setCellValueFactory(new PropertyValueFactory<CarData, String>("personalID"));
		CarTybe.setCellValueFactory(new PropertyValueFactory<CarData, String>("carTybe"));
		CarModel.setCellValueFactory(new PropertyValueFactory<CarData, String>("carModel"));
		CarNum.setCellValueFactory(new PropertyValueFactory<CarData, String>("carNumber"));
		getData();
		Table.setItems(dataList);
	}
	@FXML
    void getSelected (MouseEvent event){
    index = Table.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }	
    personalIDtext.setText(personalIdCol.getCellData(index).toString());
    TybeText.setText(CarTybe.getCellData(index).toString());
    ModelText.setText(CarModel.getCellData(index).toString());
    carNumText.setText(CarNum.getCellData(index).toString());
    }

	@FXML
	void EditClick(ActionEvent event) throws ClassNotFoundException, SQLException {   
        try {
        	conn = Link.a.connectDB();
            String v1 = personalIDtext.getText();
            String v2 = TybeText.getText();
            String v3 = ModelText.getText();
            String v4 = carNumText.getText();         
                       
            String sql3 = " update car set carmodel='"+v3+"', carnumber= '"+v4+"', cartype= '"+ v2+ "'where ownerID='"+v1+"'";
     
            pst= conn.prepareStatement(sql3);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update Done");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }	
	
	}
