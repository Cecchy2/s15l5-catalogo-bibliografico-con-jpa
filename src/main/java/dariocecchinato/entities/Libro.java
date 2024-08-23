package dariocecchinato.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Libro extends Publication {
    private String autore;
    @Enumerated(EnumType.STRING)
    private Genere genere;

    //***********************************  Costruttori  ****************************************************


    public Libro(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, String autore, Genere genere) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
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
        return "Libro: " +
                "Titolo= '" + getTitolo() + '\'' +
                " Autore= '" + autore + '\'' +
                ", genere= " + genere +
                ' ';
    }
}
