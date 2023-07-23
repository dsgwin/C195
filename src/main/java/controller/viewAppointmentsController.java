package controller;

import DAO.AppointmentsQuery;
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
import java.util.ResourceBundle;

public class viewAppointmentsController  implements Initializable {

    Stage stage;

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
    private Label errorPaneTxt;

    @FXML
    private RadioButton weeklyRBtn;

    @FXML
    private ToggleGroup weekMonth;

    @FXML
    private RadioButton monthlyRBtn;

    @FXML
    void monthlyRBtnClick(ActionEvent event) {

    }

    @FXML
    void onAddBtnClick(ActionEvent event) {

        helper.controllerHelper.loadAddAppointment(event);

    }

    @FXML
    void onDeleteBtnClick(ActionEvent event) {

    }

    @FXML
    void onMainMenuBtnClick(ActionEvent event) {

        helper.controllerHelper.loadMainMenu(event);

    }

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
            stage.show();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No appointment selected");
            alert.setContentText("Please select an appointment from the list to update");
            alert.showAndWait();
        }



    }

    @FXML
    void weeklyRBtnClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tblView.setItems(AppointmentsQuery.getAllAppointments());
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



    }

}
