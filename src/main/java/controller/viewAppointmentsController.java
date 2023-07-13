package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
    private ToggleGroup weekMonth;

    @FXML
    void onAddBtnClick(ActionEvent event) {

    }

    @FXML
    void onDeleteBtnClick(ActionEvent event) {

    }

    @FXML
    void onMainMenuBtnClick(ActionEvent event) {

    }

    @FXML
    void onUpdateBtnClick(ActionEvent event) {

    }

}
