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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */


/**
 * Class to load the Update Appointment interface of the application
 */
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

    /**
     * Method to return the user to the View Appointment menu of the application
     * @param event
     */
    @FXML
    void onCancelBtnClick(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?\nAll values will be discarded.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            helper.controllerHelper.loadAppointmentView(event);
        }

    }

    /**
     * Method to update an existing appointment in the SQL database.
     * Input Data will be saved and then sent to the MySQL server using the update method.
     * @param event
     * @throws SQLException if SQL update fails
     */
    @FXML
    void onSaveBtnClick(ActionEvent event) throws SQLException {

        String alertText = helper.inputCheck.appointmentCheck(customerIdTxt.getText(), userIdTxt.getText());
        try {
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
            LocalDateTime localStart = LocalDateTime.of(startDate, LocalTime.of(Integer.parseInt(startHour), Integer.parseInt(startMinute)));
            LocalDateTime localEnd = LocalDateTime.of(endDate, LocalTime.of(Integer.parseInt(endHour), Integer.parseInt(endMinute)));
            ObservableList<Appointments> overlapCheck = helper.inputCheck.overlapCheck(customerId, appointmentId, localStart, localEnd);
            Boolean businessHour = helper.inputCheck.businessHoursCheck(localStart, localEnd);
            Boolean customerCheck = helper.inputCheck.customerCheck(customerId);
            Boolean userCheck = helper.inputCheck.userCheck(userId);

            //Check that appointment is within Business Hours
            if (!businessHour) {
                alertText = "Appointment must be scheduled within Business Hours of 8:00am EST - 10:00pm EST";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Adding Appointment");
                alert.setContentText(alertText);
                alert.showAndWait();
            }

            //Check Overlap
            else if (!(overlapCheck.isEmpty())){
                StringBuilder appointmentList = new StringBuilder();
                for(Appointments appointment : overlapCheck){
                    appointmentList.append("ID: "+ appointment.getAppointmentId() + "\n");
                    appointmentList.append("Start Time: " + appointment.getStart() + "\n");
                    appointmentList.append("End Time: " + appointment.getEnd() + "\n\n");
                }

                alertText = "Appointment time conflicts with existing customer appointment:\n\n" + appointmentList + "Please modify.";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Adding Appointment");
                alert.setContentText(alertText);
                alert.showAndWait();
            }
            else if (!customerCheck){
                alertText = "Customer ID does not exist. Please enter a valid Customer ID.";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Adding Appointment");
                alert.setContentText(alertText);
                alert.showAndWait();
            }
            else if (!userCheck){
                alertText = "User ID does not exist. Please enter a valid User ID.";
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Adding Appointment");
                alert.setContentText(alertText);
                alert.showAndWait();
            }

            if (businessHour == true && overlapCheck.isEmpty() && customerCheck == true && userCheck == true) {
                AppointmentsQuery.update(appointmentId, title, description, location, type, appointmentStart, appointmentEnd, customerId, userId, contactId, Users.currentUserName);
                helper.controllerHelper.loadAppointmentView(event);
            }
        }

        catch(Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Adding Appointment");
                alert.setContentText(alertText);
                alert.showAndWait();
            }


    }

    /**
     * This method is used to send the data from a selected appointment in the View Appointment screen and auto-populate the fields for update.
     * @param appointment
     */
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

    /**
     * Method to populate date/time and contact ComboBoxes
     * @param url
     * @param rb
     */
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
