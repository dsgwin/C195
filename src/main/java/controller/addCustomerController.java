package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Countries;
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
 * Class for the GUI controls of Adding Customers to Database
 */
public class addCustomerController implements Initializable {


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
     * Method that loads the First Level Divisions relevant to the selected Country in ComboBox
     * @param event
     */
    @FXML
    void onSelect(ActionEvent event) {
        state_provinceBox.getItems().clear();
        Countries country = countryBox.getValue();
        int countryId = country.getCountryId();

        ObservableList<FirstLevelDivisions> divisionList = FXCollections.observableArrayList();
        divisionList.addAll(DAO.FirstLevelDivisionsQuery.getAllDivisions());
        for(FirstLevelDivisions division : divisionList) {
            if (division.getCountryId() == countryId) {
                state_provinceBox.getItems().add(division);
            }
        }



    }

    /**
     * Method to send the user back to the view customers menu without saving the customer data
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
     * Method that saves the customer input data and inserts into MySQL Database
     * @param event
     */
    @FXML
    void onSaveBtnClick(ActionEvent event) {

        if(!nameTxt.getText().isEmpty() && !addressTxt.getText().isEmpty() && !postalCodeTxt.getText().isEmpty() && !phoneTxt.getText().isEmpty() && !(state_provinceBox.getSelectionModel().isEmpty())){

            String customerName = nameTxt.getText();
            String customerAddress = addressTxt.getText();
            String customerPostal = postalCodeTxt.getText();
            String phone = phoneTxt.getText();
            FirstLevelDivisions division = state_provinceBox.getValue();
            int divisionId = division.getDivisionId();
            String user = Users.currentUserName;
            try{
                DAO.CustomerQuery.insert(customerName, customerAddress, customerPostal, phone, divisionId, user);
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
     * Method that initializes the data for ComboBox selection data
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<Countries> countryList = FXCollections.observableArrayList();
        countryList.addAll(DAO.CountriesQuery.getAllCountries());
        for(Countries country : countryList){
            countryBox.getItems().add(country);
        }


    }

}
