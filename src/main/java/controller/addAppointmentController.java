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
import model.Contacts;
import model.Countries;
import model.Users;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class addAppointmentController implements Initializable {

    ObservableList<String> hours = FXCollections.observableArrayList();
    ObservableList<String> minutes = FXCollections.observableArrayList();

    @FXML
    private TextField appointmentIdTxt;

    @FXML
    private Button cancelBtn;

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
    private Button saveBtn;

    @FXML
    private DatePicker startDateBox;


    @FXML
    private TextField titleTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private TextField userIdTxt;

    @FXML
    void onCancelBtnClick(ActionEvent event) {

        helper.controllerHelper.loadAppointmentView(event);

    }

    @FXML
    void onSaveBtnClick(ActionEvent event) throws SQLException {
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
        Timestamp appointmentStart = dateTimeFormatter.localToUTCTimestamp(startDate, startHour, startMinute);
        //Process Appointment End Date/Time
        LocalDate endDate = startDateBox.getValue();
        String endHour = startHourBox.getValue();
        String endMinute = startMinuteBox.getValue();
        Timestamp appointmentEnd = dateTimeFormatter.localToUTCTimestamp(startDate, startHour, startMinute);
        AppointmentsQuery.insert(title, description, location, type, appointmentStart, appointmentEnd, customerId, userId, contactId, Users.currentUserName);


    }


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
