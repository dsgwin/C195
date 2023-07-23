package controller;

import DAO.AppointmentsQuery;
import DAO.ContactsQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointments;
import model.Contacts;
import helper.*;
import model.Users;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;



public class updateAppointmentController implements Initializable {

    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();


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
    private ComboBox<Contacts> contactBox;

    @FXML
    private DatePicker startDateBox;

    @FXML
    private ComboBox<String> startHourBox;

    @FXML
    private ComboBox<String> startMinuteBox;


    @FXML
    private ComboBox<String> endHourBox;

    @FXML
    private ComboBox<String> endMinuteBox;

    @FXML
    private DatePicker endDateBox;

    @FXML
    void onCancelBtnClick(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?\nAll values will be discarded.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            helper.controllerHelper.loadAppointmentView(event);
        }

    }

    @FXML
    void onSaveBtnClick(ActionEvent event) throws SQLException {

        int appointmentId = Integer.parseInt(appointmentIdTxt.getText());
        String title = titleTxt.getText();
        String description = descriptionTxt.getText();
        String location = locationTxt.getText();
        String type = typeTxt.getText();
        int customerId = Integer.parseInt(customerIdTxt.getText());
        int userId = Integer.parseInt(userIdTxt.getText());
        Contacts contact = contactBox.getValue();
        int contactId = contact.getContactId();

        //Process Appointment Start Date/Time
        LocalDate startDate = startDateBox.getValue();
        String startHour = startHourBox.getValue();
        String startMinute = startMinuteBox.getValue();
        Timestamp appointmentStart = dateTimeFormatter.localToTimestamp(startDate, startHour, startMinute);
        //Process Appointment End Date/Time
        LocalDate endDate = endDateBox.getValue();
        String endHour = endHourBox.getValue();
        String endMinute = endMinuteBox.getValue();
        Timestamp appointmentEnd = dateTimeFormatter.localToTimestamp(endDate, endHour, endMinute);

        AppointmentsQuery.update(appointmentId, title, description, location, type, appointmentStart, appointmentEnd, customerId, userId, contactId, Users.currentUserName);

        helper.controllerHelper.loadAppointmentView(event);

    }

    void sendAppointment(Appointments appointment){

        String startHour = dateTimeFormatter.formatTimeHour(appointment.getStart().toLocalDateTime());
        String startMinute = dateTimeFormatter.formatTimeMinute(appointment.getStart().toLocalDateTime());
        String endHour = dateTimeFormatter.formatTimeHour(appointment.getEnd().toLocalDateTime());
        String endMinute = dateTimeFormatter.formatTimeMinute(appointment.getEnd().toLocalDateTime());

        appointmentIdTxt.setText(String.valueOf(appointment.getAppointmentId()));
        titleTxt.setText(appointment.getTitle());
        descriptionTxt.setText(appointment.getDescription());
        locationTxt.setText(appointment.getLocation());
        typeTxt.setText(appointment.getType());
        startDateBox.setValue(appointment.getStart().toLocalDateTime().toLocalDate());
        startHourBox.setValue(startHour);
        startMinuteBox.setValue(startMinute);
        endDateBox.setValue(appointment.getEnd().toLocalDateTime().toLocalDate());
        endHourBox.setValue(endHour);
        endMinuteBox.setValue(endMinute);
        customerIdTxt.setText(String.valueOf(appointment.getCustomerId()));
        userIdTxt.setText(String.valueOf(appointment.getUserId()));


        int contactId = appointment.getContactId();
        for(Contacts contact : ContactsQuery.getAllContacts()){
            if(contact.getContactId() == contactId) {
                contactBox.setValue(contact);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize Date/Time ComboBoxes
        hours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minutes.addAll("00", "15", "30", "45");
        startHourBox.setItems(hours);
        startMinuteBox.setItems(minutes);
        endHourBox.setItems(hours);
        endMinuteBox.setItems(minutes);
        // Initialize Contact ComboBoxes
        ObservableList<Contacts> contactList = ContactsQuery.getAllContacts();
        for (Contacts contact : contactList) {
            contactBox.getItems().add(contact);
        }
    }

    }
