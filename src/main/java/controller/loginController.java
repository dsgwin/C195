package controller;

import DAO.UsersQuery;
import helper.LogInterface;
import helper.dateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Users;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    void loginBtnClicked(ActionEvent event) throws SQLException {
        ResourceBundle rb = ResourceBundle.getBundle("MainApplication/Nat", Locale.getDefault());
        String username = loginUsername.getText();
        String password = loginPassword.getText();

        if(!username.isEmpty() || !password.isEmpty()) {
                if(UsersQuery.userLoginQuery(username, password)) {

                    // Lambda #1 - Create log message at successful login
                    LogInterface logMessage = s -> dateTimeFormatter.getCurrentTimestamp() + ": " + s + " for user \"" + username+ "\"!";
                    logWrite(logMessage.getLogMessage("Successful login attempt"));
                    System.out.println(Users.currentUserId);

                    helper.controllerHelper.upcomingAppointmentCheck(Users.currentUserId);

                    helper.controllerHelper.loadMainMenu(event);
                    }
                else {
                    // Lambda #1 - Create log message at failed login
                        LogInterface logMessage = s -> dateTimeFormatter.getCurrentTimestamp() + ": " + s + " for user \"" + username + "\"!";
                        logWrite(logMessage.getLogMessage("Failed login attempt"));

                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle(rb.getString("Failed"));
                        alert.setContentText(rb.getString("TryAgain"));
                        alert.showAndWait();
                    }
                }
        else {
            errorLbl.setText(rb.getString("Required"));
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ZoneId zoneId = ZoneId.systemDefault();

        try {
            rb = ResourceBundle.getBundle("MainApplication/Nat", Locale.getDefault());


            welcomeLbl.setText(rb.getString("Welcome"));
            pleaseLbl.setText(rb.getString("Please"));
            usernameLbl.setText(rb.getString("Username"));
            passwordLbl.setText(rb.getString("Password"));
            detectedLbl.setText(rb.getString("Detected"));
            loginBtn.setText(rb.getString("Login"));
            exitBtn.setText(rb.getString("Exit"));
        }
        catch (Exception e){
            // Handles exception and defaults to English if locale not recognized.

        }

        zoneIdLbl.setText(String.valueOf(zoneId));

    }

    private void logWrite(String logMessage){
        try {
            FileWriter myWriter = new FileWriter("log_activity.txt", true);
            myWriter.append(logMessage+"\n");
            myWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
