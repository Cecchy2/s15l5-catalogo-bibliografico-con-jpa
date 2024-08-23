package dariocecchinato.Dao;

import dariocecchinato.entities.Utente;
import dariocecchinato.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class UtenteDao {

    private final EntityManager em;

    public UtenteDao(EntityManager em) {
        this.em = em;
    }

    //*************************************  Metodo SAVE  ****************************************

    public void save(Utente utente) {
        //1.
        EntityTransaction transaction = em.getTransaction();
        //2.
        transaction.begin();
        //3.
        em.persist(utente);
        //4.
        transaction.commit();

        System.out.println("L' utente " + utente.getNome() + " è stato salvato correttamente");
    }

    //*************************************  Metodo FINDBYISBN  ****************************************

    public Utente getById(String idUtente) {
        Utente found = em.find(Utente.class, idUtente);
        if (found == null) {
            throw new NotFoundException(idUtente);
        } else {
            return found;
        }
    }

    //*************************************  Metodo FINDBYISBN  & DELETE****************************************

    public void delete(String idUtente) {
        Utente found = this.getById(idUtente);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("La Utente " + found.getNome() + " è stata rimossa");

    }

    public List<Utente> findAll() {
        TypedQuery<Utente> query = em.createQuery("SELECT p FROM Utente p", Utente.class);
        return query.getResultList();
    }
}
