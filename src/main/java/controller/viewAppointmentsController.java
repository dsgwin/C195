package controller;

import DAO.AppointmentsQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Contacts;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */

/**
 * Class that controls the View Appointment menu of the application
 */
public class viewAppointmentsController  implements Initializable {

    Stage stage;

    ObservableList<Appointments> allAppointments = FXCollections.observableArrayList();

    @FXML
    private TableView<Appointments> tblView;

    @FXML
    private TableColumn<Appointments, Integer> idCol;

    @FXML
    private TableColumn<Appointments, String> titleCol;

    @FXML
    private TableColumn<Appointments, String> descriptionCol;

    @FXML
    private TableColumn<Appointments, String> locationCol;

    @FXML
    private TableColumn<Contacts, String> contactCol;

    @FXML
    private TableColumn<Appointments, String> typeCol;

    @FXML
    private TableColumn<Appointments, Timestamp> startCol;

    @FXML
    private TableColumn<Appointments, Timestamp> endCol;

    @FXML
    private TableColumn<Appointments, Integer> customerIdCol;

    @FXML
    private TableColumn<Appointments, Integer> userIdCol;

    @FXML
    private DatePicker filterDate;

    @FXML
    private RadioButton weeklyRBtn;

    @FXML
    private RadioButton monthlyRBtn;

    /**
     * Method that changes the date filter to all dates when All Radio Button is selected
     * @param event
     */
    @FXML
    void allRBtnSelected(ActionEvent event) {
        filterDate.setDisable(true);
        tblView.setItems(AppointmentsQuery.getAllAppointments());
        tblView.refresh();


    }

    /**
     * Method that updates the filter based on date input in the date selector
     * @param event
     */
    @FXML
    void filterDateChanged(ActionEvent event) {
        // Get Local Date from Date Selector
        LocalDate startDate = filterDate.getValue();
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(00,00));


        if(weeklyRBtn.isSelected()) {
            // Add 1 week for weekly filter endDate
            LocalDateTime endDateTime = LocalDateTime.of(startDate.plusWeeks(1), LocalTime.of(23,59));
            // Convert to UTC Zoned Date for SQL Query
            ZonedDateTime filterStart = helper.dateTimeFormatter.localToUTC(startDateTime);
            ZonedDateTime filterEnd = helper.dateTimeFormatter.localToUTC(endDateTime);
            ObservableList<Appointments> filteredAppointments = AppointmentsQuery.getAppointmentsFiltered(filterStart, filterEnd);
            tblView.setItems(filteredAppointments);
            tblView.refresh();

        } else if (monthlyRBtn.isSelected()) {
            // Add 1 week for weekly filter endDate
            LocalDateTime endDateTime = LocalDateTime.of(startDate.plusMonths(1), LocalTime.of(23,59));
            // Convert to UTC Zoned Date for SQL Query
            ZonedDateTime filterStart = helper.dateTimeFormatter.localToUTC(startDateTime);
            ZonedDateTime filterEnd = helper.dateTimeFormatter.localToUTC(endDateTime);
            ObservableList<Appointments> filteredAppointments = AppointmentsQuery.getAppointmentsFiltered(filterStart, filterEnd);
            tblView.setItems(filteredAppointments);
            tblView.refresh();
        }

    }

    /**
     * Method that updates the filter to a range that is 1 month from the selected filter date
     * @param event
     */
    @FXML
    void monthlyRBtnSelected(ActionEvent event) {
        filterDate.setDisable(false);
        // Get Local Date from Date Selector and add 1 month for monthly filter endDate
        LocalDate startDate = filterDate.getValue();
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(00,00));
        LocalDateTime endDateTime = LocalDateTime.of(startDate.plusMonths(1), LocalTime.of(23,59));
        // Convert to UTC Zoned Date for SQL Query
        ZonedDateTime filterStart = helper.dateTimeFormatter.localToUTC(startDateTime);
        ZonedDateTime filterEnd = helper.dateTimeFormatter.localToUTC(endDateTime);
        ObservableList<Appointments> filteredAppointments = AppointmentsQuery.getAppointmentsFiltered(filterStart, filterEnd);
        tblView.setItems(filteredAppointments);
        tblView.refresh();

    }

    /**
     * Method that updates the filter to a range that is 1 week from the selected filter date
     * @param event
     */
    @FXML
    void weeklyRBtnSelected(ActionEvent event) {

        filterDate.setDisable(false);
        // Get Local Date from Date Selector and add 1 week for weekly filter endDate
        LocalDate startDate = filterDate.getValue();
        LocalDateTime startDateTime = LocalDateTime.of(startDate, LocalTime.of(00,00));
        LocalDateTime endDateTime = LocalDateTime.of(startDate.plusWeeks(1), LocalTime.of(23,59));
        // Convert to UTC Zoned Date for SQL Query
        ZonedDateTime filterStart = helper.dateTimeFormatter.localToUTC(startDateTime);
        ZonedDateTime filterEnd = helper.dateTimeFormatter.localToUTC(endDateTime);
        ObservableList<Appointments> filteredAppointments = AppointmentsQuery.getAppointmentsFiltered(filterStart, filterEnd);
        tblView.setItems(filteredAppointments);
        tblView.refresh();



    }

    /**
     * Method that loads the Add Appointment screen of the application
     * @param event
     */
    @FXML
    void onAddBtnClick(ActionEvent event) {

        helper.controllerHelper.loadAddAppointment(event);

    }

    /**
     * Method that deletes the selected appointment
     * If no appointment is selected, an alert will appear.
     * @param event
     */
    @FXML
    void onDeleteBtnClick(ActionEvent event) {

        Appointments appointment = tblView.getSelectionModel().getSelectedItem();
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to delete Appointment ID:" + appointment.getAppointmentId() + " for " + appointment.getType() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {

                DAO.AppointmentsQuery.delete(appointment.getAppointmentId());
                tblView.setItems(AppointmentsQuery.getAllAppointments());
                tblView.refresh();
            }
        }
        catch (Exception e){
            System.out.println(e);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No appointment selected");
            alert.setContentText("Please select an appointment from the list to delete");
            alert.showAndWait();

        }

    }

    /**
     * Method that returns the user to the main menu of the application
     * @param event
     */
    @FXML
    void onMainMenuBtnClick(ActionEvent event) {

        helper.controllerHelper.loadMainMenu(event);

    }

    /**
     * Method that loads the Update Appointment screen based on the selected appointment.
     * If no appointment is selected, an alert will appear.
     * @param event
     */
    @FXML
    void onUpdateBtnClick(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/MainApplication/updateAppointment.fxml"));
            loader.load();

            updateAppointmentController updateController = loader.getController();

            updateController.sendAppointment(tblView.getSelectionModel().getSelectedItem());


            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.centerOnScreen();
            stage.show();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No appointment selected");
            alert.setContentText("Please select an appointment from the list to update");
            alert.showAndWait();
        }



    }

    /**
     * Method to initialize the data in the view and populate with appointments identified via SQL query
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idCol.setSortType(TableColumn.SortType.ASCENDING);

        allAppointments.addAll(AppointmentsQuery.getAllAppointments());

        tblView.setItems(allAppointments);
        idCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));

        tblView.getSortOrder().add(idCol);

        LocalDate today = LocalDateTime.now().toLocalDate();
        filterDate.setValue(today);



    }

}
