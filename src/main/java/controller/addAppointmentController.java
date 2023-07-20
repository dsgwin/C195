package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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
    private ComboBox<String> contactBox;

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
    void onSaveBtnClick(ActionEvent event) {
        LocalDate startDate = startDateBox.getValue();
        String startHour = startHourBox.getValue();
        String startMinute = startMinuteBox.getValue();
        // obtain the LocalDateTime
        LocalDateTime ldt = LocalDateTime.of(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth(), Integer.parseInt(startHour), Integer.parseInt(startMinute));
        // obtain the ZonedDateTime version of LocalDateTime
        ZonedDateTime locZdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
        // obtain the UTC ZonedDateTime of the ZonedDateTime version of LocalDateTime
        ZonedDateTime utcZdt = locZdt.withZoneSameInstant(ZoneOffset.UTC);
        // make it look good in 24 hour format sortable by yyyy-MM-dd HH:mm:ss  (we are going to ignore fractions beyond seconds
        DateTimeFormatter customFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println(customFormatter.format(locZdt));
        System.out.println(customFormatter.format(utcZdt));

    }


    @Override
    public void initialize(URL url, ResourceBundle rb){
        hours.addAll("00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
        minutes.addAll("00", "15", "30", "45");
        startHourBox.setItems(hours);
        startMinuteBox.setItems(minutes);
        endHourBox.setItems(hours);
        endMinuteBox.setItems(minutes);

    }

}
