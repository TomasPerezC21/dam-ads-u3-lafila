package Entidades;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservas", schema = "club_dama")
public class Reserva {

    @Id
    @Column(name = "id_reserva", nullable = false, length = 36)
    private String idReserva;

    @ManyToOne
    @JoinColumn(name = "id_socio")
    private Socio idSocio;

    @ManyToOne
    @JoinColumn(name = "id_pista")
    private Pista idPista;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "duracion_min")
    private Integer duracionMin;

    // CONSTRUCTOR VACÍO
    public Reserva() {}

    // CONSTRUCTOR COMPLETO
    public Reserva(String idReserva, Socio socio, Pista pista,
                   LocalDate fecha, LocalTime horaInicio, Integer duracionMin) {

        this.idReserva = idReserva;
        this.idSocio = socio;
        this.idPista = pista;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.duracionMin = duracionMin;
    }

    // GETTERS Y SETTERS

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    public Socio getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(Socio idSocio) {
        this.idSocio = idSocio;
    }

    public Pista getIdPista() {
        return idPista;
    }

    public void setIdPista(Pista idPista) {
        this.idPista = idPista;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Integer getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(Integer duracionMin) {
        this.duracionMin = duracionMin;
    }

    @Override
    public String toString() {
        return idReserva + " - " + idSocio.getNombre() + " - " + idPista.getDeporte();
    }
}