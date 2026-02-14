package vista.views;

import servicio.ClubDeportivo;
import Entidades.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.function.Consumer;

public class BajaSocioView extends GridPane {



    private void showError(String msg) {
        Alert a = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        a.setHeaderText("Error");
        a.showAndWait();
    }
    private void showInfo(String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        a.setHeaderText(null);
        a.showAndWait();
    }

}
