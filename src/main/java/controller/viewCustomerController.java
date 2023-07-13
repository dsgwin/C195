package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

public class viewCustomerController {

    @FXML
    private TableColumn<?, ?> idCol;

    @FXML
    private TableColumn<?, ?> nameCol;

    @FXML
    private TableColumn<?, ?> addressCol;

    @FXML
    private TableColumn<?, ?> cityCol;

    @FXML
    private TableColumn<?, ?> stateProvCol;

    @FXML
    private TableColumn<?, ?> postalCodeCol;

    @FXML
    private TableColumn<?, ?> countryCol;

    @FXML
    private TableColumn<?, ?> phoneCol;

    @FXML
    private Label errorPaneTxt;

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
