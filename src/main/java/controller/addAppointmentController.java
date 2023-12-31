package controller;

import DAO.AppointmentsQuery;
import DAO.ContactsQuery;
import helper.dateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Appointments;
import model.Contacts;
import model.Users;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * Class and Methods to Add Appointments
 */
public class addAppointmentController implements Initializable {

    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();

    @FXML
    private ComboBox<Contacts> contactBox;

    @FXML
    private ComboBox<String> startHourBox;

    @FXML
    private ComboBox<String> startMinuteBox;

    @FXML
    private ComboBox<String> endHourBox;

    @FXML
    private ComboBox<String> endMinuteBox;

    @FXML
    private TextField customerIdTxt;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private DatePicker endDateBox;


    @FXML
    private TextField locationTxt;

    @FXML
    private DatePicker startDateBox;


    @FXML
    private TextField titleTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private TextField userIdTxt;

    /**
     * Method to send the user back to the view appointment menu without saving the appointment data
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
     * Method to save to appointment input data and insert into the MySQL database.
     * @param event
     * @throws SQLException if SQL insert fails
     */

    @FXML
    void onSaveBtnClick(ActionEvent event) throws SQLException {

        String alertText = helper.inputCheck.appointmentCheck(customerIdTxt.getText(), userIdTxt.getText());
        try {
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
            ObservableList<Appointments> overlapCheck = helper.inputCheck.overlapCheck(customerId, -1, localStart, localEnd);
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
                AppointmentsQuery.insert(title, description, location, type, appointmentStart, appointmentEnd, customerId, userId, contactId, Users.currentUserName);
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
     * Method to initialize the view and load combo boxes with selectable data
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
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
        for(Contacts contact : contactList){
            contactBox.getItems().add(contact);
        }


    }

}
