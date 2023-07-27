package controller;

import helper.*;;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class mainMenuController {

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

    @FXML
    void onReportBtnClick(ActionEvent event) {

        helper.controllerHelper.loadReportMenu(event);

    }


}