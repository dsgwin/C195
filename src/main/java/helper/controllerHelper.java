package helper;

import DAO.AppointmentsQuery;
import controller.*;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.Appointments;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Class containing functions to help controllers move from one view to another.
 */
public abstract class controllerHelper {

    /**
     * Exits the application and provides a confirmation alert.
     * @param event
     */
    public static void applicationExit(Event event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to exit the application?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            JDBC.closeConnection();
            System.exit(0);
        }
    }

    /**
     * Loads the main menu screen of application
     * @param event
     */
    public static void loadMainMenu(Event event){

        Stage stage;

        try{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(controllerHelper.class.getResource("/MainApplication/MainMenu.fxml"));
            loader.load();

            mainMenuController Controller = loader.getController();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.centerOnScreen();
            stage.show();

        }
        catch (Exception e) {

            System.out.println(e);

        }

    }

    /**
     * Loads the view customer screen of application
     * @param event
     */
    public static void loadCustomerView(Event event){
        Stage stage;

        try{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(controllerHelper.class.getResource("/MainApplication/viewCustomers.fxml"));
            loader.load();

            viewCustomerController Controller = loader.getController();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.centerOnScreen();
            stage.show();

        }
        catch (Exception e) {

            System.out.println(e);

        }
    }

    /**
     * loads the View Appointments screen of application
     * @param event
     */
    public static void loadAppointmentView(Event event){
        Stage stage;

        try{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(controllerHelper.class.getResource("/MainApplication/viewAppointments.fxml"));
            loader.load();

            viewAppointmentsController Controller = loader.getController();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.centerOnScreen();
            stage.show();

        }
        catch (Exception e) {

            System.out.println(e);

        }
    }

    /**
     * loads the Add appointment screen of application
     * @param event
     */
    public static void loadAddAppointment(Event event){

        Stage stage;

        try{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(controllerHelper.class.getResource("/MainApplication/addAppointment.fxml"));
            loader.load();

            addAppointmentController Controller = loader.getController();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.centerOnScreen();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.centerOnScreen();
            stage.show();

        }
        catch (Exception e) {

            System.out.println(e);

        }

    }

    /**
     * loads the Add customer screen of application
     * @param event
     */
    public static void loadAddCustomer(Event event){

        Stage stage;

        try{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(controllerHelper.class.getResource("/MainApplication/addCustomer.fxml"));
            loader.load();

            addCustomerController Controller = loader.getController();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.centerOnScreen();
            stage.show();

        }
        catch (Exception e) {

            System.out.println(e);

        }

    }

    /**
     * loads the Report Menu screen of application
     * @param event
     */
    public static void loadReportMenu(Event event){

        Stage stage;

        try{

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(controllerHelper.class.getResource("/MainApplication/ReportMenu.fxml"));
            loader.load();

            reportMenuController Controller = loader.getController();

            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.centerOnScreen();
            stage.show();

        }
        catch (Exception e) {

            System.out.println(e);

        }

    }

    /**
     * Performs a check of appointments within 15 minutes of user login
     * If an appointment exists within 15 minutes, an alert will appear with appointment information
     * If no appointment exists, an alert will appear displaying no upcoming appointments
     * @param userId User ID of the user logging in.
     */
    public static void upcomingAppointmentCheck(int userId){
        // Get current time
        LocalDateTime currentDateTime = LocalDateTime.now();
        // Add 15 minutes to current time
        LocalDateTime currentPlusFifteen = LocalDateTime.now().plusMinutes(15);
        // Convert times to Timestamp for SQL Query
        Timestamp utcStartRange = helper.dateTimeFormatter.localToTimestamp(currentDateTime);
        Timestamp utcEndRange = helper.dateTimeFormatter.localToTimestamp(currentPlusFifteen);
        //Get User Appointments within 15 minutes
        ObservableList<Appointments> appointmentList = AppointmentsQuery.getUserAppointments(userId, utcStartRange, utcEndRange);
        for (Appointments appointment : appointmentList){
            System.out.println(appointment.getAppointmentId());
        }
        if (appointmentList.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Upcoming Appointments");
            alert.setContentText("No Upcoming Appointments!");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Upcoming Appointments");
            StringBuilder alertText = new StringBuilder();
            alertText.append("Upcoming appointments: \n");
            for (Appointments appointment : appointmentList) {
                int appointmentId = appointment.getAppointmentId();
                Timestamp startTime = appointment.getStart();
                alertText.append("Appointment ID: " + appointmentId + " -- Start Time: " + startTime + "\n");
            }
            alert.setContentText(alertText.toString());
            alert.showAndWait();
        }
    }

}
