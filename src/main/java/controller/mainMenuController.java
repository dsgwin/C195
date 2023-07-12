package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class mainMenuController {

    @FXML
    void onAppointmentBtnClick(ActionEvent event) {

    }

    @FXML
    void onCustomersBtnClick(ActionEvent event) {

    }

    @FXML
    void onExitBtnClick(ActionEvent event) {

        helper.controllerHelper.applicationExit(event);

    }

}