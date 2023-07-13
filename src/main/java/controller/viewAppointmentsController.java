package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.ToggleGroup;

public class viewAppointmentsController  {

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> titleCol;

    @FXML
    private TableColumn<?, ?> descriptionCol;

    @FXML
    private TableColumn<?, ?> locationCol;

    @FXML
    private TableColumn<?, ?> contactCol;

    @FXML
    private TableColumn<?, ?> typeCol;

    @FXML
    private TableColumn<?, ?> startCol;

    @FXML
    private TableColumn<?, ?> endCol;

    @FXML
    private TableColumn<?, ?> customerIdCol;

    @FXML
    private TableColumn<?, ?> userIdCol;

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

        helper.controllerHelper.loadUpdateAppointment(event);

    }

    @FXML
    void weeklyRBtnClick(ActionEvent event) {

    }

}
