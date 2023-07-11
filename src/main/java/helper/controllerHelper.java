package helper;

import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class controllerHelper {

    public static void applicationExit(Event event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you would like to exit the application?");

        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }


    }
}
