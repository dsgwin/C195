package controller;

import helper.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class mainMenuController {

    Stage stage;
    Parent scene;


    @FXML
    void onAppointmentBtnClick(ActionEvent event) {
        controllerHelper.loadAppointmentView(event);
    }

    @FXML
    void onCustomersBtnClick(ActionEvent event) {

        controllerHelper.loadCustomerView(event);

    }

    @FXML
    void onExitBtnClick(ActionEvent event) {

        helper.controllerHelper.applicationExit(event);

    }

}