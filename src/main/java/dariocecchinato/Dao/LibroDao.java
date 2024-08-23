package dariocecchinato.Dao;

import dariocecchinato.entities.Libro;
import dariocecchinato.exceptions.IsbnNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class LibroDao {

    private final EntityManager em;

    public LibroDao(EntityManager em) {
        this.em = em;
    }

    //*************************************  Metodo SAVE  ****************************************

    public void save(Libro libro) {
        //1.
        EntityTransaction transaction = em.getTransaction();
        //2.
        transaction.begin();
        //3.
        em.persist(libro);
        //4.
        transaction.commit();

        System.out.println("Il libro " + libro.getTitolo() + " è stato salvato correttamente");
    }

    //*************************************  Metodo FINDBYISBN  ****************************************

    public Libro getByISBN(String isbnRivista) {
        Libro found = em.find(Libro.class, isbnRivista);
        if (found == null) {
            throw new IsbnNotFoundException(isbnRivista);
        } else {
            return found;
        }
    }

    //*************************************  Metodo FindByISBN & Delete ****************************************

    public void delete(String isbnRivista) {
        Libro found = this.getByISBN(isbnRivista);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Il Libro " + found.getTitolo() + " è stato rimosso");

    }

    //*************************************  Metodo FindByYear  ****************************************
    /*public List<Libro> findByYear(int annoPubblicazione) {
        TypedQuery<Libro> query = em.createQuery(
                "SELECT l FROM Libro l WHERE l.annoPubblicazione = :annoPubblicazione",
                Libro.class);
        query.setParameter("annoPubblicazione", annoPubblicazione);
        return query.getResultList();
    }*/

    //*************************************  Metodo FindByYear  ****************************************


}
