package controller;

import DAO.CountriesQuery;
import DAO.FirstLevelDivisionsQuery;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Countries;
import model.FirstLevelDivisions;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class addCustomerController implements Initializable {

    @FXML
    private TextField customerIdTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField addressTxt;

    @FXML
    private TextField cityTxt;

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
        String customerCity = cityTxt.getText();
        FirstLevelDivisions state = state_provinceBox.getValue();
        Countries country = countryBox.getValue();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<Countries> countryList = FXCollections.observableArrayList();
        countryList.addAll(DAO.CountriesQuery.getAllCountries());
        for(Countries country : countryList){
            countryBox.getItems().add(country);
        }

        ObservableList<FirstLevelDivisions> divisionList = FXCollections.observableArrayList();
        divisionList.addAll(DAO.FirstLevelDivisionsQuery.getAllDivisions());
        for(FirstLevelDivisions division : divisionList){
            state_provinceBox.getItems().add(division);
        }


    }

}
