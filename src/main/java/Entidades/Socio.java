package Entidades;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "socios")

public class Socio {

    @Id
    @Column(name = "id_socio", nullable = false, length = 36)
    private String idSocio;

    @Column(name = "dni", nullable = false, length = 16)
    private String dni;

    @Column(name = "nombre", nullable = false, length = 80)
    private String nombre;

    @Column(name = "apellidos", nullable = false, length = 120)
    private String apellidos;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "email", length = 120)
    private String email;

    @OneToMany(mappedBy = "idSocio", cascade = CascadeType.ALL)
    private List<Reserva> reservas;

// constructor vacio obligatorio para hibernate
    public Socio() {
    }

    public Socio(String idSocio, String dni, String nombre, String apellidos, String telefono, String email) {
        this.idSocio = idSocio;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }



    public String getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(String idSocio) {
        this.idSocio = idSocio;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }


    @Override
    public String toString() {
        return nombre + " (" + dni + ")";
    }
}
