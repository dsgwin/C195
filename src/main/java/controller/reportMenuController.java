package controller;

import DAO.ContactsQuery;
import DAO.ReportQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Contacts;

import java.net.URL;
import java.util.ResourceBundle;

public class reportMenuController implements Initializable {

    @FXML
    private Label reportHeader;

    @FXML
    private Button reports;

    @FXML
    private TextArea txtField;

    @FXML
    void contactScheduleBtnClick(ActionEvent event) {
        StringBuilder displayString = new StringBuilder();
        reportHeader.setText("Contact Schedule");

        ObservableList<Contacts> contactList = ContactsQuery.getAllContacts();
        for(Contacts contact : contactList){
            displayString.append(contact.getContactName()+"\n");
            ObservableList<String> list = ReportQuery.getContactSchedule(contact.getContactId());
            // Lambda #2 - Build String for Report Output
            list.forEach(s -> displayString.append(s+"\n"));
        }
        txtField.setText(displayString.toString());


    }

    @FXML
    void mainMenuBtnClick(ActionEvent event) {

        helper.controllerHelper.loadMainMenu(event);

    }

    @FXML
    void totalByCountryBtnClick(ActionEvent event) {

        StringBuilder displayString = new StringBuilder();
        reportHeader.setText("Total Appointments by Country");

        ObservableList<String> list = ReportQuery.getAppointmentsByCountry();
        // Lambda #2 - Build String for Report Output
        list.forEach(s -> displayString.append(s+"\n"));
        txtField.setText(displayString.toString());
    }

    @FXML
    void totalByCustBtnClick(ActionEvent event) {
        StringBuilder displayString = new StringBuilder();
        reportHeader.setText("Total Appointments by Type/Month");

        ObservableList<String> list = ReportQuery.getAppointmentsByType();
        // Lambda #2 - Build String for Report Output
        list.forEach(s -> displayString.append(s+"\n"));
        txtField.setText(displayString.toString());

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        StringBuilder displayString = new StringBuilder();
        reportHeader.setText("Total Appointments by Type/Month");

        ObservableList<String> list = ReportQuery.getAppointmentsByType();
        // Lambda #2 - Build String for Report Output
        list.forEach(s -> displayString.append(s+"\n"));
        txtField.setText(displayString.toString());

    }

}
