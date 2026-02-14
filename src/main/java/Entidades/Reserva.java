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
    @JoinColumn(name = "id_socio", nullable = false)
    private Socio idSocio;

    @ManyToOne
    @JoinColumn(name = "id_pista", nullable = false)
    private Pista idPista;

    @Column(name = "fecha", nullable = false)
    private LocalDate fecha;

    @Column(name = "hora_inicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "duracion_min", nullable = false)
    private int duracionMin;

    @Column(name = "precio", nullable = false)
    private double precio;

    public Reserva() {
    }

    public Reserva(String idReserva, Socio idSocio, Pista idPista,
                   LocalDate fecha, LocalTime horaInicio,
                   int duracionMin, double precio) {
        this.idReserva = idReserva;
        this.idSocio = idSocio;
        this.idPista = idPista;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.duracionMin = duracionMin;
        this.precio = precio;
    }

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

    public int getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return idReserva + " - " + idPista.getDeporte();
    }
}