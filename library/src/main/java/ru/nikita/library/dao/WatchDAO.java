package ru.nikita.library.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.nikita.library.models.Watch;

public class WatchDAO {

    public static List<Watch> index() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        EntityManager em = emf.createEntityManager();
        List<Watch> r = em.createQuery("SELECT w FROM Watch w", Watch.class).getResultList();
        emf.close();
        em.close();
        return r;
    }

    public static void addNewWatch(Watch watch) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(watch);
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        } finally {
            emf.close();
            em.close();
        }

    }

    public static void deleteWatch(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            Watch watch = em.find(Watch.class, id);
            em.remove(watch);
            t.commit();
        } catch (Exception e) {
            if (t != null) {
                t.rollback();
            }
            e.printStackTrace();
        } finally {
            emf.close();
            em.close();
        }
    }

    public static Watch findWatch(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        EntityManager em = emf.createEntityManager();
        try {
            Watch watch = em.find(Watch.class, id);
            return watch;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emf.close();
            em.close();
        }
        return null;
    }

    public static void updateWatch(int id, Watch watchUpdate) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            Watch watch = em.find(Watch.class, id);
            et.begin();
            watch.setName(watchUpdate.getName());
            watch.setSeasons(watchUpdate.getSeasons());
            watch.setSeries(watchUpdate.getSeries());
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emf.close();
            em.close();
        }
    }
}