package application;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class ServiceController {
	private ArrayList<Car2Service> data1;
	private ObservableList<Car2Service> dataList1 = FXCollections.observableArrayList();
	
	
	private ArrayList<ServiceData> data2;
	private ObservableList<ServiceData> dataList2 = FXCollections.observableArrayList();
	
	
	private ArrayList<OwnerData> data3;
	private ObservableList<OwnerData> dataList3 = FXCollections.observableArrayList();
	
	
	private ArrayList<CarData> data4;
	private ObservableList<CarData> dataList4 = FXCollections.observableArrayList();
	
	
	private ArrayList<Company2Service> data5;
	private ObservableList<Company2Service> dataList5 = FXCollections.observableArrayList();
	
	
	private ArrayList<CompanyData> data6;
	private ObservableList<CompanyData> dataList6 = FXCollections.observableArrayList();
	@FXML
    private TableView<?> Table;

    @FXML
    private TableColumn<ServiceData, ?> ServiceNameCol;

    @FXML
    private TableColumn<ServiceData, ?> ServiceIDCol;

    @FXML
    private TableColumn<?, ?> CostCol;

    @FXML
    private TableColumn<?, ?> companyPhoneCol;

    @FXML
    private TableColumn<?, ?> CompanyNameCol;

    @FXML
    private TableColumn<?, ?> OwnerNameCol;

    @FXML
    private Button RefreshButton;

    @FXML
    private TextField CostText;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button Exit;

    @FXML
    private Button HomeButton;

    @FXML
    private ComboBox<?> serviceIDCompo;

    @FXML
    private ComboBox<?> serviceCompoBox;

    @FXML
    private Button ShowDataButton;

    @FXML
    private Button viewOwnersCompany;

    @FXML
    private TextField CompanyNameText;

    @FXML
    private Button addButton;

    @FXML
    void DeleteClick(ActionEvent event) {

    }

    @FXML
    void ExitClick(ActionEvent event) {

    }

    @FXML
    void HomeClick(ActionEvent event) {

    }

    @FXML
    void UpdateClick(ActionEvent event) {

    }

}
