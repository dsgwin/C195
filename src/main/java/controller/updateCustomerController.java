package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Countries;
import model.Customers;
import model.FirstLevelDivisions;
import model.Users;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * Class that controls the Update Customer menu of the application
 */
public class updateCustomerController implements Initializable {

    ObservableList<Countries> countryList = FXCollections.observableArrayList();
    ObservableList<FirstLevelDivisions> divisionList = FXCollections.observableArrayList();


    @FXML
    private TextField customerIdTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField postalCodeTxt;

    @FXML
    private TextField phoneTxt;

    @FXML
    private ComboBox<FirstLevelDivisions> state_provinceBox;

    @FXML
    private ComboBox<Countries> countryBox;

    /**
     * Method to auto-populate first-level divisions based on the Country selection
     * @param event
     */
    @FXML
    void onSelect(ActionEvent event) {
        state_provinceBox.getItems().clear();
        Countries country = countryBox.getValue();
        int countryId = country.getCountryId();

        for (FirstLevelDivisions division : divisionList) {
            if (division.getCountryId() == countryId) {
                state_provinceBox.getItems().add(division);
            }
        }
    }

    /**
     * Method to return the user to the View Customer menu of the application
     * @param event
     */
    @FXML
    void onCancelBtnClick(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?\nAll values will be discarded.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            helper.controllerHelper.loadCustomerView(event);
        }

    }

    /**
     * Method that transmits the modified customer data and sends it to the SQL server.
     * The data is transmitted using the update function of SQL.
     * @param event
     */
    @FXML
    void onSaveBtnClick(ActionEvent event) {

        int customerId = Integer.parseInt(customerIdTxt.getText());
        String customerName = nameTxt.getText();
        String customerAddress = addressTxt.getText();
        String customerPostal = postalCodeTxt.getText();
        String phone = phoneTxt.getText();
        FirstLevelDivisions division = state_provinceBox.getValue();
        int divisionId = division.getDivisionId();

        if(!nameTxt.getText().isEmpty() && !addressTxt.getText().isEmpty() && !postalCodeTxt.getText().isEmpty() && !phoneTxt.getText().isEmpty() && !(state_provinceBox.getSelectionModel().isEmpty())){            try{
                DAO.CustomerQuery.update(customerId, customerName, customerAddress, customerPostal, phone, divisionId, Users.currentUserName);
                helper.controllerHelper.loadCustomerView(event);

            } catch (SQLException e) {
                System.out.println(e);
            }

        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fill Out Required Fields");
            alert.setContentText("Please fill out all fields.");
            alert.showAndWait();
        }

    }

    /**
     * Method to transmit data from a selected customer in the View Customer menu and populate the update fields for editing.
     * @param customer
     */
    void sendCustomer(Customers customer){
        int divisionId = customer.getDivisionId();
        int countryId = -1;
        for(FirstLevelDivisions division : divisionList){
            if(divisionId == division.getDivisionId()){
                state_provinceBox.setValue(division);
                countryId = division.getCountryId();
            }
        }
        for(FirstLevelDivisions division : divisionList) {
            if (division.getCountryId() == countryId) {
                state_provinceBox.getItems().add(division);
            }
        }
        for(Countries country : countryList){
            if(countryId == country.getCountryId()){
                countryBox.setValue(country);
            }
        }
        customerIdTxt.setText(String.valueOf(customer.getCustomerId()));
        nameTxt.setText(customer.getCustomerName());
        addressTxt.setText(customer.getAddress());
        phoneTxt.setText(customer.getPhoneNumber());
        postalCodeTxt.setText(customer.getPostalCode());

    }

    /**
     * Method to initialize ComboBox data in the update fields.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        divisionList.addAll(DAO.FirstLevelDivisionsQuery.getAllDivisions());

        countryList.addAll(DAO.CountriesQuery.getAllCountries());
        for(Countries country : countryList){
            countryBox.getItems().add(country);
        }


    }

}
