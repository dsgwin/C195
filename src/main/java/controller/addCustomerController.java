package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Countries;
import model.FirstLevelDivisions;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

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

    @FXML
    void onCancelBtnClick(ActionEvent event) {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?\nAll values will be discarded.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            helper.controllerHelper.loadCustomerView(event);
        }
    }

    @FXML
    void onSaveBtnClick(ActionEvent event) {

        String customerName = nameTxt.getText();
        String customerAddress = addressTxt.getText();
        String customerPostal = postalCodeTxt.getText();
        String phone = phoneTxt.getText();
        FirstLevelDivisions division = state_provinceBox.getValue();
        int divisionId = division.getDivisionId();

        if(!customerName.isEmpty() || !customerAddress.isEmpty() || customerPostal.isEmpty() || !phone.isEmpty() || !(division == null)){
            try{
                DAO.CustomerQuery.insert(customerName, customerAddress, customerPostal, phone, divisionId);
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<Countries> countryList = FXCollections.observableArrayList();
        countryList.addAll(DAO.CountriesQuery.getAllCountries());
        for(Countries country : countryList){
            countryBox.getItems().add(country);
        }


    }

}
