package ru.nikita.library.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import ru.nikita.library.models.Book;

public class BookDAO {

    public static List<Book> index() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        EntityManager em = emf.createEntityManager();
        List<Book> r = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
        emf.close();
        em.close();
        return r;
    }

    public static void addNewBook(Book book) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            em.persist(book);
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

    public static void deleteBook(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        try {
            t.begin();
            Book book = em.find(Book.class, id);
            em.remove(book);
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

    public static Book findBook(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        EntityManager em = emf.createEntityManager();
        try {
            Book b = em.find(Book.class, id);
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emf.close();
            em.close();
        }
        return null;
    }

    public static void updateBook(int id, Book bookUpdate) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            Book book = em.find(Book.class, id);
            et.begin();
            book.setAuthor(bookUpdate.getAuthor());
            book.setNameOfBook(bookUpdate.getNameOfBook());
            book.setYears(bookUpdate.getYears());
            book.setNumberOfPages(bookUpdate.getNumberOfPages());
            book.setPrice(bookUpdate.getPrice());
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emf.close();
            em.close();
        }
    }
}