package controller;

import helper.*;;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * Class for Main Menu of application that includes buttons for Customer, Appointment, and Report Views
 */
public class mainMenuController {

    /**
     * Loads the viewAppointmentController when button is clicked
     * @param event
     */
    @FXML
    void onAppointmentBtnClick(ActionEvent event) {
        controllerHelper.loadAppointmentView(event);
    }

    /**
     * Loads the viewCustomerController when button is clicked
     * @param event
     */
    @FXML
    void onCustomersBtnClick(ActionEvent event) {

        controllerHelper.loadCustomerView(event);

    }

    /**
     * Exits the application when clicked
     * A confirmation alert will appear to verify application close.
     * @param event
     */
    @FXML
    void onExitBtnClick(ActionEvent event) {

        helper.controllerHelper.applicationExit(event);

    }

    /**
     * Loads the reportMenuController when button is clicked
     * @param event
     */
    @FXML
    void onReportBtnClick(ActionEvent event) {

        helper.controllerHelper.loadReportMenu(event);

    }


}