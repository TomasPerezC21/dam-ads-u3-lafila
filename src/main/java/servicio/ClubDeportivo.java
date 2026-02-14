package servicio;

import Entidades.*;
import jakarta.persistence.*;

import java.util.List;


public class ClubDeportivo {

    private EntityManagerFactory emf;

    public ClubDeportivo() {
        emf = Persistence.createEntityManagerFactory("ejemplopersistenciaJPA");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    // ---------------- SOCIOS ----------------

    public void altaSocio(Socio socio) throws Exception {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Socio existente = em.find(Socio.class, socio.getIdSocio());

            if (existente != null) {
                throw new Exception("Ya existe un socio con ese ID");
            }

            em.persist(socio);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive())
                em.getTransaction().rollback();

            throw e;

        } finally {

            em.close();

        }
    }

    public boolean bajaSocio(String idSocio) throws Exception {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Socio socio = em.find(Socio.class, idSocio);

            if (socio == null)
                return false;

            if (socio.getReservas() != null && !socio.getReservas().isEmpty())
                throw new Exception("No se puede eliminar el socio porque tiene reservas");

            em.remove(socio);

            em.getTransaction().commit();

            return true;

        } catch (Exception e) {

            if (em.getTransaction().isActive())
                em.getTransaction().rollback();

            throw e;

        } finally {

            em.close();

        }
    }

    public List<Socio> getSocios() {

        EntityManager em = emf.createEntityManager();

        try {

            return em.createQuery("SELECT s FROM Socio s", Socio.class)
                    .getResultList();

        } finally {

            em.close();

        }
    }

    // ---------------- PISTAS ----------------

    public void altaPista(Pista pista) throws Exception {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Pista existente = em.find(Pista.class, pista.getIdPista());

            if (existente != null)
                throw new Exception("Ya existe una pista con ese ID");

            em.persist(pista);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive())
                em.getTransaction().rollback();

            throw e;

        } finally {

            em.close();

        }
    }

    public boolean cambiarDisponibilidadPista(String idPista, boolean disponible) throws Exception {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Pista pista = em.find(Pista.class, idPista);

            if (pista == null)
                return false;

            pista.setDisponible(disponible);

            em.getTransaction().commit();

            return true;

        } catch (Exception e) {

            if (em.getTransaction().isActive())
                em.getTransaction().rollback();

            throw e;

        } finally {

            em.close();

        }
    }

    public List<Pista> getPistas() {

        EntityManager em = emf.createEntityManager();

        try {

            return em.createQuery("SELECT p FROM Pista p", Pista.class)
                    .getResultList();

        } finally {

            em.close();

        }
    }

    // ---------------- RESERVAS ----------------

    public void crearReserva(Reserva reserva) throws Exception {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Socio socio = em.find(Socio.class, reserva.getIdSocio().getIdSocio());

            if (socio == null)
                throw new Exception("El socio no existe");

            Pista pista = em.find(Pista.class, reserva.getIdPista().getIdPista());

            if (pista == null)
                throw new Exception("La pista no existe");

            if (!pista.getDisponible())
                throw new Exception("La pista no está disponible");

            reserva.setIdSocio(socio);
            reserva.setIdPista(pista);

            em.persist(reserva);

            em.getTransaction().commit();

        } catch (Exception e) {

            if (em.getTransaction().isActive())
                em.getTransaction().rollback();

            throw e;

        } finally {

            em.close();

        }
    }

    public boolean cancelarReserva(String idReserva) throws Exception {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Reserva reserva = em.find(Reserva.class, idReserva);

            if (reserva == null)
                return false;

            em.remove(reserva);

            em.getTransaction().commit();

            return true;

        } catch (Exception e) {

            if (em.getTransaction().isActive())
                em.getTransaction().rollback();

            throw e;

        } finally {

            em.close();

        }
    }

    public List<Reserva> getReservas() {

        EntityManager em = emf.createEntityManager();

        try {

            return em.createQuery(
                            "SELECT r FROM Reserva r " +
                                    "JOIN FETCH r.idSocio " +
                                    "JOIN FETCH r.idPista",
                            Reserva.class)
                    .getResultList();

        } finally {

            em.close();

        }
    }
}