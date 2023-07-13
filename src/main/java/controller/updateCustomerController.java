package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class updateCustomerController {

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
    private ComboBox<?> state_provinceBox;

    @FXML
    private ComboBox<?> countryBox;

    @FXML
    void onCancelBtnClick(ActionEvent event) {

        helper.controllerHelper.loadCustomerView(event);

    }

    @FXML
    void onSaveBtnClick(ActionEvent event) {

    }

}
