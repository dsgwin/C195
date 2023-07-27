package controller;

import DAO.AppointmentsQuery;
import helper.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import model.Appointments;
import model.Users;

import java.net.URL;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;

public class mainMenuController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb){

        int userId = Users.currentUserId;
        // Get current time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Add 15 minutes to current time
        LocalDateTime currentPlusFifteen = LocalDateTime.now().plusMinutes(15);
        // Convert times to Timestamp for SQL Query
        Timestamp utcStartRange = helper.dateTimeFormatter.localToTimestamp(currentDateTime);
        Timestamp utcEndRange = helper.dateTimeFormatter.localToTimestamp(currentPlusFifteen);
        //Get User Appointments within 15 minutes
        ObservableList<Appointments> appointmentList = AppointmentsQuery.getUserAppointments(userId, utcStartRange, utcEndRange);
        if (appointmentList.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Upcoming Appointments");
            alert.setContentText("No Upcoming Appointments!");
            alert.showAndWait();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Upcoming Appointments");
            String alertText = "";
            for(Appointments appointment : appointmentList){
                int appointmentId = appointment.getAppointmentId();
                Timestamp startTime = appointment.getStart();
                alertText.concat("Appointment ID: " + appointmentId + " -- Start Time: "+ startTime + "\n");
            }
            alert.setContentText("No Upcoming Appointments!");

        }

    }

}