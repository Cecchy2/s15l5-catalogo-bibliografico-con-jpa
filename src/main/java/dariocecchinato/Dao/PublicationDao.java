package dariocecchinato.Dao;

import dariocecchinato.entities.Publication;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PublicationDao {
    private final EntityManager em;

    public PublicationDao(EntityManager em) {
        this.em = em;
    }

    //*************************************  Metodo FindByAll  ****************************************
    public List<Publication> findAll() {
        TypedQuery<Publication> query = em.createQuery("SELECT p FROM Publication p", Publication.class);
        return query.getResultList();
    }

    //*************************************  Metodo FindByYear  ****************************************
    public List<Publication> findByYear(int annoPubblicazione) {
        TypedQuery<Publication> query = em.createQuery(
                "SELECT p FROM Publication p WHERE p.annoPubblicazione = :annoPubblicazione",
                Publication.class);
        query.setParameter("annoPubblicazione", annoPubblicazione);
        return query.getResultList();
    }

    //*************************************  Metodo FindByYear  ****************************************
    public List<Publication> findByAuthor(String autore) {
        TypedQuery<Publication> query = em.createQuery(
                "SELECT p FROM Publication p WHERE p.autore = :autore",
                Publication.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    //***********************************  FIND BY TITOLO O PARTE DI ESSO ****************************************
    public List<Publication> findByTitolo(String titolo) {
        TypedQuery<Publication> query = em.createQuery(
                "Select p FROM Publication p WHERE p.titolo LIKE : titolo", Publication.class);
        query.setParameter("titolo", "%" + titolo + "%");
        return query.getResultList();
    }
}
