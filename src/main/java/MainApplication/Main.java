package MainApplication;

import DAO.CustomerQuery;
import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/MainApplication/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 450);
        stage.setTitle("C195 - Duncan Gwin");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        ZoneId myZone = ZoneId.systemDefault();
        ZonedDateTime myZDT = ZonedDateTime.of(java.time.LocalDateTime.now(), myZone);
        ZoneId utcZoneId = ZoneId.of("UTC");
        ZonedDateTime utcZDT = ZonedDateTime.ofInstant(myZDT.toInstant(), utcZoneId);
        System.out.println(myZDT);
        System.out.println(utcZDT);



        launch();
        JDBC.closeConnection();

    }


}