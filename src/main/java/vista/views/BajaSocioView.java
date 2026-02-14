package vista.views;

import servicio.ClubDeportivo;
import Entidades.*;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.function.Consumer;

public class BajaSocioView extends GridPane {


    public BajaSocioView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8);
        setVgap(8);

        ComboBox<Socio> combo = new ComboBox<>();
        combo.getItems().addAll(club.getSocios());

        Button baja = new Button("Dar de baja");

        addRow(0, new Label("Socio:"), combo);
        add(baja, 1, 1);
        baja.setOnAction(e -> {

            Socio socio = combo.getValue();

            if (socio == null) {
                showError("Selecciona un socio");
                return;
            }
            try {

                club.bajaSocio(socio.getIdSocio());
                combo.getItems().remove(socio);
                showInfo("Socio eliminado correctamente");

            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });
    }

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
