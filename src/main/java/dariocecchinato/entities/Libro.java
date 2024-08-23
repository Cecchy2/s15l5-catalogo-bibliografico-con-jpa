package dariocecchinato.entities;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Libro extends Publication {
    private String autore;
    private Genere genere;

    //***********************************  Costruttori  ****************************************************


    public Libro(String codiceISBN, String titolo, LocalDate annoPubblicazione, int numeroPagine, List<Prestito> prestiti, String autore, Genere genere) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine, prestiti);
        this.autore = autore;
        this.genere = genere;
    }

    public Libro(String autore, Genere genere) {
        this.autore = autore;
        this.genere = genere;
    }

    public Libro() {
    }

    //***********************************  Getter & Setter  ****************************************************


    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    //*********************************** Metodo ToString   ****************************************************


    @Override
    public String toString() {
        return "Libro{" +
                "autore='" + autore + '\'' +
                ", genere=" + genere +
                '}';
    }
}
