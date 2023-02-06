package application;

import java.io.IOException;
import java.util.ArrayList;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Stage;

import java.sql.SQLException;

public class OwnerController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	int index = -1;
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	private ArrayList<OwnerData> data;
	private ObservableList<OwnerData> dataList = FXCollections.observableArrayList();

	@FXML
	private TableView<OwnerData> OwnerTable;

	@FXML
	private TableColumn<OwnerData, Integer> OwnerID;

	@FXML
	private TableColumn<OwnerData, String> OwnerName;

	@FXML
	private TableColumn<OwnerData, String> OwnerSSN;

	@FXML
	private TableColumn<OwnerData, String> PhoneNumper;

	@FXML
	private Button UpdateButton;

	@FXML
	private Button addButton;

	@FXML
	private Button showDataButton;

	@FXML
	private TextField textname;

	@FXML
	private TextField ssnText;

	@FXML
	private TextField PhoneText;

	@FXML
	private Button DeleteButton;

	@FXML
	private Button Exit;

	@FXML
	private TextField SearchText;

	@FXML
	private Button HomeButton;

	@FXML
	private Button SearchButton;

	@FXML
	private Button EditButton;

	@FXML
	private Button NumOFOwnersButton;

	@FXML
	private TextField TextID;

	@FXML
	private TextField TextID1; // for edit click

	@FXML
	private TextField NumOfOwnersText;

	// UPDATE products SET name = ?, price = ?, added_date = ? WHERE id = ?"
	@FXML
	void ShowDataClick(ActionEvent event) {

		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		OwnerTable.setEditable(true);
		OwnerID.setCellValueFactory(new PropertyValueFactory<OwnerData, Integer>("ownerID"));
		OwnerName.setCellValueFactory(new PropertyValueFactory<OwnerData, String>("ownerName"));
		OwnerSSN.setCellValueFactory(new PropertyValueFactory<OwnerData, String>("ssn"));
		PhoneNumper.setCellValueFactory(new PropertyValueFactory<OwnerData, String>("phoneNO"));
		getData();
		OwnerTable.setItems(dataList);
	}

	private ObservableList<OwnerData> getData() {
		String SQL = "select * from carowner ORDER BY ownername";
		try {

			// ObservableList<OwnerData> list = FXCollections.observableArrayList();
			Link.a.connectDB();
			Statement state = (Statement) Link.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				OwnerData O = new OwnerData(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3),
						rs.getString(4));
				dataList.add(O);
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

		String sql = "insert into carowner (ownerID , phonenumber , personalID , ownername)values(?,?,?,? )";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(4, textname.getText());
			pst.setString(3, ssnText.getText());
			pst.setString(2, PhoneText.getText());
			pst.setString(1, null);
			pst.execute();

			JOptionPane.showMessageDialog(null, "Owners Add succes");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	@FXML
	void SearchClick(ActionEvent event) {

		OwnerID.setCellValueFactory(new PropertyValueFactory<OwnerData, Integer>("ownerID"));
		OwnerName.setCellValueFactory(new PropertyValueFactory<OwnerData, String>("ownerName"));
		OwnerSSN.setCellValueFactory(new PropertyValueFactory<OwnerData, String>("ssn"));
		PhoneNumper.setCellValueFactory(new PropertyValueFactory<OwnerData, String>("phoneNO"));

		OwnerTable.setItems(dataList);
		FilteredList<OwnerData> filteredData = new FilteredList<>(dataList, b -> true);
		SearchText.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getOwnerName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches
				} else if (String.valueOf(person.getSsn()).indexOf(lowerCaseFilter) != -1)
					return true;
				else
					return false; // Does not match.
				// Does not match.
			});
		});
		SortedList<OwnerData> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(OwnerTable.comparatorProperty());
		OwnerTable.setItems(sortedData);
	}

	@FXML
	void DeleteClick(ActionEvent event) throws ClassNotFoundException, SQLException {

		conn = Link.a.connectDB();
		String sql = "delete from carowner where ownerID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, TextID.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null, "Deleted who have ID: " + TextID.getText());

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
	//Refresh
	void UpdateClick(ActionEvent event) {

		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		OwnerTable.setEditable(true);
		OwnerID.setCellValueFactory(new PropertyValueFactory<OwnerData, Integer>("ownerID"));
		OwnerName.setCellValueFactory(new PropertyValueFactory<OwnerData, String>("ownerName"));
		OwnerSSN.setCellValueFactory(new PropertyValueFactory<OwnerData, String>("ssn"));
		PhoneNumper.setCellValueFactory(new PropertyValueFactory<OwnerData, String>("phoneNO"));
		getData();
		OwnerTable.setItems(dataList);
	}

	/*@FXML
	void Edit(ActionEvent event) throws ClassNotFoundException, SQLException {

		Connection conn = Link.a.connectDB();
		String sql = " update carowner set ownername = ?, phonenumber = ? , personalID =  ? where ownerID = ?";

		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, textname.getText());
			pst.setString(2, PhoneText.getText());
			pst.setString(3, ssnText.getText());
			pst.setString(4, TextID1.getText());
			pst.execute();

			JOptionPane.showMessageDialog(null, "Owners Update");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	*/ //bad function
	
	@FXML
    void getSelected (MouseEvent event){
    index = OwnerTable.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }	
    TextID1.setText(OwnerID.getCellData(index).toString());
    textname.setText(OwnerName.getCellData(index).toString());
    ssnText.setText(OwnerSSN.getCellData(index).toString());
	PhoneText.setText(PhoneNumper.getCellData(index).toString());
    }

	@FXML
	void EditButton(ActionEvent event) throws ClassNotFoundException, SQLException {   
        try {
        	conn = Link.a.connectDB();
            String v3 = TextID1.getText();
            String v2 = textname.getText();
            String v1 = ssnText.getText();
            String v4 = PhoneText.getText();          
            
            String sql2 = " update carowner set ownername ='"+v2+"', phonenumber = '"+v4+"', personalID = '"+ v1+"'where ownerID'"+v3+"'";
            
            pst= conn.prepareStatement(sql2);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

	@FXML
	void CountButton(ActionEvent event) throws ClassNotFoundException, SQLException {
		conn = Link.a.connectDB();

		String sql = "SELECT COUNT(ownerID) FROM carowner AS count";

		Statement stmt = (Statement) conn.createStatement();
		// Executing the query
		ResultSet rs = stmt.executeQuery(sql);
		// Retrieving the result
		rs.next();
		int count = rs.getInt(1);
		NumOfOwnersText.setText("" + count);
	}
	
}
