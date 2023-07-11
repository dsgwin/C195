package controller;

import DAO.DBCountries;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.Countries;

public class loginController {

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginBtn;

    @FXML
    private Button exitBtn;

    @FXML
    void exitBtnClicked(ActionEvent event) {

        helper.controllerHelper.applicationExit(event);

    }

    @FXML
    void loginBtnClicked(ActionEvent event) {

    }

}