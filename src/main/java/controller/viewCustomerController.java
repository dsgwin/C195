package controller;

import DAO.CustomerQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Countries;
import model.Customers;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Class that controller the View Customers menu of the application
 */
public class viewCustomerController implements Initializable {

    Stage stage;


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
    private TableColumn<Countries, String> countryCol;


    @FXML
    private TableColumn<Customers, String> phoneCol;


    /**
     * Method that loads the Add Customer screen of the application
     * @param event
     */
    @FXML
    void onAddBtnClick(ActionEvent event) {

        helper.controllerHelper.loadAddCustomer(event);

    }

    /**
     * Method that deletes the selected customer and all associated appointments.
     * A confirmation will appear when a customer is selected for deletion.
     * If no customer is selected, an alert will appear.
     * @param event
     * @throws SQLException
     */
    @FXML
    void onDeleteBtnClick(ActionEvent event) throws SQLException {
        Customers customer = customersTblView.getSelectionModel().getSelectedItem();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to delete " + customer.getCustomerName() + " and all associated appointments?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                int appointmentsDeleted = DAO.AppointmentsQuery.deleteCustomerAppointments(customer.getCustomerId());

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

    /**
     * Method that returns the user to the main menu of the application
     * @param event
     */
    @FXML
    void onMainMenuBtnClick(ActionEvent event) {

        helper.controllerHelper.loadMainMenu(event);

    }

    /**
     * Method that loads the update customer screen based on the selected customer.
     * If no customer is selected, an alert will appear.
     * @param event
     */
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
            stage.centerOnScreen();
            stage.show();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No customer selected");
            alert.setContentText("Please select a customer from the list to update");
            alert.showAndWait();
        }

    }

    /**
     * Method to initialize the customer data based on SQL Query
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        idCol.setSortType(TableColumn.SortType.ASCENDING);



        idCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        countryCol.setCellValueFactory(new PropertyValueFactory<>("country"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        stateProvCol.setCellValueFactory(new PropertyValueFactory<>("division"));
        customersTblView.setItems(CustomerQuery.getAllCustomers());
        customersTblView.getSortOrder().add(idCol);
    }

}
