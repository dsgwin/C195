package controller;

import DAO.CustomerQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customers;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ResourceBundle;

public class viewCustomerController implements Initializable {

    @FXML
    private TableView<Customers> customersTblView;

    @FXML
    private TableColumn<Customers, Integer> idCol;

    @FXML
    private TableColumn<Customers, String> nameCol;

    @FXML
    private TableColumn<Customers, String> addressCol;

    @FXML
    private TableColumn<Customers, String> cityCol;

    @FXML
    private TableColumn<Customers, String> stateProvCol;

    @FXML
    private TableColumn<Customers, String> postalCodeCol;

    @FXML
    private TableColumn<Customers, String> countryCol;

    @FXML
    private TableColumn<Customers, String> phoneCol;

    @FXML
    private Label errorPaneTxt;

    @FXML
    void onAddBtnClick(ActionEvent event) {

        helper.controllerHelper.loadAddCustomer(event);

    }

    @FXML
    void onDeleteBtnClick(ActionEvent event) {

    }

    @FXML
    void onMainMenuBtnClick(ActionEvent event) {

        helper.controllerHelper.loadMainMenu(event);

    }

    @FXML
    void onUpdateBtnClick(ActionEvent event) {

        helper.controllerHelper.loadUpdateCustomer(event);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        customersTblView.setItems(CustomerQuery.getAllCustomers());
        idCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));


    }

}
