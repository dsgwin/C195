package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.time.ZoneId;
import java.util.Locale;

public class loginController {
    Locale locale = Locale.getDefault();

    @FXML
    private TextField loginUsername;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Label zoneIdLbl;

    @FXML
    void exitBtnClicked(ActionEvent event) {

        helper.controllerHelper.applicationExit(event);

    }

    @FXML
    void loginBtnClicked(ActionEvent event) {
        Locale locale = Locale.getDefault();
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println(locale);
        System.out.println(zoneId);
        zoneIdLbl.setText(String.valueOf(zoneId));


    }

}
