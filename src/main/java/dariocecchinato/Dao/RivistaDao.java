package dariocecchinato.Dao;

import dariocecchinato.entities.Rivista;
import dariocecchinato.exceptions.IsbnNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class RivistaDao {

    private final EntityManager em;

    public RivistaDao(EntityManager em) {
        this.em = em;
    }

    //*************************************  Metodo SAVE  ****************************************
    public void save(Rivista rivista) {
        //1.
        EntityTransaction transaction = em.getTransaction();
        //2.
        transaction.begin();
        //3.
        em.persist(rivista);
        //4.
        transaction.commit();

        System.out.println("La rivista " + rivista.getTitolo() + " è stata salvata correttamente");
    }

    //*************************************  Metodo FINDBYISBN  ****************************************
    public Rivista getByISBN(String isbnRivista) {
        Rivista found = em.find(Rivista.class, isbnRivista);
        if (found == null) {
            throw new IsbnNotFoundException(isbnRivista);
        } else {
            return found;
        }
    }

    //*************************************  Metodo FINDBYISBN  & DELETE****************************************
    public void delete(String isbnRivista) {
        Rivista found = this.getByISBN(isbnRivista);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("La Rivista " + found.getTitolo() + " è stata rimossa");

    }
}
