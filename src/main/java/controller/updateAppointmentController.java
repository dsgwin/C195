package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Appointments;
import model.Countries;
import model.Customers;
import model.FirstLevelDivisions;

public class updateAppointmentController {

    @FXML
    private TextField appointmentIdTxt;

    @FXML
    private TextField titleTxt;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private TextField locationTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private TextField customerIdTxt;

    @FXML
    private TextField userIdTxt;

    @FXML
    private ComboBox<?> contactBox;

    @FXML
    private DatePicker startDateBox;

    @FXML
    private ComboBox<?> startTimeBox;

    @FXML
    private ComboBox<?> endTimeBox;

    @FXML
    private DatePicker endDateBox;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    void onCancelBtnClick(ActionEvent event) {

        helper.controllerHelper.loadAppointmentView(event);

    }

    @FXML
    void onSaveBtnClick(ActionEvent event) {

    }

    void sendAppointment(Appointments appointment){

        appointmentIdTxt.setText(String.valueOf(appointment.getAppointmentId()));
        titleTxt.setText(appointment.getTitle());
        descriptionTxt.setText(appointment.getDescription());
        locationTxt.setText(appointment.getLocation());
        typeTxt.setText(appointment.getType());
        //startDateBox.setDayCellFactory();
        //endDateBox.setDayCellFactory();
        customerIdTxt.setText(String.valueOf(appointment.getCustomerId()));
        userIdTxt.setText(String.valueOf(appointment.getUserId()));
        //contactBoxIdTxt.setText(String.valueOf(appointment.getContactId()));


    }
}
