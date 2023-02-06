package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
import javafx.stage.Stage;

import com.mysql.jdbc.Statement;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class EmployeeController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	int index = -1;
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pst = null;

	private ArrayList<EmployeeData> data;
	private ObservableList<EmployeeData> dataList = FXCollections.observableArrayList();
	@FXML
	private TableView<EmployeeData> Table;

	@FXML
	private TableColumn<EmployeeData, String> EmployeeName; // example <EmployeeData , String>

	@FXML
	private TableColumn<EmployeeData, Integer> EmployeeID;

	@FXML
	private TableColumn<EmployeeData, String> phonenumber;

	@FXML
	private TableColumn<EmployeeData, Double> salary;

	@FXML
	private TableColumn<EmployeeData, String> PersonalID;

	@FXML
	private TextField PersonalIDText;

	@FXML
	private Button UpdateButton;

	@FXML
	private Button addButton;

	@FXML
	private TextField textname;

	@FXML
	private TextField SalaryText;

	@FXML
	private TextField PhoneText;

	@FXML
	private Button DeleteButton;

	@FXML
	private Button Exit;

	@FXML
	private Button HomeButton;

	@FXML
	private TextField SearchText;

	@FXML
	private Button SearchButton;

	@FXML
	private Button ShowData;

	@FXML
	private TextField personalIDTextToDelete;

	@FXML
	private TextField IDTextForEdit;

	@FXML
	private Button EditButton;

	@FXML
	private Button SalaryButton;

	@FXML
	private TextField SalaryAboveText;

	@FXML
	private Button MaxSalaryButton;

	@FXML
	private TextField AnswerText;

	@FXML
	private Button NoOfEmpButton;

	/*
	@FXML
	void EditClick(ActionEvent event) throws ClassNotFoundException, SQLException {

		Connection conn = Link.a.connectDB();
		String sql = " update employee set salary=? , ename=?, phonenumber=? , personal_ID=? where employeeID=?";

		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, SalaryText.getText());
			pst.setString(2, textname.getText());
			pst.setString(3, PhoneText.getText());
			pst.setString(4, PersonalIDText.getText());
			pst.setString(5, IDTextForEdit.getText());
			pst.execute();

			JOptionPane.showMessageDialog(null, "Employees Update");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	*/

	@FXML
	void AddClick(ActionEvent event) throws ClassNotFoundException, SQLException {
		conn = Link.a.connectDB();

		String sql = "insert into employee (employeeid, salary , ename, phonenumber , personal_ID)values(?,?,?,?,?)";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(5, PersonalIDText.getText());
			pst.setString(4, PhoneText.getText());
			pst.setString(3, textname.getText());
			pst.setString(2, SalaryText.getText());
			pst.setString(1, null); // for ownerID
			pst.execute();

			JOptionPane.showMessageDialog(null, "Employee Add succes");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	@FXML
	void DeleteClick(ActionEvent event) throws ClassNotFoundException, SQLException {
		conn = Link.a.connectDB();
		String sql = "delete from employee where personal_ID = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, personalIDTextToDelete.getText());
			pst.execute();
			JOptionPane.showMessageDialog(null,
					"Deleted The Employee who have personal ID : " + personalIDTextToDelete.getText());

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

		EmployeeName.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("ename"));
		EmployeeID.setCellValueFactory(new PropertyValueFactory<EmployeeData, Integer>("employeeNO"));
		phonenumber.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("phoneNO"));
		salary.setCellValueFactory(new PropertyValueFactory<EmployeeData, Double>("salary"));
		PersonalID.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("personalID"));
		Table.setItems(dataList);
		FilteredList<EmployeeData> filteredData = new FilteredList<>(dataList, b -> true);
		SearchText.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(person -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();

				if (person.getEname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches
				} else if (String.valueOf(person.getPersonalID()).indexOf(lowerCaseFilter) != -1)
					return true;
				else
					return false; // Does not match.
				// Does not match.
			});
		});
		SortedList<EmployeeData> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(Table.comparatorProperty());
		Table.setItems(sortedData);
	}

	private ObservableList<EmployeeData> getData() {
		String SQL = "select * from employee";
		try {

			// ObservableList<OwnerData> list = FXCollections.observableArrayList();
			Link.a.connectDB();
			Statement state = (Statement) Link.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				EmployeeData O = new EmployeeData(Integer.parseInt(rs.getString(1)),
						Double.parseDouble(rs.getString(2)), rs.getString(3), rs.getString(4), rs.getString(5));
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
	void ShowDataClick(ActionEvent event) {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		Table.setEditable(true);
		EmployeeName.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("ename"));
		EmployeeID.setCellValueFactory(new PropertyValueFactory<EmployeeData, Integer>("employeeNO"));
		phonenumber.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("phoneNO"));
		salary.setCellValueFactory(new PropertyValueFactory<EmployeeData, Double>("salary"));
		PersonalID.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("personalID"));
		getData();
		Table.setItems(dataList);
	}

	@FXML
	void UpdateClick(ActionEvent event) {

		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		Table.setEditable(true);
		EmployeeName.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("ename"));
		EmployeeID.setCellValueFactory(new PropertyValueFactory<EmployeeData, Integer>("employeeNO"));
		phonenumber.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("phoneNO"));
		salary.setCellValueFactory(new PropertyValueFactory<EmployeeData, Double>("salary"));
		PersonalID.setCellValueFactory(new PropertyValueFactory<EmployeeData, String>("personalID"));
		getData();
		Table.setItems(dataList);
	}

	@FXML
	ObservableList<EmployeeData> SalaryAboveClick(ActionEvent event) throws ClassNotFoundException, SQLException {
		ObservableList<EmployeeData> E1 = FXCollections.observableArrayList();
		// TODO Auto-generated method stub
		conn = Link.a.connectDB();
		/*
		 * SELECT ename FROM employee WHERE salary = (SELECT MAX(salary) FROM employee);
		 */
		String SQL = "select * from employee e where e.salary >" + SalaryAboveText.getText() + " order by e.ename";
		// String SQL = "select * from employee e where e.salary >" +
		// SalaryAboveText.getText() + " order by e.ename";
		try {

			// ObservableList<OwnerData> list = FXCollections.observableArrayList();
			Link.a.connectDB();
			Statement state = (Statement) Link.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				EmployeeData O = new EmployeeData(Integer.parseInt(rs.getString(1)),
						Double.parseDouble(rs.getString(2)), rs.getString(3), rs.getString(4), rs.getString(5));
				E1.add(O);
				Table.setItems(E1); ///////////////////////////////////////////////////////////////////////////////////////
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

		return E1;
	}

	@FXML
	void MaxSalaryClick(ActionEvent event) throws ClassNotFoundException, SQLException {
		conn = Link.a.connectDB();

		String sql = "SELECT ename FROM employee WHERE salary = (SELECT MAX(salary) FROM employee AS name);";

		Statement stmt = (Statement) conn.createStatement();
		// Executing the query
		ResultSet rs = stmt.executeQuery(sql);
		// Retrieving the result
		rs.next();
		String name = rs.getString(1);		
		AnswerText.setText("" + name);
	}

	@FXML
	void NoOfEmployeeClick(ActionEvent event) throws ClassNotFoundException, SQLException {
		conn = Link.a.connectDB();

		String sql = "SELECT COUNT(employeeID) FROM employee AS count";

		Statement stmt = (Statement) conn.createStatement();
		// Executing the query
		ResultSet rs = stmt.executeQuery(sql);
		// Retrieving the result
		rs.next();
		int count = rs.getInt(1);
		AnswerText.setText("" + count);
	}
	@FXML
    void getSelected (MouseEvent event){
    index = Table.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }	
	IDTextForEdit.setText(EmployeeID.getCellData(index).toString());
	PersonalIDText.setText(PersonalID.getCellData(index).toString());
	PhoneText.setText(phonenumber.getCellData(index).toString());
	textname.setText(EmployeeName.getCellData(index).toString());
	SalaryText.setText(salary.getCellData(index).toString());
    
    }

	@FXML
	void EditClick(ActionEvent event) throws ClassNotFoundException, SQLException {   
        try {
        	conn = Link.a.connectDB();
            String value1 = IDTextForEdit.getText();
            String value2 = textname.getText();
            String value3 = SalaryText.getText();
            String value4 = PhoneText.getText();
            String value5 = PersonalIDText.getText();
            
            String sql2 = " update employee set salary='"+value3+"', ename= '"+value2+"', phonenumber= '"+ value4+
            		"', personal_ID= '"+value5+"'where employeeID='"+value1+"'";
            
         //   String sql = "update users set user_id= '"+value1+"',username= '"+value2+"',password= '"+
             //       value3+"',email= '"+value4+"',type= '"+value5+"' where user_id='"+value1+"' ";
            pst= conn.prepareStatement(sql2);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Update");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
}