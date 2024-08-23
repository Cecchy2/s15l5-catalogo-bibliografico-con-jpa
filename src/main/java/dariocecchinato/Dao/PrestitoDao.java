package dariocecchinato.Dao;

import dariocecchinato.entities.Prestito;
import dariocecchinato.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class PrestitoDao {

    private final EntityManager em;

    public PrestitoDao(EntityManager em) {
        this.em = em;
    }

    //*************************************  Metodo SAVE  ****************************************
    public void save(Prestito prestito) {
        //1.
        EntityTransaction transaction = em.getTransaction();
        //2.
        transaction.begin();
        //3.
        em.persist(prestito);
        //4.
        transaction.commit();

        System.out.println("Il prestito di " + prestito.getElementoPrestato() + " è stato salvato correttamente");
    }

    //*************************************  Metodo FINDBYISBN  ****************************************
    public Prestito getById(String idPrestito) {
        Prestito found = em.find(Prestito.class, idPrestito);
        if (found == null) {
            throw new NotFoundException(idPrestito);
        } else {
            return found;
        }
    }

    //*************************************  Metodo FINDBYISBN  & DELETE****************************************
    public void delete(String idPrestito) {
        Prestito found = this.getById(idPrestito);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("Il Prestito di " + found.getElementoPrestato() + " è stato rimosso");

    }

    //*********************************** Metodo FIND PUBLICATIONS ATTUALMENTE IN PRESTITO PER TESSERA ****************************************
    public List<Prestito> findPublicationPrestateperTessera(int numeroTessera) {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.utente.numeroTessera = :numeroTessera AND  p.dataRestituzioneEffettiva IS NULL", Prestito.class
        );
        query.setParameter("numeroTessera", numeroTessera);
        return query.getResultList();
    }

    //*********************************** Metodo FIND PRESTITI SCADUTI NON RESTITUITI ****************************************
    public List<Prestito> findPrestitiScadutiNonRestituiti() {
        TypedQuery<Prestito> query = em.createQuery(
                "SELECT p FROM Prestito p WHERE p.dataRestituzionePrevista < :today AND p.dataRestituzioneEffettiva IS NULL",
                Prestito.class);
        query.setParameter("today", LocalDate.now());
        return query.getResultList();
    }

}
