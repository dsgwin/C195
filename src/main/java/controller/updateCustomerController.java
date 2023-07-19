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

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

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
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

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

        for (FirstLevelDivisions division : divisionList) {
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

        int customerId = Integer.parseInt(customerIdTxt.getText());
        String customerName = nameTxt.getText();
        String customerAddress = addressTxt.getText();
        String customerPostal = postalCodeTxt.getText();
        String phone = phoneTxt.getText();
        FirstLevelDivisions division = state_provinceBox.getValue();
        int divisionId = division.getDivisionId();

        if(!customerName.isEmpty() || !customerAddress.isEmpty() || customerPostal.isEmpty() || !phone.isEmpty() || !(division == null)){
            try{
                DAO.CustomerQuery.update(customerId, customerName, customerAddress, customerPostal, phone, divisionId);
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

    void sendCustomer(Customers customer){
        int divisionId = customer.getDivisionId();
        int countryId = -1;
        for(FirstLevelDivisions division : divisionList){
            if(divisionId == division.getDivisionId()){
                state_provinceBox.setValue(division);
                countryId = division.getCountryId();
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        divisionList.addAll(DAO.FirstLevelDivisionsQuery.getAllDivisions());

        countryList.addAll(DAO.CountriesQuery.getAllCountries());
        for(Countries country : countryList){
            countryBox.getItems().add(country);
        }


    }

}
