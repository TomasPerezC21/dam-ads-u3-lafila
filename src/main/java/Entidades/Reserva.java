package Entidades;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservas")

public class Reserva {

    @Id
    @Column(name = "id_reserva")
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
    private int duracionMin;

    @Column(name = "precio")
    private double precio;


    // constructor VACÍO
    public Reserva() {
    }

    // constructor correcto
    public Reserva(String idReserva,
                   Socio idSocio,
                   Pista idPista,
                   LocalDate fecha,
                   LocalTime horaInicio,
                   double precio) {

        this.idReserva = idReserva;
        this.idSocio = idSocio;
        this.idPista = idPista;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.precio = precio;

        // duración por defecto
        this.duracionMin = 60;
    }


    public String getIdReserva() {
        return idReserva;
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

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public double getPrecio() {
        return precio;
    }

    @Override
    public String toString() {
        return idReserva + " - " + idSocio + " - " + idPista;
    }
}
