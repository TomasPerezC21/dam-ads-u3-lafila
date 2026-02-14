package Entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "pistas")

public class Pista {

    @Id
    @Column(name = "id_pista", nullable = false, length = 36)
    private String idPista;

    @Column(name = "deporte", nullable = false, length = 80)
    private String deporte;

    @Column(name = "descripcion", length = 120)
    private String descripcion;

    @Column(name = "disponible")
    private Boolean disponible;

    @OneToMany(mappedBy = "idPista")
    private List<Reserva> reservas;

    // CONSTRUCTOR VACÍO
    public Pista() {}

    // CONSTRUCTOR COMPLETO
    public Pista(String idPista, String deporte, String descripcion, Boolean disponible) {
        this.idPista = idPista;
        this.deporte = deporte;
        this.descripcion = descripcion;
        this.disponible = disponible;
    }

    // GETTERS Y SETTERS

    public String getIdPista() {
        return idPista;
    }

    public void setIdPista(String idPista) {
        this.idPista = idPista;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        return deporte + " (" + idPista + ")";
    }
}