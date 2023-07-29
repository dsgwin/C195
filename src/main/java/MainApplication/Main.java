package MainApplication;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author
 * Duncan Gwin
 * dgwin4@wgu.edu
 * 008698673
 */


/**
 * Main class that loads the application
 */
public class Main extends Application {


    /**
     * Loads the login screen of the application.
     * @param stage
     * @throws IOException if an I/O Error occurs
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/MainApplication/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 450);
        stage.setTitle("C195 - Duncan Gwin");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates log file for logging user activity and connects to the Database using the JDBC connection methods.
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        try {
            File logFile = new File("log_activity.txt");
            if (logFile.createNewFile()) {
                System.out.println("File created: " + logFile.getName());
            } else {
                System.out.println(logFile.getName() + " already exists");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        JDBC.openConnection();
        launch();
        JDBC.closeConnection();

    }


}