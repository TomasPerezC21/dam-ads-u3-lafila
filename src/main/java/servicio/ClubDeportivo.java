package servicio;

import Entidades.*;
import jakarta.persistence.*;

import java.util.List;


public class ClubDeportivo {

    private EntityManagerFactory emf;

    public ClubDeportivo() {
        emf = Persistence.createEntityManagerFactory("clubDamaPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public void altaSocio(Socio socio) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(socio);
            tx.commit();
            em.close();

        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    public void altaPista(Pista pista) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // comprobar si ya existe
            Pista existente = em.find(Pista.class, pista.getIdPista());

            if (existente != null) {
                throw new Exception("Ya existe una pista con ese ID");
            }
            em.persist(pista);
            em.getTransaction().commit();

        } catch (Exception e) {
            throw e;
        }finally {
            em.close();
        }
    }

    public void cambiarDisponibilidadPista(int idPista, boolean disponible) throws Exception {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Pista pista = em.find(Pista.class, idPista);

            if (pista == null) {
                throw new Exception("La pista no existe");
            }

            pista.setDisponible(disponible);

            em.merge(pista);

            em.getTransaction().commit();

        } catch (Exception e) {

            throw e;

        } finally {

            em.close();

        }

    }

    public void crearReserva(Reserva reserva) throws Exception {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            // comprobar que el socio existe
            Socio socio = em.find(Socio.class, reserva.getIdSocio().getDni());

            if (socio == null) {
                throw new Exception("El socio no existe");
            }

            // comprobar que la pista existe
            Pista pista = em.find(Pista.class, reserva.getIdPista().getIdPista());

            if (pista == null) {
                throw new Exception("La pista no existe");
            }

            // comprobar disponibilidad
            if (!pista.getDisponible()) {
                throw new Exception("La pista no está disponible");
            }

            // asociar entidades gestionadas
            reserva.setIdSocio(socio);
            reserva.setIdPista(pista);

            em.persist(reserva);

            em.getTransaction().commit();

        } catch (Exception e) {

            throw e;

        } finally {

            em.close();

        }

    }

    public void cancelarReserva(int idReserva) throws Exception {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Reserva reserva = em.find(Reserva.class, idReserva);

            if (reserva == null) {
                throw new Exception("La reserva no existe");
            }
            em.remove(reserva);
            em.getTransaction().commit();

        } catch (Exception e) {
            throw e;

        } finally {
            em.close();
        }

    }
    public void bajaSocio(String dni) throws Exception {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Socio socio = em.find(Socio.class, dni);
            if (socio == null) {
                throw new Exception("El socio no existe");
            }
            // comprobar si tiene reservas
            if (socio.getReservas() != null && !socio.getReservas().isEmpty()) {
                throw new Exception("No se puede eliminar el socio porque tiene reservas");
            }
            em.remove(socio);
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            throw e;

        }
    }

    public List<Socio> getSocios() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Socio> query = em.createQuery("SELECT s FROM Socio s", Socio.class);
            return query.getResultList();
        } finally {
            em.close();
        }

    }

    public List<Pista> getPistas() {

        EntityManager em = emf.createEntityManager();

        try {

            TypedQuery<Pista> query =
                    em.createQuery("SELECT p FROM Pista p", Pista.class);

            return query.getResultList();

        } finally {

            em.close();

        }

    }


    public List<Reserva> getReservas() {

        EntityManager em = emf.createEntityManager();

        try {

            TypedQuery<Reserva> query =
                    em.createQuery(
                            "SELECT r FROM Reserva r " +
                                    "JOIN FETCH r.idSocio " +
                                    "JOIN FETCH r.idPista",
                            Reserva.class
                    );

            return query.getResultList();

        } finally {

            em.close();

        }

    }





}