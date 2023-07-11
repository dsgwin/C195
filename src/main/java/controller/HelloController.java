package controller;

import DAO.DBCountries;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.Countries;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        ObservableList<Countries> countryList = DBCountries.getAllCountries();
        for(Countries C : countryList){
            System.out.println("CountryID: " + C.getCountry_id() + " Name: " + C.getCountryName());
        }

    }
}