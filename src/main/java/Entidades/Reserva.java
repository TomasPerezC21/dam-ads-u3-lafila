package Entidades;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservas", schema = "club_dama")
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

    private LocalDate fecha;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "duracion_min")
    private int duracionMin;

    private double precio;

    public Reserva() {}

    public Reserva(String idReserva, Socio socio, Pista pista,
                   LocalDate fecha, LocalTime horaInicio,
                   int duracionMin, double precio) {

        this.idReserva = idReserva;
        this.idSocio = socio;
        this.idPista = pista;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.duracionMin = duracionMin;
        this.precio = precio;
    }

    public String getIdReserva() { return idReserva; }

    public Socio getIdSocio() { return idSocio; }

    public Pista getIdPista() { return idPista; }

    public LocalDate getFecha() { return fecha; }

    public LocalTime getHoraInicio() { return horaInicio; }

    public int getDuracionMin() { return duracionMin; }

    public double getPrecio() { return precio; }
}