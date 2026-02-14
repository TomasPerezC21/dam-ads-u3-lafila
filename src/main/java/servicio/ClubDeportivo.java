package servicio;

import Entidades.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

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

}