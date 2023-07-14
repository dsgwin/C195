package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    @FXML
    private Label usernameLbl;

    @FXML
    private TextField loginUsername;

    @FXML
    private Label passwordLbl;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Button loginBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Label welcomeLbl;

    @FXML
    private Label pleaseLbl;

    @FXML
    private Label detectedLbl;

    @FXML
    private Label zoneIdLbl;

    @FXML
    private Label errorLbl;


    @FXML
    void exitBtnClicked(ActionEvent event) {

        helper.controllerHelper.applicationExit(event);

    }

    @FXML
    void loginBtnClicked(ActionEvent event) {

            helper.controllerHelper.loadMainMenu(event);



    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Locale locale = Locale.getDefault();
        ZoneId zoneId = ZoneId.systemDefault();
        rb = ResourceBundle.getBundle("MainApplication/Nat", Locale.FRANCE);
        System.out.println(rb);

        welcomeLbl.setText(rb.getString("Welcome"));
        pleaseLbl.setText(rb.getString("Please"));
        usernameLbl.setText(rb.getString("Username"));
        passwordLbl.setText(rb.getString("Password"));
        detectedLbl.setText(rb.getString("Detected"));
        loginBtn.setText(rb.getString("Login"));
        exitBtn.setText(rb.getString("Exit"));

        System.out.println(locale);
        System.out.println(zoneId);
        zoneIdLbl.setText(String.valueOf(zoneId));



    }

}
