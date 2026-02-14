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

}