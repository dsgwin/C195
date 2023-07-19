package controller;

import DAO.CustomerQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;

public class viewCustomerController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableView<Customers> customersTblView;

    @FXML
    private TableColumn<Customers, Integer> idCol;

    @FXML
    private TableColumn<Customers, String> nameCol;

    @FXML
    private TableColumn<Customers, String> addressCol;

    @FXML
    private TableColumn<Customers, String> stateProvCol;

    @FXML
    private TableColumn<Customers, String> postalCodeCol;


    @FXML
    private TableColumn<Customers, String> phoneCol;


    @FXML
    void onAddBtnClick(ActionEvent event) {

        helper.controllerHelper.loadAddCustomer(event);

    }

    @FXML
    void onDeleteBtnClick(ActionEvent event) throws SQLException {
        Customers customer = customersTblView.getSelectionModel().getSelectedItem();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to delete " + customer.getCustomerName() + " and all associated appointments?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                DAO.CustomerQuery.delete(customer.getCustomerId());
                customersTblView.setItems(CustomerQuery.getAllCustomers());
                customersTblView.refresh();
            }
        }
        catch (Exception e){
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No customer selected");
            alert.setContentText("Please select a customer from the list to delete");
            alert.showAndWait();

        }

    }

    @FXML
    void onMainMenuBtnClick(ActionEvent event) {

        helper.controllerHelper.loadMainMenu(event);

    }

    @FXML
    void onUpdateBtnClick(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainApplication/updateCustomer.fxml"));
            loader.load();

            updateCustomerController updateController = loader.getController();

            updateController.sendCustomer(customersTblView.getSelectionModel().getSelectedItem());


            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No customer selected");
            alert.setContentText("Please select a customer from the list to update");
            alert.showAndWait();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        customersTblView.setItems(CustomerQuery.getAllCustomers());
        idCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        stateProvCol.setCellValueFactory(new PropertyValueFactory<>("division"));



    }

}
