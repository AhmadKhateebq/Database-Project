package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class AccidentsController {
	private ArrayList<AccidentsRecords> data;
	private ObservableList<AccidentsRecords> dataList = FXCollections.observableArrayList();
	private Stage stage;
	private Scene scene;
	private Parent root;
	int index = -1;
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

    @FXML
    private TableView<AccidentsRecords> Table;

    @FXML
    private TableColumn<AccidentsRecords, Integer> AccidentNo;

    @FXML
    private TableColumn<AccidentsRecords, String> AccidentDate;

    @FXML
    private TableColumn<AccidentsRecords, String> AccidentLocation;

    @FXML
    private TableColumn<AccidentsRecords, Double> Cost;

    @FXML
    private TableColumn<AccidentsRecords, String> carNumber;

    @FXML
    private TextField AccidentDateText;

    @FXML
    private TextField AccidentLocationText;

    @FXML
    private TextField CostText;

    @FXML
    private TextField CarNumberText;

    @FXML
    private Button UpdateButton;

    @FXML
    private Button addButton;

    @FXML
    private Button ShowDataButton;

    @FXML
    private Button EditButton;

    @FXML
    private Button Exit;
    
    @FXML
    private TextField accidentNoText;

    @FXML
    private Button HomeButton;
    private ObservableList<AccidentsRecords> getData() {
		String SQL = "select * from accident";
		try {

			// ObservableList<OwnerData> list = FXCollections.observableArrayList();
			Link.a.connectDB();
			Statement state = (Statement) Link.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				AccidentsRecords A = new AccidentsRecords( Double.parseDouble(rs.getString(1)),Integer.parseInt(rs.getString(2)), rs.getString(3),
						rs.getString(4),rs.getString(5));
				dataList.add(A);
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
    	Connection conn = Link.a.connectDB();

		String sql = "insert into accident(cost , accidentnum ,accidentdate , accidentlocation ,carnumber ) values(?,?,?,?,? )";

		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, CostText.getText());
			pst.setString(2, null);
			pst.setString(3, AccidentDateText.getText());
			pst.setString(4, AccidentLocationText.getText()); // for ownerID
			pst.setString(5, CarNumberText.getText());
			pst.execute();

			JOptionPane.showMessageDialog(null, "Users Add succes");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
    }
  
    @FXML
    void getSelected (MouseEvent event){
    index = Table.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }	
    CostText.setText(Cost.getCellData(index).toString());
    AccidentDateText.setText(AccidentDate.getCellData(index).toString());
    AccidentLocationText.setText(AccidentLocation.getCellData(index).toString());
    CarNumberText.setText(carNumber.getCellData(index).toString());
    accidentNoText.setText(AccidentNo.getCellData(index).toString());
    
    }

	@FXML
	void EditClick(ActionEvent event) throws ClassNotFoundException, SQLException {   
        try {
        	conn = Link.a.connectDB();
            String value1 = CostText.getText();
            String value2 = AccidentDateText.getText();
            String value3 = AccidentLocationText.getText();
            String value4 = CarNumberText.getText();
            String value5 = accidentNoText.getText();           
            String sql2 = " update accident set cost='"+value1+"', accidentnum= '"+value5+"', accidentdate= '"+ value2+
            		"', accidentlocation= '"+value3+"'where carnumber='"+value4+"'";        
            pst= conn.prepareStatement(sql2);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
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
    	Object root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("Start.fxml"));

		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage stage = new Stage();
		Scene scene = new Scene((Parent) root);
		stage.setScene(scene);
		stage.show();
    }

    @FXML
    void ShowDataClick(ActionEvent event) {
    	data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		Table.setEditable(true);
		AccidentNo.setCellValueFactory(new PropertyValueFactory<AccidentsRecords, Integer>("accidentNo"));
		AccidentDate.setCellValueFactory(new PropertyValueFactory<AccidentsRecords, String>("accidentDate"));
		AccidentLocation.setCellValueFactory(new PropertyValueFactory<AccidentsRecords, String>("accidentLocation"));
		Cost.setCellValueFactory(new PropertyValueFactory<AccidentsRecords, Double>("cost"));
		carNumber.setCellValueFactory(new PropertyValueFactory<AccidentsRecords, String>("carNumber"));
		getData();
		Table.setItems(dataList);
    }

    @FXML
    void UpdateClick(ActionEvent event) {
    	data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		Table.setEditable(true);
		AccidentNo.setCellValueFactory(new PropertyValueFactory<AccidentsRecords, Integer>("accidentNo"));
		AccidentDate.setCellValueFactory(new PropertyValueFactory<AccidentsRecords, String>("accidentDate"));
		AccidentLocation.setCellValueFactory(new PropertyValueFactory<AccidentsRecords, String>("accidentLocation"));
		Cost.setCellValueFactory(new PropertyValueFactory<AccidentsRecords, Double>("cost"));
		carNumber.setCellValueFactory(new PropertyValueFactory<AccidentsRecords, String>("carNumber"));
		getData();
		Table.setItems(dataList);
    }

}
