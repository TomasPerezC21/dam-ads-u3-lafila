package vista.views;
import Entidades.*;
import servicio.ClubDeportivo;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.util.function.Consumer;

public class CancelarReservaView extends GridPane {
    public CancelarReservaView(ClubDeportivo club) {
        setPadding(new Insets(12));
        setHgap(8); setVgap(8);

        ComboBox<Reserva> id = new ComboBox();

        if (club.getReservas() != null) {
            id.getItems().addAll(club.getReservas());
        }

        Button cancelar = new Button("Cancelar reserva");

        addRow(0, new Label("Reserva"), id);
        add(cancelar, 1, 1);

        cancelar.setOnAction(e -> {

            try {
                Reserva reservaSeleccionada = id.getValue();

                if (reservaSeleccionada == null) {
                    showError("Por favor, selecciona una reserva de la lista.");
                    return;
                }

                boolean exito = club.cancelarReserva(reservaSeleccionada.getIdReserva());

                if (exito) {
                    showInfo("La reserva ha sido cancelada correctamente.");

                    id.getItems().remove(reservaSeleccionada);
                    id.setValue(null);
                } else {
                    showError("No se pudo cancelar la reserva.");
                }
            }catch (Exception ex) {
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
