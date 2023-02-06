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
import javafx.stage.Stage;

public class HistoryController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	Connection conn = null;
	PreparedStatement pst = null;
	private ArrayList<PaymentHistoryData> data;
	private ObservableList<PaymentHistoryData> dataList = FXCollections.observableArrayList();
	@FXML
	private TextField TextID;

	@FXML
	private Button ViewButton;

	@FXML
	private TableView<PaymentHistoryData> Table;

	@FXML
	private TableColumn<PaymentHistoryData, String> sdateCol;

	@FXML
	private TableColumn<PaymentHistoryData, String> edateCol;

	@FXML
	private TableColumn<PaymentHistoryData, String> StatusCol;

	@FXML
	private TableColumn<PaymentHistoryData, Double> AmountCol;

	@FXML
	private TableColumn<PaymentHistoryData, String> IDCol;

	@FXML
	private TableColumn<PaymentHistoryData, String> CarNumberCol;

	@FXML
	private TableColumn<PaymentHistoryData, String> ServiceIDCol;

	@FXML
	private Button HomeButton;

	@FXML
	private Button AddButton;

	@FXML
	private TextField sdText;

	@FXML
	private TextField edText;

	@FXML
	private TextField AmountText;

	@FXML
	private TextField StatusText;

	@FXML
	private TextField addIDText;

	@FXML
	private TextField CarNumberText;

	@FXML
	private TextField ServiceIDText;

	@FXML
	private Button showDataButton;

	@FXML
	void showDataClick(ActionEvent event) {

		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		Table.setEditable(true);
		sdateCol.setCellValueFactory(new PropertyValueFactory<PaymentHistoryData, String>("serviceStartDate"));
		edateCol.setCellValueFactory(new PropertyValueFactory<PaymentHistoryData, String>("ServiceEndDate"));
		StatusCol.setCellValueFactory(new PropertyValueFactory<PaymentHistoryData, String>("payament_status"));
		AmountCol.setCellValueFactory(new PropertyValueFactory<PaymentHistoryData, Double>("money_amount"));
		IDCol.setCellValueFactory(new PropertyValueFactory<PaymentHistoryData, String>("personal_ID"));
		CarNumberCol.setCellValueFactory(new PropertyValueFactory<PaymentHistoryData, String>("carnumber"));
		ServiceIDCol.setCellValueFactory(new PropertyValueFactory<PaymentHistoryData, String>("serviceID"));
		getData1();
		Table.setItems(dataList);
	}

	private ObservableList<PaymentHistoryData> getData1() {
		String SQL = "select * from ServiceHistoryData";
		try {

			// ObservableList<OwnerData> list = FXCollections.observableArrayList();
			Link.a.connectDB();
			Statement state = (Statement) Link.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				PaymentHistoryData H = new PaymentHistoryData(Double.parseDouble(rs.getString(1)), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				dataList.add(H);
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

		String sql = "insert into  ServiceHistoryData(moneyamount,Sstart , Send , payment_status ,personal_ID ,carnumber ,serviceID )values(?,?,?,?,?,?,? )";

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, AmountText.getText());
			pst.setString(2, sdText.getText());
			pst.setString(3, edText.getText());
			pst.setString(4, StatusText.getText());
			pst.setString(5, addIDText.getText());
			pst.setString(6, CarNumberText.getText());
			pst.setString(7, ServiceIDText.getText());
			pst.execute();

			JOptionPane.showMessageDialog(null, "Users Add succes");

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	private ObservableList<PaymentHistoryData> getData() {
		String SQL = "select Sstart , Send , payment_status , moneyamount from ServiceHistoryData where personal_ID = "
				+ TextID.getText() + ";";
		try {

			// ObservableList<OwnerData> list = FXCollections.observableArrayList();
			Link.a.connectDB();
			Statement state = (Statement) Link.a.connectDB().createStatement();
			ResultSet rs = state.executeQuery(SQL);
			while (rs.next()) {
				PaymentHistoryData H = new PaymentHistoryData(rs.getString(1), rs.getString(2), rs.getString(3),
						Double.parseDouble(rs.getString(4)));
				dataList.add(H);
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
	void ViewClick(ActionEvent event) {
		data = new ArrayList<>();
		dataList = FXCollections.observableArrayList(data);
		Table.setEditable(true);
		sdateCol.setCellValueFactory(new PropertyValueFactory<PaymentHistoryData, String>("serviceStartDate"));
		edateCol.setCellValueFactory(new PropertyValueFactory<PaymentHistoryData, String>("ServiceEndDate"));
		StatusCol.setCellValueFactory(new PropertyValueFactory<PaymentHistoryData, String>("payament_status"));
		AmountCol.setCellValueFactory(new PropertyValueFactory<PaymentHistoryData, Double>("money_amount"));
		getData();
		Table.setItems(dataList);

	}

}
