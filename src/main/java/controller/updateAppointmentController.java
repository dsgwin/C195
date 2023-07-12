package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class updateAppointmentController  {

    @FXML
    private TextField appointmentIdTxt;

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<?> contactBox;

    @FXML
    private TextField customerIdTxt;

    @FXML
    private TextField descriptionTxt;

    @FXML
    private DatePicker endDateBox;

    @FXML
    private ComboBox<?> endTimeBox;

    @FXML
    private TextField locationTxt;

    @FXML
    private Button saveBtn;

    @FXML
    private DatePicker startDateBox;

    @FXML
    private ComboBox<?> startTimeBox;

    @FXML
    private TextField titleTxt;

    @FXML
    private TextField typeTxt;

    @FXML
    private TextField userIdTxt;

    @FXML
    void onCancelBtnClick(ActionEvent event) {

    }

    @FXML
    void onSaveBtnClick(ActionEvent event) {

    }

}
