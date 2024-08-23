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

    public List<Publication> findAll() {
        TypedQuery<Publication> query = em.createQuery("SELECT p FROM Publication p", Publication.class);
        return query.getResultList();
    }
}
