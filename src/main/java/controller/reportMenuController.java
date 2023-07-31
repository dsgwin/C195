package controller;

import DAO.ContactsQuery;
import DAO.ReportQuery;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.Contacts;

import java.net.URL;
import java.util.ResourceBundle;
/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * Class that manages the Report Menu Interface
 */
public class reportMenuController implements Initializable {

    @FXML
    private Label reportHeader;

    @FXML
    private TextArea txtField;

    /**
     * Method that loads the data for the Contact Schedule report when clicked
     * @param event
     */
    @FXML
    void contactScheduleBtnClick(ActionEvent event){
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

    /**
     * Method that takes the user back to the main menu when clicked
     * @param event
     */
    @FXML
    void mainMenuBtnClick(ActionEvent event) {

        helper.controllerHelper.loadMainMenu(event);

    }

    /**
     * Method that loads the data for the Total Appointments by Country report
     * Contains Lamba Expression #2 - Build String for Report Output
     * @param event
     */
    @FXML
    void totalByCountryBtnClick(ActionEvent event) {

        StringBuilder displayString = new StringBuilder();
        reportHeader.setText("Total Appointments by Country");

        ObservableList<String> list = ReportQuery.getAppointmentsByCountry();
        // Lambda #2 - Build String for Report Output
        list.forEach(s -> displayString.append(s+"\n"));
        txtField.setText(displayString.toString());
    }

    /**
     * Method that loads the Total Appointments by Month and Type Report
     * Contains Lamba Expression #2 - Build String for Report Output
     * @param event
     */
    @FXML
    void totalByTypeBtnClick(ActionEvent event) {
        StringBuilder displayString = new StringBuilder();
        reportHeader.setText("Total Appointments by Type/Month");

        ObservableList<String> list = ReportQuery.getAppointmentsByType();
        // Lambda #2 - Build String for Report Output
        list.forEach(s -> displayString.append(s+"\n"));
        txtField.setText(displayString.toString());

    }

    /**
     * Method to initialize the view and load the total appointments by type/month report
     * Contains Lamba Expression #2 - Build String for Report Output
     * @param url
     * @param rb
     */
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
